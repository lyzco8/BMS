package com.lsf.entity;

/**
 * 分页辅助页
 * @author 刘愿
 * @date 2020/11/17 13:52
 * @see [相关类/方法]
 * @since V1.00
 */
public class Paginate {
    public static Integer GROUP_SIZE=5;
    private static final Integer MIN_PAGESIZE=3;
    private Integer pageNo;
    private Integer pageSize;
    private Integer pages;
    private Integer records;
    private Integer start;

    public Integer getStart() {
        return start;
    }

    public Integer getEnd() {
        return end;
    }

    private Integer end;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo<1){
            this.pageNo=1;
        }
        else {
            this.pageNo = pageNo;
        }
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize<MIN_PAGESIZE){
            this.pageSize=MIN_PAGESIZE;
        }
        else {
            this.pageSize = pageSize;
        }
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        if(records==0){
            throw new RuntimeException("查无数据");
        }
        this.records = records;
        this.pages=(records+this.pageSize-1)/this.pageSize;
        this.pageNo=this.pageNo>pages?pages:this.pageNo;
        this.start=(this.pageNo-1)/GROUP_SIZE*GROUP_SIZE+1;
        this.end=this.start+GROUP_SIZE-1;
        this.end=this.end>this.pages?this.pages:this.end;
    }

}
