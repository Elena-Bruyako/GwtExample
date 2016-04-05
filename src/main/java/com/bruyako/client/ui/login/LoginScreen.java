package com.bruyako.client.ui.login;

import com.bruyako.client.MainRpcService;
import com.bruyako.client.MainRpcServiceAsync;
import com.bruyako.client.events.UserLoginEvent;
import com.bruyako.client.exception.EmptyLoginException;
import com.bruyako.client.exception.EmptyLoginPasswordException;
import com.bruyako.client.exception.EmptyPasswordException;
import com.bruyako.client.model.UserDto;
import com.bruyako.client.ui.login.resources.raw.LoginResources;
import com.bruyako.client.ui.login.resources.string.LoginStrings;
import com.bruyako.client.utils.LoginValidator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.shared.SimpleEventBus;
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
public class LoginScreen extends Composite implements LoginView {

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

    private final SimpleEventBus eventBus;
    private LoginPresenter presenter;
    private PopupPanel popup;
    private LoginStrings loginStrings = GWT.create(LoginStrings.class);

    private LoginScreenUiBinder ourUiBinder = GWT.create(LoginScreenUiBinder.class);

    public LoginScreen(SimpleEventBus eventBus) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.res = GWT.create(LoginResources.class);
        res.style().ensureInjected();

        this.eventBus = eventBus;
        presenter = new LoginPresenter(this);
    }

    @UiHandler("buttonSubmit")
    void doClickSubmit(ClickEvent event) {
        presenter.loginUser(loginBox.getText(), passwordBox.getText());
    }

    @UiHandler("passwordBox")
    void onKeyDown(KeyDownEvent event){
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
            presenter.loginUser(loginBox.getText(), passwordBox.getText());
    }

    @Override
    public void showAuthorizationPopup(){
        popup = new PopupPanel(false, true);
        popup.add(new Label(loginStrings.authorization()));
        popup.setGlassEnabled(true);
        popup.center();
    }

    @Override
    public void hideAuthorizationPopup(){
        popup.hide();
    }

    @Override
    public void showError(String error){
        Window.alert(error);
    }

    @Override
    public void userWasAuthorized(String userName){
        eventBus.fireEvent(new UserLoginEvent(userName));
    }

}