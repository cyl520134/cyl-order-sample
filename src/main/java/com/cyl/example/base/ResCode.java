package com.cyl.example.base;

/**
 * @author changYL01
 * @date 2023/6/14 11:44
 * @description
 */
public enum ResCode {
    //公共编码
    /**
     * 1xx
     */
    DEBUG_MODE("100101", "调试模式"),
    UNKNOWN_ERROR("9999","未知异常"),

    /**
     * 2xx
     */
    SUCCESS("200", "成功"),
    /**
     * 3xx
     */
    ORDER_PRICE_IS_ERROR("3001", "请输入正确的订单金额"),
    PACK_ORDER_NOT_EXIST("3002", "订单不存在");



    private String code;
    private String msg;

    ResCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsg(Integer code) {
        for (ResCode c : ResCode.values()) {
            if (c.getCode().equals(code)) {
                return c.getMsg();
            }
        }
        return null;
    }
}
