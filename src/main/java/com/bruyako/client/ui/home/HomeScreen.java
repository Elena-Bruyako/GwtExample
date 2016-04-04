package com.bruyako.client.ui.home;

import com.bruyako.client.events.UserLogoutEvent;
import com.bruyako.client.ui.home.resources.string.HomeMessages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by brunyatko on 26.03.16.
 */
public class HomeScreen extends Composite {

    private String userName;
    private final SimpleEventBus eventBus;
    private final Logger logger = Logger.getLogger(HomeScreen.class.getName());

    @UiTemplate("HomeScreen.ui.xml")
    interface HomeScreenUiBinder extends UiBinder<Widget, HomeScreen> {
    }

    @UiField
    Label helloText;

    private static HomeScreenUiBinder ourUiBinder = GWT.create(HomeScreenUiBinder.class);
    private static HomeMessages messages = GWT.create(HomeMessages.class);

    public HomeScreen(SimpleEventBus eventBus, String userName) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = eventBus;

        this.userName = userName;
        helloText.setText(getLocalizableMessage(this.userName));
    }

    @UiHandler("buttonSubmit")
    void doClickSubmit(ClickEvent event) {
        eventBus.fireEvent(new UserLogoutEvent(userName));
    }

    private String getLocalizableMessage(String userName) {

        Date date = new Date();
        int hours = date.getHours();
        if (hours >= 6 && hours < 9) {
            logger.log(Level.INFO, "Hello message was: " + messages.morning(userName));
            return messages.morning(userName);
        } else if (hours >= 9 && hours < 19) {
            logger.log(Level.INFO, "Hello message was: " + messages.day(userName));
            return messages.day(userName);
        } else if (hours >= 19 && hours < 23) {
            logger.log(Level.INFO, "Hello message was: " + messages.evening(userName));
            return messages.evening(userName);
        } else {
            logger.log(Level.INFO, "Hello message was: " + messages.night(userName));
            return messages.night(userName);
        }
    }
}