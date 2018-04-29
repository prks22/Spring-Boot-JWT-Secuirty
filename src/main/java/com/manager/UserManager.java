package com.manager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.common.dto.UserDto;
import com.common.exception.UserException;

@Component
public interface UserManager {

  public List<UserDto> getUsers() throws UserException;


}
