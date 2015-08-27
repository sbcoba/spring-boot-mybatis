package org.springframework.boot.mybatis.autoconfigure;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.type.TypeHandler;

public class MyBatisConfigurerSupport implements MyBatisConfigurer {
	
	@Override
	public Interceptor[] getPlugins() {
		return new Interceptor[] {};
	}
	
	@Override
	public TypeHandler<?>[] getTypeHandlers() {
		return new TypeHandler<?>[] {} ;
	}

}