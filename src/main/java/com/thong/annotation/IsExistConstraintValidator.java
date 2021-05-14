package com.thong.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.thong.entity.User;
import com.thong.service.IUserService;

public class IsExistConstraintValidator implements ConstraintValidator<IsExist, String> {
	@Autowired
	private IUserService userService;

	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if (value != null) {
			User user = userService.findByUserName(value).orElse(null);
			return user != null ? false : true;
		}
		return true;
	}

}
