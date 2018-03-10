package org.chason.openapi.entity;

import javax.persistence.*;

@Table(name = "api_blank_list")
public class ApiBlankList {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * API路径
     */
    @Column(name = "API")
    private String api;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取API路径
     *
     * @return API - API路径
     */
    public String getApi() {
        return api;
    }

    /**
     * 设置API路径
     *
     * @param api API路径
     */
    public void setApi(String api) {
        this.api = api;
    }
}