package org.tinycloud.bean.param;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.tinycloud.common.model.BasePageParam;

@Getter
@Setter
public class UcUserPageQuery extends BasePageParam {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty("用户账号")
    @TableField("username")
    private String username;

    @ApiModelProperty("用户昵称（中文姓名）")
    @TableField("nickname")
    private String nickname;
}
