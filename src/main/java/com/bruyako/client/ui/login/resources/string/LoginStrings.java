package com.bruyako.client.ui.login.resources.string;

import com.google.gwt.i18n.client.Constants;

/**
 * Created by brunyatko on 28.03.16.
 */
public interface LoginStrings extends Constants {

    @Key(value = "login")
    String login();

    @Key(value = "password")
    String password();

    @Key(value = "submit")
    String submit();

    @Key(value = "unknown")
    String unknown();

    @Key(value = "emptyLogin")
    String emptyLogin();

    @Key(value = "emptyPassword")
    String emptyPassword();

    @Key(value = "emptyLoginPassword")
    String emptyLoginPassword();

    @Key(value = "authorization")
    String authorization();
}
