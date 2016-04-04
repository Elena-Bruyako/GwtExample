package com.bruyako.server;

import com.bruyako.client.MainRpcService;
import com.bruyako.client.model.UserDto;
import com.bruyako.server.repository.UserRepository;
import com.bruyako.server.util.BCrypt;
import com.bruyako.shared.User;
import com.bruyako.shared.UsernameNotFoundException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by brunyatko on 23.03.16.
 */
public class HomeServlet extends RemoteServiceServlet implements MainRpcService {

    private UserRepository userRepository = new UserRepository();

    @Override
    public String getLoggedinUserName(UserDto userDto) throws UsernameNotFoundException {

        User user = getUserByLogin(userDto.getLogin());

        if (BCrypt.checkpw(userDto.getPassword(), user.getPassword())) {
            return user.getName();
        }
        throw new UsernameNotFoundException();
    }

    public User getUserByLogin(String username) {
        return userRepository.getUserByLogin(username);
    }
}
