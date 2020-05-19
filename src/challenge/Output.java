package challenge;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.json.simple.JSONArray;

public class Output {
  public void writeToOutputJson(JSONArray bidResults) {
    try {

      Writer fileWriter = new FileWriter("./output.json");
      fileWriter.write(bidResults.toJSONString());
      fileWriter.close();

    } catch (IOException e) {
      e.printStackTrace();

    }
  }
}