package util;

import java.nio.charset.Charset;
import java.util.Random;

public class RandomUser {

    public static String randomUser() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String randomUser = new String(array, Charset.forName("UTF-8"));
        return randomUser;
    }

}
