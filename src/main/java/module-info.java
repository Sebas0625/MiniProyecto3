module project.miniproject3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens project.miniproject3.controller to javafx.fxml;
    opens project.miniproject3 to javafx.fxml;
    exports project.miniproject3;
}