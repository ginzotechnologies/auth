package com.spring.auth.anotations.validations.validators;

import com.spring.auth.anotations.validations.constraints.UsernameNullableConstraint;
import com.spring.auth.util.RegexUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author diegotobalina created on 24/06/2020
 */
public class UsernameNullableValidator
        implements ConstraintValidator<UsernameNullableConstraint, String> {

    @Override
    public void initialize(final UsernameNullableConstraint contactNumber) {
        // empty
    }

    @Override
    public boolean isValid(final String username, final ConstraintValidatorContext cxt) {
        if (StringUtils.isBlank(username)) return true;
        return RegexUtil.isValidUsername(username);
    }
}
