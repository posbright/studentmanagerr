package com.ujiuye.util;

import java.util.List;

/**
 * 分页工具类会封装
 * @param <T>
 */
public class PageTools<T> {
    // 每页显示多少条数据
    private Integer initSize = 5;
    // 当前是第几页,默认是第一页
    private Integer currentPage = 1;
    // 需要分页的数据总共有多少条
    private Integer countNum;
    // 总共有多少页
    private Integer countPage;
    // 上一页
    private Integer prePage;
    // 下一页
    private Integer nextPage;
    // 页面包含的数据
    private List<T> pageList ;

    public PageTools(Integer initSize, Integer currentPage, Integer countNum) {
        this.initSize = initSize;
        this.currentPage = currentPage;
        this.countNum = countNum;
        // 总页码
        int num = this.countNum / this.initSize;
        this.countPage = this.countNum % this.initSize == 0 ? num : num + 1;
        // 前页面: 如果当前页小于等于1 ,则前一页就是1 ,否则 当前页减1
        this.prePage = this.currentPage <= 1 ? 1 : this.currentPage - 1;
        // 后一页: 如果当前页 大于等于总页面,则为总页码, 否则当前页 +1
        this.nextPage = this.currentPage >= this.countPage ? this.countPage : this.currentPage + 1;
    }

    public PageTools(Integer currentPage, Integer countNum, List<T> pageList) {
        this.currentPage = currentPage;
        this.countNum = countNum;
        this.pageList = pageList;
        // 总页码
        int num = this.countNum / this.initSize;
        this.countPage = this.countNum % this.initSize == 0 ? num : num + 1;
        // 前页面: 如果当前页小于等于1 ,则前一页就是1 ,否则 当前页减1
        this.prePage = this.currentPage <= 1 ? 1 : this.currentPage - 1;
        // 后一页: 如果当前页 大于等于总页面,则为总页码, 否则当前页 +1
        this.nextPage = this.currentPage >= this.countPage ? this.countPage : this.currentPage + 1;
    }

    public PageTools(Integer initSize, Integer currentPage, Integer countNum, List<T> pageList) {
        this.initSize = initSize;
        this.currentPage = currentPage;
        this.countNum = countNum;
        this.pageList = pageList;
        // 总页码
        int num = this.countNum / this.initSize;
        this.countPage = this.countNum % this.initSize == 0 ? num : num + 1;
        // 前页面: 如果当前页小于等于1 ,则前一页就是1 ,否则 当前页减1
        this.prePage = this.currentPage <= 1 ? 1 : this.currentPage - 1;
        // 后一页: 如果当前页 大于等于总页面,则为总页码, 否则当前页 +1
        this.nextPage = this.currentPage >= this.countPage ? this.countPage : this.currentPage + 1;
    }



    public Integer getInitSize() {
        return initSize;
    }

    public void setInitSize(Integer initSize) {
        this.initSize = initSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getCountPage() {
        return countPage;
    }

    public void setCountPage(Integer countPage) {
        this.countPage = countPage;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public List<T> getPageList() {
        return pageList;
    }

    public void setPageList(List<T> pageList) {
        this.pageList = pageList;
    }
}
