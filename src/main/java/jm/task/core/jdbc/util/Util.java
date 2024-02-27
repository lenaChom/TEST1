package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class Util {
    // реализуйте настройку соеденения с БД
    private static final String password = "1234567890";
    private static final String username = "postgres";
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";

    private Util(){}

    public static Connection open(){
        try {
            return DriverManager.getConnection( url, username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory openSessionFactory(){
        if(sessionFactory == null){
            try {
                Properties properties = new Properties();
                properties.setProperty("hibernate.connection.url", url);
                properties.setProperty("hibernate.connection.username", username);
                properties.setProperty("hibernate.connection.password", password);
                properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
                properties.setProperty("hibernate.connection.show_sql", "true");
                Configuration configuration = new Configuration();
                sessionFactory = configuration.addProperties(properties).addAnnotatedClass(User.class).buildSessionFactory();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
        return sessionFactory;
    }


}
