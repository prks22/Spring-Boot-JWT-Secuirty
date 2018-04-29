package com.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.common.config.BaseDao;
import com.common.exception.UserException;
import com.dao.UserDao;

/**
 * The Class UserDaoImpl.
 *
 * @author <a href="mailto:Prakash.Bisht@harman.com">Prakash Bisht</a>
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

  /*
   * (non-Javadoc)
   * 
   * @see com.harman.dao.UserDao#getUsers()
   */
  @Override
  public List<Map<String, Object>> getUsers() throws UserException {
    final String sql = "Select * from user";
    return getJdbcTemplate().queryForList(sql);

  }



}
