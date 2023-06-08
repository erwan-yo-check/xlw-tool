package org.xlw.common.model;

import lombok.Data;
import org.xlw.common.enums.ResultsCodeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 封装结果类
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/1 15:47
 */
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public Boolean success;

    public int code;

    private String msg;

    private T data;

    private final Map<String, Object> mapData = new HashMap<String, Object>();

    public void Result() {
        this.success = Boolean.TRUE;
        this.code = ResultsCodeEnum.SUCCESS.code;
        this.msg = ResultsCodeEnum.SUCCESS.message;
    }

    private Result(Boolean success, int code, String message, T data) {
        this.success = success;
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public static <T> Result<T> createResult(Boolean success, int code, String message, T data) {
        return new Result<T>(success, code, message, data);
    }

    public static <T> Result<T> createSuccessResult(T data) {
        return createResult(Boolean.TRUE, ResultsCodeEnum.SUCCESS.code, ResultsCodeEnum.SUCCESS.message, data);
    }

    public static <T> Result<T> createFailedResult(String msg) {
        return createResult(Boolean.FALSE, ResultsCodeEnum.FAIL.code, msg, null);
    }

    public Result<T> setCode(ResultsCodeEnum resultsCode) {
        this.code = resultsCode.code;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Result<T> putDataValue(String key, Object value) {
        mapData.put(key, value);
        this.data = (T) mapData;
        return this;
    }

    /**
     * @desc 使用成功
     *
     * @date 2023/6/8 14:52
     * @param
     * @return java.lang.Boolean
     * @throws
     * @since
    */
    public Boolean isSuccess() {
        if (Boolean.TRUE.equals(success)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public String toString() {
        return "{" +
                "success=" + success +
                ", code=" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
