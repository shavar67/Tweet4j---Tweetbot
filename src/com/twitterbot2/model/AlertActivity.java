package com.twitterbot2.model;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import com.twitterbot.view.TimelineView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AlertActivity {

    public Scene popupDialog(Stage stage, Scene view, String title, String message) {
        TimelineView bot = new TimelineView();
        StackPane stackpane = new StackPane();
        Scene scene = new Scene(stackpane, 1080, 600);
        scene.getStylesheets().add(this.getClass().getResource("Application.css").toExternalForm());
        JFXTextArea display = new JFXTextArea();
        display.setText(message);
        display.setWrapText(true);
        display.setEditable(false);
        display.setPrefSize(400,400);
        JFXDialogLayout content = new JFXDialogLayout();
        Label header = new Label(title);
        header.setAlignment(Pos.CENTER);
        header.setTranslateX(250);
        content.setHeading(header);
        content.setPrefSize(800, 400);
        content.setBody(display);
        JFXDialog dialog = new JFXDialog(stackpane, content, JFXDialog.DialogTransition.BOTTOM, false);

        JFXButton close = new JFXButton("Close");




        close.setOnAction(event -> {

            dialog.close();
            stage.setScene(view);

        });
        content.setActions(close);
        dialog.show();
        return scene;
    }
}

