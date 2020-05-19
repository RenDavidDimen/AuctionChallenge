package challenge;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Configs {
  private HashMap<String, SiteData> siteConfigs = new HashMap<>();
  private HashMap<String, Double> validBidders = new HashMap<>();

  // set up site config dictionary
  private void setupSiteConfig(JSONArray sites) {
    for (int i = 0; i < sites.size(); i++) {
      JSONObject site = (JSONObject) sites.get(i);
      JSONArray objBidders = (JSONArray) site.get("bidders");

      // convert object array of bidders to an array of strings
      String[] strBidders = Utils.arrObjToString(objBidders.toArray());
      // Create siteData objects
      SiteData siteConfig = new SiteData(site.get("name").toString(), strBidders, (long) site.get("floor"));
      siteConfigs.put(siteConfig.getName(), siteConfig);
    }
  }

  // set up valid bidder dictionary
  private void setupBidderConfig(JSONArray bidders) {
    for (int i = 0; i < bidders.size(); i++) {
      JSONObject bidder = (JSONObject) bidders.get(i);
      validBidders.put(bidder.get("name").toString(), ((Number) bidder.get("adjustment")).doubleValue());
    }
  }

  // Load config.json and parse relevant data
  public void loadConfigs() {
    JSONParser parser = new JSONParser();

    try {
      Object configInput = parser.parse(new FileReader("config.json"));

      JSONObject config = (JSONObject) configInput;
      JSONArray sites = (JSONArray) config.get("sites");
      JSONArray bidders = (JSONArray) config.get("bidders");

      setupSiteConfig(sites);
      setupBidderConfig(bidders);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public HashMap<String, SiteData> getSiteConfigs() {
    return siteConfigs;
  }

  public HashMap<String, Double> getValidBidders() {
    return validBidders;
  }
}