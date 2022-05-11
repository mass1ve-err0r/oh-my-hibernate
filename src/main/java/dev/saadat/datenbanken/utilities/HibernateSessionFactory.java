package dev.saadat.datenbanken.utilities;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dev.saadat.datenbanken.models.User;


/**
 * A lightweight wrapper around the Hibernate SessionFactory.
 *
 * It basically wraps a SessionFactory and exposes it's thread-safe method @openSession.
 * Also configured to use the local database.
 */
public final class HibernateSessionFactory {

    private static SessionFactory sessionFactory;


    static {
        buildSessionFactory();
    }

    private static void buildSessionFactory() {
        Configuration configuration = new Configuration()
                // Set the driver to H2
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver")
                // Point to the local database file. Created if file doesn't exist
                .setProperty("hibernate.connection.url", "jdbc:h2:file:./datenbank")
                // Use Hikari-CP as connection pooler
                .setProperty("hibernate.connection.provider", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider")
                // Credentials
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "")
                // Use the H2 dialect
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                // Here we add our models
                .addAnnotatedClass(User.class)
                ;

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory get() {
        return sessionFactory;
    }

    public static Session getSession() {
        try {
            return sessionFactory.openSession();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        sessionFactory.close();
    }


    /* End */
}