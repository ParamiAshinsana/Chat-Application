package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ClientFormController {
    public Label userNameLbl;

    public void setLabel(String userName) {
        userNameLbl.setText(userName+" 's Chat Room");
    }
}
