/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstopwatch;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Michael
 */
public class Mdsf3fStopwatch extends Application {
    int secondsCount = 0, minutesCount = 0, splitCount = 0, hundredthsCount = 0;
    ImageView hand, minuteHand;
    Label seconds, minutes, hundredths;
    VBox splits;
    
    @Override
    public void start(Stage primaryStage) {
        
        VBox root = new VBox();
        root.setSpacing(10);
        
        splits = new VBox();
        splits.setSpacing(2);
        
        StackPane analogClock = new StackPane();
        
        ImageView clockFace = new ImageView();
        Image clockFaceImage = new Image(this.getClass().getResourceAsStream("clockface.png"));
        clockFace.setImage(clockFaceImage);
        
        hand = new ImageView();
        Image handImage = new Image(this.getClass().getResourceAsStream("hand.png"));
        hand.setImage(handImage);
        
        minuteHand = new ImageView();
        minuteHand.setImage(handImage);
        
        analogClock.getChildren().addAll(clockFace, hand, minuteHand);
        
        HBox digitalClock = new HBox();
        digitalClock.setAlignment(Pos.CENTER);
       
        minutes = new Label("00");
        minutes.setFont(Font.font("Times New Roman", 40));
        
        Label separator = new Label(":");
        separator.setFont(Font.font("Times New Roman", 40));
        
        seconds = new Label("00");
        seconds.setFont(Font.font("Times New Roman", 40));
        
        Label separator2 = new Label(":");
        separator2.setFont(Font.font("Times New Roman", 40));
        
        hundredths = new Label("00");
        hundredths.setFont(Font.font("Times New Roman", 40));
        
        digitalClock.getChildren().addAll(minutes, separator, seconds, separator2, hundredths);
        
        HBox splitTextContainer = new HBox();
        splitTextContainer.setAlignment(Pos.CENTER);
        Label splitText = new Label("Split/Lap Time");
        splitTextContainer.getChildren().add(splitText);
        
        HBox controls = new HBox();
        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Button reset = new Button("Reset");
        Button split = new Button("Split");
        
        controls.setAlignment(Pos.CENTER);
        controls.getChildren().addAll(start, stop, reset, split);
        
        root.getChildren().addAll(analogClock, digitalClock, controls, splitTextContainer, splits);

        
        Scene scene = new Scene(root, 350, 700);
        
        primaryStage.setTitle("Stopwatch");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        Timeline time = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> updateClock()));
        time.setCycleCount(Animation.INDEFINITE);
        
        
        start.setOnAction(actionEvent -> {
            time.play();
        });
        stop.setOnAction(actionEvent ->{
            time.pause();
        });
        reset.setOnAction(actionEvent->{
            time.pause();
            resetClock();
        });
        split.setOnAction(actionEvent ->{
            splitTime();
        });
    }
    
    private void updateClock(){
        hundredthsCount++;
        if(hundredthsCount == 100){
            hundredthsCount = 0;
            secondsCount++;
            hand.setRotate(hand.getRotate() + 6);

            if(secondsCount == 60){
                secondsCount = 0;
                minutesCount++;
                minuteHand.setRotate(minuteHand.getRotate() + 6);

                if(minutesCount < 10){
                    minutes.setText("0" + String.valueOf(minutesCount));
                }else{
                    minutes.setText(String.valueOf(minutesCount));
                }

            }

            if(secondsCount< 10){
                seconds.setText("0" + String.valueOf(secondsCount));
            }else{
                seconds.setText(String.valueOf(secondsCount));
            }
        }
        
        if(hundredthsCount < 10){
            hundredths.setText("0" + String.valueOf(hundredthsCount));
        }else{
            hundredths.setText(String.valueOf(hundredthsCount));
        }
        
        
    }
    
    private void resetClock(){
        secondsCount = 0;
        minutesCount = 0;
        
        hand.setRotate(0);
        minutes.setText("00");
        seconds.setText("00");
        hundredths.setText("00");
        
        splits.getChildren().clear();
    }
    
    private void splitTime(){
        splitCount++;
        Label split = new Label("Split " + String.valueOf(splitCount) + " : " + minutes.getText()+ ":" + seconds.getText() + ":" + hundredths.getText() );
        splits.getChildren().add(split);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
