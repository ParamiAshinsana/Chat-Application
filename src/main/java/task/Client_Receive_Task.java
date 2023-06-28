package task;

import javafx.concurrent.Task;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class Client_Receive_Task extends Task<String> {
    DataInputStream dataInputStream;

    public Client_Receive_Task(DataInputStream in){
        dataInputStream = in ;
    }

    @Override
    protected String call() throws Exception {

        String message = "";
        while(!message.equals("finished")) {
            message = dataInputStream.readUTF();
            updateValue(message);
        }
        return "";
    }
}
