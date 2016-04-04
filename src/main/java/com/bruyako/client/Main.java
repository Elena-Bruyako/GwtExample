package com.bruyako.client;

import com.bruyako.client.events.UserLoginEvent;
import com.bruyako.client.events.UserLoginEventHandler;
import com.bruyako.client.events.UserLogoutEvent;
import com.bruyako.client.events.UserLogoutEventHandler;
import com.bruyako.client.ui.home.HomeScreen;
import com.bruyako.client.ui.login.LoginScreen;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.logging.client.SimpleRemoteLogHandler;
import com.google.gwt.user.client.ui.*;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by brunyatko on 23.03.16.
 */
public class Main implements EntryPoint {

    private final SimpleRemoteLogHandler remoteLog = new SimpleRemoteLogHandler();
    private final SimpleEventBus eventBus = new SimpleEventBus();

    @Override
    public void onModuleLoad() {
        initEventBus();
        RootLayoutPanel.get().add(new LoginScreen(eventBus));
    }

    private void initEventBus(){
        eventBus.addHandler(UserLoginEvent.TYPE, new UserLoginEventHandler() {
            @Override
            public void onLogin(UserLoginEvent event) {
                RootLayoutPanel.get().clear();
                RootLayoutPanel.get().add(new HomeScreen(eventBus, event.getUserName()));
                remoteLog.publish(new LogRecord(Level.INFO, "User " + event.getUserName() + " successfully logged in"));
            }
        });

        eventBus.addHandler(UserLogoutEvent.TYPE, new UserLogoutEventHandler() {
            @Override
            public void onLogout(UserLogoutEvent event) {
                RootLayoutPanel.get().clear();
                RootLayoutPanel.get().add(new LoginScreen(eventBus));
                remoteLog.publish(new LogRecord(Level.INFO, "User " + event.getUserName() + " successfully logged out"));
            }
        });
    }
}
