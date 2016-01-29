/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstockwatcher;

import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Michael
 */
public abstract class SceneSwitcher {
    public static Scene currentScene;
    public static final HashMap<String, SceneSwitcher> controllers = new HashMap();
    
    private Parent root;

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }
    
    public static SceneSwitcher addToList(String name){
        SceneSwitcher controller;
        
        controller = controllers.get(name);
        
        if(controller == null){
            try{
                FXMLLoader loader = new FXMLLoader(SceneSwitcher.class.getResource(name + ".fxml"));
                Parent root = (Parent)loader.load();
                controller = (SceneSwitcher)loader.getController();
                controller.setRoot(root);
                controllers.put(name, controller);
            }catch (IOException e){
                System.out.println(e);
            }
        }
        
        return controller;
    }
    
    public static void switchToController(String name){
        SceneSwitcher controller = controllers.get(name);
        
        if(controller == null){
            controller = SceneSwitcher.addToList(name);
        }
        if(controller != null){
            if(currentScene != null){
                currentScene.setRoot(controller.getRoot());
            }
        }
    }
}
