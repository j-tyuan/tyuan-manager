package org.tyuan.common.enums;

public enum LogType {

        ADD(1, "添加"),
        DEL(2, "删除"),
        EDIT(3, "修改"),
        SELECT(4, "查询");

        private int type;
        private String name;

        LogType(int type, String name) {
            this.type = type;
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }
