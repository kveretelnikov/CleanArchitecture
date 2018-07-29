package com.leventime.qualificationproject.base.core.presentation;

import com.leventime.qualificationproject.util.Strings;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;

/**
 * Test {@link Validator}
 *
 * @author kv
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class ValidatorTest{

    private static final Validator mValidator = new Validator();

    @Test
    public void validateLoginEmailSuccess(){
        String email = "user@user.com";
        assertEquals(true, mValidator.validateLoginEmail(email));
    }

    @Test
    public void validateLoginEmailFail(){
        String email = "";
        assertEquals(false, mValidator.validateLoginEmail(email));
    }

    @Test
    public void validateLoginPasswordSuccess(){
        String password = "password";
        assertEquals(true, mValidator.validateLoginPassword(password));
    }

    @Test
    public void validateLoginPasswordFail(){
        String password = Strings.EMPTY;
        assertEquals(false, mValidator.validateLoginPassword(password));
    }
}