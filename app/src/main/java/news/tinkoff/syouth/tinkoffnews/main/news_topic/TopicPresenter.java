package news.tinkoff.syouth.tinkoffnews.main.news_topic;

import news.tinkoff.syouth.tinkoffnews.main.utils.Result;

/**
 * Created by antonivanov on 24.03.18.
 */

public class TopicPresenter implements TopicContract.Presenter {
    private TopicContract.Model mModel;
    private TopicContract.View mView;

    TopicPresenter(TopicContract.Model model, TopicContract.View view) {
        mModel = model;
        mView = view;
    }

    @Override
    public void onLoad(long id) {
        mModel.getContent(id, new Result<String>() {
            @Override
            public void onSuccess(String res) {
                mView.showContent(res);
            }

            @Override
            public void onError(Throwable e) {
                mView.showError();
            }
        });
    }
}
