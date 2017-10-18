package lab373.android.bluetoothlegatt.data;
import java.util.HashMap;
/**
 * Created by ianzhang on 10/17/17.
 */

/**
 * TODO: What's the default value if there is no device code found
 */
public class DeviceCodeTable {
  private static HashMap<String, String> deviceCodeTable = new HashMap();

  static {
    deviceCodeTable.put("EnableModeOption", "88 01");
  }

  public static String lookup(String key) {
    String deviceCode = deviceCodeTable.get(key);
    return deviceCode;
  }
}
