package com.bruyako.server;

import com.bruyako.client.MainRpcService;
import com.bruyako.server.repository.UserRepository;
import com.bruyako.shared.User;
import com.bruyako.shared.UsernameNotFoundException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Created by brunyatko on 23.03.16.
 */
public class HomeServlet extends RemoteServiceServlet implements MainRpcService {

    private UserRepository userRepository = new UserRepository();

    @Override
    public User getLoggedinUser(String login, String password) throws UsernameNotFoundException {
        User user = getUserByLogin(login);

        if (user.getPassword().equals(password))
            return user;

        throw new UsernameNotFoundException();
    }

    public User getUserByLogin(String username) throws UsernameNotFoundException {
        for (User user : userRepository.getAll()) {
            if (user.getLogin().equals(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException();
    }
}
