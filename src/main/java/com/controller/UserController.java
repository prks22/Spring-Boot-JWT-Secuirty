package com.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.dto.UserDto;
import com.common.exception.UserException;
import com.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
  @Inject
  private UserService userService;

  @GetMapping("/user")
  public List<UserDto> getUsers() throws UserException {
    // logger.info("inside user controller.");
    return userService.getUsers();
  }
}
