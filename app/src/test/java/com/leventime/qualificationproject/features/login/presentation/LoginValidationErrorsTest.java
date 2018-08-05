package com.leventime.qualificationproject.features.login.presentation;

import com.leventime.qualificationproject.util.Strings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Test {@link LoginValidationErrors}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class LoginValidationErrorsTest{

    @Test
    public void hasErrors(){
        String error = "error";
        LoginValidationErrors loginValidationErrors = new LoginValidationErrors();
        loginValidationErrors.setEmailError(error);
        loginValidationErrors.setPasswordError(Strings.EMPTY);
        assertTrue(loginValidationErrors.hasErrors());

        loginValidationErrors.setEmailError(error);
        loginValidationErrors.setPasswordError(error);
        assertTrue(loginValidationErrors.hasErrors());

        loginValidationErrors.setEmailError(Strings.EMPTY);
        loginValidationErrors.setPasswordError(Strings.EMPTY);
        assertFalse(loginValidationErrors.hasErrors());
    }
}
