package com.security.demoapp.vo;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@ApiModel(description= "用户数据")
@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 用户简单视图
     */
    public interface UserSimpleView{}

    /**
     * 用户详情视图
     */
    public interface UserDetailView extends UserSimpleView{}

    @ApiModelProperty(value = "用户id")
    @JsonView(UserSimpleView.class)
    private String id;
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    @JsonView(UserSimpleView.class)
    private String username;
    @ApiModelProperty(value = "密码")
    @JsonView(UserDetailView.class)
    private String password;
    @ApiModelProperty(value = "出生日期")
    @JsonView(UserSimpleView.class)
    private Date birthday;
    @ApiModelProperty(value = "年龄")
    @JsonView(UserSimpleView.class)
    private Integer age;


}
