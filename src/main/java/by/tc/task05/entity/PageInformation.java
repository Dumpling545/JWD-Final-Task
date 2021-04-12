package by.tc.task05.entity;

import java.io.Serializable;

public class PageInformation implements Serializable {
    private static final long serialVersionUID = -5821809017770995876L;
    private boolean pageInitialized = false;
    private int page;
    private boolean pageSizeInitialized = false;
    private int pageSize;

    public PageInformation() {
    }

    public PageInformation(int page, int pageSize) {
        pageInitialized = true;
        pageSizeInitialized = true;
        this.page = page;
        this.pageSize = pageSize;
    }

    public boolean isPageInitialized() {
        return pageInitialized;
    }

    public void setPageInitialized(boolean pageInitialized) {
        this.pageInitialized = pageInitialized;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        pageInitialized = true;
        this.page = page;
    }

    public boolean isPageSizeInitialized() {
        return pageSizeInitialized;
    }

    public void setPageSizeInitialized(boolean pageSizeInitialized) {
        this.pageSizeInitialized = pageSizeInitialized;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        pageSizeInitialized = true;
        this.pageSize = pageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PageInformation that = (PageInformation) o;

        if (pageInitialized != that.pageInitialized) return false;
        if (page != that.page) return false;
        if (pageSizeInitialized != that.pageSizeInitialized) return false;
        return pageSize == that.pageSize;
    }

    @Override
    public int hashCode() {
        int result = (pageInitialized ? 1 : 0);
        result = 31 * result + page;
        result = 31 * result + (pageSizeInitialized ? 1 : 0);
        result = 31 * result + pageSize;
        return result;
    }

    @Override
    public String toString() {
        return "PageInformation{" + "pageInitialized=" + pageInitialized +
                ", page=" + page + ", pageSizeInitialized=" +
                pageSizeInitialized + ", pageSize=" + pageSize + '}';
    }
}
