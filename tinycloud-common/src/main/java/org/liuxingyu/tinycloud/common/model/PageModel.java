package org.liuxingyu.tinycloud.common.model;


import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 分页模型类，分页查询返回结果时需要用此类包裹，如PageModel<OrgAuditQueryVo>
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-03-07 15:47:38
 */
public class PageModel<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 总记录数
    private Integer totalCount;

    // 总页数
    private Integer totalPage;

    // 结果集
    private List<T> records;

    // 当前页码
    private Integer pageNo;

    // 当前页大小
    private Integer pageSize;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount.intValue();
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage.intValue();
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

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

    public PageModel() {
    }

    public PageModel(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public PageModel(Integer pageNo, Integer pageSize, List<T> records, Integer totalCount) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.records = records;
        this.totalCount = totalCount;
        // 自动计算出总页数
        if (totalCount != 0) {
            this.totalPage = (totalCount - 1) / pageSize + 1;
        } else {
            this.totalPage = 0;
        }
    }

    public PageModel(Integer pageNo, Integer pageSize, List<T> records, Integer totalCount, Integer totalPage) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.records = records;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }

    public static <T> PageModel<T> build(Integer page, Integer size, List<T> list, Integer totalCount, Integer totalPage) {
        return new PageModel<>(page, size, list, totalCount, totalPage);
    }
}
