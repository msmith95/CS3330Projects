/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstockwatcher;

import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class Company {
    private String name;
    private String ticker;
    private String stockExchange;
    private Stock stock;

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }

    public String getStockExchange() {
        return stockExchange;
    }

    public Stock getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return ticker;
    }
    
    
}
