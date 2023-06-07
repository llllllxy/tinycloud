package org.tinycloud.bean.vo;

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
    private Long id;

    @ApiModelProperty("用户编码")
    private String userId;

    @ApiModelProperty("用户账号")
    private String username;

    @ApiModelProperty("用户昵称（中文姓名）")
    private String nickname;

    @ApiModelProperty("所属组织编码")
    private String organId;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("状态（0--正常 1--冻结）")
    private String status;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("创建时间")
    private Date createdAt;

    @ApiModelProperty("更新时间")
    private Date updatedAt;
}
