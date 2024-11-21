module org.example.lr4model {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.lr4model to javafx.fxml;
    exports org.example.lr4model;
}