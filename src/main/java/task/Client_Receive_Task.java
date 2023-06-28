package task;

import javafx.concurrent.Task;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Client_Receive_Task extends Task<String> {
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream ;

    public Client_Receive_Task(DataOutputStream out){
        dataOutputStream = out ;
    }

    @Override
    protected String call() throws Exception {

        String message = "";
        while(!message.equals("finished")) {
            message = dataInputStream.readUTF();
            updateMessage(message);
        }
        return "";
    }
}
