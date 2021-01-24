package com.omkaaij.omkaaij_nl.data.validator;

import com.omkaaij.omkaaij_nl.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UserNameExistsConstraint, String> {

    @Autowired
    private VisitorService visitorService;

    @Override
    public void initialize(UserNameExistsConstraint userNameExistsConstraint) {

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return visitorService.getVisitorByUserName(userName).isEmpty();
    }
}
