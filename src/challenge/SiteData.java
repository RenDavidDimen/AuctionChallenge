package challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SiteData {
  private String siteName;
  private List<String> siteBidders = new ArrayList<String>();
  private long siteFloor;

  public SiteData(String name, String[] bidders, long floor) {
    siteName = name;
    siteBidders = Arrays.asList(bidders);
    siteFloor = floor;
  }

  public String getName() {
    return siteName;
  }

  public List<String> getBidders() {
    return siteBidders;
  }

  public long getFloor() {
    return siteFloor;
  }

  public String toString() {
    return "siteName: " + siteName + "\nSiteBidders: " + siteBidders.toString() + "\nsiteFloor: " + siteFloor + "\n";
  }
}