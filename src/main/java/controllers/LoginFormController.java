package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public AnchorPane rootLogin;
    public JFXTextField loginTxtField;
    public JFXButton btnLogin;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = loginTxtField.getText();

        FXMLLoader loader= new FXMLLoader(getClass().getResource("/view/client_form.fxml "));
        AnchorPane anchorPane = loader.load();
        ClientFormController clientFormController = loader.getController();
        clientFormController.setLabel(userName);
        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        loginTxtField.setText("");
    }
}
