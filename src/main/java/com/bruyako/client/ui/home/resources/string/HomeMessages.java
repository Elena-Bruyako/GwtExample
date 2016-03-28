package com.bruyako.client.ui.home.resources.string;

import com.google.gwt.i18n.client.Messages;

/**
 * Created by brunyatko on 25.03.16.
 */
public interface HomeMessages extends Messages {

    @DefaultMessage(value = "morning")
    String morning(String userName);

    @DefaultMessage(value = "day")
    String day(String userName);

    @DefaultMessage(value = "evening")
    String evening(String userName);

    @DefaultMessage(value = "night")
    String night(String userName);
}
