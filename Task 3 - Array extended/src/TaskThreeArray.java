import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskThreeArray {
    //set vaccine count
    private static int vaccineCount = 150;
    public static void main(String[] args) {
        //create arrays to store patient details
        String [] booth = new String[6];
        String [] firstName = new String[6];
        String [] surName = new String[6];
        String [] vaccine = new String[6];
        String [] request = {"AstraZeneca","Sinopharm","Pfizer"};

        //initialise array data
        for (int i = 0; i < booth.length; ++i){
            booth[i] = "-";
            firstName[i] = "-";
            surName[i] = "-";
            vaccine[i] = "-";
        }
        //calling method initialise to initialise data to empty
        initialise(booth);
        while (true){

            //set warning for low vaccine count
            if (vaccineCount <= 20){
                System.out.println("Waring! Vaccine Count Reached The Limit of " + vaccineCount);
            }

            Scanner x = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter Following Numbers/KeyWords To View Details!");
            System.out.println();

            System.out.println("""
                            100 or VVB: View all Vaccination Booths\s
                            101 or VEB: View all Empty Booths\s
                            102 or APB: Add Patient to a Booth\s
                            103 or RPB: Remove Patient from a Booth\s
                            104 or VPS: View Patients Sorted in alphabetical order\s
                            105 or SPD: Store Program Data into file\s
                            106 or LPD: Load Program Data from file\s
                            107 or VRV: View Remaining Vaccinations\s
                            108 or AVS: Add Vaccinations to the Stock\s
                            999 or EXT: Exit the Program
                            """
            );
            System.out.print("Please Enter Respective Number: ");
            String number = x.nextLine().toLowerCase().trim();

            if (number.equals("100") || number.equals("vvb")){
                viewBooth(booth,firstName,surName,vaccine);
            }
            else if (number.equals("101") || number.equals("veb")){
                emptyBooth(booth);
            }
            else if (number.equals("102") || number.equals("apb")){
                addBooth(booth,firstName,surName,vaccine,request);
            }
            else if (number.equals("103") || number.equals("rpb")){
                removeBooth(booth,firstName,surName,vaccine);
            }
            else if (number.equals("104") || number.equals("vps")){
                sortBooth(booth,firstName,surName);
            }
            else if (number.equals("105") || number.equals("spd")){
                storeData(booth,firstName,surName,vaccine);
            }
            else if (number.equals("106") || number.equals("lpd")){
                readData();
            }
            else if (number.equals("107") || number.equals("vrv")){
                System.out.println("Remaining Vaccine Count: " + vaccineCount);
            }
            else if (number.equals("108") || number.equals("avs")){
                addVaccine();
            }
            else if (number.equals("999") || number.equals("ext")){
                System.out.println("Program Ended!");
                break;
            }
            else {
                System.out.println("Invalid Entry Please Follow Given Instructions!");
            }
        }

    }
    //Method initialise
    private static void initialise(String[] patientRef) {
        for (int x = 0; x < patientRef.length; x++) {
            patientRef[x] = "Booth Empty";

        }
    }
    //method to validate and view booths
    private static void viewBooth(String [] patientRef,String[] firstName,String[] surName, String[] vaccine) {
        for (int x = 0; x < patientRef.length; x++) {
            if (patientRef[x].equals("Booth Empty")) {
                System.out.println("Booth " + x + " is Empty");
            } else {
                System.out.println("Booth " + x + " Already Occupied By " + patientRef[x]);
                System.out.println("First Name : " + firstName[x]);
                System.out.println("SurName : " + surName[x]);
                System.out.println("Vaccine : " + vaccine[x]);
                System.out.println();
            }
        }
    }
    //method to view empty booths
    private static void emptyBooth(String[] patientRef) {
        int count = 0;
        for (int x = 0; x < patientRef.length; x++) {
            if (patientRef[x].equals("Booth Empty")) {
                System.out.println("Booth " + x + " is Empty");
                count = count + 1;
            }
        }
        if (count == 0){
            System.out.println("No Empty Booths Available");
        }
    }
    //method to add patients to booth
    public static void addBooth(String [] patientRef,String[] firstName,String[] surName, String[] vaccine, String[] request){
        boolean adding = true;
        int count = 0;
        int count2 = 0;
        Scanner input = new Scanner(System.in);
        while (adding){
            try {
                //checking least one empty booth available
                for (int x = 0; x < patientRef.length; ++x){
                    if (!patientRef[x].equals("Booth Empty")){
                        count2 = count2 + 1;
                    }
                }
                if (count2 == 6){
                    System.out.println("All Booths Are Filled!");
                    adding = false;
                }
                //if least one empty booth available
                else {
                    System.out.print("Please Request Your Vaccine AstraZeneca(0)/SinoPharm(1)/Pfizer(2): ");
                    String orderVaccine = input.nextLine().trim();
                    int order = Integer.parseInt(orderVaccine);
                    //check that empty booth is issue req vaccine
                    if (order >= 0 && order <= 2){
                        for (int i = 0; i < vaccine.length; ++i){
                            if (vaccine[i].equals(request[order])){
                                count = count + 1;
                            }
                        }
                        if (count == 2){
                            System.out.println("No Empty Booths Available for the Requested Vaccine!");
                            adding = false;
                        }
                        //if booth issue req vaccine
                        else {
                            //calling method to take and validate user names
                            String fname = validateNames("firstName");
                            String lname = validateNames("lastName");
                            //calling method to check booth and validate for req vaccine
                            int boothNum = validateRequest(order);
                            if (boothNum == 6){
                                System.out.println("Exited");
                                adding = false;
                            }
                            //if req booth already occupied
                            else if (!patientRef[boothNum].equals("Booth Empty")){
                                System.out.println("Booth Already Occupied By Someone!");
                                count = 0;
                                count2 = 0;
                            }
                            //if req booth empty add
                            else {
                                System.out.println("Booth No " + boothNum + " Occupied By " + fname);
                                System.out.println("Vaccine: "+ request[order]);
                                patientRef[boothNum] = fname;
                                firstName[boothNum] = fname;
                                surName[boothNum] = lname;
                                vaccine[boothNum] = request[order];
                                vaccineCount = vaccineCount - 1;
                                adding = false;
                            }
                        }
                    }
                    else {
                        System.out.println("Invalid Request!");
                        count = 0;
                        count2 = 0;
                    }
                }
            }
            catch (Exception e){
                System.out.println("Invalid Request!");
            }
        }
    }
    //method to remove patients from booth
    public static void removeBooth(String [] patientRef,String[] firstName,String[] surName, String[] vaccine){
        int count = 0;
        boolean removing = true;
        Scanner rmv = new Scanner(System.in);
        while (removing){
            //check for least not one empty booth available
            for (int x = 0; x < patientRef.length; ++x){
                if (patientRef[x].equals("Booth Empty")){
                    count = count + 1;
                }
            }
            if (count == 6){
                System.out.println("All Booths Are Empty No Patients To Remove");
                removing = false;
            }
            //if least one booth not empty
            else {
                //calling method to validate removing booth number
                int remove = validateBoothNum();
                if (remove == 6){
                    System.out.println("Exited");
                    removing = false;
                }
                //if selected booth already empty
                else {
                    if (patientRef[remove].equals("Booth Empty")){
                        System.out.println("Booth Already Empty ");
                        count = 0;
                    }
                    //removing patient
                    else {
                        System.out.println("Patient " + patientRef[remove] + " Removed From Booth " + remove );
                        System.out.println("Vaccine Used: " + vaccine[remove]);
                        patientRef[remove] = "Booth Empty";
                        firstName[remove] = "-";
                        surName[remove] = "-";
                        vaccine[remove] = "-";
                        removing = false;
                    }
                }
            }
        }
    }
    //method to sort data in alphabetical order
    private static void sortBooth(String [] patientRef, String[] firstName, String[] surName){
        //copy data to a new array to preventing changing data in original array
        String [] sorting = new String[patientRef.length];
        for (int i = 0; i < patientRef.length; i++) {
            sorting[i] = firstName[i] + " " + surName[i];
        }
        for(int i = 0; i < sorting.length; i++){
            for (int j = i + 1;j < sorting.length; j++){
                if (sorting[i].compareTo(sorting[j]) > 0 ){
                    String temp = sorting[i];
                    sorting[i] = sorting[j];
                    sorting[j] = temp;
                }
            }
        }
        //print sorted patient names only
        int count = 0;
        for (int i = 0; i < sorting.length; i++){
            if(!sorting[i].equals("Booth Empty") && !sorting[i].equals("- -")) {
                System.out.println(sorting[i] );
                count = count + 1;
            }

        }
        if (count == 0){
            System.out.println("No Patients to View");
        }

    }
    //method to store data in a text file
    private static void storeData(String [] patientRef,String[] firstName,String[] surName,String[] vaccine){
        try{
            FileWriter myFile = new FileWriter("TaskThreeArray.txt");
            for(int x =0 ; x < patientRef.length; x++){
                myFile.write(" -------------------------- Booth Details --------------------------" + "\n");
                myFile.write("Name : " + patientRef[x] + "\n");
                myFile.write("Booth Number: " + x + "\n");
                myFile.write("      First Name : " + firstName[x] + "\n" + "      SurName : " + surName[x]+ "\n" + "      Vaccine : " + vaccine[x] + "\n");
                myFile.write("________________________________________________________________________________________" + "\n");
                myFile.write("\n\n ");
            }
            myFile.write("Remaining Vaccine Count: " + Integer.toString(vaccineCount));
            myFile.close();
            System.out.println("Data Successfully Stored");
        }catch (IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    //method to read data from text file
    private static void readData(){
        try{
            File myFile = new File("TaskThreeArray.txt");
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
            System.out.println("Data Successfully loaded");
        }catch (FileNotFoundException e){
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }
    }
    //method to add vaccine
    public static void addVaccine(){
        boolean adding = true;
        Scanner adder = new Scanner(System.in);
        while (adding){
            try {
                System.out.println("Enter Adding Vaccine Count or 0 to exit: ");
                String addVaccineCount = adder.nextLine().trim();
                int add = Integer.parseInt(addVaccineCount);
                if (add == 0){
                    System.out.println("Exited");
                    adding = false;
                }
                else if (add > 0){
                    vaccineCount = vaccineCount + add;
                    System.out.println("Vaccines Added: " + add);
                    System.out.println("Total Count: " + vaccineCount);
                    adding = false;
                }
                else {
                    System.out.println("Invalid Entry!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Entry!");
            }
        }
    }
    //Method to validate user name input
    public static String validateNames(String nameType) {
        Scanner n = new Scanner(System.in);
        String name = "";
        while (true) {
            if (nameType.equals("firstName")) {
                System.out.print("Please Enter Your Name: ");
                name = n.nextLine().trim();
            } else if (nameType.equals("lastName")) {
                System.out.print("Please Enter Last Name: ");
                name = n.nextLine().trim();
            }
            if (name.length() > 0) {
                return name;
            } else {
                System.out.println("Invalid name!");
            }
        }
    }
    //Method to validate booth number input
    public static Integer validateBoothNum(){
        Scanner y = new Scanner(System.in);
        while (true){
            try {
                System.out.print("Booth Number(0-5) or (6) to exit: ");
                String boothNumber = y.nextLine().trim();
                int boothNum = Integer.parseInt(boothNumber);
                if (boothNum <= 6 && boothNum >= 0){
                    return boothNum;
                }
                else {
                    System.out.println("Invalid Booth Number!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Data Type!");
            }
        }
    }
    //method to validate vaccine request
    public static Integer validateRequest(int order){
        Scanner r = new Scanner(System.in);
        while (true){
            try {
                if (order == 0){
                    System.out.print("Booth No 0/1 to add or (6) to exit: ");
                    String boothNumber = r.nextLine().trim();
                    int boothNum = Integer.parseInt(boothNumber);
                    if (boothNum == 0 || boothNum == 1 || boothNum == 6){
                        return boothNum;
                    }
                    else {
                        System.out.println("Invalid Booth Number");
                    }
                }
                else if (order == 1){
                    System.out.print("Booth No 2/3 to add or (6) to exit: ");
                    String boothNumber = r.nextLine().trim();
                    int boothNum = Integer.parseInt(boothNumber);
                    if (boothNum == 2 || boothNum == 3 || boothNum == 6){
                        return boothNum;
                    }
                    else {
                        System.out.println("Invalid Booth Number");
                    }
                }
                else if (order == 2){
                    System.out.print("Booth No 4/5 to add or (6) to exit: ");
                    String boothNumber = r.nextLine().trim();
                    int boothNum = Integer.parseInt(boothNumber);
                    if (boothNum == 4 || boothNum == 5 || boothNum == 6){
                        return boothNum;
                    }
                    else {
                        System.out.println("Invalid Booth Number");
                    }
                }
                else {
                    System.out.println("Invalid Request!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Request!");
            }
        }
    }
}
