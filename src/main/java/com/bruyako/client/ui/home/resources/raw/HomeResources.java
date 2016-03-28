package com.bruyako.client.ui.home.resources.raw;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

/**
 * Created by brunyatko on 25.03.16.
 */
public interface HomeResources extends ClientBundle {

    @Source("logo.png")
    ImageResource logo();

    @Source("Home.css")
    Style style();

    public interface Style extends CssResource {
        String blackText();
        String logoutButton();
    }
}