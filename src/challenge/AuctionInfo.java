package challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AuctionInfo {
  private String site;
  private List<String> units = new ArrayList<String>();
  private List<Bid> bids = new ArrayList<Bid>();

  AuctionInfo(String s, String[] u, JSONArray jsonBids) {
    site = s;
    units = Arrays.asList(u);
    setupBidInfo(jsonBids);
  }

  private void setupBidInfo(JSONArray jsonBids) {
    for (int i = 0; i < jsonBids.size(); i++) {
      JSONObject bidInfo = (JSONObject) jsonBids.get(i);
      bids.add(new Bid(bidInfo.get("bidder").toString(), bidInfo.get("unit").toString(), (Long) bidInfo.get("bid")));
      Collections.sort(bids);
    }
  }

  public String toString() {
    return "\nSite: " + site + "\nUnits: " + Arrays.toString(units.toArray()) + "\nBids: "
        + Arrays.toString(bids.toArray());
  }

  public String getSite() {
    return site;
  }

  public List<String> getUnits() {
    return units;
  }

  public List<Bid> getBids() {
    return bids;
  }
}