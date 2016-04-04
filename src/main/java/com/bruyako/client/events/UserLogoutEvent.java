package com.bruyako.client.events;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by brunyatko on 05.04.16.
 */
public class UserLogoutEvent extends GwtEvent<UserLogoutEventHandler> {

    public static Type<UserLogoutEventHandler> TYPE = new Type<UserLogoutEventHandler>();

    private String userName;

    public UserLogoutEvent(String userName) {
        this.userName = userName;
    }

    @Override
    public Type<UserLogoutEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UserLogoutEventHandler handler) {
        handler.onLogout(this);
    }

    public String getUserName() {
        return userName;
    }
}
