package br.com.generic.dao.type;

import java.lang.reflect.Field;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.generic.dao.Parameter;
import br.com.generic.dao.exception.PredicateInvalidException;


public enum Predicates {
	NOT_EQUAL("!=") {
		@SuppressWarnings("rawtypes")
		@Override
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			Path<T> path = this.<T>getPath(root, property);
			Field field = getField(property, getLastProperty(property));
			if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
				return builder.notEqual(((Join)path).join(getLastProperty(property)), parameter.getValue());
			}
			return builder.notEqual(path.get(getLastProperty(property)), parameter.getValue());
		}
	},
	
	GREATER_THAN_OR_EQUAL_TO(">=") {
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			validateComparable(parameter);
			String property = parameter.getProperty();
			Path<T> path = this.<T>getPath(root, property);
			Field field = getField(property, getLastProperty(property));
			if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
				return builder.greaterThanOrEqualTo(((Join)path).<Comparable>join(getLastProperty(property)), (Comparable) parameter.getValue());
			}
			return builder.greaterThanOrEqualTo(path.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},
	
	LESS_THAN_OR_EQUAL_TO("<=") {
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			validateComparable(parameter);
			String property = parameter.getProperty();
			Path<T> path = this.<T>getPath(root, property);
			Field field = getField(property, getLastProperty(property));
			if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
				return builder.lessThanOrEqualTo(((Join)path).<Comparable>join(getLastProperty(property)), (Comparable) parameter.getValue());
			}
			return builder.lessThanOrEqualTo(path.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},
	
	/*IN("><") {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public <T> Predicate getPredicate(CriteriaBuilder builder, Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			builder.in(this.<T>getPath(root, property));
			
			return builder.greaterThan(this.<T>getPath(root, property)
					.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},*/
	
	GREATER_THAN(">") {
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			validateComparable(parameter);
			String property = parameter.getProperty();
			Path<T> path = this.<T>getPath(root, property);
			Field field = getField(property, getLastProperty(property));
			if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
				return builder.greaterThan(((Join)path).<Comparable>join(getLastProperty(property)), (Comparable) parameter.getValue());
			}
			return builder.greaterThan(path.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},
	
	LESS_THAN("<") {
		@Override
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			validateComparable(parameter);
			String property = parameter.getProperty();
			Path<T> path = this.<T>getPath(root, property);
			Field field = getField(property, getLastProperty(property));
			if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
				return builder.lessThan(((Join)path).<Comparable>join(getLastProperty(property)), (Comparable) parameter.getValue());
			}
			return builder.lessThan(path.<Comparable>get(getLastProperty(property)), (Comparable) parameter.getValue());
		}
	},
	
	LIKE("+") {
		@Override
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			validateString(parameter);
			String property = parameter.getProperty();
			return builder.like(this.<T>getPath(root, property)
					.<String>get(getLastProperty(property)), (String) parameter.getValue());
		}
	},
	
	//sempre colocar esse por ultimo
	EQUAL("=") {
		@SuppressWarnings("rawtypes")
		@Override
		public <T> Predicate getPredicate(CriteriaBuilder builder,
				Root<T> root, Parameter parameter) {
			String property = parameter.getProperty();
			Path<T> path = this.<T>getPath(root, property);
			Field field = getField(property, getLastProperty(property));
			if((annotedEntity(field.getType()) || isCollectionEntity(field)) && this.<T>responderJoin(path)){
				return builder.equal(((Join)path).join(getLastProperty(property)), parameter.getValue());
			}
			return builder.equal(path.get(getLastProperty(property)), parameter.getValue());
		}
	};
	
	
	private String value;
	private Class<?> entityClass;
	
	private Predicates(String value){
		this.value = value;
	}
	
	public abstract <T> Predicate getPredicate(CriteriaBuilder builder, Root<T> root, Parameter parameter);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T> Path<T> getPath(Root<T> root, String properties){
		
		if(properties.contains(".")){
			String[] ropertys =  properties.split("\\.");
			
			Path<T> path = root;
			Field field;
			for(int i = 0 ; i < (ropertys.length - 1) ; i++){
				field = getField(properties, ropertys[i]);
				if(this.<T>responderJoin(path)){
					if(annotedEntity(field.getType()) || isCollectionEntity(field)){
						if(path instanceof Join)
							path = ((Join)path).join(ropertys[i]);
						else
							path = ((Root)path).join(ropertys[i]);
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
	
	protected boolean annotedEntity(Class<?> clazz){
		return clazz.isAnnotationPresent(Entity.class);
	}
	
	protected boolean isCollectionEntity(Field field){
		if(implementsCollection(field.getClass())){
			return field.isAnnotationPresent(OneToMany.class) || 
					field.isAnnotationPresent(ManyToMany.class);
		}
		
		return false;
	}
	
	protected <T> boolean responderJoin(Path<T> path){
		return path instanceof Join || path instanceof Root;
	}
	
	
	protected Field getField(String properties, String node){
		String[] ropertys =  properties.split("\\.");
		Field field;
		Class<?> clazz = entityClass;
		for(int i = 0 ; i < ropertys.length ; i++){
			do{
				field = getField(clazz, node);
				if(field == null)
					clazz = clazz.getSuperclass();
			}while (field == null && !clazz.equals(Object.class));
			
			if(field != null){
				return field;
			}
		}
		return null;
	}
	
	private boolean implementsCollection(Class<?> clazz){
		do{
			for(Class<?> inter : clazz.getInterfaces()){
				if(inter.equals(Collection.class)){
					return true;
				}
			}
			clazz = clazz.getSuperclass();
		}while(!clazz.equals(Object.class));
		
		return false;
	}
	
	private Field getField(Class<?> entityClass, String fieldName){
		Field fieldReturn = null;;
		try {
			fieldReturn = entityClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		if(fieldReturn != null){
			return fieldReturn;
		}else if(!entityClass.getSuperclass().equals(Object.class)){
			return getField(entityClass.getSuperclass(), fieldName);
		}else{
			return null;
		}
	}
	
	protected String getLastProperty(String property){
		if(property.contains(".")){
			return property.substring(property.lastIndexOf(".") + 1);
		}else{
			return property;
		}
	}
	
	protected void validateComparable(Parameter parameter){
		if(!(parameter.getValue() instanceof Comparable)) {
			throw new PredicateInvalidException("the attribute "+ parameter.getProperty() 
					+" must implement java.lang.Comparable to use " + parameter.getPredicates().getValue() + ".");
		}
	}
	
	protected void validateString(Parameter parameter){
		if(!(parameter.getValue() instanceof String)) {
			throw new PredicateInvalidException("the attribute "+ 
					parameter.getProperty() +" must implement comparable to use LIKE.");
		}
	}
	
	public String getValue() {
		return value;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}
	
	
	
}
