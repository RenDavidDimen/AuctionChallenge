package challenge;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class ValidateBids {
  private JSONArray bidResults = new JSONArray();
  private HashMap<String, Bid> siteBidResults = new HashMap<String, Bid>();

  private void writeToJson() {
    JSONArray siteResults = new JSONArray();
    siteBidResults.forEach((k, v) -> {
      siteResults.add(v.toJson());
    });
    bidResults.add(siteResults);
  }

  // adjust price helper function
  private double adjustedPrice(Bid bid, Double adjustment) {
    return bid.getBid() + bid.getBid() * adjustment;
  }

  // compare adjusted prices based on bidder
  private boolean compareBids(Bid currentBest, Bid contesting, HashMap<String, Double> validBidders) {
    // Avoid comparing bids by the same user
    // Bids are sorted least to greatest,
    // so subsequent bids from the same user provide no changes
    if (!currentBest.getBidder().equals(contesting.getBidder())) {
      double adjustedPriceCurr = adjustedPrice(currentBest, validBidders.get(currentBest.getBidder()));
      double adjustedPriceCont = adjustedPrice(contesting, validBidders.get(contesting.getBidder()));
      return adjustedPriceCurr < adjustedPriceCont;
    } else {
      return false;
    }
  }

  private void runSiteBid(AuctionInfo currentSite, List<Bid> siteBids, HashMap<String, SiteData> siteConfig,
      HashMap<String, Double> validBidders) {
    SiteData currentSiteConfig = siteConfig.get(currentSite.getSite());
    List<String> currentSiteUnits = currentSite.getUnits();
    Long currentSiteFloor = currentSiteConfig.getFloor();

    for (int j = 0; j < siteBids.size(); j++) {
      Bid currentBid = siteBids.get(j);
      Boolean validBidder = currentSiteConfig.getBidders().contains(currentBid.getBidder());
      Boolean validUnit = currentSiteUnits.contains(currentBid.getUnit());
      Boolean validBid = (adjustedPrice(currentBid, validBidders.get(currentBid.getBidder())) >= currentSiteFloor);

      // verify that:
      // - bidder is allowed to bid on current site
      // - unit being bid on is a valid option
      // - bid is greater than floor
      if (validBidder && validUnit && validBid) {
        // If no bids have been looked at yet or no bids exist for a specific unit,
        // add bid to map with the unit as the key
        if (siteBidResults.isEmpty() || !siteBidResults.containsKey(currentBid.getUnit())) {
          siteBidResults.put(currentBid.getUnit(), currentBid);
        } else if (compareBids(siteBidResults.get(currentBid.getUnit()), currentBid, validBidders)) {
          siteBidResults.replace(currentBid.getUnit(), currentBid);
        }
      }
    }
  }

  public void validateBids(HashMap<String, SiteData> siteConfigs, HashMap<String, Double> validBidders,
      List<AuctionInfo> auctionInfo) {
    // Run through bids by site
    for (int i = 0; i < auctionInfo.size(); i++) {
      // Examine Bids if site is listed in configs
      AuctionInfo currentSite = auctionInfo.get(i);
      if (siteConfigs.containsKey(currentSite.getSite())) {
        List<Bid> siteBids = currentSite.getBids();
        runSiteBid(currentSite, siteBids, siteConfigs, validBidders);
        writeToJson();
        siteBidResults.clear();
      }
    }
  }

  public JSONArray getBidResults() {
    return bidResults;
  }
}