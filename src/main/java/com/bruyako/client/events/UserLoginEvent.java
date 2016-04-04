package com.bruyako.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by brunyatko on 05.04.16.
 */
public class UserLoginEvent extends GwtEvent<UserLoginEventHandler> {

    public static Type<UserLoginEventHandler> TYPE = new Type<UserLoginEventHandler>();

    private String userName;

    public UserLoginEvent(String userName) {
        this.userName = userName;
    }

    @Override
    public Type<UserLoginEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UserLoginEventHandler handler) {
        handler.onLogin(this);
    }

    public String getUserName() {
        return userName;
    }
}
