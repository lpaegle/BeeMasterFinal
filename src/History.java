import java.util.Scanner;

public class History {
    private int id;
    private String actionDone;
    private String dataAndTime;

    Scanner scanner = new Scanner(System.in);

    public History() {
    }

    public History(int id, String actionDone, String dataAndTime) {
        this.id = id;
        this.actionDone = actionDone;
        this.dataAndTime = dataAndTime;
    }

    //    GET  INFORMATION ABOUT ACTIONS DONE ON LAST VISIT
    public static void actionsInLastVisit (DbConnection dbConnection, History history) {
        System.out.println("");
        System.out.println(" - These actions were done on last visit: ");
        System.out.println("");
        dbConnection.allActionsInLastVisit(history);
    }
    //    GET  ALL INFO FROM HISTORY TABLE
    public static void allInfoFromHistory  (DbConnection dbConnection, History history) {
        System.out.println("");
        System.out.println(" - This is all information from history table: ");
        System.out.println("");
        dbConnection.allActionsInLastVisit(history);
    }
    //    GET  DATES WHEN ACTIONS WERE DONE
    public static void datesWhenActionsWhereDone  (DbConnection dbConnection, History history) {
        System.out.println("");
        System.out.println(" - These are dates when actions where done: ");
        System.out.println("");
        dbConnection.getDistinctEntriesFromHistory(history);
    }
    //    GET INFORMATION FROM HISTORY TABLE ABOUT SPECIFIC DATA
    public static void getInfoFromHistoryTableAboutData(History history, Scanner scanner, DbConnection dbConnection) {
        boolean thereAreActionsOnThisDate = false;
        do {
            System.out.println("Please enter date you would like see information about in format: dd-mm-yyyy");
            history.setDataAndTime(scanner.nextLine());

            for (String eachVariable : dbConnection.getDate()
            ) {
                if (eachVariable.equals(history.getDataAndTime())) {

                    thereAreActionsOnThisDate = true;
                    break;

                } else
                    thereAreActionsOnThisDate = false;
            }
            if (thereAreActionsOnThisDate == false) {
                System.out.println("There where not actions done on this date!");
            }
        }
            while (thereAreActionsOnThisDate == false) ;
        System.out.println("");
        System.out.println(" - These actions where done on: "+history.getDataAndTime());
        System.out.println("");
            dbConnection.getInformationFromHistoryTable(history);
        }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", actionDone='" + actionDone + '\'' +
                ", dataAndTime='" + dataAndTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActionDone() {
        return actionDone;
    }

    public void setActionDone(String actionDone) {
        this.actionDone = actionDone;
    }

    public String getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }
}