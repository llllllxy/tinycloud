package org.tinycloud.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 系统用户信息表
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-05-17 11:49:24
 */
@TableName("t_uc_user")
@ApiModel(value = "UcUser对象", description = "系统用户信息表")
public class UcUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键，内码")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户编码")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("用户账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("用户密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("用户昵称（中文姓名）")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("所属组织编码")
    @TableField("organ_id")
    private String organId;

    @ApiModelProperty("手机号")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("状态（0--正常 1--冻结）")
    @TableField("status")
    private String status;

    @ApiModelProperty("用户头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("删除标志（0--未删除1--已删除）")
    @TableField("del_flag")
    private Integer delFlag;

    @ApiModelProperty("创建时间")
    @TableField(value = "created_at")
    private Date createdAt;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_at")
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UcUser{" +
                "id = " + id +
                ", userId = " + userId +
                ", username = " + username +
                ", password = " + password +
                ", nickname = " + nickname +
                ", organId = " + organId +
                ", phone = " + phone +
                ", email = " + email +
                ", status = " + status +
                ", avatar = " + avatar +
                ", delFlag = " + delFlag +
                "}";
    }
}
