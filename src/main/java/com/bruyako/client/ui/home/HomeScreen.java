package com.bruyako.client.ui.home;

import com.bruyako.shared.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;

/**
 * Created by brunyatko on 26.03.16.
 */
public class HomeScreen extends Composite {

    private OnUserLogoutCallBack listener;

    @UiTemplate("HomeScreen.ui.xml")
    interface HomeScreenUiBinder extends UiBinder<Widget, HomeScreen> {
    }

    @UiField
    Label helloText;

    private static HomeScreenUiBinder ourUiBinder = GWT.create(HomeScreenUiBinder.class);
    private static HomeMessages messages = GWT.create(HomeMessages.class);

    public HomeScreen(OnUserLogoutCallBack listener, String userName) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.listener = listener;

        helloText.setText(getLocalizableMessage(userName));
    }

    @UiHandler("buttonSubmit")
    void doClickSubmit(ClickEvent event) {
        listener.onUserLogout();
    }

    private String getLocalizableMessage(String userName) {

        Date date = new Date();
        int hours = date.getHours();
        if (hours >= 6 && hours < 9) {
            return messages.morning(userName);
        } else if (hours >= 9 && hours < 19) {
            return messages.day(userName);
        } else if (hours >= 19 && hours < 23) {
            return messages.evening(userName);
        } else {
            return messages.night(userName);
        }
    }

    public interface OnUserLogoutCallBack {
        public void onUserLogout();
    }
}