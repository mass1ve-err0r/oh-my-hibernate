package dev.saadat.datenbanken.services;

import liquibase.Contexts;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import dev.saadat.datenbanken.utilities.AbstractService;


public class LiquibaseService extends AbstractService {


    public void runChangelogs() {
        this.session.doWork(connection -> {
            try {
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                Liquibase liquibase = new Liquibase("classpath:/liquibase/db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.update(new Contexts());
            } catch (LiquibaseException e) {
                throw new RuntimeException(e);
            }
        });
    }


    /* End */
}