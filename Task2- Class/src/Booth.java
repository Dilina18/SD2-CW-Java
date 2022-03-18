public class Booth {
    private String Name;
    private int Count = 150;

    //set constructor
    public Booth (){
        Name = "";
    }
    //method to change object name
    public void setName(String name) {
        Name = name;
    }
    //method to return object name
    public String getName() {
        return Name;
    }
    //method to update vaccine count
    public void adding(int count) {
        Count = Count + count;
    }
    //method to return vaccine count
    public int getCount() {
        return Count;
    }
}
