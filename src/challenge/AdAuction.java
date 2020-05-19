package challenge;

// Main class used to run through the auction process
public class AdAuction {

  public static void main(String[] args) {
    Configs config = new Configs();
    InputHandler handler = new InputHandler();
    ValidateBids validate = new ValidateBids();
    Output output = new Output();

    // Load config data
    config.loadConfigs();

    // Load input from `input.json`
    handler.loadInput();

    // Validate bids
    validate.validateBids(config.getSiteConfigs(), config.getValidBidders(), handler.getAuctionInfo());

    // Write to output.json
    output.writeToOutputJson(validate.getBidResults());
  }
}