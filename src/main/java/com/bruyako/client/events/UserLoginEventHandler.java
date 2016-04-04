package com.bruyako.client.events;

import com.google.gwt.event.shared.EventHandler;

/**
 * Created by brunyatko on 05.04.16.
 */
public interface UserLoginEventHandler extends EventHandler {
    public void onLogin(UserLoginEvent event);
}
