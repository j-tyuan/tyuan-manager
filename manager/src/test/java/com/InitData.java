package com;

import com.tyuan.manager.Application;
import com.tyuan.common.file.ExcelAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static org.apache.ibatis.jdbc.SqlBuilder.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class InitData {

    @Autowired
    DataSource dataSource;

    @Test
    public void init() throws IOException, SQLException {

        FileInputStream fileInputStream = new FileInputStream(new File("/Users/guiqijiang/Documents/data/code/cn-tyuan/manage-quick-start/db/sys/data.xlsx"));
        ExcelAnalysis excelAnalysis = new ExcelAnalysis(fileInputStream);
        List<String> sheetNames = excelAnalysis.getAllSheetNames();
        for (String names : sheetNames) {
            List<Map<String, Object>> maps = excelAnalysis.get(names);
            insertTable(names, maps);
        }

    }

    private void insertTable(String name, List<Map<String, Object>> maps) throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(true);

        Statement statement = connection.createStatement();
        statement.execute("truncate " + name);

        for (Map<String, Object> map : maps) {
            String sql = getInsertSql(name, map);

            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println(sql +" ----- ok");
        }
    }

    /**
     * 获取添加语句
     *
     * @param name
     * @param maps
     * @return
     */
    private String getInsertSql(String name, Map<String, Object> maps) {

        BEGIN();

        INSERT_INTO(name);

        for (Map.Entry<String, Object> e : maps.entrySet()) {

            Object val = e.getValue();
            if (null == val) {

                VALUES(e.getKey(), "null");
            } else {

                VALUES(e.getKey(), "'" + val + "'");
            }
        }

        return SQL();
    }


}
