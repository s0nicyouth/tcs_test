package news.tinkoff.syouth.tinkoffnews.main.news_list;

import java.io.IOException;
import java.util.List;

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

public class NewsListModel implements NewsListContract.Model {

    private static final String CACHE_FILE = "cache.json";
    private static final String NEWS_URL = "https://api.tinkoff.ru/v1/news";

    private NetworkDataProvider mNetworkProvider;
    private PersistantDataProvider mPersistantData;

    NewsListModel(PersistantDataProvider persistantDataProvider, NetworkDataProvider networkDataProvider) {
        mPersistantData = persistantDataProvider;
        mNetworkProvider = networkDataProvider;
    }

    private Misc.DataResult getNewsInternal() throws IOException {
        try {
            String cached = mPersistantData.Load(CACHE_FILE);
            return new Misc.DataResult(Misc.DataResult.Source.CACHE, cached);
        } catch (IOException e) {
            LogUtils.D("Missed cache");
        }

        return new Misc.DataResult(Misc.DataResult.Source.NETWORK, mNetworkProvider.getContent(NEWS_URL));
    }


    @Override
    public void getNews(Result<List<NewsListContract.Topic>> result) {
        Observable.fromCallable(this::getNewsInternal).
                subscribeOn(Schedulers.io()).
                observeOn(Schedulers.io()).
                map(val -> {
                    LogUtils.D("Got data from: " + val.src.name());
                    if (val.src == Misc.DataResult.Source.NETWORK)
                        mPersistantData.Store(val.val, CACHE_FILE);
                    return JSONParser.ParseFeed(val.val);
                }).
                flatMapIterable(topics -> topics).
                toSortedList((l, r) -> (int) Math.signum(r.getDate() - l.getDate())).
                subscribe(result::onSuccess, result::onError);

    }

    @Override
    public void refreshNews(Result<Object> result) {
        LogUtils.D("Refreshing...");
        Observable.fromCallable(() ->  {
            mPersistantData.clearData();
            return mNetworkProvider.getContent(NEWS_URL);
        }).
                subscribeOn(Schedulers.io()).
                observeOn(Schedulers.io()).
                map(val -> {
                    mPersistantData.Store(val, CACHE_FILE);
                    LogUtils.D("Done refreshing!");
                    return val;
                }).
                subscribe(result::onSuccess, result::onError);
    }
}
