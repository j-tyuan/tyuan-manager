/**
 * Copyright (c) 2020-2038, Jiangguiqi 齐 (author@tyuan.design).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tyuan.service.manage.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * 模型工具类·
 *
 * @author tangyuanliang
 */
public final class ModelUtil {
    /**
     * 日志.
     */
    private static Logger LOGGER = LoggerFactory.getLogger(ModelUtil.class);

    // 默认除法精度
    private static final int DEF_DIV_SCALE = 10;

    /***
     * mA: 模型A分数， mB: 模型B分数 ，wA: 模型A系数， wB: 模型B系数 ，vA: 校正系数A ，vB: 校正系数B
     *
     * @return 模型最终分数
     */
    public static Double modelScore(double mA, double mB, double wA, double wB, double vA, double vB) {
        Double x1 = -div(Math.log(sub(div(1, mA), 1)), wA);
        Double x2 = -div(Math.log(sub(div(1, mB), 1)), wB);
        Double y = div(1, (add(1, Math.exp(-add(mul(vA, add(x1, x2)), vB)))));
        if (Double.isInfinite(y) || Double.isNaN(y)) {
            LOGGER.error("[modelScore]x1={}, x2={}, y={}.", x1, x2, y);
            return null;
        }
        return y;
    }

    /***
     * mA: 模型A分数， mB: 模型B分数 ，wA: 模型A系数， wB: 模型B系数
     *
     * @return 模型最终分数
     */
    public static Double modelScore(double mA, double mB, double wA, double wB) {
        Double x1 = -div(Math.log(sub(div(1, mA), 1)), wA);
        Double x2 = -div(Math.log(sub(div(1, mB), 1)), wB);
        Double y = div(1, (add(1, Math.exp(-add(x1, x2)))));
        if (Double.isInfinite(y) || Double.isNaN(y)) {
            LOGGER.error("[modelScore]x1={}, x2={}, y={}.", x1, x2, y);
            return null;
        }
        return y;
    }

    /***
     * 企业吊销分校正
     */
    public static Double revokeScore(Double score) {
        if (null == score) {
            return score;
        }
        Double x = Math.log(div(score, sub(1, score)));
        Double yHat = sub(mul(1.0449, x), 1.1575);
        Double newScore = div(1, (add(1, Math.exp(-yHat))));
        if (Double.isInfinite(newScore) || Double.isNaN(newScore)) {
            LOGGER.error("[revokeScore]score={}, x={}, yHat={}, y={}.", score, x, yHat, newScore);
            return null;
        }
        return newScore;
    }

    /**
     * 加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 计算方差
     */
    public static double variance(List<Double> valueList) {
        Double sum = 0.0;
        for (int i = 0; i < valueList.size(); i++) {
            sum = add(sum, valueList.get(i));
        }
        Double avg = div(sum, valueList.size());
        sum = 0.0;
        for (int i = 0; i < valueList.size(); i++) {
            Double pow2 = Math.pow(sub(valueList.get(i), avg), 2);
            sum = add(sum, pow2);
        }
        return div(sum, valueList.size());
    }

    /**
     * 计算斜率
     */
    public static double slope(List<Double> valueList) {
        Double sumx = 0.0;
        Double sumy = 0.0;
        Double sumxy = 0.0;
        Double sumXPow2 = 0.0;
        for (int i = 0; i < valueList.size(); i++) {
            Double xy = mul((i + 1), valueList.get(i));
            sumxy = add(sumxy, xy);
            sumx = add(sumx, (i + 1));
            sumy = add(sumy, valueList.get(i));
            sumXPow2 = add(sumXPow2, Math.pow((i + 1), 2));
        }
        Double kfz = sub(sumxy, div(mul(sumx, sumy), valueList.size()));
        Double kfm = sub(sumXPow2, div(Math.pow(sumx, 2), valueList.size()));
        return div(kfz, kfm);
    }

