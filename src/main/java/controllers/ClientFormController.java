package controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import task.Client_Receive_Task;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicReference;

public class ClientFormController extends Application {
    public Label userNameLbl;
    public JFXTextArea clientTextArea;
    public JFXTextField clientTextField;
    public JFXButton clientSendBtn;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    Socket socket;
    static String message = "";

    public void initialize(){
        try {
            socket = new Socket("localhost",4005);
            //to get input from server
            dataInputStream = new DataInputStream(socket.getInputStream());
            //to send data to server
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            Client_Receive_Task ob = new Client_Receive_Task(dataInputStream);
            ob.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String oldvalue, String newValue) {
                    clientTextArea.appendText("\nServer : "+newValue);
                }
            });
            new Thread(ob).start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void setLabel(String userName) {
        userNameLbl.setText(userName+" 's Chat Room");
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    public void clientSendBtnOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(clientTextField.getText().trim());
        dataOutputStream.flush();
        clientTextField.clear();
    }
}
