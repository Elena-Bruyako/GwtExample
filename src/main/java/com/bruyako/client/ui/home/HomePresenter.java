package com.bruyako.client.ui.home;

import com.bruyako.client.ui.home.resources.string.HomeMessages;
import com.google.gwt.core.client.GWT;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by brunyatko on 05.04.16.
 */
public class HomePresenter {

    private HomeView view;

    private final Logger logger = Logger.getLogger(HomeScreen.class.getName());
    private HomeMessages messages = GWT.create(HomeMessages.class);

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public void updateMessage(String userName){
        view.updateMessage(getLocalizableMessage(userName));
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
