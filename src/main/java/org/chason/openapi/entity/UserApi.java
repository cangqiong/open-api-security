package org.chason.openapi.entity;

import javax.persistence.*;

@Table(name = "user_api")
public class UserApi {
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * uiid 用户唯一标识
     */
    private String uiid;

    /**
     * API路径
     */
    @Column(name = "api_path")
    private String apiPath;

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
     * 获取uiid 用户唯一标识
     *
     * @return uiid - uiid 用户唯一标识
     */
    public String getUiid() {
        return uiid;
    }

    /**
     * 设置uiid 用户唯一标识
     *
     * @param uiid uiid 用户唯一标识
     */
    public void setUiid(String uiid) {
        this.uiid = uiid;
    }

    /**
     * 获取API路径
     *
     * @return api_path - API路径
     */
    public String getApiPath() {
        return apiPath;
    }

    /**
     * 设置API路径
     *
     * @param apiPath API路径
     */
    public void setApiPath(String apiPath) {
        this.apiPath = apiPath;
    }
}