    /**
     * 除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, Integer scale) {
        int newScale = scale == null ? DEF_DIV_SCALE : scale.intValue();
        return div(v1, v2, newScale);
    }

    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    private static double div(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 信用分扩大至[181, 845]
     */
    public static Integer transform(double score) {
        double ai = 513.5614;
        double bi = 28.8539;
        double min_val = 0.00001;
        double max_val = 0.99999;
        Integer fico_min_val = 180;
        Integer fico_max_val = 845;
        if (score > max_val) {
            score = max_val;
        }
        if (score < min_val) {
            score = min_val;
        }
        Integer fico_score = (int) (ai - Math.round(bi * Math.log(score / (1 - score))));
        if (fico_score > fico_max_val) {
            fico_score = fico_max_val;
        }
        if (fico_score < fico_min_val) {
            fico_score = fico_min_val;
        }
        return fico_score;
    }

    /**
     * 乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static long mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(0, BigDecimal.ROUND_UP).longValue();
    }

    public static void main(String[] args) {
        //System.err.println(slope(Lists.newArrayList(0.0034525, 0.0038525, 0.0032925)));
        //System.out.println(mul("1130.0", "0.69"));
//        Double m58Score = 0.30330342D;
//        Double m59Score = 0.3981085D;
//
        Double m58 = 0.672D;
        Double m59 = 0.328D;
//
//        Double vA = 1.08559949D;
//        Double vB = 0.14798841D;
//        System.out.println(getFicoScore(modelScore(m58Score, m59Score, m58, m59, vA, vB)));
//        System.out.println(modelScore(m58Score, m59Score, m58, m59, vA, vB));
       // System.out.println(0.980000000D);
      //  System.out.println(formatDouble3(0.348D, 10));
        Double modelScore = 0.1885493316D;
        System.out.println(formatDouble3(modelScore,3));

        Double vA = 0.99960693D;
        Double vB = 0.99959806;
        System.out.println(modelScore(vA,vB,m58,m59));
//        Double modelScore1 =  0.3485832054D;
//        System.out.println(getFicoScore(modelScore1));
//
//        Double modelScore2 =  0.3485832055D;
//        System.out.println(getFicoScore(modelScore2));
//
//        Double modelScore3 =  0.34858320546134125D;
//        System.out.println(getFicoScore(modelScore3));

    }

    private static void test1() {
        Double x1 = -(Math.log(1 / 0.4410407 - 1) / 0.298); // -0.795098994898883
        Double x2 = -(Math.log(1 / 0.34476718 - 1) / 0.892); // -0.7198668993443492
        Double y = 1 / (1 + Math.exp(-(1.207225 * (x1 + x2) + 0.459759)));
        // Double y = 1 / (1 + Math.exp(-(x1 + x2)));
        System.err.println(x1);
        System.err.println(x2);
        System.err.println("--------- " + y);
        BigDecimal bg = new BigDecimal(y).setScale(9, RoundingMode.HALF_UP);
        System.err.println(bg.doubleValue());

        double Y_hat = 1.207225 * Math.log(y / (1 - y)) + 0.459759;
        System.err.println(Y_hat);
        System.err.println("score_new= " + (1 / (1 + Math.exp(-Y_hat)))); // 0.20275790624326032

        // 菜花=1/(1+EXP(-(LOG((m1/(1-m1)), EXP(1))/wA+LOG((m2/(1-m2)), EXP(1))/wB)))
    }

    public static String setScale(String v1, int newScale) {
        BigDecimal bg = new BigDecimal(v1);
        return bg.setScale(newScale, BigDecimal.ROUND_DOWN).toString();
    }


    public static int getRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * 分数矫正
     *
     * @param mA
     * @param mB
     * @param score
     * @return
     */
    public static Double adjustC2P1Score(Double mA, Double mB, Double score) {
        score = Math.log(score / (1 - score));
        Double y = mA * score + mB;
        return 1 / (1 + Math.exp(-y));
    }


    public static Double  formatDouble3(double d,int digits) {
        return new BigDecimal(d).setScale(digits,RoundingMode.HALF_UP).doubleValue();
    }

}
