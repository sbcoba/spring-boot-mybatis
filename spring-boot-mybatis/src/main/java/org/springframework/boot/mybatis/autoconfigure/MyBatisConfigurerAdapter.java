package org.springframework.boot.mybatis.autoconfigure;

import java.util.List;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.type.TypeHandler;

public class MyBatisConfigurerAdapter implements MyBatisConfigurer {

	@Override
	public void addPlugins(List<Interceptor> interceptors) {
		
	}

	@Override
	public void addTypeHandlers(List<TypeHandler<?>> typeHandlers) {
	}

}