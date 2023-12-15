package com.drakalhaklat.drakalh.game.bernard_adventure;

//import com.drakalhaklat.drakalh.library.library_javafx_serialisable.*;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;

public class VariableGlobal
{

    public static Stage stage;
    public static Pane pane;

    public static String stringButtonPrimary = "-fx-background-color: rgba(88, 88, 88, 0.9); " +
            "-fx-background-radius: 50px; " +
            "-fx-border-radius: 50px; " +
            "-fx-font-size: 20px; " +
            "-fx-text-fill: white; " +
            "-fx-border-color: white; " +
            "-fx-border-width: 2px; " +
            "-fx-border-style: solid; " +
            "-fx-padding: 10px; " +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0); " +
            "-fx-cursor: hand; " +
            "-fx-font-family: \"Arial\"; " +
            "-fx-font-weight: bold;";

    public static HashMap<String, Integer> listPaneObject = new HashMap<String, Integer>();
    public static HashMap<String, Node> listObjectEditor = new HashMap<String, Node>();

}
