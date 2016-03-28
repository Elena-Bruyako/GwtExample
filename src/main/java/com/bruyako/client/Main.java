package com.bruyako.client;

import com.bruyako.client.ui.home.HomeScreen;
import com.bruyako.client.ui.home.HomeScreen.OnUserLogoutCallBack;
import com.bruyako.client.ui.login.LoginScreen;
import com.bruyako.client.ui.login.LoginScreen.OnUserLoginCallBack;
import com.bruyako.shared.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.logging.client.SimpleRemoteLogHandler;
import com.google.gwt.user.client.ui.*;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by brunyatko on 23.03.16.
 */
public class Main implements EntryPoint, OnUserLoginCallBack, OnUserLogoutCallBack {

    private final SimpleRemoteLogHandler remoteLog = new SimpleRemoteLogHandler();

    @Override
    public void onModuleLoad() {
        RootLayoutPanel.get().add(new LoginScreen(this));
    }

    @Override
    public void onUserLogin(String userName){
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(new HomeScreen(this, userName));
        remoteLog.publish(new LogRecord(Level.INFO, "User " + userName + " successfully logged in"));
    }

    @Override
    public void onUserLogout(String userName) {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(new LoginScreen(this));
        remoteLog.publish(new LogRecord(Level.INFO, "User " + userName + " successfully logged out"));
    }
}
