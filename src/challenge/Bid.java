package challenge;

import org.json.simple.JSONObject;

public class Bid implements Comparable<Bid> {
  private String bidder;
  private String unit;
  private Long bid;

  Bid(String bidder, String unit, Long bid) {
    this.bidder = bidder;
    this.unit = unit;
    this.bid = bid;
  }

  public String getBidder() {
    return bidder;
  }

  public String getUnit() {
    return unit;
  }

  public Long getBid() {
    return bid;
  }

  public String toString() {
    return "Bidder: " + bidder + " Unit: " + unit + " Bid: " + bid.toString();
  }

  // In the future, I would most likely use a better JSON library here.
  public JSONObject toJson() {
    JSONObject json = new JSONObject();
    json.put("bidder", bidder);
    json.put("bid", bid);
    json.put("unit", unit);
    return json;
  }

  @Override
  public int compareTo(Bid bid) {
    return this.getBid().intValue() - bid.getBid().intValue();
  }
}