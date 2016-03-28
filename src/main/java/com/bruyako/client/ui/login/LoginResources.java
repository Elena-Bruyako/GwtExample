package com.bruyako.client.ui.login;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created by brunyatko on 25.03.16.
 */
public interface LoginResources extends ClientBundle {

    @Source("logo.png")
    ImageResource logo();

    @Source("Login.css")
    Style style();

    public interface Style extends CssResource {
        String blackText();
        String loginButton();
        String box();

    }
}