package com.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.security.demo.common.aspect.annotation.AutoLog;
import com.security.demo.common.exception.UserNotExistException;
import com.security.demo.vo.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "用户RESTful API")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    @JsonView({User.UserSimpleView.class})
    @ApiOperation(value = "根据用户id获取用户", notes = "id参数必须有值")
    User getUser(@ApiParam(required = true, name = "id", value = "用户id") @PathVariable String id) {
        User user = new User();
        user.setId("1");
        user.setAge(20);
        user.setBirthday(new Date());
        user.setPassword("12345");
        log.info("{}", user);
        return user;
    }
    @AutoLog
    @GetMapping("/all")
    @JsonView({User.UserDetailView.class})
    @ApiOperation(value = "获取所有用户", notes = "")
    List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("1", "黎宇", "123", new Date(), 23));
        userList.add(new User("2", "张三", "123", new Date(), 20));
        return userList;
    }

    @PostMapping("")
    @ApiOperation(value = "新增用户", notes = "")
    User createUser(
            @Valid
            @ApiParam(required = true, name = "user", value = "用户")
            @RequestBody User user) {
        log.info("new user:{}", user.toString());
        return user;
    }

    @PutMapping()
    @ApiOperation(value = "修改用户", notes = "")
    User updateUser(@ApiParam(required = true, name = "user", value = "用户") @RequestBody User user) {
        throw new UserNotExistException(user.getId());
//        log.info("new user:{}", user.toString());
//        return user;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除用户", notes = "")
    String deleteUser(@ApiParam(required = true, name = "id", value = "用户id") @PathVariable String id) {
        return "删除成功";
    }

}
