package news.tinkoff.syouth.tinkoffnews.main.data;

import java.io.IOException;

import news.tinkoff.syouth.tinkoffnews.main.utils.LogUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by antonivanov on 24.03.18.
 */

public class NetworkDataProvider {

    private OkHttpClient mClient = new OkHttpClient();

    public String getContent(String url) throws IOException {
        LogUtils.D("Loading fron network on " + Thread.currentThread().getName());
        Request req = new Request.Builder().
                url(url).
                build();
        Response resp = mClient.newCall(req).execute();
        return resp.body().string();
    }
}
