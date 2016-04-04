package com.bruyako.client.ui.login;

/**
 * Created by brunyatko on 05.04.16.
 */
public interface LoginView {

    public void showAuthorizationPopup();

    public void hideAuthorizationPopup();

    public void showError(String error);

    public void userWasAuthorized(String userName);

}
