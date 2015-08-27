package org.springframework.boot.mybatis.autoconfigure;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.type.TypeHandler;

public interface MyBatisConfigurer {
	Interceptor[] getPlugins();

	TypeHandler<?>[] getTypeHandlers();
}