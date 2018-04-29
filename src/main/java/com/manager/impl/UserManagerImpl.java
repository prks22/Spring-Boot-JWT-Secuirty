package com.manager.impl;

import static com.enums.ErrorCode.USER_NOTFOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.common.dto.UserDto;
import com.common.exception.UserException;
import com.dao.UserDao;
import com.manager.UserManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Transactional
public class UserManagerImpl implements UserManager {
  @Autowired
  UserDao userDao;

  @Override
  public List<UserDto> getUsers() throws UserException {
   
    final List<Map<String, Object>> datas = userDao.getUsers();
    final List<UserDto> users = new ArrayList<>();
    for (final Map<String, Object> map : datas) {
      final UserDto userDto = new UserDto();
      userDto.setName("xt");
      userDto.setUserName("admin");
      users.add(userDto);
    }

    if (users.isEmpty()) {
    //  log.error("Exception occured during getting user details");
      throw new UserException(USER_NOTFOUND.getErrorMessage(), USER_NOTFOUND.getErrorCode());

    }
    return users;
  }
}
