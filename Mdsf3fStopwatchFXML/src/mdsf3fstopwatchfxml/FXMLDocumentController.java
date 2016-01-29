/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstopwatchfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author Michael
 */
public class FXMLDocumentController implements Initializable {
        
    @FXML
    private ImageView hand;
    
    @FXML
    private Label minutes;
    
    @FXML
    private Label seconds;
    
    @FXML
    private Label milis;
    
    @FXML
    private Button start;
    
    @FXML 
    private Button stop;
    
    @FXML
    private Button reset;
    
    @FXML
    private ImageView minuteHand;
    
    private int hundredthsCount = 0, secondsCount = 0, minutesCount = 0;
    private Timeline time;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        time = new Timeline(new KeyFrame(Duration.millis(10), actionEvent -> updateClock()));
        time.setCycleCount(Animation.INDEFINITE);
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
            milis.setText("0" + String.valueOf(hundredthsCount));
        }else{
            milis.setText(String.valueOf(hundredthsCount));
        }
        
        
    }
    
    private void resetClock(){
        secondsCount = 0;
        minutesCount = 0;
        
        hand.setRotate(0);
        minuteHand.setRotate(0);
        minutes.setText("00");
        seconds.setText("00");
        milis.setText("00");
       
    }
    
    @FXML
    private void handleStartButton(){
        time.play();
    }
    
    @FXML
    private void handleStopButton(){
        time.pause();
    }
    
    @FXML
    private void handleResetButton(){
        time.pause();
        resetClock();
    }
    
}
