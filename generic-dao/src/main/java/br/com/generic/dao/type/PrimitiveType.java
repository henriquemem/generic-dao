package br.com.generic.dao.type;

import java.lang.reflect.Type;

public enum PrimitiveType {
	BYTE("byte") {
		@Override
		public Object getValueDefalt() {
			return new Byte("").byteValue();
		}
	}, 
	SHORT("short") {
		@Override
		public Object getValueDefalt() {
			return 0;
		}
	}, 
	INT("int") {
		@Override
		public Object getValueDefalt() {
			return 0;
		}
	}, 
	LONG("long") {
		@Override
		public Object getValueDefalt() {
			return 0l;
		}
	}, 
	FLOAT("float") {
		@Override
		public Object getValueDefalt() {
			return 0.0;
		}
	}, 
	DOUBLE("double") {
		@Override
		public Object getValueDefalt() {
			return 0.0;
		}
	}, 
	CHAR("char") {
		@Override
		public Object getValueDefalt() {
			
			return ' ';
		}
	}, 
	BOOLEAN("boolean") {
		@Override
		public Object getValueDefalt() {
			return true;
		}
	};
	
	private String nameType;
	
	private PrimitiveType(String nameType){
		this.nameType = nameType;
	}
	
	public String getNameType() {
		return nameType;
	}

	public static boolean isPrimitiveType(Type type){
		boolean r = false;
		for(PrimitiveType primitiveType : PrimitiveType.values()){
			if(type.getTypeName().equals(primitiveType.getNameType())){
				r = true;
			}
		}
		return r;
	}
	
	public static PrimitiveType getPrimitiveType(Type type){
		for(PrimitiveType primitiveType : PrimitiveType.values()){
			if(type.getTypeName().equals(primitiveType.getNameType())){
				return primitiveType;
			}
		}
		return null;
	}

	public abstract Object getValueDefalt();; 
}
