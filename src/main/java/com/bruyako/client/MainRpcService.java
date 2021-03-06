package com.bruyako.client;

import com.bruyako.client.model.UserDto;
import com.bruyako.shared.UsernameNotFoundException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.core.java.lang.Boolean_CustomFieldSerializer;

import java.util.List;

/**
 * Created by brunyatko on 23.03.16.
 */
@RemoteServiceRelativePath("remoteService")
public interface MainRpcService extends RemoteService {
    String getLoggedinUserName(UserDto userDto) throws UsernameNotFoundException;
}
