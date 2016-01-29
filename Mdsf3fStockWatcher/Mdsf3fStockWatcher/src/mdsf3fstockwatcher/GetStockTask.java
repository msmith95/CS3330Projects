/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstockwatcher;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Michael
 */
public class GetStockTask extends AsyncTask {
   String api, rText;
    CloseableHttpClient client;
    HttpPost post;
    HttpGet get;
    HttpResponse response;
    JSONObject json;
    public static MainController controller;
    private String ticker;
    private Stock stock;
    
    public GetStockTask(String ticker){
        this.ticker = ticker;
    }

    @Override
    public void onPreExecute() {
        stock = new Stock();
        controller.updateStatus("Stock information is being retrieved.");
    }

    @Override
    public void doInBackground() {
            client = HttpClients.createDefault();
            get = new HttpGet("http://dev.markitondemand.com/MODApis/Api/v2/Quote/json?symbol="+ticker);
            json = null;


            try {  
                response = client.execute(get);
            } catch (IOException e) {
                System.out.println(e.getCause());
            }

            try {
                rText = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                System.out.println(e.getCause());
            }

            try {
                json = new JSONObject(rText);
                stock.setChange(json.getDouble("Change"));
                stock.setChangePct(json.getDouble("ChangePercent"));
                stock.setHigh(json.getDouble("High"));
                stock.setLastPrice(json.getDouble("LastPrice"));
                stock.setLow(json.getDouble("Low"));
                stock.setOpen(json.getDouble("Open"));
                stock.setYtdChange(json.getDouble("ChangePercentYTD"));
            } catch (JSONException e) {
                System.out.println(e.getCause());
            }
    }

    @Override
    public void onPostExecute() {
        controller.retreiveStock(stock);
        controller.updateStatus("");
    }

    
}
