package challenge;

import java.util.Arrays;

public class Utils {

  // convert object array to string array
  public static String[] arrObjToString(Object[] objArr) {
    String[] stringArray = Arrays.copyOf(objArr, objArr.length, String[].class);
    return stringArray;
  }
}