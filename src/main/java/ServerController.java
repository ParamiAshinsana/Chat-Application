import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import task.Server_Receive_Task;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController extends Application {

    static String message = "";
    public javafx.scene.control.Label serverNameLabel;
    @FXML
    private AnchorPane topRoot;

    @FXML
    private Label serverNameLbl;

    @FXML
    private JFXTextArea serverTextArea;

    @FXML
    private JFXTextField serverTextField;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;

    ServerSocket serverSocket;
    Socket socket;

    public static void main(String[] args) {
        launch(args);
    }

    public void initialize(){
        System.out.println("Server Controller");
        Server_Receive_Task serverReceiveTask = null;
        try {
            serverSocket=new ServerSocket(4005);
            serverTextArea.appendText("Server Started");
            socket = serverSocket.accept();
            serverTextArea.appendText("Client Accepted");
            //serverTextArea.setText("Client Accepted");
            dataInputStream=new DataInputStream(socket.getInputStream());
            dataOutputStream=new DataOutputStream(socket.getOutputStream());
            serverReceiveTask = new Server_Receive_Task(dataInputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        serverReceiveTask.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                serverTextArea.appendText("\nClient : " + t1);
                //serverTextArea.setText("\nClient : " + t1);
            }
        });

        new Thread(serverReceiveTask).start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/server_form.fxml"))));
        stage.show();
        stage.setTitle("Server Form");
    }

    public void serverSendBtnOnAction(ActionEvent actionEvent) throws IOException {
        dataOutputStream.writeUTF(serverTextField.getText().trim());
        dataOutputStream.flush();
        serverTextField.clear();
    }
}
