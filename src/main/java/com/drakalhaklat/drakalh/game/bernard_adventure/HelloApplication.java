package com.drakalhaklat.drakalh.game.bernard_adventure;

//import com.drakalhaklat.drakalh.library.library_javafx_serialisable.*;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static com.drakalhaklat.drakalh.game.bernard_adventure.VariableGlobal.*;

public class HelloApplication extends Application {


    @Override
    public void start(javafx.stage.Stage stage) throws IOException {
        pane = new Pane();
        pane.setPrefSize(1920, 1080);
        Scene scene = new Scene(pane, 1920, 1080);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
        VariableGlobal.stage = stage;
        intro();
    }

    public static void intro()
    {
        pane.getChildren().clear();
        pane.setStyle("-fx-background-color: black;");
        ImageView imageView = new ImageView(new Image(HelloApplication.class.getResourceAsStream("image/flag.png")));
        imageView.setFitHeight(1080);
        imageView.setFitWidth(1920);
        pane.getChildren().add(imageView);
        listPaneObject.put("intro", pane.getChildren().size() - 1);
        Transition transition = new Transition() {
            {
                setCycleDuration(javafx.util.Duration.seconds(4));
            }
            @Override
            protected void interpolate(double frac) {
                imageView.setOpacity(1.0 - frac);
            }
        };
        imageView.setOnMouseClicked(event -> {
            transition.stop();
            mainMenu();
        });
        imageView.setOnKeyPressed(event -> {
            transition.stop();
            mainMenu();
        });
        //creer une animation de disparition de l'image sur 4 secondes
        transition.setOnFinished(event -> {
            mainMenu();
        });
        transition.play();
    }

    public static void mainMenu()
    {
        if (listPaneObject.containsKey("videoMainMenu"))
        {
            for (int i = 1; i < pane.getChildren().size(); i++)
            {
                pane.getChildren().remove(i);
                listPaneObject.remove(i);
            }
        }
        else
        {
            pane.getChildren().clear();
            listPaneObject.clear();
            Media media = new Media(HelloApplication.class.getResource("video/test.mp4").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setCycleCount(Animation.INDEFINITE);
            mediaPlayer.setVolume(0);
            mediaPlayer.play();
            MediaView mediaView = new MediaView(mediaPlayer);
            mediaView.setFitHeight(1080);
            mediaView.setFitWidth(1920);
            pane.getChildren().add(mediaView);
            listPaneObject.put("videoMainMenu", pane.getChildren().size() - 1);
        }
        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.prefWidthProperty().bind(pane.widthProperty().divide(3));
        menu.prefHeightProperty().bind(pane.heightProperty().divide(1.4));
        menu.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(menu.widthProperty().divide(2)));
        menu.layoutYProperty().bind(pane.heightProperty().divide(2).subtract(menu.heightProperty().divide(2)));
        menu.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
        Label label = new Label("Bernard Adventure");
        label.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
        Button buttonPlay = new Button("Play");
        buttonPlay.setStyle(stringButtonPrimary);
        buttonPlay.setOnAction(event -> playMenu());
        Button buttonEditor = new Button("Editor");
        buttonEditor.setStyle(stringButtonPrimary);
        buttonEditor.setOnAction(event -> editorMenu());
        Button buttonSettings = new Button("Settings");
        buttonSettings.setStyle(stringButtonPrimary);
        buttonSettings.setOnAction(event -> settingsMenu());
        Button buttonExit = new Button("Exit");
        buttonExit.setStyle(stringButtonPrimary);
        buttonExit.setOnAction(event -> System.exit(0));
        menu.getChildren().addAll(label, buttonPlay, buttonEditor, buttonSettings, buttonExit);
        menu.spacingProperty().bind(menu.heightProperty().divide(menu.getChildren().size() + 2));
        pane.getChildren().add(menu);
        listPaneObject.put("menuMainMenu", pane.getChildren().size() - 1);
    }

