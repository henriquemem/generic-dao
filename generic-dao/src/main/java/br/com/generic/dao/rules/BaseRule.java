package br.com.generic.dao.rules;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import br.com.generic.exceptions.BaseRuntimeException;

public abstract class BaseRule implements Rule{
	
	private static Map<Class<?>, PropertyDescriptor[]> propertyDescriptorsCache = new HashMap<Class<?>, PropertyDescriptor[]>();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T> Path<T> getPath(Class<?> entityClass, Root<T> root, String properties){
		
		if(!properties.isEmpty()){
			String[] ropertys =  properties.split("\\.");
			
			Path<T> path = root;
			PropertyDescriptor propertyDescriptor;
			for(int i = 0 ; i < (ropertys.length) ; i++){
				propertyDescriptor = getPropertyDescriptor(entityClass, properties, ropertys[i]);
				if(this.<T>responderFrom(path)){
					if(annotedEntity(propertyDescriptor.getReadMethod().getReturnType()) || isCollectionEntity(propertyDescriptor)){
						path = ((From)path).join(ropertys[i]);
					}else{
						path = path.get(ropertys[i]);
					}
				}else{
					path = path.get(ropertys[i]);
				}
				
			}
			return path;
		}else{
			return root;
		}
	}
	
	protected PropertyDescriptor getPropertyDescriptor(Class<?> entityClass, String properties, String node){
		String[] ropertys =  properties.split("\\.");
		PropertyDescriptor propertyDescriptor;
		Field field;
		Class<?> clazz = entityClass;
		for(int i = 0 ; i < ropertys.length ; i++){
			propertyDescriptor = getPropertyDescriptor(clazz, ropertys[i]);
			if(propertyDescriptor != null && propertyDescriptor.getName().equals(node)){
				return propertyDescriptor;
			}else if(propertyDescriptor != null){
				if(isCollectionEntity(propertyDescriptor)){
					field = getField(clazz, ropertys[i]);
			        clazz = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
				}else{
					clazz = propertyDescriptor.getReadMethod().getReturnType();
				}
			}
		}
		return null;
	}
	
	protected <T> boolean responderFrom(Path<T> path){
		return path instanceof From;
	}
	
	protected boolean annotedEntity(Class<?> clazz){
		return clazz.isAnnotationPresent(Entity.class);
	}
	protected Field getField(Class<?> entityClass, String fieldName){
		Field fieldReturn = null;
		try {
			fieldReturn = entityClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
		} catch (SecurityException e) {
		}
		if(fieldReturn != null){
			return fieldReturn;
		}else if(!entityClass.getSuperclass().equals(Object.class)){
			return getField(entityClass.getSuperclass(), fieldName);
		}else{
			throw new BaseRuntimeException(fieldName + " not found in " + entityClass);
		}
	}
	
	protected boolean isCollectionEntity(PropertyDescriptor propertyDescriptor){
		boolean ret = propertyDescriptor.getReadMethod().isAnnotationPresent(OneToMany.class) || 
				propertyDescriptor.getReadMethod().isAnnotationPresent(ManyToMany.class);
		if(!ret){
			Field field = getField(propertyDescriptor.getReadMethod().getDeclaringClass(), propertyDescriptor.getName());
			ret = field.isAnnotationPresent(OneToMany.class) || 
					field.isAnnotationPresent(ManyToMany.class);
		}
		return ret;
	}
	
	protected PropertyDescriptor getPropertyDescriptor(Class<?> entityClass, String field){
		PropertyDescriptor[] PropertyDescriptors = propertyDescriptorsCache.get(entityClass);
		if(PropertyDescriptors == null){
			PropertyDescriptors = getPropertyDescriptors(entityClass);
			propertyDescriptorsCache.put(entityClass, PropertyDescriptors);
		}
		for(PropertyDescriptor propertyDescriptor : PropertyDescriptors){
			if(propertyDescriptor.getName().equals(field)){
				return propertyDescriptor;
			}
		}
		throw new BaseRuntimeException(field + " not found in " + entityClass);
	}
	
	protected PropertyDescriptor[] getPropertyDescriptors(Class<?> entityClass){
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(entityClass);
		} catch (IntrospectionException e) {
			throw new BaseRuntimeException(e);
		}
		return beanInfo.getPropertyDescriptors();
	}
	
	protected String getLastProperty(String property){
		if(property.contains(".")){
			return property.substring(property.lastIndexOf(".") + 1);
		}else{
			return property;
		}
	}

	protected <T> boolean isFrom(Class<?> entityClass, Path<T> path, String properties){
		PropertyDescriptor propertyDescriptor = getPropertyDescriptor(entityClass, properties, getLastProperty(properties));
		return (annotedEntity(propertyDescriptor.getPropertyType()) || isCollectionEntity(propertyDescriptor)) && this.<T>responderFrom(path);
	}
	
	protected String getNavigation(String property){
		return  property.contains(".")?  property.replace("." + getLastProperty(property), ""): "";
	}

}
