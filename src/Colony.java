import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Colony {
    private Integer id;
    private Integer hiveId;
    private String colonyOrigin;
    private String queenBreed;
    private Integer queenYear;
    private Integer numberOfSupers;
    private Integer numberOfFrames;
    private Integer numberOfBees;
    private Integer numberOfBrood;
    private Integer kgHoney;
    private Integer pollen;
    private String varroaTreatment;
    private Integer foodAdded;
    public Colony() {
    }
    //    ARGUMENT CONSTRUCTOR
    public Colony(Integer id, Integer hiveId, String colonyOrigin, String queenBreed, Integer queenYear, Integer numberOfSupers, Integer numberOfFrames, Integer numberOfBees, Integer numberOfBrood, Integer kgHoney, Integer pollen, String varroaTreatment, Integer foodAdded) {
        this.id = id;
        this.hiveId = hiveId;
        this.colonyOrigin = colonyOrigin;
        this.queenBreed = queenBreed;
        this.queenYear = queenYear;
        this.numberOfSupers = numberOfSupers;
        this.numberOfFrames = numberOfFrames;
        this.numberOfBees = numberOfBees;
        this.numberOfBrood = numberOfBrood;
        this.kgHoney = kgHoney;
        this.pollen = pollen;
        this.varroaTreatment = varroaTreatment;
        this.foodAdded = foodAdded;
    }
    // CREATE METHOD TO ADD NEW COLONY

    public static void addColony(Colony colony, Scanner scanner, Hive hive, History history, Location
            location, LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
        do {
            System.out.println(" - Select hive id where you want to add this colony: ");
            colony.setHiveId(methods.validatorForIntegers(scanner));

            if (colony.getHiveId() == dbConnection.thereIsColonyWithThisHiveNr(colony)) {
                System.out.println("There is colony in this hive already, please choose another one!");
            }
            if (colony.getHiveId() != dbConnection.isThereHiveWithThisNr(hive, colony)) {
                System.out.println("There is no hive with this number, please choose what would you like to do: ");
                int menu3;
                do {
                    System.out.println("    - to create new hive - enter 1;");
                    System.out.println("    - to chose another hive - enter 0.");
                    menu3 = methods.validatorForIntegers(scanner);

                    switch (menu3) {
                        default:
                            if (menu3 != 0) {
                                System.out.println("This menu item does not exist!");
                                System.out.println("Please double check the number you have entered!");
                            }
                            break;
                        case 1:
                            hive.addNewHive(scanner, hive, history, location, localDateTime, dateTimeFormatter, methods, dbConnection);
                    }
                    if (menu3 == 1) {
                        break;
                    }

                } while (menu3 != 0);

            }
        }
        while (colony.getHiveId() == dbConnection.thereIsColonyWithThisHiveNr(colony)
                || colony.getHiveId() != dbConnection.isThereHiveWithThisNr(hive, colony));

        System.out.println(" - Please enter colony origin: ");
        colony.setColonyOrigin(scanner.nextLine());
        System.out.println(" - Please enter queen breed: ");
        colony.setQueenBreed(scanner.nextLine());
        System.out.println(" - Please enter queen year: ");
        colony.setQueenYear(methods.validatorForIntegers(scanner));
        System.out.println(" - Please enter the number of supers: ");
        colony.setNumberOfSupers(methods.validatorForIntegers(scanner));
        System.out.println(" - Please enter the number of frames: ");
        colony.setNumberOfFrames(methods.validatorForIntegers(scanner));
        System.out.println(" - Please enter the number of bees: ");
        colony.setNumberOfBees(methods.validatorForIntegers(scanner));
        System.out.println(" - Please enter the number of brood: ");
        colony.setNumberOfBrood(methods.validatorForIntegers(scanner));
        System.out.println(" - Please enter the amount of honey (kg): ");
        colony.setKgHoney(methods.validatorForIntegers(scanner));
        System.out.println(" - Please enter the amount of pollen (kg): ");
        colony.setPollen(methods.validatorForIntegers(scanner));
        int selectedVarroaTreatment;
        do {
            System.out.println(" - Please enter the varroa treatment: ");
            System.out.println("    select 1 for skudrskābe,");
            System.out.println("    select 2 for skābeņskābe,");
            System.out.println("    select 3 for HiveClean.");

            selectedVarroaTreatment = methods.validatorForIntegers(scanner);

            if (selectedVarroaTreatment==1){
            colony.setVarroaTreatment("skudrskābe");
        }else if (selectedVarroaTreatment==2){
            colony.setVarroaTreatment("skābeņskābe");
        }else if(selectedVarroaTreatment ==3) {
                colony.setVarroaTreatment("HiveClean");
            }else {
                System.out.println("No such option!");
            }

        }while ((selectedVarroaTreatment != 1)&&(selectedVarroaTreatment!=2)&&(selectedVarroaTreatment!=3));

//        (colony.getVarroaTreatment().equals("skudrskābe") || colony.getVarroaTreatment().equals("skābeņskābe")
//                || colony.getVarroaTreatment().equals("'HiveClean'"));
        System.out.println(" - Please enter the amount of added food: ");
        colony.setFoodAdded(methods.validatorForIntegers(scanner));
        dbConnection.createColony(colony);
        history.setActionDone("Added new colony to hive nr. " + colony.getHiveId() + " in location: " + dbConnection.findLocationForAddedHive(location, hive));
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
        dbConnection.updateHiveStatus(hive,colony);
        System.out.println("Added new colony to hive nr. " + colony.getHiveId() + " in location: " + dbConnection.findLocationForAddedHive(location, hive));
    }
    public static void hiveIdForAddingValues (Scanner scanner, Colony colony, DbConnection dbConnection, Hive hive){
        do {System.out.println(" - Please enter hive id, you would like to change information about: ");
colony.setHiveId(Methods.validatorForIntegers(scanner));
//int var1;
            if ((colony.getHiveId()!= dbConnection.thereIsColonyWithThisHiveNr(colony)) &&
                (colony.getHiveId()== dbConnection.isThereHiveWithThisNr(hive,colony) ))
            {
                System.out.println("There is no colony in this hive!");
            }
            else if (colony.getHiveId()!= dbConnection.isThereHiveWithThisNr(hive,colony)){

//            var1=1;
                System.out.println("There is no hive with this number!");}
        }
        while ( colony.getHiveId()!= dbConnection.isThereHiveWithThisNr(hive,colony) ||
                (colony.getHiveId()!= dbConnection.thereIsColonyWithThisHiveNr(colony)));
dbConnection.selectHive(colony);
    }

    //case 4 methods for ADD
    //ADD FOOD
    public static void addFoodAdded(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add food to the hive: ");
        colony.setFoodAdded(methods.validatorForIntegers(scanner));
        dbConnection.attachFoodAdded(colony);
        // ADD TO HISTORY
        history.setActionDone("Added food: " + colony.getFoodAdded() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        System.out.println("Added food: " + colony.getFoodAdded() + ", in hive nr. " + colony.getHiveId());
        dbConnection.addRecordToHistory(history);
    }
    //ADD VARROA TREATMENT
    public static void addVarroaTreatment(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        int selectedVarroaTreatment;
        do {
            System.out.println(" - Please enter the varroa treatment: ");
            System.out.println("    select 1 for skudrskābe,");
            System.out.println("    select 2 for skābeņskābe,");
            System.out.println("    select 3 for HiveClean.");

            selectedVarroaTreatment = methods.validatorForIntegers(scanner);

            if (selectedVarroaTreatment==1){
                colony.setVarroaTreatment("skudrskābe");
            }else if (selectedVarroaTreatment==2){
                colony.setVarroaTreatment("skābeņskābe");
            }else if(selectedVarroaTreatment ==3) {
                colony.setVarroaTreatment("HiveClean");
            }else {
                System.out.println("No such option!");
            }

        }while ((selectedVarroaTreatment != 1)&&(selectedVarroaTreatment!=2)&&(selectedVarroaTreatment!=3));

        dbConnection.attachVarroaTreatment(colony);
        // ADD TO HISTORY
        history.setActionDone("Added varroa treatment: " + colony.getVarroaTreatment() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
//        PRINT OUT IN CONSOLE
        System.out.println("Added varroa treatment: " + colony.getVarroaTreatment() + ", in hive nr. " + colony.getHiveId());
    }

    //ADD POLLEN
    public static void addPollen(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add pollen (kg): ");
        colony.setPollen(methods.validatorForIntegers(scanner));
        dbConnection.attachPollen(colony);
        // ADD TO HISTORY
        history.setActionDone("Added kg of pollen: " + colony.getPollen() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
 //        PRINT OUT IN CONSOLE
        System.out.println("Added kg of pollen: " + colony.getPollen() + ", in hive nr. " + colony.getHiveId());
    }
    //ADD KG OF HONEY
    public static void addKgHoney(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add honey (kg): ");
        colony.setKgHoney(methods.validatorForIntegers(scanner));
        dbConnection.attachKgHoney(colony);
        // ADD TO HISTORY
        history.setActionDone("Added kg of honey: " + colony.getKgHoney() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
//        PRINT OUT IN CONSOLE
        System.out.println("Added kg of honey: " + colony.getKgHoney() + ", in hive nr. " + colony.getHiveId());
    }
    //ADD NUMBER OF BROOD
    public static void addNumOfBrood(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add number of brood: ");
        colony.setNumberOfBrood(methods.validatorForIntegers(scanner));
        dbConnection.attachNumOfBrood(colony);
        // ADD TO HISTORY
        history.setActionDone("Added number of brood: " + colony.getNumberOfBrood() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
//        PRINT OUT IN CONSOLE
        System.out.println("Added number of brood: " + colony.getNumberOfBrood() + ", in hive nr. " + colony.getHiveId());
    }
    //ADD NUMBER OF BEES
    public static void addNumOfBees(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add number of bees: ");
        colony.setNumberOfBees(methods.validatorForIntegers(scanner));
        dbConnection.attachNumOfBees(colony);
        // ADD TO HISTORY
        history.setActionDone("Added number of bees: " + colony.getNumberOfBees() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
//        PRINT OUT IN CONSOLE
        System.out.println("Added number of bees: " + colony.getNumberOfBees() + ", in hive nr. " + colony.getHiveId());
    }
    //ADD NUMBER OF FRAMES
    public static void addNumOfFrames(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add number of frames: ");
        colony.setNumberOfFrames(methods.validatorForIntegers(scanner));
        dbConnection.attachNumOfFrames(colony);
        // ADD TO HISTORY
        history.setActionDone("Added number of frames: " + colony.getNumberOfFrames() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
//        PRINT OUT IN CONSOLE
        System.out.println("Added number of frames: " + colony.getNumberOfFrames() + ", in hive nr. " + colony.getHiveId());
    }
    //ADD NUMBER OF SUPERS
    public static void addNumOfSupers(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add number of supers: ");
        colony.setNumberOfSupers(methods.validatorForIntegers(scanner));
        dbConnection.attachNumOfSupers(colony);
        // ADD TO HISTORY
        history.setActionDone("Added number of supers: " + colony.getNumberOfSupers() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
//        PRINT OUT IN CONSOLE
        System.out.println("Added number of supers: " + colony.getNumberOfSupers() + ", in hive nr. " + colony.getHiveId());
    }
    //ADD QUEEN YEAR
    public static void addQueenYear(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add queen's year: ");
        colony.setQueenYear(methods.validatorForIntegers(scanner));
        dbConnection.attachQueenYear(colony);
        // ADD TO HISTORY
        history.setActionDone("Added queen's year: " + colony.getQueenYear()+ ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
//        PRINT OUT IN CONSOLE
        System.out.println("Added queen's year: " + colony.getQueenYear() + ", in hive nr. " + colony.getHiveId());
    }
    // ADD QUEEN BREED
    public static void addQueenBreed(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add queen's breed: ");
        colony.setQueenBreed(scanner.nextLine());
        dbConnection.attachQueenBreed(colony);
        // ADD TO HISTORY
        history.setActionDone("Added queen's breed: " + colony.getQueenBreed() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
//        PRINT OUT IN CONSOLE
        System.out.println("Added queen's breed: " + colony.getQueenBreed() + ", in hive nr. " + colony.getHiveId());
    }
    //ADD COLONY ORIGIN
    public static void addColonyOrigin(Scanner scanner, Colony colony, History history, LocalDateTime
            localDateTime, DateTimeFormatter dateTimeFormatter, Methods methods, DbConnection dbConnection) {
//        System.out.println("Enter hive id ");
//        colony.setHiveId(methods.validatorForIntegers(scanner));
        System.out.println(" - Add colony's origin: ");
        colony.setColonyOrigin(scanner.nextLine());
        dbConnection.attachColonyOrigin(colony);
        // ADD TO HISTORY
        history.setActionDone("Added colony's origin: " + colony.getColonyOrigin() + ", in hive nr. " + colony.getHiveId());
        history.setDataAndTime(localDateTime.format(dateTimeFormatter));
        dbConnection.addRecordToHistory(history);
        //        PRINT OUT IN CONSOLE
        System.out.println("Added colony's origin: " + colony.getColonyOrigin() + ", in hive nr. " + colony.getHiveId());
    }
    //HOW MANY COLONIES ARE TREATED WITH VARROA TREATMENT,SORT BY COUNT
    public static void varroaTreatmentCount(Colony colony, DbConnection dbConnection) {
        dbConnection.varroaTreatmentCount(colony);
    }
    //  3 STRONGEST COLONIES BY HONEY
    public static void strongestHivesByHoney(Colony colony, Location location, Hive hive, DbConnection dbConnection) {
        dbConnection.strongestColoniesByHoney(colony, hive, location);
    }
    //    SUM OF HONEY AND POLLEN
    public static void honeyAndPollenSum(Colony colony, DbConnection dbConnection) {
        dbConnection.sumOfHoneyAndPollen(colony);
    }
//    TO STRING METHOD

    @Override
    public String toString() {
        return "Colony{" +
                "id=" + id +
                ", hiveId=" + hiveId +
                ", colonyOrigin='" + colonyOrigin + '\'' +
                ", queenBreed='" + queenBreed + '\'' +
                ", queenYear='" + queenYear + '\'' +
                ", numberOfSupers=" + numberOfSupers +
                ", numberOfFrames=" + numberOfFrames +
                ", numberOfBees=" + numberOfBees +
                ", numberOfBrood=" + numberOfBrood +
                ", kgHoney=" + kgHoney +
                ", pollen=" + pollen +
                ", varroaTreatment='" + varroaTreatment + '\'' +
                ", foodAdded=" + foodAdded +
                '}';
    }


//    GETTERS & SETTERS


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHiveId() {
        return hiveId;
    }

    public void setHiveId(Integer hiveId) {
        this.hiveId = hiveId;
    }

    public String getColonyOrigin() {
        return colonyOrigin;
    }

    public void setColonyOrigin(String colonyOrigin) {
        this.colonyOrigin = colonyOrigin;
    }

    public String getQueenBreed() {
        return queenBreed;
    }

    public void setQueenBreed(String queenBreed) {
        this.queenBreed = queenBreed;
    }

    public Integer getQueenYear() {
        return queenYear;
    }

    public void setQueenYear(Integer queenYear) {
        this.queenYear = queenYear;
    }

    public Integer getNumberOfSupers() {
        return numberOfSupers;
    }

    public void setNumberOfSupers(Integer numberOfSupers) {
        this.numberOfSupers = numberOfSupers;
    }

    public Integer getNumberOfFrames() {
        return numberOfFrames;
    }

    public void setNumberOfFrames(Integer numberOfFrames) {
        this.numberOfFrames = numberOfFrames;
    }

    public Integer getNumberOfBees() {
        return numberOfBees;
    }

    public void setNumberOfBees(Integer numberOfBees) {
        this.numberOfBees = numberOfBees;
    }

    public Integer getNumberOfBrood() {
        return numberOfBrood;
    }

    public void setNumberOfBrood(Integer numberOfBrood) {
        this.numberOfBrood = numberOfBrood;
    }

    public Integer getKgHoney() {
        return kgHoney;
    }

    public void setKgHoney(Integer kgHoney) {
        this.kgHoney = kgHoney;
    }

    public Integer getPollen() {
        return pollen;
    }

    public void setPollen(Integer pollen) {
        this.pollen = pollen;
    }

    public String getVarroaTreatment() {
        return varroaTreatment;
    }

    public void setVarroaTreatment(String varroaTreatment) {
        this.varroaTreatment = varroaTreatment;
    }

    public Integer getFoodAdded() {
        return foodAdded;
    }

    public void setFoodAdded(Integer foodAdded) {
        this.foodAdded = foodAdded;
    }
}