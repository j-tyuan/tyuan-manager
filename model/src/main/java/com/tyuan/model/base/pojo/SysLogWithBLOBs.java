package com.tyuan.model.base.pojo;

public class SysLogWithBLOBs extends SysLog {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.params
     *
     * @mbg.generated
     */
    private String params;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_log.exception
     *
     * @mbg.generated
     */
    private String exception;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.params
     *
     * @return the value of sys_log.params
     *
     * @mbg.generated
     */
    public String getParams() {
        return params;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.params
     *
     * @param params the value for sys_log.params
     *
     * @mbg.generated
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_log.exception
     *
     * @return the value of sys_log.exception
     *
     * @mbg.generated
     */
    public String getException() {
        return exception;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_log.exception
     *
     * @param exception the value for sys_log.exception
     *
     * @mbg.generated
     */
    public void setException(String exception) {
        this.exception = exception == null ? null : exception.trim();
    }
}