package news.tinkoff.syouth.tinkoffnews.main.news_list;

import java.util.ArrayList;
import java.util.List;

import news.tinkoff.syouth.tinkoffnews.main.base.BasePresenter;
import news.tinkoff.syouth.tinkoffnews.main.utils.Result;

/**
 * Created by antonivanov on 24.03.18.
 */

public class NewsListPresenter extends BasePresenter<NewsListContract.View> implements NewsListContract.Presenter {

    private NewsListContract.Model mModel;

    private List<NewsListContract.Topic> mEmptyTopics = new ArrayList<>();

    NewsListPresenter(NewsListContract.View view, NewsListContract.Model model) {
        super(view);
        mModel = model;
    }

    @Override
    public void onLoad() {
        runOnView(NewsListContract.View::showProgress);
        mModel.getNews(new Result<List<NewsListContract.Topic>>() {
            @Override
            public void onSuccess(List<NewsListContract.Topic> res) {
                runOnView(view -> {
                    view.setNews(res);
                    view.showData();
                });
            }

            @Override
            public void onError(Throwable e) {
                onFail();
            }
        });
    }

    @Override
    public void onRefresh() {
        runOnView(NewsListContract.View::showProgress);
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
        runOnView(view -> {
            view.setNews(mEmptyTopics);
            view.showError();
            view.showData();
        });
    }

    @Override
    public void onTopicSelected(long id) {
        runOnView(view -> view.proceedToTopic(id));
    }
}
