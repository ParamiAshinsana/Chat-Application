package task;

import javafx.concurrent.Task;

import java.io.DataInputStream;

public class Server_Receive_Task extends Task<String> {
    DataInputStream dataInputStream;

    public Server_Receive_Task(DataInputStream in){
        dataInputStream=in;
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
