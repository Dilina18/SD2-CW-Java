import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Task1 {
    // set booth array
    private static String[] currentBooth = new String[6];

    // set vaccine count
    private static int vaccineCount = 150;

    public static void main(String[] args) {
        // calling initialise method to initialise booth array with empty
        initialise(currentBooth);

        while (true){
            //set warning for low vaccine count
            if (vaccineCount <= 20){
                System.out.println("Waring! Vaccine Count Reached The Limit of " + vaccineCount);
            }

            // print user options
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
            // taking user inputs and showing required data
            System.out.print("Please Enter Respective Number: ");
            String number = x.nextLine().toLowerCase().trim();

            if (number.equals("100") || number.equals("vvb")){
                viewBooth(currentBooth);
            }
            else if (number.equals("101") || number.equals("veb")){
                emptyBooths(currentBooth);
            }
            else if (number.equals("102") || number.equals("apb")){
                addBooth(currentBooth);
            }
            else if (number.equals("103") || number.equals("rpb")){
                removeFromBooth(currentBooth);
            }
            else if (number.equals("104") || number.equals("vps")){
                sortBooth(currentBooth);
            }
            else if (number.equals("105") || number.equals("spd")){
                storeData(currentBooth);
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
                System.out.println("Program End!");
                break;
            }
            else {
                System.out.println("Invalid Input Please Follow Given Instructions");
            }

        }

    }
    //Method initialise
    private static void initialise(String [] initialise){
        for (int i = 0; i < initialise.length; i++){
            initialise[i] = "Booth Empty";
        }

    }
    //Method to validate and view booth details
    public static void viewBooth(String[] vaccineBooth){
        for (int i = 0; i < vaccineBooth.length; ++i){
            if (vaccineBooth[i].equals("Booth Empty")){
                System.out.println("Booth " + i + " is Empty");
            }
            else {
                System.out.println("Booth " + i + " Already Occupied By " + vaccineBooth[i]);
            }


        }
    }
    //Method to validate and view empty booths
    public static void emptyBooths(String[] emptyBooth){
        int count = 0;
        for (int i = 0; i < emptyBooth.length; ++i){
            if (emptyBooth[i].equals("Booth Empty")){
                System.out.println(i + " - " + emptyBooth[i]);
                count = count + 1;
            }
        }
        if (count == 0){
            System.out.println("No Empty Booths Available!");
        }


    }
    //Method to validated user inputs and add patients to booth
    public static void addBooth(String[] addBooth){
        boolean innerLoop = true;
        int count = 0;
        while (innerLoop){
            //check for empty booths before add
            for (int i = 0; i < addBooth.length; ++i){
                if (!addBooth[i].equals("Booth Empty")){
                    count = count + 1;
                }
            }
            if (count == 6){
                System.out.println("No Empty Booths Available!");
                innerLoop = false;
            }
            //if empty booths available
            else {
                //calling method to take and validate user input
                String name = validateNames("firstName");
                //calling method to take and validate booth number
                int boothNum = validateBoothNum();
                if (boothNum == 6){
                    System.out.println("Exited!");
                    innerLoop = false;
                }
                //checking for selected booth is empty?
                else if (!addBooth[boothNum].equals("Booth Empty")){
                    System.out.println("Booth Already Occupied");
                    count = 0;
                }
                //add patient to booth
                else {
                    addBooth[boothNum] = name;
                    System.out.println(name + " Added to Booth " + boothNum);
                    vaccineCount = vaccineCount - 1;
                    innerLoop = false;
                }
            }
        }



    }
    //Method to validate and remove patient from booth
    public static void removeFromBooth(String[] removeFromBooth) {
        Scanner r = new Scanner(System.in);
        boolean innerRemove = true;
        int count = 0;
        while (innerRemove){
            //checking for any booth filled with patients
            for (int i = 0; i < removeFromBooth.length; ++i){
                if (removeFromBooth[i].equals("Booth Empty")){
                    count = count + 1;
                }
            }
            if (count == 6){
                System.out.println("All Booths Are Empty No Patients to Remove");
                innerRemove = false;
            }
            //if at least one booth not empty
            else {
                //calling method to take and validate booth number to remove
                int patient = validateBoothNum();
                if (patient == 6){
                    System.out.println("Exited!");
                    innerRemove = false;
                }
                //if selected booth already empty
                else if (removeFromBooth[patient].equals("Booth Empty")){
                    System.out.println("Booth Already Empty!");
                    count = 0;
                }
                //removing and update details
                else {
                    System.out.println(removeFromBooth[patient] + " Removed From Booth " + patient);
                    removeFromBooth[patient] = "Booth Empty";
                    innerRemove = false;
                }
            }
        }
    }
    //Method to sort and data in alphabetical order
    public static void sortBooth(String[] sortBooth){
        int count = 0;
        //checking is there least one patient to view
        for (int k = 0; k < sortBooth.length; k++){
            if (sortBooth[k].equals("Booth Empty")){
                count = count + 1;
            }
        }
        if (count == 6){
            System.out.println("No Patients to View");
        }
        else {
            //copy data to an new array to preventing changing data in original array
            String[] sorting = Arrays.copyOfRange(sortBooth, 0, sortBooth.length);
            int j = 0;
            String temp;
            for (int i = 0; i < sorting.length; i++){
                for (j = i + 1; j < sorting.length -1; ++j){
                    if(sorting[i].compareTo(sorting[j]) > 0){
                        temp=sorting[i];
                        sorting[i]=sorting[j];
                        sorting[j]=temp;
                    }
                }
            }
            //check for only patients names to print
            for(int k=0;k<sorting.length;k++){
                if (!sorting[k].equals("Booth Empty")){
                    System.out.println(sorting[k]);
                }

            }
        }

    }
    //Method to store booth details in a text file
    public static void storeData(String [] storeData){

        try {
            FileWriter myFile = new FileWriter("Task1.txt");
            for(int x =0 ; x < storeData.length; x++){
                myFile.write(" -------------------------- Booth Details --------------------------" + "\n");
                myFile.write("Name : " + storeData[x] + "\n");
                myFile.write("Booth Number: " + x + "\n");
                myFile.write("________________________________________________________________________________________" + "\n");
                myFile.write("\n\n ");
            }
            myFile.write("Remaining Vaccine Count: " + Integer.toString(vaccineCount));
            myFile.close();
            System.out.println("Data Successfully Stored! ");
        } catch (IOException e) {
            System.out.println("An Error Occurred");
            e.printStackTrace();
        }

    }
    //Method to read data from a text file
    public static void readData(){
        try {
            File myFile = new File("Task1.txt");
            Scanner readFile = new Scanner(myFile) ;
            while (readFile.hasNextLine()){
                String data = readFile.nextLine();
                System.out.println(data);
                System.out.println();
            }
            readFile.close();
            System.out.println("Data Successfully Loaded! ");
        } catch (IOException e) {
            System.out.println("An Error Occurred");
            e.printStackTrace();
        }
    }
    //Method to validate and add vaccines to the store
    public static void addVaccine(){
        boolean vaccineLoop = true;
        Scanner r = new Scanner(System.in);
        while (vaccineLoop){
            try {
                System.out.print("Adding Vaccine Count or (0) to Exit: ");
                String adder = r.nextLine().trim();
                int add = Integer.parseInt(adder);
                if (add == 0){
                    System.out.println("Exited!");
                    vaccineLoop = false;
                }
                else if (add > 0){
                    vaccineCount = vaccineCount + add;
                    System.out.println(add + " Vaccines Added To Store");
                    System.out.println("Total Vaccine Count: " + vaccineCount);
                    vaccineLoop = false;
                }
                else {
                    System.out.println("Invalid Entry/Count");
                }
            }
            catch (Exception e){
                System.out.println("Invalid Entry!");
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
