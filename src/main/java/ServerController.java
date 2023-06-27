import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

    public void initialize(){
        new Thread(()->{
            try {
                serverSocket = new ServerSocket(4005);
                serverTextArea.appendText("Client Started!");
                socket = serverSocket.accept();
                serverTextArea.appendText("Client Accepted!");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (!message.equals("finish")){
                    message = dataInputStream.readUTF();
                    serverTextArea.appendText("\nClient : "+message);
                }
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
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
