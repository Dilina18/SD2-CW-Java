public class Booth {
    private String Name;
    private int Count = 150;

    //set constructor
    public Booth (){
        Name = "";
    }
    //method to set object name
    public void setName(String name) {
        Name = name;
    }
    //method to get object name
    public String getName() {
        return Name;
    }
    //method to update vaccine count
    public void adding(int count) {
        Count = Count + count;
    }
    //method to get vaccine count
    public int getCount() {
        return Count;
    }
}
