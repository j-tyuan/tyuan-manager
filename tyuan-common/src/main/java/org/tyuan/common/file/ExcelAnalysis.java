/**
 * @ClassName file.ExcelAnalysis
 * @Description excel解析器
 * @Author dev@tyuan.design
 * @Date 2020/7/23 12:35
 */
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
package org.tyuan.common.file;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.OLE2NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelAnalysis {

    Logger logger = LoggerFactory.getLogger(ExcelAnalysis.class);

    Workbook workbook;

    private ExcelAnalysis() {
    }

    public ExcelAnalysis(InputStream is) throws IOException {

        try {

            workbook = new XSSFWorkbook(is);
        } catch (OLE2NotOfficeXmlFileException e) {

            workbook = new HSSFWorkbook(is);
        }
    }

    public List<String> getAllSheetNames() {

        Iterator<Sheet> iterator = workbook.iterator();
        List<String> sheetName = new ArrayList<>();

        while (iterator.hasNext()) {

            Sheet item = iterator.next();
            sheetName.add(item.getSheetName());
        }
        return sheetName;
    }

    /**
     * 获取第一个sheet
     *
     * @return
     */
    public List<Map<String, Object>> getFirst() {

        return analysis(workbook.getSheetAt(0));
    }

    /**
     * 根据名称获取sheet
     *
     * @param name
     * @return
     */
    public List<Map<String, Object>> get(String name) {

        return analysis(workbook.getSheet(name));
    }

    /**
     * 根据下表后去sheet
     *
     * @param i
     * @return
     */
    public List<Map<String, Object>> get(int i) {

        return analysis(workbook.getSheetAt(i));
    }

    private List<Map<String, Object>> analysis(Sheet sheet) {
        logger.info("【文件解析】 开始....");

        if (null == sheet) {

            return new ArrayList();
        }

        // 获取第一列，作为头部
        Row headRow = sheet.getRow(0);

        // 获取最后一行
        int lastRow = sheet.getLastRowNum();
        logger.info("【文件解析】 共有数据：{}", lastRow);

        int lastCellNum = headRow.getLastCellNum();
        String[] headStr = new String[lastCellNum];
        for (int i = 0; i < lastCellNum; i++) {
            headStr[i] = getCellToStr(headRow, i);
        }

        long nowTime = System.currentTimeMillis();
        logger.info("【文件解析】 解析中...");
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 1; i <= lastRow; i++) {

            Map<String, Object> map = new LinkedHashMap<>();

            Row row = sheet.getRow(i);
            for (int j = 0; j < lastCellNum; j++) {
                String val = getCellToStr(row, j);
                map.put(headStr[j], val);
            }
            list.add(map);
        }

        logger.info("【文件解析】 解析完成，共耗时：{} 毫秒", System.currentTimeMillis() - nowTime);
        return list;
    }


    /**
     * 获取Cell字符串类型的值
     *
     * @param row
     * @param cellNum 当前cell的位置
     * @return
     */
    private String getCellToStr(Row row,
                                Integer cellNum) {
        Cell cell = row.getCell(cellNum);
        if (null == cell) {
            return null;
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }
}