    public static void settingsMenu() {
    }

    public static void editorMenu()
    {
        for (int i = 1; i < pane.getChildren().size(); i++)
        {
            pane.getChildren().remove(i);
            listPaneObject.remove(i);
        }
        pane.setStyle("-fx-background-color: black;");
        VBox menu = new VBox();
        menu.setAlignment(Pos.CENTER);
        menu.prefWidthProperty().bind(pane.widthProperty().divide(3));
        menu.prefHeightProperty().bind(pane.heightProperty().divide(1.4));
        menu.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(menu.widthProperty().divide(2)));
        menu.layoutYProperty().bind(pane.heightProperty().divide(2).subtract(menu.heightProperty().divide(2)));
        menu.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
        Label label = new Label("Editor");
        label.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
        Button buttonMap = new Button("Map");
        buttonMap.setStyle(stringButtonPrimary);
        buttonMap.setOnAction(event -> {
            mapEditor();
        });
        Button buttonObject = new Button("Object");
        buttonObject.setStyle(stringButtonPrimary);
        buttonObject.setOnAction(event -> {
            objectEditor();
        });
        Button buttonBack = new Button("Back");
        buttonBack.setStyle(stringButtonPrimary);
        buttonBack.setOnAction(event -> {
            mainMenu();
        });
        menu.getChildren().addAll(label, buttonMap, buttonObject, buttonBack);
        menu.spacingProperty().bind(menu.heightProperty().divide(menu.getChildren().size() + 2));
        pane.getChildren().add(menu);
        listPaneObject.put("menuEditorMenu", pane.getChildren().size() - 1);
    }

    public static void mapEditor() {
    }

    public static void objectEditor()
    {
        pane.getChildren().clear();
        pane.setStyle("-fx-background-color: black;");
        Pane paneObject = new Pane();
        paneObject.prefWidthProperty().bind(pane.widthProperty());
        paneObject.prefHeightProperty().bind(pane.heightProperty());
        paneObject.setStyle("-fx-background-color: black;");
        pane.getChildren().add(paneObject);
        listPaneObject.put("paneObjectEditor", pane.getChildren().size() - 1);
        AtomicInteger x = new AtomicInteger();
        AtomicInteger y = new AtomicInteger();
        VBox menuObject = new VBox();
        menuObject.prefWidthProperty().bind(pane.widthProperty().divide(5));
        menuObject.prefHeightProperty().bind(pane.heightProperty());
        menuObject.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
        menuObject.setAlignment(Pos.TOP_CENTER);
        menuObject.setSpacing(10);
        VBox menuListObject = new VBox();
        menuListObject.prefWidthProperty().bind(pane.widthProperty().divide(5));
        menuListObject.prefHeightProperty().bind(pane.heightProperty());
        menuListObject.layoutXProperty().bind(pane.widthProperty().subtract(menuListObject.widthProperty()));
        menuListObject.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
        menuListObject.setAlignment(Pos.TOP_CENTER);
        menuListObject.setSpacing(10);

        Label label = new Label("Create");
        label.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll("Rectangle", "Circle", "Polygon");
        comboBox.getSelectionModel().selectFirst();
        comboBox.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        Button buttonCreate = new Button("Create");
        buttonCreate.setStyle(stringButtonPrimary);

        AtomicInteger nbPoint = new AtomicInteger();
        ArrayList<Point> listPoint = new ArrayList<>();
        ArrayList<Shape> listShape = new ArrayList<>();
        ArrayList<Shape> listShapePoint = new ArrayList<>();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.prefWidthProperty().bind(menuListObject.widthProperty());
        scrollPane.prefHeightProperty().bind(menuListObject.heightProperty().divide(6));
        scrollPane.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        menuListObject.getChildren().add(scrollPane);

        VBox vBox = new VBox();
        vBox.prefWidthProperty().bind(scrollPane.widthProperty());
        vBox.prefHeightProperty().bind(scrollPane.heightProperty());
        vBox.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
        scrollPane.setContent(vBox);
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);


        buttonCreate.setOnAction(event -> {
            paneObject.setCursor(Cursor.HAND);
            if (comboBox.getSelectionModel().getSelectedItem().equals("Rectangle"))
            {
                nbPoint.set(4);
                listPoint.clear();
                listShapePoint.clear();
                paneObject.setOnMouseClicked(event1 -> {
                    if (nbPoint.get() > 0)
                    {
                        createPoint(paneObject, nbPoint, listPoint, listShapePoint, event1);
                        if(nbPoint.get() == 0)
                        {
                            paneObject.setOnMouseClicked(null);
                            paneObject.setCursor(Cursor.DEFAULT);
                            Rectangle rectangle = new Rectangle(listPoint.get(0).getX(), listPoint.get(0).getY(), listPoint.get(1).getX() - listPoint.get(0).getX(), listPoint.get(2).getY() - listPoint.get(0).getY());
                            rectangle.setStyle("-fx-fill: white;");
                            listShape.add(rectangle);
                            paneObject.getChildren().add(rectangle);
                            Label label1 = new Label("Rectangle");
                            vBox.getChildren().add(label1);
                            label1.setOnMouseClicked(event2 -> {
                                if(event2.getButton().equals(MouseButton.PRIMARY))
                                {
                                    if(rectangle.getStroke() == null)
                                    {
                                        rectangle.setStroke(javafx.scene.paint.Color.RED);
                                        rectangle.setStrokeWidth(5);
                                    }
                                    else
                                    {
                                        rectangle.setStroke(null);
                                    }
                                }
                                if(event2.getButton().equals(MouseButton.SECONDARY))
                                {
                                    Pane paneDelete = new Pane();
                                    paneDelete.prefWidthProperty().bind(pane.widthProperty().divide(3));
                                    paneDelete.prefHeightProperty().bind(pane.heightProperty().divide(3));
                                    paneDelete.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(paneDelete.widthProperty().divide(2)));
                                    paneDelete.layoutYProperty().bind(pane.heightProperty().divide(2).subtract(paneDelete.heightProperty().divide(2)));
                                    paneDelete.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
                                    pane.getChildren().add(paneDelete);
                                    listPaneObject.put("paneDelete", pane.getChildren().size() - 1);
                                    Label labelDelete = new Label("Are you sure you want to delete this object ?");
                                    labelDelete.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
                                    Button buttonYes = new Button("Yes");
                                    buttonYes.setStyle(stringButtonPrimary);
                                    buttonYes.setOnAction(event3 -> {
                                        paneObject.getChildren().remove(rectangle);
                                        listShape.remove(rectangle);
                                        vBox.getChildren().remove(label1);
                                        pane.getChildren().remove(paneDelete);
                                        listPaneObject.remove("paneDelete");
                                    });
                                    Button buttonNo = new Button("No");
                                    buttonNo.setStyle(stringButtonPrimary);
                                    buttonNo.setOnAction(event3 -> {
                                        pane.getChildren().remove(paneDelete);
                                        listPaneObject.remove("paneDelete");
                                    });
                                    VBox vBoxDelete = new VBox();
                                    vBoxDelete.prefWidthProperty().bind(paneDelete.widthProperty());
                                    vBoxDelete.prefHeightProperty().bind(paneDelete.heightProperty());
                                    vBoxDelete.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
                                    vBoxDelete.setAlignment(Pos.CENTER);
                                    vBoxDelete.setSpacing(10);
                                    vBoxDelete.getChildren().addAll(labelDelete, buttonYes, buttonNo);
                                    paneDelete.getChildren().add(vBoxDelete);
                                    stage.getScene().setOnKeyPressed(event4 -> {
                                        if (event4.getCode().toString().equals("ESCAPE"))
                                        {
                                            pane.getChildren().remove(paneDelete);
                                            listPaneObject.remove("paneDelete");
                                        }
                                    });
                                }
                            });
                            for (Shape shape : listShapePoint)
                            {
                                paneObject.getChildren().remove(shape);
                            }
                            listShapePoint.clear();
                        }
                    }
                });
            }
            if (comboBox.getSelectionModel().getSelectedItem().equals("Circle"))
            {
                nbPoint.set(2);
                listPoint.clear();
                listShapePoint.clear();
                paneObject.setOnMouseClicked(event1 -> {
                    if (nbPoint.get() > 0)
                    {
                        createPoint(paneObject, nbPoint, listPoint, listShapePoint, event1);
                        if(nbPoint.get() == 0)
                        {
                            paneObject.setOnMouseClicked(null);
                            paneObject.setCursor(Cursor.DEFAULT);
                            //the first point is the center of the circle and the second point is a radius
                            Circle circle2 = new Circle(listPoint.get(0).getX(), listPoint.get(0).getY(), Math.sqrt(Math.pow(listPoint.get(1).getX() - listPoint.get(0).getX(), 2) + Math.pow(listPoint.get(1).getY() - listPoint.get(0).getY(), 2)));
                            circle2.setStyle("-fx-fill: white;");
                            listShape.add(circle2);
                            paneObject.getChildren().add(circle2);
                            Label label1 = new Label("Circle");
                            vBox.getChildren().add(label1);
                            label1.setOnMouseClicked(event2 -> {
                                if(event2.getButton().equals(MouseButton.PRIMARY))
                                {
                                    if(circle2.getStroke() == null)
                                    {
                                        circle2.setStroke(javafx.scene.paint.Color.RED);
                                        circle2.setStrokeWidth(5);
                                    }
                                    else
                                    {
                                        circle2.setStroke(null);
                                    }
                                }
                                if(event2.getButton().equals(MouseButton.SECONDARY))
                                {
                                    Pane paneDelete = new Pane();
                                    paneDelete.prefWidthProperty().bind(pane.widthProperty().divide(3));
                                    paneDelete.prefHeightProperty().bind(pane.heightProperty().divide(3));
                                    paneDelete.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(paneDelete.widthProperty().divide(2)));
                                    paneDelete.layoutYProperty().bind(pane.heightProperty().divide(2).subtract(paneDelete.heightProperty().divide(2)));
                                    paneDelete.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
                                    pane.getChildren().add(paneDelete);
                                    listPaneObject.put("paneDelete", pane.getChildren().size() - 1);
                                    Label labelDelete = new Label("Are you sure you want to delete this object ?");
                                    labelDelete.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
                                    Button buttonYes = new Button("Yes");
                                    buttonYes.setStyle(stringButtonPrimary);
                                    buttonYes.setOnAction(event3 -> {
                                        paneObject.getChildren().remove(circle2);
                                        listShape.remove(circle2);
                                        vBox.getChildren().remove(label1);
                                        pane.getChildren().remove(paneDelete);
                                        listPaneObject.remove("paneDelete");
                                    });
                                    Button buttonNo = new Button("No");
                                    buttonNo.setStyle(stringButtonPrimary);
                                    buttonNo.setOnAction(event3 -> {
                                        pane.getChildren().remove(paneDelete);
                                        listPaneObject.remove("paneDelete");
                                    });
                                    VBox vBoxDelete = new VBox();
                                    vBoxDelete.prefWidthProperty().bind(paneDelete.widthProperty());
                                    vBoxDelete.prefHeightProperty().bind(paneDelete.heightProperty());
                                    vBoxDelete.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
                                    vBoxDelete.setAlignment(Pos.CENTER);
                                    vBoxDelete.setSpacing(10);
                                    vBoxDelete.getChildren().addAll(labelDelete, buttonYes, buttonNo);
                                    paneDelete.getChildren().add(vBoxDelete);
                                    stage.getScene().setOnKeyPressed(event4 -> {
                                        if (event4.getCode().toString().equals("ESCAPE"))
                                        {
                                            pane.getChildren().remove(paneDelete);
                                            listPaneObject.remove("paneDelete");
                                        }
                                    });
                                }
                            });
                            for (Shape shape : listShapePoint)
                            {
                                paneObject.getChildren().remove(shape);
                            }
                            listShapePoint.clear();
                        }
                    }
                });
            }
            if (comboBox.getSelectionModel().getSelectedItem().equals("Polygon"))
            {
                listPoint.clear();
                listShapePoint.clear();
                Button buttonFinish = new Button("Create Polygon");
                buttonFinish.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); " +
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
                        "-fx-font-weight: bold;");
                buttonFinish.setOnAction(event1 -> {
                    paneObject.setOnMouseClicked(null);
                    paneObject.setCursor(Cursor.DEFAULT);
                    ArrayList<Double> listDouble = new ArrayList<>();
                    for (Point point : listPoint)
                    {
                        listDouble.add(point.getX());
                        listDouble.add(point.getY());
                    }
                    Polygon polygon = new Polygon(listDouble.stream().mapToDouble(Double::doubleValue).toArray());
                    polygon.setStyle("-fx-fill: white;");
                    listShape.add(polygon);
                    paneObject.getChildren().add(polygon);
                    Label label1 = new Label("Polygon");
                    vBox.getChildren().add(label1);
                    label1.setOnMouseClicked(event2 -> {
                        if(event2.getButton().equals(MouseButton.PRIMARY))
                        {
                            if(polygon.getStroke() == null)
                            {
                                polygon.setStroke(javafx.scene.paint.Color.RED);
                                polygon.setStrokeWidth(5);
                            }
                            else
                            {
                                polygon.setStroke(null);
                            }
                        }
                        if(event2.getButton().equals(MouseButton.SECONDARY))
                        {
                            Pane paneDelete = new Pane();
                            paneDelete.prefWidthProperty().bind(pane.widthProperty().divide(3));
                            paneDelete.prefHeightProperty().bind(pane.heightProperty().divide(3));
                            paneDelete.layoutXProperty().bind(pane.widthProperty().divide(2).subtract(paneDelete.widthProperty().divide(2)));
                            paneDelete.layoutYProperty().bind(pane.heightProperty().divide(2).subtract(paneDelete.heightProperty().divide(2)));
                            paneDelete.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
                            pane.getChildren().add(paneDelete);
                            listPaneObject.put("paneDelete", pane.getChildren().size() - 1);
                            Label labelDelete = new Label("Are you sure you want to delete this object ?");
                            labelDelete.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
                            Button buttonYes = new Button("Yes");
                            buttonYes.setStyle(stringButtonPrimary);
                            buttonYes.setOnAction(event3 -> {
                                paneObject.getChildren().remove(polygon);
                                listShape.remove(polygon);
                                vBox.getChildren().remove(label1);
                                pane.getChildren().remove(paneDelete);
                                listPaneObject.remove("paneDelete");
                            });
                            Button buttonNo = new Button("No");
                            buttonNo.setStyle(stringButtonPrimary);
                            buttonNo.setOnAction(event3 -> {
                                pane.getChildren().remove(paneDelete);
                                listPaneObject.remove("paneDelete");
                            });
                            VBox vBoxDelete = new VBox();
                            vBoxDelete.prefWidthProperty().bind(paneDelete.widthProperty());
                            vBoxDelete.prefHeightProperty().bind(paneDelete.heightProperty());
                            vBoxDelete.setStyle("-fx-background-color: rgba(88, 88, 88, 0.9); -fx-background-radius: 50px; -fx-border-radius: 50px;");
                            vBoxDelete.setAlignment(Pos.CENTER);
                            vBoxDelete.setSpacing(10);
                            vBoxDelete.getChildren().addAll(labelDelete, buttonYes, buttonNo);
                            paneDelete.getChildren().add(vBoxDelete);
                            stage.getScene().setOnKeyPressed(event4 -> {
                                if (event4.getCode().toString().equals("ESCAPE"))
                                {
                                    pane.getChildren().remove(paneDelete);
                                    listPaneObject.remove("paneDelete");
                                }
                            });
                        }
                    });
                    menuObject.getChildren().remove(buttonFinish);
                    for (Shape shape : listShapePoint)
                    {
                        paneObject.getChildren().remove(shape);
                    }
                    listShapePoint.clear();
                });
                paneObject.setOnMouseClicked(event1 -> {
                    createPoint(paneObject, nbPoint, listPoint, listShapePoint, event1);
                });
                menuObject.getChildren().add(buttonFinish);
            }
        });
        menuObject.getChildren().addAll(label, comboBox, buttonCreate);

        pane.getChildren().addAll(menuObject, menuListObject);
        stage.getScene().setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ESCAPE"))
            {
                menueditorPause();
            }
            if (event.getCode().toString().equals("Z") && !event.isShiftDown() && !event.isControlDown())
            {
                y.addAndGet(30);
                moveGridY(paneObject, 30);
            }
            if (event.getCode().toString().equals("S") && !event.isShiftDown() && !event.isControlDown())
            {
                y.addAndGet(-30);
                moveGridY(paneObject, -30);
            }
            if (event.getCode().toString().equals("Q") && !event.isShiftDown() && !event.isControlDown())
            {
                x.addAndGet(30);
                moveGridX(paneObject, 30);
            }
            if (event.getCode().toString().equals("D") && !event.isShiftDown() && !event.isControlDown())
            {
                x.addAndGet(-30);
                moveGridX(paneObject, -30);
            }
            if (event.getCode().toString().equals("DELETE"))
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        paneObject.getChildren().remove(shape);
                        listShape.remove(shape);
                        for (int i = 0; i < vBox.getChildren().size(); i++)
                        {
                            if (vBox.getChildren().get(i).equals(shape))
                            {
                                vBox.getChildren().remove(i);
                            }
                        }
                    }
                }
            }
            if (event.getCode().toString().equals("ESCAPE"))
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setStroke(null);
                    }
                }
            }
            if (event.getCode().toString().equals("A") && !event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setRotate(shape.getRotate() - 5);
                    }
                }
            }
            if (event.getCode().toString().equals("E") && !event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setRotate(shape.getRotate() + 5);
                    }
                }
            }
            if (event.getCode().toString().equals("A") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setRotate(shape.getRotate() - 45);
                    }
                }
            }
            if (event.getCode().toString().equals("E") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setRotate(shape.getRotate() + 45);
                    }
                }
            }
            if (event.getCode().toString().equals("A") && event.isControlDown() && !event.isShiftDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setRotate(shape.getRotate() - 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("E") && event.isControlDown() && !event.isShiftDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setRotate(shape.getRotate() + 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("Z") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutY(shape.getLayoutY() + 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("S") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutY(shape.getLayoutY() - 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("Q") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutX(shape.getLayoutX() + 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("D") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutX(shape.getLayoutX() - 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("Z") && !event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutY(shape.getLayoutY() + 5);
                    }
                }
            }
            if (event.getCode().toString().equals("S") && !event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutY(shape.getLayoutY() - 5);
                    }
                }
            }
            if (event.getCode().toString().equals("Q") && !event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutX(shape.getLayoutX() + 5);
                    }
                }
            }
            if (event.getCode().toString().equals("D") && !event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutX(shape.getLayoutX() - 5);
                    }
                }
            }

            if (event.getCode().toString().equals("Z") && event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutY(shape.getLayoutY() + 30);
                    }
                }
            }
            if (event.getCode().toString().equals("S") && event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutY(shape.getLayoutY() - 30);
                    }
                }
            }
            if (event.getCode().toString().equals("Q") && event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutX(shape.getLayoutX() + 30);
                    }
                }
            }
            if (event.getCode().toString().equals("D") && event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setLayoutX(shape.getLayoutX() - 30);
                    }
                }
            }

            if (event.getCode().toString().equals("W") && !event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setScaleX(shape.getScaleX() + 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("X") && !event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setScaleY(shape.getScaleY() + 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("W") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null && shape.getScaleX() > 0.2)
                    {
                        shape.setScaleX(shape.getScaleX() - 0.1);
                    }
                }
            }
            if (event.getCode().toString().equals("X") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null && shape.getScaleY() > 0.2)
                    {
                        shape.setScaleY(shape.getScaleY() - 0.1);
                    }
                }
            }

            if (event.getCode().toString().equals("W") && !event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setScaleX(shape.getScaleX() + 0.01);
                    }
                }
            }
            if (event.getCode().toString().equals("X") && !event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setScaleY(shape.getScaleY() + 0.01);
                    }
                }
            }
            if (event.getCode().toString().equals("W") && event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null && shape.getScaleX() > 0.02)
                    {
                        shape.setScaleX(shape.getScaleX() - 0.01);
                    }
                }
            }
            if (event.getCode().toString().equals("X") && event.isShiftDown() && event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null && shape.getScaleY() > 0.02)
                    {
                        shape.setScaleY(shape.getScaleY() - 0.01);
                    }
                }
            }

            if (event.getCode().toString().equals("C") && !event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null)
                    {
                        shape.setScaleX(shape.getScaleX() + 0.01);
                        shape.setScaleY(shape.getScaleY() + 0.01);
                    }
                }
            }
            if (event.getCode().toString().equals("C") && event.isShiftDown() && !event.isControlDown())
            {
                for (Shape shape : listShape)
                {
                    if (shape.getStroke() != null && shape.getScaleX() > 0.02 && shape.getScaleY() > 0.02)
                    {
                        shape.setScaleX(shape.getScaleX() - 0.01);
                        shape.setScaleY(shape.getScaleY() - 0.01);
                    }
                }
            }
        });
        stage.getScene().setOnScroll(event -> {
            if (event.getDeltaY() > 0)
            {
                for (Shape shape : listShape)
                {
                    shape.setScaleX(shape.getScaleX() + 0.1);
                    shape.setScaleY(shape.getScaleY() + 0.1);
                    shape.setLayoutX(shape.getLayoutX() + 0.1 * shape.getLayoutX());
                    shape.setLayoutY(shape.getLayoutY() + 0.1 * shape.getLayoutY());
                }
            }
            if (event.getDeltaY() < 0)
            {
                for (Shape shape : listShape)
                {
                    if (shape.getScaleX() > 0.2 && shape.getScaleY() > 0.2)
                    {
                        shape.setScaleX(shape.getScaleX() - 0.1);
                        shape.setScaleY(shape.getScaleY() - 0.1);
                        shape.setLayoutX(shape.getLayoutX() - 0.1 * shape.getLayoutX());
                        shape.setLayoutY(shape.getLayoutY() - 0.1 * shape.getLayoutY());
                    }
                }
            }
        });
    }

    public static void createPoint(Pane paneObject, AtomicInteger nbPoint, ArrayList<Point> listPoint, ArrayList<Shape> listShapePoint, MouseEvent event1) {
        Point point = new Point(event1.getX(), event1.getY());
        Circle circle = new Circle(point.getX(), point.getY(), 5);
        circle.setStyle("-fx-fill: white;");
        listShapePoint.add(circle);
        paneObject.getChildren().add(circle);
        listPoint.add(point);
        nbPoint.getAndDecrement();
    }

    public static void moveGridX(Pane paneObject, int x)
    {
        paneObject.setLayoutX(paneObject.getLayoutX() + x);
    }

    public static void moveGridY(Pane paneObject, int y)
    {
        paneObject.setLayoutY(paneObject.getLayoutY() + y);
    }

    public static void menueditorPause()
    {

    }

    public static void playMenu() {

    }

    public static void main(String[] args) {
        launch();
    }
}