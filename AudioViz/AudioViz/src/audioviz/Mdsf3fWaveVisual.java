/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import static java.lang.Integer.min;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Michael
 */
public class Mdsf3fWaveVisual implements Visualizer {
     private final String name = "Mdsf3f WaveForm Visualizer";
    private int numOfLines;
    private AnchorPane vizPane;
    
    private final double curveHeight1 = 4.5;
    
    private Double width = 0.0;
    private Double height = 0.0;
    private double curveLength;
    private final double startingX = 1.0;
    private double startingY = 20.0;
    private final int startHue = 180;
    
    private Line[] curve;
    private Line[] curve2;
    

    @Override
    public void start(Integer numBands, AnchorPane vizPane) {
        end();
        this.numOfLines = numBands;
        this.vizPane = vizPane;
        
        width = vizPane.getWidth();
        height = vizPane.getHeight();
        startingY = height/2;
        //startingY = height - 50;
        double lastX = startingX;
        double lastY = startingY;
        double lastX2 = startingX;
        double lastY2 = startingY;
        
        this.curveLength = width/numOfLines;
        curve = new Line[numOfLines];
        curve2 = new Line[numOfLines];

         for (int x = 0; x < numOfLines; x++) {
                if (lastX != -1) {
                    Line line = new Line();
                    line.setStartX(lastX);
                    line.setStartY(lastY);
                    line.setEndX(line.getStartX() + curveLength);
                    line.setEndY(startingY);
                    line.setSmooth(true);
                    line.setStrokeWidth(6);
                    lastX = line.getEndX();
                    lastY = line.getEndY();
                    
                    Line line2 = new Line();
                    line2.setStartX(lastX2);
                    line2.setStartY(lastY2);
                    line2.setEndX(line.getStartX() + curveLength);
                    line2.setEndY(startingY);
                    line2.setSmooth(true);
                    line2.setStrokeWidth(6);
                    lastX2 = line.getEndX();
                    lastY2 = line.getEndY();
                    
                    curve[x] = line;
                    curve2[x] = line2;
                    this.vizPane.getChildren().add(line);
                    this.vizPane.getChildren().add(line2);
                    
                }

                
            }
    }

    @Override
    public void end() {
        if (curve != null) {
            for(Line line: curve)
                vizPane.getChildren().remove(line);  
            curve = null;
        } 
        if(curve2 != null){
            for(Line line: curve2)
                vizPane.getChildren().remove(line);
            curve2 = null;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        if (curve == null || curve2 == null) {
            return;
        }
        
        Integer num = min(curve.length, magnitudes.length);
        double lastY = startingY;
        double lastY2 = startingY;
        for (int i = 0; i < num; i ++) { 
            Line line = curve[i];
            line.setStartY(lastY);
            line.setEndY((-60*curveHeight1) - (magnitudes[i]*curveHeight1) + startingY);
            line.setStroke(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
            lastY = line.getEndY();
            
            Line line2 = curve2[i];
            line2.setStartY(lastY2);
            line2.setEndY(startingY + (magnitudes[i]+60)*curveHeight1);
            line2.setStroke(Color.hsb(startHue - (magnitudes[i] * -6.0), 1.0, 1.0, 1.0));
            lastY2 = line2.getEndY();
        }
    }
}


