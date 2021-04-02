package org.springmg.common.file; /**
 * @InterFaceName file.FileAnalysis
 * @Description 文件解析工具
 * @Author dev@tyuan.design
 * @Date 2020/7/23 12:37
 */

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface FileAnalysis {

    /**
     * 解析文件
     *
     * @param is
     * @return list的index作为顺序 , Map key 字段名称，value 为值
     */
    List<Map<String, Object>> analysis(InputStream is);
}
