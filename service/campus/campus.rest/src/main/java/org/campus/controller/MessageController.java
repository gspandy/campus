package org.campus.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;

@RestController
@RequestMapping("/msg")
@Api(value = "MessageController", description = "消息相关操作")
public class MessageController {

}
