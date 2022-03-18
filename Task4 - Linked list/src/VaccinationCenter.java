import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class VaccinationCenter {
    //create arrays of booth & patient objects
    Booth [] boothRef = new Booth[6];
    Patient [] patientRef = new Patient[6];;

    //import classes
    Booth myBooth = new Booth();
    Patient myPatient = new Patient();

    //create linked lists to store waiting list details
    LinkedList<String> waiting = new LinkedList<String>();
    LinkedList<String> waitingVaccine = new LinkedList<String>();
    LinkedList<String> wSurName = new LinkedList<String>();
    LinkedList<Integer> wAge = new LinkedList<Integer>();
    LinkedList<String> wCity = new LinkedList<String>();
    LinkedList<Integer> wPassID = new LinkedList<Integer>();

    Scanner x = new Scanner(System.in);

    //program to take user inputs and display data
    public void program() {
        //initialise objects in both Booth and Patient arrays
        for (int x = 0; x < boothRef.length; x++) {
            boothRef[x] = new Booth();
            patientRef[x] = new Patient();
        }
        //calling method to initialise booth object name to empty
        initialise(boothRef);
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
            System.out.print("Please Enter Respective Number: ");
            String number = x.nextLine().toLowerCase().trim();

            if (number.equals("100") || number.equals("vvb")) {
                viewBooth(boothRef,patientRef);
            } else if (number.equals("101") || number.equals("veb")) {
                emptyBooth(boothRef);
            } else if (number.equals("102") || number.equals("apb")) {
                addBooth(boothRef,patientRef);
            }
            else if (number.equals("103") || number.equals("rpb")) {
                removeBooth(boothRef,patientRef);
            }
            else if (number.equals("104") || number.equals("vps")) {
                sortBooth(boothRef,patientRef);
            }
            else if (number.equals("105") || number.equals("spd")) {
                storeData(boothRef,patientRef);
            }
            else if (number.equals("106") || number.equals("lpd")) {
                readData();
            }
            else if (number.equals("107") || number.equals("vrv")) {
                System.out.println("Remaining Vaccine Count: " + myBooth.getCount());
            }
            else if (number.equals("108") || number.equals("avs")) {
                addVaccine();
            }
            else if (number.equals("999") || number.equals("ext")) {
                System.out.println("Program Ended!");
                break;
            }
            else {
                System.out.println("Invalid Entry Please Follow The Given Instructions!");
            }


        }
    }
    //method initialise
    private static void initialise(Booth[] boothRef) {
        for (int x = 0; x < boothRef.length; x++) {
            boothRef[x].setName("Booth Empty");
        }
    }
    //method to view booth details
    private void viewBooth(Booth [] boothRef,Patient[] patientRef) {
        for (int x = 0; x < boothRef.length; x++) {
            if (boothRef[x].getName().equals("Booth Empty")) {
                System.out.println("Booth " + x + " is Empty");
            } else {
                System.out.println();
                System.out.println("Booth " + x + " Already Occupied By " + boothRef[x].getName());
                System.out.println("First Name : " + patientRef[x].getFirstName());
                System.out.println("SurName : " + patientRef[x].getSurName());
                System.out.println("Age : " + patientRef[x].getAge());
                System.out.println("City : " + patientRef[x].getCity());
                System.out.println("Passport No/ID : " + patientRef[x].getPassport());
                System.out.println("Vaccine : " + patientRef[x].getVaccine());
                System.out.println();
            }
        }
    }
    //method to view empty booths
    private void emptyBooth(Booth[] boothRef) {
        int count = 0;
        for (int x = 0; x < boothRef.length; x++) {
            if (boothRef[x].getName().equals("Booth Empty")) {
                System.out.println("Booth " + x + " is Empty");
                count = count + 1;
            }
        }
        if (count == 0){
            System.out.println("No Empty Booths Available");
        }
    }
    //method to add patients to booths
    public void addBooth(Booth [] boothRef, Patient[] patientRef){
        boolean adding = true;
        int count = 0;
        int count2 = 0;
        Scanner input = new Scanner(System.in);
        while (adding){
                try {
                    System.out.print("Please Request Your Vaccine AstraZeneca(0)/SinoPharm(1)/Pfizer(2): ");
                    String orderVaccine = input.nextLine().trim();
                    int order = Integer.parseInt(orderVaccine);
                    if (order >= 0 && order <= 2){
                        //checking for least one empty booth available to continue
                        for (int i = 0; i < boothRef.length; ++i){
                            if (patientRef[i].getVaccine().equals(myPatient.setRequest(order))){
                                count = count + 1;
                            }
                        }
                        //if all booths are full ask to add waiting list
                        if (count == 2){
                            boolean fullLoop = true;
                            while (fullLoop){
                                System.out.println("No Empty Booths Available for Requested Vaccine. ");
                                System.out.println("Would you like to stay little longer, we'll give you a room as soon as one of our guests leaves? ");
                                System.out.print("(1)Yes / (2)No : ");
                                String wait = input.next();
                                if (wait.equals("1")){
                                    //calling methods validating user inputs
                                    String fname = validateNames("firstName");
                                    String lname = validateNames("lastName");
                                    int age = ageAndID("age");
                                    String city = validateNames("city");
                                    int passID = ageAndID("passID");
                                    System.out.println(fname + " Added to The Waiting List");
                                    System.out.println("Requested Vaccine: " + myPatient.setRequest(order));
                                    waiting.add(fname);
                                    wSurName.add(lname);
                                    wAge.add(age);
                                    wCity.add(city);
                                    wPassID.add(passID);
                                    waitingVaccine.add(myPatient.setRequest(order));
                                    fullLoop = false;
                                    adding = false;
                                }
                                else if (wait.equals("2")){
                                    System.out.println("Thank You For Reaching Us Please Try Again Later!");
                                    fullLoop = false;
                                    adding = false;
                                }
                                else {
                                    System.out.println("Invalid Number!");
                                    count = 0;
                                }
                            }
                        }
                        //if empty booths available
                        else {
                            //calling methods validating user inputs
                            String fname = validateNames("firstName");
                            String lname = validateNames("lastName");
                            int age = ageAndID("age");
                            String city = validateNames("city");
                            int passID = ageAndID("passID");
                            int boothNum = validateRequest(order);
                            if (boothNum == 6){
                                System.out.println("Exited");
                                adding = false;
                            }
                            //if selected booth already occupied
                            else if (!boothRef[boothNum].getName().equals("Booth Empty")){
                                System.out.println("Booth Already Occupied By Someone!");
                                count = 0;
                            }
                            //if selected booth available add
                            else {
                                System.out.println("Booth No " + boothNum + " Occupied By " + fname);
                                System.out.println("Vaccine: "+ myPatient.setRequest(order));
                                boothRef[boothNum].setName(fname);
                                patientRef[boothNum].setFirstName(fname);
                                patientRef[boothNum].setSurName(lname);
                                patientRef[boothNum].setAge(age);
                                patientRef[boothNum].setCity(city);
                                patientRef[boothNum].setPassport(passID);
                                patientRef[boothNum].setVaccine(myPatient.setRequest(order));
                                myBooth.adding(-1);
                                adding = false;
                            }
                        }
                    }
                    else {
                        System.out.println("Invalid Request!");
                        count = 0;
                    }
                }
                catch (Exception e){
                    System.out.println("Invalid Data type!");
                }
            }
        }
    //method to remove patients
    public void removeBooth(Booth [] boothRef,Patient[] patientRef){
        int count = 0;
        int count2 = 0;
        boolean removing = true;
        Scanner rmv = new Scanner(System.in);
        while (removing){
            //checking for least one empty booth to remove
            for (int x = 0; x < boothRef.length; ++x){
                if (boothRef[x].getName().equals("Booth Empty")){
                    count = count + 1;
                }
            }
            if (count == 6){
                System.out.println("All Booths Are Empty No Patients To Remove");
                removing = false;
            }
            //if there least one not empty booth
            else {
                //calling method to validate booth number input
                int remove = validateBoothNum();
                if (remove == 6){
                    System.out.println("Exited");
                    removing = false;
                }
                else {
                    //if selected booth already empty
                    if (boothRef[remove].getName().equals("Booth Empty")){
                        System.out.println("Booth Already Empty ");
                        count = 0;
                    }
                    //checking if there is any patient in waiting list has req the same vaccine used by removing patient
                    else {
                        for (int j = 0;j < waiting.size(); ++j){
                            if (patientRef[remove].getVaccine().equals(waitingVaccine.get(j))){
                                count2 = count2 + 1;
                            }
                        }
                        //if there matching person in waiting list remove and add from waiting list
                        if (count2 > 0){
                            for (int k = 0; k < waitingVaccine.size(); ++k){
                                if (patientRef[remove].getVaccine().equals(waitingVaccine.get(k))){
                                    System.out.println("Patient " + boothRef[remove].getName() + " Removed From Booth " + remove );
                                    System.out.println("Vaccine Used: " + patientRef[remove].getVaccine());
                                    System.out.println();
                                    System.out.println("Patient " + waiting.get(k) + " Added to Booth " + remove );
                                    System.out.println("Vaccine Requested: " + patientRef[remove].getVaccine());

                                    boothRef[remove].setName(waiting.get(k));
                                    patientRef[remove].setFirstName(waiting.get(k));
                                    patientRef[remove].setSurName(wSurName.get(k));
                                    patientRef[remove].setAge(wAge.get(k));
                                    patientRef[remove].setCity(wCity.get(k));
                                    patientRef[remove].setPassport(wPassID.get(k));
                                    patientRef[remove].setVaccine(waitingVaccine.get(k));
                                    myBooth.adding(-1);

                                    waiting.remove(k);
                                    wSurName.remove(k);
                                    wAge.remove(k);
                                    wCity.remove(k);
                                    wPassID.remove(k);
                                    waitingVaccine.remove(k);
                                    removing = false;
                                    break;
                                }
                            }
                        }
                        // if there is no matching only remove
                        else if (count2 == 0){
                            System.out.println("Patient " + boothRef[remove].getName() + " Removed From Booth " + remove );
                            System.out.println("Vaccine Used: " + patientRef[remove].getVaccine());
                            boothRef[remove].setName("Booth Empty");
                            patientRef[remove].setFirstName("");
                            patientRef[remove].setSurName("");
                            patientRef[remove].setAge(0);
                            patientRef[remove].setCity("");
                            patientRef[remove].setPassport(0);
                            patientRef[remove].setVaccine("");
                            removing = false;
                        }
                    }
                }
            }
        }
    }
    //method to sort booth data in alphabetical order
    private static void sortBooth(Booth [] boothRef, Patient[] patientRef){
        //copy data to a new array to preventing changing data in original array
        String [] sorting = new String[boothRef.length];
        for (int i = 0; i < boothRef.length; i++) {
            sorting[i] = patientRef[i].getFirstName() + " " + patientRef[i].getSurName();
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
        //print sorted names
        int count = 0;
        for (int i = 0; i < sorting.length; i++){
            if(!sorting[i].equals("Booth Empty") && !sorting[i].equals(" ")) {
                System.out.println(sorting[i] );
                count = count + 1;
            }
        }
        if (count == 0){
            System.out.println("No patients to view");
        }
    }
    //method to store booth details in a text file
    private void storeData(Booth [] boothRef,Patient[] patientRef){
        try{
            FileWriter myFile = new FileWriter("TaskFourClass.txt");
            for(int x =0 ; x < boothRef.length; x++){
                myFile.write(" -------------------------- Booth Details --------------------------" + "\n");
                myFile.write("Name : " + boothRef[x].getName() + "\n");
                myFile.write("Booth Number: " + x + "\n");
                myFile.write("      First Name : " + patientRef[x].getFirstName() + "\n" + "      SurName : " + patientRef[x].getSurName()+ "\n" + "      Vaccine : " + patientRef[x].getVaccine() + "\n");
                myFile.write("      Age : " + patientRef[x].getAge() + "\n" + "      City : " + patientRef[x].getCity()+ "\n" + "      Passport/ID : " + patientRef[x].getPassport() + "\n");
                myFile.write("________________________________________________________________________________________" + "\n");
                myFile.write("\n\n ");
            }
            myFile.write("Remaining Vaccine Count: " + Integer.toString(myBooth.getCount()));
            myFile.write("________________________________________________________________________________________" + "\n");
            myFile.write("_________________________________WAITING LIST_________________________________" + "\n");
            for (int k = 0; k < waiting.size(); k++){
                myFile.write("Name : " + waiting.get(k) + "\n");
                myFile.write("      First Name : " + waiting.get(k) + "\n" + "      SurName : " + wSurName.get(k)+ "\n" + "      Vaccine : " + waitingVaccine.get(k) + "\n");
                myFile.write("      Age : " + wAge.get(k) + "\n" + "      City : " + wCity.get(k)+ "\n" + "      Passport/ID : " + wPassID.get(k) + "\n");
                myFile.write("________________________________________________________________________________________" + "\n");
                myFile.write("\n\n ");
            }
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
            File myFile = new File("TaskFourClass.txt");
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()){
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("An Error Occurred.");
            e.printStackTrace();
        }
    }
    //method to add vaccine
    public void addVaccine(){
        boolean adding = true;
        Scanner adder = new Scanner(System.in);
        while (adding){
            try {
                System.out.println("Enter Adding Vaccine Count or 0 to exit: ");
                String addVaccine = adder.nextLine().trim();
                int add = Integer.parseInt(addVaccine);
                if (add == 0){
                    System.out.println("Exited");
                    adding = false;
                }
                else if (add > 0){
                    myBooth.adding(add);
                    System.out.println("Vaccines Added: " + add);
                    System.out.println("Total Count: " + myBooth.getCount());
                    adding = false;
                }
                else {
                    System.out.println("Invalid Entry!");
                }
            }
            catch (Exception e){
                System.out.println("Invalid data type!");
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
            else if (nameType.equals("city")) {
                System.out.print("Please Enter City Name: ");
                name = n.nextLine().trim();
            }
            if (name.length() > 0) {
                return name;
            } else {
                System.out.println("Invalid Entry!");
            }
        }
    }
    //method to validate pass ID & Age
    public static int ageAndID(String type){
        Scanner r = new Scanner(System.in);
        int ageID;
        while (true){
            try {
                if (type.equals("age")){
                    System.out.print("Please Enter Your Age: ");
                    String myAge = r.nextLine().trim();
                    ageID = Integer.parseInt(myAge);
                    return ageID;
                }
                else if (type.equals("passID")){
                    System.out.print("Please Enter Your ID: ");
                    String myAge = r.nextLine().trim();
                    ageID = Integer.parseInt(myAge);
                    return ageID;
                }
            }
            catch (Exception e){
                System.out.println("Invalid Data Type!");
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
