package game;

public class Util {
    public static String rightPad(String s, int len){
        StringBuilder sBuilder = new StringBuilder(s);
        while (sBuilder.length() < len){
            sBuilder.append(" ");
        }
        return sBuilder.toString();
    }
}
