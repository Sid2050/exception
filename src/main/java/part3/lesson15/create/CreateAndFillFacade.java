package part3.lesson15.create;

import part3.lesson15.ConnectionManager.ConnectionManagerJdbcImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @autor Aleksey Danilchik
 */
public class CreateAndFillFacade {
    private CreationTable creationTable;
    private FillTable fillTable;

    public CreateAndFillFacade(CreationTable creationTable, FillTable fillTable) {
        this.creationTable = creationTable;
        this.fillTable = fillTable;
    }

    public void createAndFillTable() {
        try (Connection connection = ConnectionManagerJdbcImpl.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            creationTable.dropTableDB(statement);

            creationTable.createUser(statement);
            fillTable.fillUser(statement);
            creationTable.createRole(statement);
            fillTable.fillRole(statement);
            creationTable.createUserRole(statement);
            fillTable.fillUserRole(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
