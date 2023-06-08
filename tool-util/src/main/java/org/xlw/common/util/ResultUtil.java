package org.xlw.common.util;

import org.xlw.common.enums.ResultsCodeEnum;
import org.xlw.common.model.Result;

/**
 * Description: 返回工具
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/8 15:29
 */
public class ResultUtil {

    public static <T> Result<T> createSuccessResult(T data) {
        return Result.createResult(Boolean.TRUE, ResultsCodeEnum.SUCCESS.code, ResultsCodeEnum.SUCCESS.message, data);
    }

    public static <T> Result<T> createFailedResult(String msg) {
        return Result.createResult(Boolean.FALSE, ResultsCodeEnum.FAIL.code, msg, null);
    }
}
