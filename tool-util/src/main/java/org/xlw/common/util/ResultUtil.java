package org.xlw.common.util;

import org.xlw.common.enums.ResultsCodeEnum;
import org.xlw.common.model.results.PageData;
import org.xlw.common.model.results.PageResult;
import org.xlw.common.model.results.Result;

import java.util.LinkedList;
import java.util.List;

/**
 * Description: 返回工具
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/8 15:29
 */
public class ResultUtil {

    /**
     * @desc 创建成功返回值
     *
     * @date 2023/6/20 15:16
     * @param data
     * @return org.xlw.common.model.results.Result<T>
     * @throws
     * @since
    */
    public static <T> Result<T> createSuccessResult(T data) {
        return Result.createResult(Boolean.TRUE, ResultsCodeEnum.SUCCESS.code, ResultsCodeEnum.SUCCESS.message, data);
    }

    /**
     * @desc 创建失败返回值
     *
     * @date 2023/6/20 15:16
     * @param msg
     * @return org.xlw.common.model.results.Result<T>
     * @throws
     * @since
    */
    public static <T> Result<T> createFailedResult(String msg) {
        return Result.createResult(Boolean.FALSE, ResultsCodeEnum.FAIL.code, msg, null);
    }


    /**
     * @desc 创建成功的分页返回结果
     *
     * @date 2023/6/20 15:21
     * @param total
     * @param currentPage
     * @param pageSize
     * @param data
     * @return org.xlw.common.model.results.PageResult<T>
     * @throws
     * @since
    */
    public static <T> PageResult<T> createSuccessPageResult(Integer total, Integer currentPage, Integer pageSize, List<T> data) {
        return PageResult.createPageResult(Boolean.TRUE, ResultsCodeEnum.SUCCESS.code, ResultsCodeEnum.SUCCESS.message,
                new PageData<>(total, currentPage, pageSize, data));
    }


    /**
     * @desc 创建失败的分页返回结果
     *
     * @date 2023/6/20 15:23
     * @param msg
     * @return org.xlw.common.model.results.PageResult<T>
     * @throws
     * @since
    */
    public static <T> PageResult<T> createFailedPageResult(String msg) {
        return PageResult.createPageResult(Boolean.FALSE, ResultsCodeEnum.FAIL.code, msg,
                new PageData<>(null, null, null, new LinkedList<>()));
    }
}
