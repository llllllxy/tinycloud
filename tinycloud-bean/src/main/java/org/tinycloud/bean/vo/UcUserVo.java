package org.tinycloud.bean.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "UcUser对象", description = "系统用户信息表")
public class UcUserVo implements Serializable {
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

    @ApiModelProperty("用户昵称（中文姓名）")
    @TableField("nickname")
    private String nickname;

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

    @ApiModelProperty("创建时间")
    @TableField(value = "created_at")
    private Date createdAt;

    @ApiModelProperty("更新时间")
    @TableField(value = "updated_at")
    private Date updatedAt;

}
