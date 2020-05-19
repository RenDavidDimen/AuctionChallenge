package challenge;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputHandler {
  // Load input.json and parse relevant data
  private List<AuctionInfo> siteAuctionData = new ArrayList<AuctionInfo>();

  public void loadInput() {
    JSONParser parser = new JSONParser();

    try {

      // Get input.json file
      Object input = parser.parse(new FileReader("input.json"));
      JSONArray jsInput = (JSONArray) input;

      for (int i = 0; i < jsInput.size(); i++) {
        JSONObject auctionInput = (JSONObject) jsInput.get(i);

        // Site being bid on
        String siteToBid = auctionInput.get("site").toString();

        // Get units available
        JSONArray jsonUnits = (JSONArray) auctionInput.get("units");
        String[] units = Utils.arrObjToString(jsonUnits.toArray());

        // Get Bids
        JSONArray jsonBids = (JSONArray) auctionInput.get("bids");
        siteAuctionData.add(new AuctionInfo(siteToBid, units, jsonBids));
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  public List<AuctionInfo> getAuctionInfo() {
    return siteAuctionData;
  }
}