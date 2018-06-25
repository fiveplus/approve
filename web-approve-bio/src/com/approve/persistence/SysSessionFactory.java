package com.approve.persistence;

import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

public class SysSessionFactory extends AnnotationSessionFactoryBean{
	public SysSessionFactory(SysMapping config){
		this.setAnnotatedClasses(config.getAnnotatedClasses());
		this.setMappingResources(config.getMappingResources());
	}
}
