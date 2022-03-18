public class Patient {
    private String FirstName;
    private String SurName;
    private int Age;
    private String City;
    private int Passport;
    private String Vaccine;
    private String Astra = "AstraZeneca";
    private String Sino = "Sinopharm";
    private String Pfizer = "Pfizer";

    //set constructor
    public Patient (){
        FirstName = "";
        SurName = "";
        Age = 0;
        City = "";
        Passport = 0;
        Vaccine = "";
    }
    //method to change first name of object
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    //method to change last name of object
    public void setSurName(String surName) {
        SurName = surName;
    }
    //method to change age of object
    public void setAge(int age) {
        Age = age;
    }
    //method to change city name of object
    public void setCity(String city) {
        City = city;
    }
    //method to change id of object
    public void setPassport(int passport){Passport = passport;}
    //method to change vaccine name of object
    public void setVaccine(String vaccine) {
        Vaccine = vaccine;
    }
    //method to get first name
    public String getFirstName() {
        return FirstName;
    }
    //method to get last name
    public String getSurName() {
        return SurName;
    }
    //method to get age
    public int getAge() {
        return Age;
    }
    //method to get city name
    public String getCity() {
        return City;
    }
    //method to get id
    public int getPassport(){return Passport;}
    //method to get vaccine name
    public String getVaccine() {
        return Vaccine;
    }
    //method to validate vaccine req
    public String setRequest(int req){
        if (req == 0){
            return Astra;
        }
        else if (req == 1){
            return Sino;
        }
        else {
            return Pfizer;
        }
    }


}
