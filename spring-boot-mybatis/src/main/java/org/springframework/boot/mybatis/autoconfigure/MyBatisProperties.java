package org.springframework.boot.mybatis.autoconfigure;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Properties;

import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@ConfigurationProperties("spring.mybatis")
public class MyBatisProperties {
	private String[] basePackage;
	private String sqlSessionFactoryRef;
	private String sqlSessionTemplateRef;
	private Class<? extends MapperFactoryBean<?>> factoryBean;
	private Class<? extends BeanNameGenerator> nameGenerator;
	private Class<? extends Object> markerInterface;
	private Class<? extends Annotation> annotationClass = Mapper.class;
	
	private Resource configLocation = new ClassPathResource("mybatis-configuration.xml");
	private String typeAliasesPackage;
	private Resource[] mapperLocations;
	
	private Properties configurationProperties = new Properties();

	public String[] getBasePackage() {
		return basePackage;
	}

	public void setBasePackage(String[] basePackage) {
		this.basePackage = basePackage;
	}

	public String getSqlSessionFactoryRef() {
		return sqlSessionFactoryRef;
	}

	public void setSqlSessionFactoryRef(String sqlSessionFactoryRef) {
		this.sqlSessionFactoryRef = sqlSessionFactoryRef;
	}

	public String getSqlSessionTemplateRef() {
		return sqlSessionTemplateRef;
	}

	public void setSqlSessionTemplateRef(String sqlSessionTemplateRef) {
		this.sqlSessionTemplateRef = sqlSessionTemplateRef;
	}

	public Class<? extends MapperFactoryBean<?>> getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(Class<? extends MapperFactoryBean<?>> factoryBean) {
		this.factoryBean = factoryBean;
	}

	public Class<? extends BeanNameGenerator> getNameGenerator() {
		return nameGenerator;
	}

	public void setNameGenerator(Class<? extends BeanNameGenerator> nameGenerator) {
		this.nameGenerator = nameGenerator;
	}

	public Class<? extends Object> getMarkerInterface() {
		return markerInterface;
	}

	public void setMarkerInterface(Class<? extends Object> markerInterface) {
		this.markerInterface = markerInterface;
	}

	public Class<? extends Annotation> getAnnotationClass() {
		return annotationClass;
	}

	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}
	
	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	public Resource getConfigLocation() {
		return this.configLocation;
	}	

	public String getTypeAliasesPackage() {
		return this.typeAliasesPackage;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}	
	
	public Resource[] getMapperLocations() {
		return mapperLocations;
	}
	
	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
	
	public Properties getConfigurationProperties() {
		return configurationProperties;
	}

	public void setConfigurationProperties(Properties configurationProperties) {
		this.configurationProperties = configurationProperties;
	}

	@Override
	public String toString() {
		return String.format(
				"MyBatisProperties [basePackage=%s, sqlSessionFactoryRef=%s, sqlSessionTemplateRef=%s, factoryBean=%s, nameGenerator=%s, markerInterface=%s, annotationClass=%s, configLocation=%s, typeAliasesPackage=%s, mapperLocations=%s, configurationProperties=%s]",
				Arrays.toString(basePackage), sqlSessionFactoryRef, sqlSessionTemplateRef, factoryBean, nameGenerator,
				markerInterface, annotationClass, configLocation, typeAliasesPackage, Arrays.toString(mapperLocations),
				configurationProperties);
	}
}
