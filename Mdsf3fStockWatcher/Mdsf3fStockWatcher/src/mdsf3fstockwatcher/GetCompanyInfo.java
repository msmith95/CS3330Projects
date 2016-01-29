/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdsf3fstockwatcher;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Michael
 */
public class GetCompanyInfo extends AsyncTask {
    
    String api, rText;
    CloseableHttpClient client;
    HttpPost post;
    HttpGet get;
    HttpResponse response;
    JSONObject json;
    public static MainController controller;
    private String ticker;
    private Company company;
    
    public GetCompanyInfo(String ticker){
        this.ticker = ticker;
    }

    @Override
    public void onPreExecute() {
        company = new Company();
    }

    @Override
    public void doInBackground() {
         client = HttpClients.createDefault();
            get = new HttpGet("http://dev.markitondemand.com/MODApis/Api/v2/Lookup/json?input="+ticker);
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
                //json = new JSONObject(rText);
                JSONArray array = new JSONArray(rText);
                //array = json.getJSONArray("");
                json = array.getJSONObject(0);
                company.setTicker(json.getString("Symbol"));
                company.setName(json.getString("Name"));
                company.setStockExchange(json.getString("Exchange"));
            } catch (JSONException e) {
                System.out.println(e.getCause());
            }
    }

    @Override
    public void onPostExecute() {
        controller.updateCurrentCompany(company);
        DBHelper.insert(company);
        //System.out.println(company);
        //System.out.println(rText);
        //System.out.println(json);
    }
    
}
