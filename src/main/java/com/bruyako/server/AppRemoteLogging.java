package com.bruyako.server;

import com.google.gwt.logging.shared.RemoteLoggingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Created by brunyatko on 28.03.16.
 */
public class AppRemoteLogging extends RemoteServiceServlet implements RemoteLoggingService {

    private final static Logger logger = Logger.getLogger(AppRemoteLogging.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    public final String logOnServer(LogRecord lr) {
        if (lr.getLevel().equals(Level.SEVERE)) {
            logger.error(lr.getMessage(),lr.getThrown());
        } else if (lr.getLevel().equals(Level.INFO)) {
            logger.info(lr.getMessage(),lr.getThrown());
        } else if (lr.getLevel().equals(Level.WARNING)) {
            logger.warn(lr.getMessage(),lr.getThrown());
        } else if (lr.getLevel().equals(Level.FINE)) {
            logger.debug(lr.getMessage(),lr.getThrown());
        } else {
            logger.trace(lr.getMessage(),lr.getThrown());
        }
        return null;
    }
}
