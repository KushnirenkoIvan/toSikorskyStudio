package webapp.util;

import org.json.simple.JSONObject;
import webapp.domain.TextString;

/**
 * Created by ivan on 11.10.15.
 */
public class JSONConvertor {

    public static String convertToJSON(TextString textString) {
        if (textString == null) {
            throw new IllegalArgumentException();
        }
        JSONObject resultJson = new JSONObject();
        resultJson.put("id", textString.getId().toString());
        resultJson.put("info", textString.getInfo());
        return resultJson.toString();
    }


}
