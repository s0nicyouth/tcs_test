package news.tinkoff.syouth.tinkoffnews.main.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import news.tinkoff.syouth.tinkoffnews.main.news_list.NewsListContract;

/**
 * Created by antonivanov on 24.03.18.
 */

public class JSONParser {

    private static final String RESULT_CODE = "resultCode";

    public static List<NewsListContract.Topic> ParseFeed(String str) throws Exception {
        JSONArray payload = extractPayload(str);

        List<NewsListContract.Topic> result = new ArrayList<>();
        for (int i = 0; i < payload.length(); i++) {
            JSONObject curObj = payload.getJSONObject(i);
            result.add(new NewsListContract.Topic(
                    curObj.getLong("id"),
                    curObj.getString("text"),
                    curObj.getJSONObject("publicationDate").getLong("milliseconds")));
        }

        return result;
    }

    public static String parseContent(String str) throws Exception {
        JSONObject payload = extractPayload(str);
        return payload.getString("content");
    }

    private static<T> T extractPayload(String str) throws Exception {
        JSONObject obj = new JSONObject(str);
        if (!obj.getString("resultCode").equals("OK"))
            throw new Exception("Wrong result code");
        return (T) obj.get("payload");
    }
}
