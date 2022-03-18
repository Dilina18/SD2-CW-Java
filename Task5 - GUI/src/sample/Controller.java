package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class Controller {
    //mapping variables via ID by importing scene builder items
    @FXML
    public TextField firstname, lastname, age, city, pass, booth;
    @FXML
    public Circle ageIndicator, idIndicator;
    @FXML
    public RadioButton astra, sino, pfizer;
    @FXML
    public Button check, reset, receipt;
    @FXML
    public String vaccine = "";
    @FXML
    String names = "^[a-zA-Z]{3,15}$";
    @FXML
    String id = "^[0-9]{10,12}$";
    @FXML
    String bank = "^[0-9]{1,3}$";

    //create new stage, scene and root to show receipt
    private Stage stage;
    private Scene scene;
    private Parent root;

    //method to update vaccine when radio btn selected
    @FXML
    public void radioGroup(){
        ToggleGroup group = new ToggleGroup();
        astra.setToggleGroup(group);
        sino.setToggleGroup(group);
        pfizer.setToggleGroup(group);

        if (astra.isSelected()){
            vaccine = "AstraZeneca";
        }
        else if (sino.isSelected()){
            vaccine = "SinoPharm";
        }
        else if (pfizer.isSelected()){
            vaccine = "Pfizer";
        }
        }

    //method to validate check button to get booth number
    @FXML
    public void checkButton(ActionEvent e){
        int count = 0;
        //check for least one radio btn selected
        for (int i = 0; i < 3; ++i){
            if (astra.isSelected() || sino.isSelected() || pfizer.isSelected()){
                count = count + 1;
            }
        }
        //checking for input fields are empty
        if (firstname.getText().trim().isEmpty() || lastname.getText().trim().isEmpty() || age.getText().trim().isEmpty() ||
                city.getText().trim().isEmpty() || pass.getText().trim().isEmpty() || count == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing!");
            alert.setHeaderText("Some Details Are Missing !");
            alert.setContentText("Please Fill All The Above Details To Get A Booth Number");
            alert.showAndWait();
        }
        //checking names match to Java Regex
        else if (!firstname.getText().matches(names) || !lastname.getText().matches(names)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Name!");
            alert.setHeaderText("Please Enter Your Names Correctly(Ex - First Name: John, Last Name: Smith)");
            alert.setContentText("Please Fill All The Above Details Correctly To Get A Booth Number");
            alert.showAndWait();
        }
        //checking at least one vowel include
        else if (!firstname.getText().contains("a") && !firstname.getText().contains("e") && !firstname.getText().contains("i")&&
                !firstname.getText().contains("o")&&!firstname.getText().contains("u") || !lastname.getText().contains("a") && !lastname.getText().contains("e") &&
                !lastname.getText().contains("i")&&
                !lastname.getText().contains("o")&&!lastname.getText().contains("u")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Name!");
            alert.setHeaderText("Missing Vowels(a,e,i,o,u)");
            alert.setContentText("Please Fill All The Above Details Correctly To Get A Booth Number");
            alert.showAndWait();
        }
        //check age match to Java Regex
        else if (!age.getText().matches(bank)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Syntax!");
            alert.setHeaderText("Enter Age Correctly(Ex - 18/20...)");
            alert.setContentText("Please Fill All The Above Details Correctly To Get A Booth Number");
            alert.showAndWait();
        }
        //check city match to Java Regex
        else if (!city.getText().matches(names)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid City!");
            alert.setHeaderText("Enter Standard Existing City(Ex - Colombo");
            alert.setContentText("Please Fill All The Above Details Correctly To Get A Booth Number");
            alert.showAndWait();
        }
        //check ID match to Java Regex
        else if (!pass.getText().matches(id)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid ID!");
            alert.setHeaderText("ID/Passport Should Contain 10-12 Numbers");
            alert.setContentText("Please Fill All The Above Details Correctly To Get A Booth Number");
            alert.showAndWait();
        }
        //if all details filled correctly get a booth number
        else {
            int randomNum = (int)(Math.random() * 6);
            booth.setText(Integer.toString(randomNum));

            firstname.setEditable(false);
            lastname.setEditable(false);
            age.setEditable(false);
            city.setEditable(false);
            pass.setEditable(false);
            check.setDisable(true);
            astra.setDisable(true);
            sino.setDisable(true);
            pfizer.setDisable(true);
            reset.setText("EDIT");
        }
    }

    //method to validate light indicator Age
    @FXML
    public void indicatorAge(KeyEvent event){
        if (age.getText().length() > 0){
            if (age.getText().matches(bank)){
                ageIndicator.setFill(Color.LIGHTGREEN);
            }
            else{
                ageIndicator.setFill(Color.RED);
            }
        }
        else {
            ageIndicator.setFill(Color.GREY);
        }
    }

    //method to validate light indicator ID
    @FXML
    public void indicatorID(KeyEvent event){
        if (pass.getText().length() > 0){
            if (pass.getText().matches(id)){
                idIndicator.setFill(Color.LIGHTGREEN);
            }
            else{
                idIndicator.setFill(Color.RED);
            }
        }
        else {
            idIndicator.setFill(Color.GREY);
        }
    }

    //method to validate reset btn
    @FXML
    public void resetEdit(ActionEvent e){
        //if check btn already clicked set all field to editable to edit again
        if (check.isDisable()){
            firstname.setEditable(true);
            lastname.setEditable(true);
            age.setEditable(true);
            city.setEditable(true);
            pass.setEditable(true);
            check.setDisable(false);
            astra.setDisable(false);
            sino.setDisable(false);
            pfizer.setDisable(false);
            booth.setText("");
            reset.setText("RESET");
        }
        //if still filling if user want to reset
        else {
            firstname.setText("");
            lastname.setText("");
            age.setText("");
            city.setText("");
            pass.setText("");
            booth.setText("");
            ageIndicator.setFill(Color.GREY);
            idIndicator.setFill(Color.GREY);
            astra.setSelected(false);
            pfizer.setSelected(false);
            sino.setSelected(false);
            vaccine = "";
        }
    }

    //method to generate receipt
    @FXML
    public void generateReceipt(ActionEvent e) throws IOException {
        //checking for user has got a booth number by filling all the details
        if (booth.getText().length() <= 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Details!");
            alert.setHeaderText("Please Get A Booth Number To Continue");
            alert.setContentText("Please Fill All The Above Details Correctly To Get The Receipt");
            alert.showAndWait();
        }
        //if filled correctly store input field values to variables
        else {
            String fname = firstname.getText();
            String lname = lastname.getText();
            String old = age.getText();
            String cityOf = city.getText();
            String id = pass.getText();
            String reqVac = vaccine;
            String boothOf = booth.getText();


            //if all the details filled correctly ask your to confirm to continue
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure To Confirm?", ButtonType.YES, ButtonType.NO);
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);

            //if user confirmed to continue
            if (ButtonType.YES.equals(result)){

                //set fxml loader to pass data to generate.fxml file and load it
                FXMLLoader loader = new FXMLLoader(getClass().getResource("generate.fxml"));
                root = loader.load();

                //import controller2 and call print receipt method and passing data
                ControllerTwo controllerTwo = loader.getController();
                controllerTwo.printReceipt(fname,lname,old,cityOf,id,reqVac,boothOf);


                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                Image icon = new Image(new File("icon2.png").toURI().toString());
                stage.getIcons().add(icon);
                stage.setScene(scene);
                stage.setTitle("Thank You!");
                stage.show();

            }
            //if user not confirmed consume
            if (ButtonType.NO.equals(result)) {
                e.consume();
            }
        }
    }
}
