package dev.saadat.datenbanken.tasks;

import liquibase.change.custom.CustomTaskChange;
import liquibase.change.custom.CustomTaskRollback;
import liquibase.database.Database;
import liquibase.exception.CustomChangeException;
import liquibase.exception.RollbackImpossibleException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;


/**
 * This is a custom Liquibase task, in this case a simple echo task which doesn't do anything special.
 *
 * You'd primarily work within the @execute method to perform changes.
 * Please refer to the Liquibase documentation since this is a very vast topic.
 */
public class LiquibaseEchoTask implements CustomTaskChange, CustomTaskRollback {


    public LiquibaseEchoTask() { }

    @Override
    public void execute(Database database) throws CustomChangeException {
        System.out.println("Hello world from our custom task!");
    }

    @Override
    public String getConfirmationMessage() { return null; }

    @Override
    public void setUp() throws SetupException { }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) { }

    @Override
    public ValidationErrors validate(Database database) { return null; }

    @Override
    public void rollback(Database database) throws CustomChangeException, RollbackImpossibleException { }


    /* End */
}