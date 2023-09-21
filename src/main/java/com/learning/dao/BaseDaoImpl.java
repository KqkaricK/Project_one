package com.learning.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public abstract class BaseDaoImpl implements InitializingBean {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        logger.info("Setting dataSource");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void afterPropertiesSet() throws Exception {
        if (jdbcTemplate == null)
            throw new IllegalStateException("jdbcTemplate must not be null");
    }

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
