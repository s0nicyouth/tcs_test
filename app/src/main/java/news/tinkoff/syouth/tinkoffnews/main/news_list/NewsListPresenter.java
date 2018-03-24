package news.tinkoff.syouth.tinkoffnews.main.news_list;

import java.util.ArrayList;
import java.util.List;

import news.tinkoff.syouth.tinkoffnews.main.utils.Result;

/**
 * Created by antonivanov on 24.03.18.
 */

public class NewsListPresenter implements NewsListContract.Presenter {

    private NewsListContract.Model mModel;
    private NewsListContract.View mView;

    private List<NewsListContract.Topic> mEmptyTopics = new ArrayList<>();

    NewsListPresenter(NewsListContract.View view, NewsListContract.Model model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void onLoad() {
        mView.showProgress();
        mModel.getNews(new Result<List<NewsListContract.Topic>>() {
            @Override
            public void onSuccess(List<NewsListContract.Topic> res) {
                mView.setNews(res);
                mView.showData();
            }

            @Override
            public void onError(Throwable e) {
                onFail();
            }
        });
    }

    @Override
    public void onRefresh() {
        mView.showProgress();
        mModel.refreshNews(new Result<Object>() {
            @Override
            public void onSuccess(Object res) {
                onLoad();
            }

            @Override
            public void onError(Throwable e) {
                onFail();
            }
        });
    }

    private void onFail() {
        mView.setNews(mEmptyTopics);
        mView.showError();
        mView.showData();
    }

    @Override
    public void onTopicSelected(long id) {
        mView.proceedToTopic(id);
    }
}
