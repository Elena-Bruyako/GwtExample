package com.bruyako.client;

import com.bruyako.client.model.UserDto;
import com.bruyako.shared.User;
import com.bruyako.shared.UsernameNotFoundException;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * Created by brunyatko on 23.03.16.
 */
public interface MainRpcServiceAsync {
    void getLoggedinUserName(UserDto userDto, AsyncCallback<String> callback) throws UsernameNotFoundException;
}
