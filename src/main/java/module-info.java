module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires lombok;
    requires org.tinylog.api;
    requires org.hibernate.orm.core;
    requires mysql.connector.java;
    requires org.hibernate.commons.annotations;

    opens org.example to javafx.fxml;
    opens controllers to javafx.fxml,lombok, java.base;
    opens model to javafx.fxml , org.hibernate.orm.core;
    opens database to javafx.fxml , org.hibernate.orm.core;


    exports org.example;
}