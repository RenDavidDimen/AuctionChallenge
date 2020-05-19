# Ren-David's Coding Challenge Notes

## Running the code

I've done my best to rework the Dockerfile that was provided in the Sortable Code Challenge repo to fit the needs of my project.

To build the docker image, simply type in

```
docker build -t challenge .
```

Unfortunately my initial environment set up for working in Java was not set up as well as I hoped it would be (more information on that in the Overall Thoughts section). So should my Dockerfile not work as intended, `challenge.jar` will be executable. Should you choose to run the jar file, simply ensure that the desired `input.json` and `config.json` are within the same folder as the `challenge.jar` file. The executable will then create an `output.json` file within the same folder that the other files are in.

To run the code from the terminal, one may use the following command should they have java installed on their local machine

```
java -jar challenge.jar
```

## Assumptions Made

- `input.json` files simply replace existing input.json files when inserting them using the cli
- Site Names are unique
- Site Floors are always non decimal numbers
- Bids are always non decimal numbers
- Bids and floors are never negative
- Bids and Floors do not go over the `MAX_VALUE` or under the MIN_VALUE set by Java Long types
- Bidder Adjustments to not go over the `MAX_VALUE` or under the MIN_VALUE set by Java Double types
- If a unit is not bid on, no json object takes its place in the `output.json` file
- Prettifying the output.json file was not a requirement

## Overall Thoughts

Initially, I was hoping to write units tests to accomodate this submission, however I didn't have my environments to work in Java set up very well and it became very cumbersome. In the future, I would hope to have things set up well enough so that I could easily write unit tests for my functions.

Testing for this program was done regressively in hopes to ensure that everything worked as intended. Functional testing was performed to look at the edge cases I believe could occur.

I think if I were to rewrite this program again, I would also look into using a better JSON library than the org.json library currently used. This would most likely make parsing the `input.json` and `config.json`, as well as writing to `output.json` slightly more efficient.

Having not set up my java environments to follow the stringent requirements it needs, I found that trying to have the Dockerfile compile, build, and package my java code quite cumbersome. Because of this, I had to resort to creating a separate runnable jar file and have docker run it. I believe Docker to be a very useful tool, and one I will be looking into more.

# Auction Coding Challenge

One of the things that the Engineering team at Sortable works on is software that
runs ad auctions, either in the browser or server-side. The goal of this challenge
is to write program that will run a simple auction, while enforcing data validity.

## Concepts

Sortable manages ads on many different websites. Each site has a different
configuration: which bidders are permitted to bid on ads on that site, and an
auction 'floor' that bids must exceed.

Each bidder also has a configuration. For this challenge we have significantly
reduced the configuration to consist of an adjustment to be applied to each bid
to account for the difference between how much the bidder claims that they'll
pay, and how much they will actually pay, based on historical data.

## Running the auctions

On start-up, your program should load the config file (`config.json`) which lists
all valid sites and bidders, and their respective configurations.

The program should then load the input (JSON) from standard input that contains
a list of auctions to run. Each auction lists the site, which ad units are being
bid on, and a list of bids that have been requested on your behalf.

For each auction, you should find the highest valid bidder for each ad unit, after
applying the adjustment factor. An adjustment factor of -0.01 means that bids are
reduced by 1%; an adjustment of 0.05 would increase them by 5%. (Positive
adjustments are rare in real life, but possible.)
For example, a bid of $0.95 and an adjustment
factor of 0.05 (adjusted to $0.9975) will beat a bid of $1.10 with an adjustment
factor of -0.1 (adjusted to $0.99). When reporting the winners, use the bid
amounts provided by the bidder, rather than the adjusted values.

It is possible that a bidder will submit multiple bids for the same ad unit in
the same auction.

A bid is invalid and should be ignored if the bidder is not permitted to bid on
this site, the bid is for an ad unit not involved in this auction, the bidder
is unknown, or if the _adjusted_ bid value is less than the site's floor.

An auction is invalid if the site is unrecognized, or there are no valid bids.
In the case of an invalid auction, just return an empty list.

The output of your program should be a list of auction results. The result of
each auction is a list of winning bids.

## Inputs

Ensuring that inputs are well-formed (e.g. all fields are present and are of the
expected types) is important, but also uninteresting. You may therefore assume
that all inputs will be well-formed. All numeric values will be 64-bit floats (as
is default for JSON).

## Sample code and Dockerfiles

Sample config, inputs and expected output are included in this repo to help you
test your submission.

Also included are some sample Dockerfiles for various languages, to be used for
building and testing your submission. Rename the appropriate template to
`Dockerfile`, or create your own. By using Docker, you can ensure that we are
building and running your submission with the same toolchain as you.

## Example build and execution

```bash
$ docker build -t challenge .
$ docker run -i -v /path/to/challenge/config.json:/auction/config.json challenge < /path/to/challenge/input.json
```
