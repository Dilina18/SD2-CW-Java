import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class VaccinationCenter {
    // set an array of booth objects
    Booth[] patients = new Booth[6];
    // import booth class
    Booth myBooth = new Booth();

    Scanner x = new Scanner(System.in);

    //set program to take user inputs
    public void program() {
        //initialise objects in array
        for (int x = 0; x < patients.length; x++) {
            patients[x] = new Booth();
        }
        //initialise object names to Empty
        initialise(patients);
        while (true) {

            //set warning for low vaccine count
            if (myBooth.getCount() <= 20){
                System.out.println("Waring! Vaccine Count Reached The Limit of " + myBooth.getCount());
            }

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
            //taking user inputs and showing required data
            System.out.print("Please Enter Respective Number: ");
            String number = x.nextLine().toLowerCase().trim();

            if (number.equals("100") || number.equals("vvb")) {
                viewBooth(patients);
            } else if (number.equals("101") || number.equals("veb")) {
                emptyBooth(patients);
            } else if (number.equals("102") || number.equals("apb")) {
                addBooth(patients);
            }
            else if (number.equals("103") || number.equals("rpb")) {
                removeBooth(patients);
            }
            else if (number.equals("104") || number.equals("vps")) {
                sortBooth(patients);
            }
            else if (number.equals("105") || number.equals("spd")) {
                storeData(patients);
            }
            else if (number.equals("106") || number.equals("lpd")) {
                readData(patients);
            }
            else if (number.equals("107") || number.equals("vrv")) {
                System.out.println("Remaining Vaccine Count " + myBooth.getCount());
            }
            else if (number.equals("108") || number.equals("avs")) {
                addVaccine();
            }
            else if (number.equals("999") || number.equals("ext")) {
                System.out.println("Program End!");
                break;
            }
            else {
                System.out.println("Invalid Entry follow given instructions!");
            }


        }

    }

    //Method initialise
    private static void initialise(Booth[] patientRef) {
        for (int x = 0; x < patientRef.length; x++) {
            patientRef[x].setName("Booth Empty");
        }
    }

    //method to validate and view booth details
    public static void viewBooth(Booth[] patientRef) {
        for (int x = 0; x < patientRef.length; ++x) {
            if (patientRef[x].getName().equals("Booth Empty")) {
                System.out.println("Booth " + x + " is empty");
            } else {
                System.out.println("Booth " + x + " Already Occupied By " + patientRef[x].getName());
            }
        }
    }

    //method to validate and view empty booths
    public static void emptyBooth(Booth[] patientsRef) {
        int count = 0;
        for (int x = 0; x < patientsRef.length; ++x) {
            if (patientsRef[x].getName().equals("Booth Empty")) {
                System.out.println("Booth " + x + " is empty");
                count = count + 1;
            }
        }
        if (count == 0){
            System.out.println("No Empty Booths Available!");
        }
    }

    //method to validate inputs and add patient
    public  void addBooth(Booth[] patientRef) {
        boolean adding = true;
        int count = 0;
        while (adding) {
            //checking at least one booth available
            for (int x = 0; x < patientRef.length; ++x) {
                if (!patientRef[x].getName().equals("Booth Empty")) {
                    count = count + 1;
                }
            }
            if (count == 6) {
                System.out.println("All Booths Are Filled");
                adding = false;
            }
            //taking details to add patient if least one booth available
            else {
                //calling method to take and validate user name
                String name = validateNames("firstName");
                //calling method to take and validate booth number
                int num = validateBoothNum();
                if (num == 6){
                    adding = false;
                }
                //check selected booth is empty
                else if (!patientRef[num].getName().equals("Booth Empty")) {
                    System.out.println("Booth Already Occupied");
                    count = 0;
                }
                //if booth empty add patient
                else {
                    patientRef[num].setName(name);
                    System.out.println(name + " Added to Booth Number " + num);
                    myBooth.adding(-1);
                    adding = false;
                }

            }
        }
    }

    //method to validate and remove patient
    public static void removeBooth(Booth[] patientRef) {
        boolean removing = true;
        int count = 0;
        while (removing) {
            //checking least one not empty booth available
            for (int x = 0; x < patientRef.length; ++x) {
                if (patientRef[x].getName().equals("Booth Empty")) {
                    count = count + 1;
                }
            }
            if (count == 6) {
                System.out.println("All Booths Are Empty No Patients to Remove");
                removing = false;
            } else {
                int num = validateBoothNum();
                if (num == 6){
                    removing = false;
                }
                //check selected booth already empty
                else if (patientRef[num].getName().equals("Booth Empty")) {
                    System.out.println("Booth Already Empty");
                    count = 0;
                }
                //if booth not empty remove patient
                else {
                    System.out.println(patientRef[num].getName() + " Removed from Booth Number " + num);
                    patientRef[num].setName("Booth Empty");
                    removing = false;
                }
            }
        }
    }

    //method to store booth details in a text file
    public static void sortBooth(Booth[] patientRef){
        //create array and copy objects data to preventing changing original data while sorting
        String [] sorting = new String[patientRef.length];
        for (int r = 0; r < patientRef.length; ++r){
            sorting[r] = patientRef[r].getName();
        }
        int j = 0;
        String temp;
        for (int i = 0; i < sorting.length; i++){
            for (j = i + 1; j < sorting.length; ++j){
                if(sorting[i].compareTo(sorting[j]) > 0){
                    temp=sorting[i];
                    sorting[i] = sorting[j];
                    sorting[j] = temp;
                }
            }
        }
        //print sorted patient names
        int count = 0;
        for(int k=0;k<sorting.length;k++){
            if (!sorting[k].equals("Booth Empty")){
                System.out.println(sorting[k]);
                count = count + 1;
            }
        }
        if (count == 0){
            System.out.println("No Patients to view");
        }
    }
    private void storeData(Booth[] patientRef){
        try{
            FileWriter myFile = new FileWriter("Task2.txt");
            for(int x =0 ; x < patientRef.length; x++){
                myFile.write("Name : " + patientRef[x].getName() + "\n" );
                myFile.write("Booth Number: " + (x) + "\n");
                myFile.write("________________________________________________________________________________________" + "\n");
                myFile.write("\n\n ");
            }
            myFile.write("Remaining Vaccine Count: " + Integer.toString(myBooth.getCount()));
            myFile.close();
            System.out.println("Data Successfully Stored");
        }catch (IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
    private static void readData(Booth[] patientRef){
        try{
            File myFile = new File("Task2.txt");
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
            System.out.println("Data Successfully Loaded");
        }catch (FileNotFoundException e){
            System.out.println("An Error occurred.");
            e.printStackTrace();
        }
    }
    //method to updating vaccine count
    public void addVaccine(){
        boolean vaccineLoop = true;
        while (vaccineLoop){
            try {
                Scanner adder = new Scanner(System.in);
                System.out.print("Adding Vaccine Count or (0) to Exit: ");
                String addVaccineCount = adder.nextLine().trim();
                int addCount = Integer.parseInt(addVaccineCount);
                if (addCount == 0){
                    System.out.println("Exited");
                    vaccineLoop = false;
                }
                else if (addCount > 0){
                    myBooth.adding(addCount);
                    System.out.println(addCount + " Vaccines added to Store");
                    System.out.println("Updated total: " + myBooth.getCount());
                    vaccineLoop = false;
                }
                else {
                    System.out.println("Invalid Entry");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Entry/Count!");
            }

        }
    }
    //Method to validate user name input
    public static String validateNames(String nameType){
        Scanner n = new Scanner(System.in);
        String name = "";
        while (true){
            if (nameType.equals("firstName")){
                System.out.print("Please Enter Your Name: ");
                name = n.nextLine().trim();
            }
            if (name.length() > 0){
                return name;
            }
            else {
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
}
