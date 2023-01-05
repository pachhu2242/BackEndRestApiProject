package com.backEndRestApi.Exception;

public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	long fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		
		super(String.format("%s not Found with %s : %s", fieldName, resourceName, fieldValue));
		this.fieldName =  fieldName;
		this.fieldValue = fieldValue;
		this.resourceName = resourceName;
		
	}
	

}
