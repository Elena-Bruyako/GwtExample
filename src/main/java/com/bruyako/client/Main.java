package com.bruyako.client;

import com.bruyako.client.ui.home.HomeScreen;
import com.bruyako.client.ui.home.HomeScreen.OnUserLogoutCallBack;
import com.bruyako.client.ui.login.LoginScreen;
import com.bruyako.client.ui.login.LoginScreen.OnUserLoginCallBack;
import com.bruyako.shared.User;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.*;

/**
 * Created by brunyatko on 23.03.16.
 */
public class Main implements EntryPoint, OnUserLoginCallBack, OnUserLogoutCallBack {

    @Override
    public void onModuleLoad() {
        RootLayoutPanel.get().add(new LoginScreen(this));
    }

    @Override
    public void onUserLogin(User result){
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(new HomeScreen(this, result.getName()));
    }

    @Override
    public void onUserLogout() {
        RootLayoutPanel.get().clear();
        RootLayoutPanel.get().add(new LoginScreen(this));
    }
}
