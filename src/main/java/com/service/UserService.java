package com.service;

import java.util.List;

import com.common.dto.UserDto;
import com.common.exception.UserException;

public interface UserService {
  public List<UserDto> getUsers() throws UserException;
}
