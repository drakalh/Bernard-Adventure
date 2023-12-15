module com.drakalhaklat.drakalh.game.bernard_adventure {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires javafx.media;
    //requires com.drakalhaklat.drakalh.library_javafx_serialisable;

    opens com.drakalhaklat.drakalh.game.bernard_adventure to javafx.fxml;
    exports com.drakalhaklat.drakalh.game.bernard_adventure;
}