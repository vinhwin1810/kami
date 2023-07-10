module com.example.kami {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    //requires mongodb;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires junit;

    opens com.example.kami to javafx.fxml;
    exports com.example.kami;
    exports com.example.kami.View;
    opens com.example.kami.View to javafx.fxml;
}