package com.bruyako.server;

import com.bruyako.client.MainRpcService;
import com.bruyako.client.model.UserDto;
import com.bruyako.server.repository.UserRepository;
import com.bruyako.server.util.BCrypt;
import com.bruyako.server.util.HibernateUtil;
import com.bruyako.shared.User;
import com.bruyako.shared.UsernameNotFoundException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * Created by brunyatko on 23.03.16.
 */
public class HomeServlet extends RemoteServiceServlet implements MainRpcService {

    private UserRepository userRepository = new UserRepository();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        HibernateUtil.buildSessionFactory();
    }

    @Override
    public void destroy() {
        HibernateUtil.shutdown();
        super.destroy();
    }

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
