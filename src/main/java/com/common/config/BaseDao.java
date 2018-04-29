package com.common.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDao {
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public void setDataSource(final DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

}
