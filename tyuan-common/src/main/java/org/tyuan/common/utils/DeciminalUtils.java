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
package org.tyuan.common.utils;

import java.math.BigDecimal;

public class DeciminalUtils {

    /**
     * 項目中價格的單位，在這裏統一設置
     * 根據保留小數點的精確度 1角 2分 3厘，來規定價格的單位
     * 0位為元
     * 1位為角
     * 2位為分
     * .....
     * ....
     *
     */
    public static final int PRICE_ACCURACY = 3;

    //價格的單位
    public static final int PRICE_UNIT;

    static {
        int priceUnit = new Double(Math.pow(10, PRICE_ACCURACY)).intValue();
        if (priceUnit == 0) {
            PRICE_UNIT = 1;
        } else {
            PRICE_UNIT = priceUnit;
        }
    }


    /**
     * 四舍五入方式保留scal位小数
     *
     * @param d
     * @param scal
     * @return
     */
    public static Double scale(Double d, int scal) {
        if (d == null) {
            return null;
        }
        BigDecimal decil = new BigDecimal(d);
        return decil.setScale(scal, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static void main(String[] args) {

    }

}
