/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstockwatcher;

/**
 *
 * @author Michael
 */
public class Stock {
    private double lastPrice;
    private double change;
    private double changePct;
    private double ytdChange;
    private double high;
    private double low;
    private double open;

    public double getLastPrice() {
        return lastPrice;
    }

    public double getChange() {
        return change;
    }

    public double getChangePct() {
        return changePct;
    }

    public double getYtdChange() {
        return ytdChange;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getOpen() {
        return open;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public void setChangePct(double changePct) {
        this.changePct = changePct;
    }

    public void setYtdChange(double ytdChange) {
        this.ytdChange = ytdChange;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setOpen(double open) {
        this.open = open;
    }
    
    
}
