module eus.ehu.sharetrip {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.hibernate.orm.core;
    requires org.apache.logging.log4j;
    requires jakarta.persistence;

    opens eus.ehu.shareTrip.domain to javafx.base, org.hibernate.orm.core;
    opens eus.ehu.shareTrip.ui to javafx.fxml;
    opens eus.ehu.shareTrip.uicontrollers to javafx.fxml;
    exports eus.ehu.shareTrip.ui;
}
