module com.example.chat_applicationinpcoursework {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chat_applicationinpcoursework to javafx.fxml;
    exports com.example.chat_applicationinpcoursework;
}