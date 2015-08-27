package org.springframework.boot.mybatis.autoconfigure;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

@EnableConfigurationProperties
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(prefix = "spring.mybatis", name = "enabled", havingValue = "true", matchIfMissing = true)
@Import(MyBatisMapperScannerRegistrar.class)
@Configuration
public class MyBatisAutoConfiguration {
	private static final Logger log = LoggerFactory.getLogger(MyBatisAutoConfiguration.class);
	
    @Autowired(required = false)
    private MyBatisConfigurer myBatisConfigurer;
    
    @Autowired
    private MyBatisProperties myBatisProperties;
    
	@Bean
	@ConditionalOnMissingBean
	public MyBatisProperties myBatisProperties() {
		return new MyBatisProperties();
	}
	
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        {
	        Resource configLocation = myBatisProperties.getConfigLocation();
	        try {
	        	configLocation.getInputStream();
	        } catch (FileNotFoundException e) {
	        	log.info(e.getMessage());
	        	configLocation = null;
	        }
	        if (configLocation != null) {
	        	factoryBean.setConfigLocation(configLocation);
	        }
        }
        {
        	Resource[] mapperLocations = myBatisProperties.getMapperLocations();
        	if (mapperLocations != null && mapperLocations.length > 0) {
        		factoryBean.setMapperLocations(mapperLocations);        
        	}
        }
        {
	        String typeAliasesPackage = myBatisProperties.getTypeAliasesPackage();
	        if (StringUtils.hasText(typeAliasesPackage)) {
	        	factoryBean.setTypeAliasesPackage(typeAliasesPackage);
	        }
        }
        factoryBean.setPlugins(getPlugins());
        factoryBean.setTypeHandlers(getTypeHandlers());
        
        factoryBean.setConfigurationProperties(myBatisProperties.getConfigurationProperties());
        return factoryBean;
    }

	private TypeHandler<?>[] getTypeHandlers() {
		if (myBatisConfigurer != null) {
			TypeHandler<?>[] typeHandlers = myBatisConfigurer.getTypeHandlers();
			if (typeHandlers != null) {
				return typeHandlers;
			}
		}
		TypeHandler<?>[] emptyTypeHandlers = new TypeHandler<?>[]{};
		return emptyTypeHandlers;
	}

	public Interceptor[] getPlugins() {
		if (myBatisConfigurer != null) {
			Interceptor[] interceptors = myBatisConfigurer.getPlugins();
			if (interceptors != null) {
				return interceptors;
			}
		}
		return new Interceptor[]{};
	}

	@ConditionalOnMissingBean
    @Bean(destroyMethod="clearCache")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    
}
