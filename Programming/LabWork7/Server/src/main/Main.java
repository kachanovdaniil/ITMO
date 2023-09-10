package main;

import managers.Invoker;
import managers.connection.ConnectionReceiver;
import org.apache.logging.log4j.Logger;
import receivers.MusicReceiver;
import receivers.UserReceiver;

import org.apache.logging.log4j.LogManager;

public class Main {

    public static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            try {
                UserReceiver.GetInstance();
                MusicReceiver.GetInstance();
                logger.info("Files loaded");
                (new ConnectionReceiver()).run(new Invoker());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        } catch (ClassNotFoundException e) {
            logger.error("PostgreSQL JDBC Driver is not found. ", e);
        }
    }
}
