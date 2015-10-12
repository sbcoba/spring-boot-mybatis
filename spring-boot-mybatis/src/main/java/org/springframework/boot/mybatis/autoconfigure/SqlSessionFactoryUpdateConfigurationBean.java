package org.springframework.boot.mybatis.autoconfigure;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * SqlSessionFactory's configuration update
 * @author sbcoba
 */
public class SqlSessionFactoryUpdateConfigurationBean extends SqlSessionFactoryBean {
    private static final Log LOGGER = LogFactory.getLog(SqlSessionFactoryUpdateConfigurationBean.class);

    private Properties configurationProperties;

    @Override
    public void setConfigurationProperties(Properties configurationProperties) {
        this.configurationProperties = configurationProperties;
    }

    @Override
    public SqlSessionFactory getObject() throws Exception {
        SqlSessionFactory sqlSessionFactory = super.getObject();
        updateConfiguration(sqlSessionFactory.getConfiguration());
        return sqlSessionFactory;
    }

    private void updateConfiguration(Configuration configuration) {
        if (configurationProperties == null) {
            return;
        }

        BeanWrapper bw = new BeanWrapperImpl(configuration);
        Set<Map.Entry<Object, Object>> entries = configurationProperties.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            String propertyName = entry.getKey().toString();
            Object propertyValue = entry.getValue();
            try {
                bw.setPropertyValue(propertyName, propertyValue);
            } catch (Exception e) {
                LOGGER.warn(propertyValue + " values are not set in " + propertyName + " property!" +
                        " (" + e.getMessage() + ")");
            }
        }
    }
}
