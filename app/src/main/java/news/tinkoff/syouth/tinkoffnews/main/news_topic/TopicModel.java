package news.tinkoff.syouth.tinkoffnews.main.news_topic;

import android.support.annotation.NonNull;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import news.tinkoff.syouth.tinkoffnews.main.data.Misc;
import news.tinkoff.syouth.tinkoffnews.main.data.NetworkDataProvider;
import news.tinkoff.syouth.tinkoffnews.main.data.PersistantDataProvider;
import news.tinkoff.syouth.tinkoffnews.main.utils.JSONParser;
import news.tinkoff.syouth.tinkoffnews.main.utils.LogUtils;
import news.tinkoff.syouth.tinkoffnews.main.utils.Result;

/**
 * Created by antonivanov on 24.03.18.
 */

class TopicModel implements TopicContract.Model {

    private static final String URL = "https://api.tinkoff.ru/v1/news_content?id=";

    private NetworkDataProvider mNetwork;
    private PersistantDataProvider mPersistant;

    TopicModel(NetworkDataProvider network, PersistantDataProvider persistantDataProvider) {
        mNetwork = network;
        mPersistant = persistantDataProvider;
    }

    private Misc.DataResult getContentInternal(long id) throws IOException {
        try {
            String cached = mPersistant.Load(getCacheFileName(id));
            return new Misc.DataResult(Misc.DataResult.Source.CACHE, cached);
        } catch (IOException e) {
            LogUtils.D("Missed cache!");
        }

        return new Misc.DataResult(Misc.DataResult.Source.NETWORK, mNetwork.getContent(URL + String.valueOf(id)));
    }


    @Override
    public void getContent(long id, Result<String> result) {
        Observable.fromCallable(() -> getContentInternal(id)).
                subscribeOn(Schedulers.io()).
                observeOn(Schedulers.io()).
                map(val -> {
                    LogUtils.D("Got content from: " + val.src.name());
                    if (val.src == Misc.DataResult.Source.NETWORK)
                        mPersistant.Store(val.val, getCacheFileName(id));
                    return JSONParser.parseContent(val.val);
                }).subscribe(result::onSuccess, result::onError);

    }

    @NonNull
    private String getCacheFileName(long id) {
        return String.valueOf(id) + ".txt";
    }
}
