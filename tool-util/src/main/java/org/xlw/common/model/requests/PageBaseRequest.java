package org.xlw.common.model.requests;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 分页查询基础请求
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/20 14:13
 */
@Data
public class PageBaseRequest implements Serializable {

    private static final long serialVersionUID = 5459987233657926259L;
    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 一页最多有多少条数据
     */
    private Integer pageSize;
}
