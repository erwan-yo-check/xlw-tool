package org.xlw.common.enums;

/**
 * Description: check_yo
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/1 15:48
 */
public enum ResultsCodeEnum {

    SUCCESS(200, "成功"),

    FAIL(-1, "失败"),

    ERROR(500, "服务器异常"),

    UNAUTHORIZED(401, "未认证（签名错误）"),

    FORBIDDEN(403, "禁止访问"),

    NOT_FOUND(404, "接口不存在"),

    AUTH_ERROR(-10000, "鉴权登陆失败，请重新登录！");

    public int code;
    public String message;

    ResultsCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}