package com.bruyako.client.ui.login;

import com.bruyako.client.MainRpcService;
import com.bruyako.client.MainRpcServiceAsync;
import com.bruyako.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

/**
 * Created by brunyatko on 24.03.16.
 */
public class LoginScreen extends Composite {

    @UiTemplate("LoginScreen.ui.xml")
    interface LoginScreenUiBinder extends UiBinder<Widget, LoginScreen> {
    }

    @UiField(provided = true)
    final LoginResources res;

    @UiField
    TextBox loginBox;

    @UiField
    TextBox passwordBox;

    @UiField
    Label completionLabel1;

    @UiField
    Label completionLabel2;

    private OnUserLoginCallBack callBack;

    private final MainRpcServiceAsync gwtService = GWT.create(MainRpcService.class);
    private static LoginScreenUiBinder ourUiBinder = GWT.create(LoginScreenUiBinder.class);
    LoginStrings loginStrings = GWT.create(LoginStrings.class);

    public LoginScreen(OnUserLoginCallBack callBack) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.res = GWT.create(LoginResources.class);
        res.style().ensureInjected();

        this.callBack = callBack;
    }

    @UiHandler("buttonSubmit")
    void doClickSubmit(ClickEvent event) {
        tryLoginUser();
    }

    @UiHandler("passwordBox")
    void onKeyDown(KeyDownEvent event){
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
            tryLoginUser();
    }

    private void tryLoginUser() {
        if (isAuthValid()) {

            gwtService.getLoggedinUserName(loginBox.getText(), passwordBox.getText(), new AsyncCallback<String>() {
                @Override
                public void onFailure(Throwable caught) {
                    Window.alert(loginStrings.unknown());
                }

                @Override
                public void onSuccess(String userName) {
                    callBack.onUserLogin(userName);
                }
            });

        } else {
            Window.alert(loginStrings.invalid());
        }
    }

    private boolean isAuthValid(){
        return isValidPassword() && isValidUserName();
    }

    private boolean isValidUserName(){
        return loginBox.getText().length() > 0;
    }

    private boolean isValidPassword(){
        return loginBox.getText().length() > 0;
    }

    public interface OnUserLoginCallBack {
        public void onUserLogin(String userName);
    }
}