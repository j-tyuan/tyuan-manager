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
package org.tyuan.common.file; /**
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
