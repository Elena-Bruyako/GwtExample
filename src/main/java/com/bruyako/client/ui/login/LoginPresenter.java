package com.bruyako.client.ui.login;

import com.bruyako.client.MainRpcService;
import com.bruyako.client.MainRpcServiceAsync;
import com.bruyako.client.exception.EmptyLoginException;
import com.bruyako.client.exception.EmptyLoginPasswordException;
import com.bruyako.client.exception.EmptyPasswordException;
import com.bruyako.client.model.UserDto;
import com.bruyako.client.ui.login.resources.string.LoginStrings;
import com.bruyako.client.utils.LoginValidator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by brunyatko on 05.04.16.
 */
public class LoginPresenter {

    private LoginView view;

    private final MainRpcServiceAsync gwtService = GWT.create(MainRpcService.class);
    private LoginStrings loginStrings = GWT.create(LoginStrings.class);

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void loginUser(String login, String password) {
        try {
            UserDto userDto = new UserDto(login, password);

            if(LoginValidator.isValidUserData(userDto))
                loginUser(userDto);

        } catch (EmptyLoginException e){
            view.showError(loginStrings.emptyLogin());
        } catch (EmptyPasswordException e){
            view.showError(loginStrings.emptyPassword());
        } catch (EmptyLoginPasswordException e){
            view.showError(loginStrings.emptyLoginPassword());
        }
    }

    private void loginUser(UserDto userDto){
        view.showAuthorizationPopup();

        gwtService.getLoggedinUserName(userDto, new AsyncCallback<String>() {
            @Override
            public void onFailure(Throwable caught) {
                view.hideAuthorizationPopup();
                view.showError(loginStrings.unknown());
            }

            @Override
            public void onSuccess(String userName) {
                view.hideAuthorizationPopup();
                view.userWasAuthorized(userName);
            }
        });
    }

}
