package org.xlw.common.model.results;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description: 分页查询数据
 * Author: erwan_check
 * Email: 1076360205@qq.com
 * Date: 2023/6/20 14:13
 */
@Data
public class PageData<T> {

    /**
     * 数据总量
     */
    private Integer total;

    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 一页最多有多少条数据
     */
    private Integer pageSize;

    /**
     * 查询数据
     */
    private List<T> list;

    /**
     * @desc 内容是否为空
     *
     * @date 2023/6/20 15:14
     * @param
     * @return java.lang.Boolean
     * @throws
     * @since
    */
    public Boolean isEmpty() {
        if (total == 0 || CollectionUtils.isEmpty(list)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public PageData(Integer total, Integer currentPage, Integer pageSize, List<T> list) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
    }
}
