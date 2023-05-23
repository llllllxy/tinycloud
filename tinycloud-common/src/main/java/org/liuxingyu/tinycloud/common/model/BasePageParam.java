package org.liuxingyu.tinycloud.common.model;

import java.io.Serializable;


/**
 * <p>
 * 分页基类，分页查询时,dto可继承此类
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public class BasePageParam implements Serializable {
    private static final long serialVersionUID = -1L;

    /**
     * 第几页
     */
    private Integer pageNo = 1;

    /**
     * 每页多少行
     */
    private Integer pageSize = 10;

    /**
     * 根据XXX ordey by
     */
    private String sort;

    /**
     * 降序还是升序，asc or desc
     */
    private String order;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
