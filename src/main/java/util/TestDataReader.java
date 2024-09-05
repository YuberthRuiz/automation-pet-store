package util;

import java.util.ResourceBundle;

public class TestDataReader {
    public static ResourceBundle resourceBundle() {
        if (System.getProperty("env") != null) {
            return ResourceBundle.getBundle(System.getProperty("env"));
        } else {
            return ResourceBundle.getBundle("local");
        }
    }
    public static String getTestData(String key) {
        return resourceBundle().getString(key);
    }
}
