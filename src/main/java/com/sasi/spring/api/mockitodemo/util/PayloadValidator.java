package com.sasi.spring.api.mockitodemo.util;

import com.sasi.spring.api.mockitodemo.model.Employee;

public class PayloadValidator {
	
	public static boolean validateCreatePayload(Employee Employee){
		if (Employee.getId() > 0){
			return false;
		}
		return true;
	}

}
