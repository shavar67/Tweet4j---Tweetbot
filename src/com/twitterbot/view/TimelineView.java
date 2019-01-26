package com.twitterbot.view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.twitterbot2.model.AlertActivity;
import com.twitterbot2.model.TweetBotFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import twitter4j.TwitterException;

public class TimelineView extends Application {
private VBox navPane;
private JFXButton retweet = new JFXButton("Check Status");
private BorderPane root;
private Pane pane;
private JFXTextField postTweet;
private JFXButton searchResults;
private JFXButton deleteQuery;
private JFXTextField searchQuery;
private JFXListView<Long>statusList;
private HBox buttonLayout;
private JFXTextArea display;
private Stage stage;
private Scene scene;
private JFXButton submit;
private VBox feed;
private JFXButton btn = new JFXButton("Retweet");
AlertActivity alert = new AlertActivity();

TweetBotFactory bot = new TweetBotFactory();
    private Parent setupContent() {

        root = new BorderPane();
        buttonLayout = new HBox(10);
        navPane = new VBox(10);
        navPane.setSpacing(20);
        navPane.setAlignment(Pos.CENTER);
        navPane.setPadding(new Insets(15));
        searchQuery = new JFXTextField();
        searchQuery.setPrefSize(300,40);
        searchResults = new JFXButton("Search");
        deleteQuery = new JFXButton("Reset");




        deleteQuery.setPrefSize(150,20);
        deleteQuery.setOnAction(delete -> {
            try {
                clearList();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        });
        searchResults.setOnAction( update -> {
            try {
                handleRequest();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        });

        searchQuery.setPromptText("Search hastags && users");
        statusList = new JFXListView<>();
        statusList.setStyle("-fx-vertical-gap:10");
        statusList.setPrefSize(300, 400);
        buttonLayout.getChildren().addAll(searchResults,deleteQuery,retweet);
        btn.setOnAction(e -> {
            statusList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Long> status;
            status = statusList.getSelectionModel().getSelectedItems();
            for(Long m : status){
                try {
                    String id = m.toString() + "l";
                 stage.setScene(alert.popupDialog(stage,scene,"Retweet successful","" + bot.postTweet(m,"")));
                } catch (TwitterException e1) {
                    System.out.println(m + " Tweet already rted");

                }
            }
        });
        navPane.getChildren().addAll( searchQuery,new Label("Twitter Status Ids"),statusList,buttonLayout);
        if (!statusList.isExpanded()) {
            statusList.setExpanded(true);
            statusList.setDepth(1);
        } else {
            statusList.setExpanded(false);
            statusList.setDepth(0);
        }
        root.setPrefSize(1080, 600);


        root.setLeft(navPane);
        root.setRight(setupHomeFeedView());
        return root;
    }

    public void handleRequest() throws TwitterException {
        statusList.getItems().addAll(bot.getStatus());
      display.setText("" + bot.searchTweets(searchQuery.getText(),20));
    }

    public void clearList() throws TwitterException {
        statusList.getItems().clear();
        display.setText("");
        searchQuery.setText("");
    }
    private Parent setupHomeFeedView(){
     feed = new VBox(10);
     HBox hbox = new HBox(10);
     hbox.setAlignment(Pos.CENTER);
     feed.setSpacing(20);
     submit = new JFXButton("Submit");
     submit.setOnAction(tweet->{

     });
     feed.setAlignment(Pos.TOP_CENTER);
     feed.setPadding(new Insets(15));
     postTweet = new JFXTextField();
     submit.setOnAction(tweet->{
         try {
             bot.PostTweets(postTweet.getText());
         } catch (TwitterException e) {
             e.printStackTrace();
         }
     });
     display = new JFXTextArea();
     display.setPrefSize(600,400);
     display.setWrapText(true);
     postTweet.setPromptText("Compose a tweet.");
     postTweet.setPrefSize(150,40);
     retweet.setOnAction(e -> {
         statusList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
         ObservableList<Long> status;
         status = statusList.getSelectionModel().getSelectedItems();
         for(Long m : status){
             try {
                 String id = m.toString() + "l";
                 display.setText(bot.retweet(m));
             } catch (TwitterException e1) {
                 System.out.println(m + " Connection Timeout");
             }
         }
     });
        JFXButton exit = new JFXButton("Close Application");
       exit.setTranslateX(200);
        exit.setOnAction(closed ->{
            Platform.exit();
        });
     hbox.getChildren().addAll(submit,btn);
     feed.getChildren().addAll(exit,new Label("Post a Tweet"),postTweet,new Label("Recent Tweets && Activity"),display,hbox);
     return  feed;
    }

    public void reset(){
        display.setText("");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       stage = new Stage();
       scene = new Scene(setupContent());
        scene.getStylesheets().add(this.getClass().getResource("Application.css").toExternalForm());
       stage.setScene(scene);
       stage.initStyle(StageStyle.UNDECORATED);
       stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
