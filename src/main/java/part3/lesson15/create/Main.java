package part3.lesson15.create;

/**
 * @autor Aleksey Danilchik
 */
public class Main {
    public static void main(String[] args) {
        CreationTable creationTable = new CreationTable();
        FillTable fillTable = new FillTable();
        CreateAndFillFacade facade = new CreateAndFillFacade(creationTable, fillTable);
        facade.createAndFillTable();
    }
}
