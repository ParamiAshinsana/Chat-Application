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
        new Thread(()->{
            try {
                socket = new Socket("localhost",4005);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (!message.equals("finish")){
                    message = dataInputStream.readUTF();
                    clientTextArea.appendText("\nServer : "+message);
                    //clientTextArea.setText("\nServer : "+message);
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }).start();
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
