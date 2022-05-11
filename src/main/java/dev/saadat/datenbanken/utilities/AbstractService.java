package dev.saadat.datenbanken.utilities;

import org.hibernate.Session;


/**
 * This is essentially an abstraction around the regular Hibernate session.
 *
 * It takes care of the session management (really just the close function) and should be used as base to write your
 *  queries in and expose a friendlier API.
 */
public abstract class AbstractService implements AutoCloseable {

    protected Session session;


    public AbstractService() {
        this.session = HibernateSessionFactory.getSession();
    }

    public AbstractService(Session session) {
        this.session = session;
    }

    public Session getRawSession() {
        return this.session;
    }

    @Override
    public void close() throws Exception {
        this.session.close();
    }


    /* End */
}