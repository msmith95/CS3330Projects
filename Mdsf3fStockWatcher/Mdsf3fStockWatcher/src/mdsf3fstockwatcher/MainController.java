/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstockwatcher;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Michael
 */
public class MainController extends SceneSwitcher implements Initializable {
    
    @FXML
    ComboBox tickers;
    
    @FXML
    Label status;
    
    @FXML
    TextField tickerToAdd;
    
    @FXML
    VBox stockInfo;
    
    ObservableList<Company> listOfTickers;
    
    Stock currentStock;
    Company currentCompany;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBHelper db = new DBHelper();
        //GetStockTask task = new GetStockTask("AAPL");
        //task.execute();
        //TODO Add get Company task, add remove from DB, add retrieve all companies from DB, add About Page
        listOfTickers = FXCollections.observableArrayList(DBHelper.getCompanies());
        tickers.getItems().addAll(listOfTickers);
        GetStockTask.controller = this;
        GetCompanyInfo.controller = this;
    }    
    
    @FXML
    public void handleAdd(){
       if(tickerToAdd.getText().equals("")){
           updateStatus("Please enter a ticker to add");
           return;
       }
       tickers.getItems().add(tickerToAdd.getText());
       GetCompanyInfo companies = new GetCompanyInfo(tickerToAdd.getText());
       tickerToAdd.setText("");
       companies.execute();
    }
    
    @FXML
    public void handleRetreive(){
        if(tickers.getSelectionModel().getSelectedIndex() < 0){
            updateStatus("Please select or enter a ticker");
            return;
        }
        currentCompany = (Company) listOfTickers.get(tickers.getSelectionModel().getSelectedIndex());
        GetStockTask task = new GetStockTask(listOfTickers.get(tickers.getSelectionModel().getSelectedIndex()).toString());
        task.execute();
    }
    
    public void retreiveStock(Stock stock){
        stockInfo.getChildren().clear();
        currentCompany.setStock(stock);
        Label companyName = new Label("Name: " + currentCompany.getName());
        Label companyTicker = new Label("Ticker: " + currentCompany.getTicker());
        Label companyExchange = new Label("Exchange: " + currentCompany.getStockExchange());
        Label lastPrice = new Label("Last Price: "+stock.getLastPrice());
        Label change = new Label("Change: " + stock.getChange());
        Label changePct = new Label("Change Percentage: " + stock.getChangePct());
        Label ytdChange = new Label("Year-to-Date Change Percentage: " + stock.getYtdChange());
        Label high = new Label("High: " + stock.getHigh());
        Label low = new Label("Low: " + stock.getLow());
        Label open = new Label("Open: " + stock.getOpen());
        stockInfo.getChildren().addAll(companyName, companyTicker, companyExchange, lastPrice, change, changePct, ytdChange, high, low, open);
    }
    
    public void updateStatus(String statusString){
        status.setText("");
        status.setText(statusString);
    }
    
    public void updateCurrentCompany(Company company){
        this.currentCompany = company;
        listOfTickers.add(currentCompany);
    }
    
    @FXML
    public void handleRemove(){
        if(tickers.getSelectionModel().getSelectedIndex() < 0){
            updateStatus("Please select or enter a ticker");
            return;
        }
        currentCompany = (Company) listOfTickers.get(tickers.getSelectionModel().getSelectedIndex());
        DBHelper.remove(currentCompany);
        tickers.getItems().remove(tickers.getSelectionModel().getSelectedIndex());
    }
    
    @FXML
    public void handleAbout(){
        switchToController("About");
    }
    
    @FXML
    public void handleClose(){
        Platform.exit();
    }
}
