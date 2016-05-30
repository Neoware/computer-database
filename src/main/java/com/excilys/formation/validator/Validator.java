package com.excilys.formation.validator;

import com.excilys.formation.util.ReturnInformation;

public interface Validator<T> {

	public ReturnInformation isValid(T toVerify);

}
