package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ControllerTwo {
    //mapping scene builder items with variables via ID
    @FXML
    private TextArea printRec;
    @FXML
    private Button btnDone;
    @FXML
    private AnchorPane receiptBody;

    //stage of receipt
    Stage stage;

    //method to set data to receipt which will be called in controller.java
    @FXML
    public void printReceipt(String firstName,String lastName,String age,String city,String passId, String vaccine,String booth){
        //set date and time
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = date.format(formatDate);

        //set details inside receipt(TextArea)
        printRec.setText("First Name : " + firstName + "\n" + "\n" +
                "Last Name : " + lastName + "\n" +"\n" +
                "Age : " + age + "\n" +"\n" +
                "City : " + city + "\n" +"\n" +
                "ID/Passport : " + passId + "\n" +"\n" +
                "Vaccine Requested : " + vaccine + "\n" +"\n" +
                "Available Booth Number : " + booth + "\n" +"\n" +
                "-------------------------" + "\n" +"\n" +
                formattedDate);
    }
    //method to Done btn in receipt window
    public void buttonDone(ActionEvent event){
           stage = (Stage) receiptBody.getScene().getWindow();
           stage.close();
    }
}
