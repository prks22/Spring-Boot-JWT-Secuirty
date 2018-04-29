package com.dao;

import java.util.List;
import java.util.Map;

import com.common.exception.UserException;

public interface UserDao {
  public List<Map<String, Object>> getUsers() throws UserException;
}
