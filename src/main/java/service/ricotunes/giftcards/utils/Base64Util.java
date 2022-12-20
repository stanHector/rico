package service.ricotunes.giftcards.utils;

import java.util.Base64;
import java.util.regex.Pattern;

public class Base64Util {

    private static final  Base64.Encoder encoder =  Base64.getUrlEncoder();
    private static final Base64.Decoder decoder = Base64.getUrlDecoder();
    private final static Pattern BASE64_REGEX_PATTERN = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");

    public static boolean isBase64(String value){
        return BASE64_REGEX_PATTERN.matcher(value).matches();
    }
}
