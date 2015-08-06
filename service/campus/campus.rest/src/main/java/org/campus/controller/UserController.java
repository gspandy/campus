package org.campus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(value = "UserController", description = "用户信息相关操作")
public class UserController {

}
