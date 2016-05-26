package com.excilys.formation.validator;

import com.excilys.formation.utils.ReturnInformation;

public interface Validator<T> {

	public ReturnInformation isValid(T toVerify);

}
