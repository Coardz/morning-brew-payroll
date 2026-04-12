module com.morningbrew {
    requires javafx.controls;
    requires javafx.fxml;

    requires transitive javafx.graphics;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.materialdesign2;


    opens com.morningbrew.Controller to javafx.fxml;
    exports com.morningbrew;
}
