package org.tyuan.service.application.service.security.auth.jwt.extractor;

import javax.servlet.http.HttpServletRequest;

/**
 * token提取接口
 *
 * @author guiqijiang
 */
public interface TokenExtractor {

    /**
     * 提取token
     *
     * @param request
     * @return
     */
    String extract(HttpServletRequest request);
}
