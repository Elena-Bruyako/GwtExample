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
public class HomeScreen extends Composite implements HomeView {

    @UiTemplate("HomeScreen.ui.xml")
    interface HomeScreenUiBinder extends UiBinder<Widget, HomeScreen> {
    }

    @UiField
    Label helloText;

    private String userName;

    private final SimpleEventBus eventBus;
    private HomePresenter presenter;

    private HomeScreenUiBinder ourUiBinder = GWT.create(HomeScreenUiBinder.class);

    public HomeScreen(SimpleEventBus eventBus, String userName) {
        initWidget(ourUiBinder.createAndBindUi(this));
        this.eventBus = eventBus;

        presenter = new HomePresenter(this);
        presenter.updateMessage(userName);
    }

    @Override
    public void updateMessage(String message){
        helloText.setText(message);
    }

    @UiHandler("buttonSubmit")
    void doClickSubmit(ClickEvent event) {
        eventBus.fireEvent(new UserLogoutEvent(userName));
    }

}