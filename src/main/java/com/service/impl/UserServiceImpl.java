package com.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.common.dto.UserDto;
import com.common.exception.UserException;
import com.manager.UserManager;
import com.service.UserService;

@Component
public class UserServiceImpl implements UserService {
  @Inject
  UserManager UserManager;

  @Override
  public List<UserDto> getUsers() throws UserException {
    return UserManager.getUsers();
  }
}
