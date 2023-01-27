module com.example.scenebuildertask {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scenebuildertask to javafx.fxml;
    exports com.example.scenebuildertask;
}