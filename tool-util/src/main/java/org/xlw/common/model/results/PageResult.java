package org.xlw.common.model.results;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: check_yo
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/20 15:04
 */
@Data
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 7401330868267655808L;

    /**
     * 是否成功
     */
    public Boolean success;

    /**
     * 返回code
     */
    public Integer code;

    /**
     * 报错msg
     */
    private String msg;

    /**
     * 内容
     */
    private PageData<T> data;

    /**
     * @desc 是否成功返回
     *
     * @date 2023/6/20 15:11
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

    /**
     * @desc data是否为空
     *
     * @date 2023/6/20 15:15
     * @param
     * @return java.lang.Boolean
     * @throws
     * @since
    */
    public Boolean isDataEmpty() {
        if (!isSuccess()) {
            return Boolean.TRUE;
        }
        if (data == null || data.isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private PageResult(Boolean success, Integer code, String msg, PageData<T> data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * @desc 构建函数
     *
     * @date 2023/6/20 15:11
     * @param success
     * @param code
     * @param msg
     * @param data
     * @return org.xlw.common.model.results.PageResult<T>
     * @throws
     * @since
    */
    public static <T> PageResult<T> createPageResult(Boolean success, Integer code, String msg, PageData<T> data) {
        return new PageResult<>(success, code, msg, data);
    }
}
