package cn.tanknee.java.pa.utils;

public class MyStringUtils {
    public static boolean isNumber(String o) {
        char[] str = new char[o.length()];
        str = o.toCharArray();
        for (char c : str) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}
