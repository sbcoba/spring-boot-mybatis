package org.springframework.boot.mybatis.autoconfigure;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.mapper.ClassPathMapperScanner;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;

@ConditionalOnMissingBean
@Configuration
public class MyBatisMapperScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware, BeanFactoryAware{
	private static final Logger log = LoggerFactory.getLogger(MyBatisMapperScannerRegistrar.class);
	
	private ResourceLoader resourceLoader;
	
	private Environment environment;

	private BeanFactory beanFactory;
	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}		
	 
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
	    ClassPathMapperScanner scanner = new ClassPathMapperScanner(registry);
	    if (resourceLoader != null) {
	      scanner.setResourceLoader(resourceLoader);
	    }
	    MyBatisProperties myBatisProperties = getMyBatisProperties();
	    log.debug("myBatisProperties : {}", myBatisProperties);
	    
	    Class<? extends Annotation> annotationClass = myBatisProperties.getAnnotationClass();
	    if (annotationClass != null && !Annotation.class.equals(annotationClass)) {
	      scanner.setAnnotationClass(annotationClass);
	    }

	    Class<?> markerInterface = myBatisProperties.getMarkerInterface();
	    if (markerInterface != null && !Class.class.equals(markerInterface)) {
	      scanner.setMarkerInterface(markerInterface);
	    }

	    Class<? extends BeanNameGenerator> generatorClass = myBatisProperties.getNameGenerator();
	    if (generatorClass != null && !BeanNameGenerator.class.equals(generatorClass)) {
	      scanner.setBeanNameGenerator(BeanUtils.instantiateClass(generatorClass));
	    }

	    Class<? extends MapperFactoryBean<?>> mapperFactoryBeanClass = myBatisProperties.getFactoryBean();
	    if (mapperFactoryBeanClass != null && !MapperFactoryBean.class.equals(mapperFactoryBeanClass)) {
	      scanner.setMapperFactoryBean(BeanUtils.instantiateClass(mapperFactoryBeanClass));
	    }

	    scanner.setSqlSessionTemplateBeanName(myBatisProperties.getSqlSessionTemplateRef());
	    scanner.setSqlSessionFactoryBeanName(myBatisProperties.getSqlSessionFactoryRef());

	    List<String> basePackages = new ArrayList<String>();
		String[] propertiesBasePackages = myBatisProperties.getBasePackage();
		if (propertiesBasePackages != null && propertiesBasePackages.length > 0) {
			for (String basePackage : propertiesBasePackages) {
				if (StringUtils.hasText(basePackage)) {
					basePackages.addAll(StringUtils.commaDelimitedListToSet(basePackage));
				}
			}
		} else {
			basePackages.addAll(AutoConfigurationPackages.get(this.beanFactory));
		}
		
	    scanner.registerFilters();
	    scanner.doScan(StringUtils.toStringArray(basePackages));		  
	}

	private MyBatisProperties getMyBatisProperties() {
		MyBatisProperties myBatisProperties = new MyBatisProperties();
	    PropertiesConfigurationFactory<MyBatisProperties> factory = 
	    		new PropertiesConfigurationFactory<MyBatisProperties>(myBatisProperties); 
	    factory.setConversionService(new DefaultConversionService());
	    ConfigurationProperties annotation = 
	    		AnnotationUtils.findAnnotation(MyBatisProperties.class, ConfigurationProperties.class);
	    String prefix = StringUtils.hasText(annotation.value()) ? annotation.value() : annotation.prefix();
	    factory.setTargetName(prefix);
	    
	    if (environment instanceof ConfigurableEnvironment) {
	    	ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) environment;
	    	MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
	    	factory.setPropertySources(propertySources);
	    }
	    
	    try {
			factory.bindPropertiesToTarget();
		} catch (BindException e) {
			throw new BeanCreationException("myBatisProperties", "Could not bind properties to "
					+ MyBatisProperties.class + " (" + annotation + ")", e);
		}
		return myBatisProperties;
	}
	
	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
	
}
