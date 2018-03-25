package news.tinkoff.syouth.tinkoffnews.main.news_topic;

import news.tinkoff.syouth.tinkoffnews.main.base.BasePresenter;
import news.tinkoff.syouth.tinkoffnews.main.utils.Result;

/**
 * Created by antonivanov on 24.03.18.
 */

public class TopicPresenter extends BasePresenter<TopicContract.View> implements TopicContract.Presenter {
    private TopicContract.Model mModel;

    TopicPresenter(TopicContract.Model model, TopicContract.View view) {
        super(view);
        mModel = model;
    }

    @Override
    public void onLoad(long id) {
        mModel.getContent(id, new Result<String>() {
            @Override
            public void onSuccess(String res) {
                runOnView(view -> view.showContent(res));
            }

            @Override
            public void onError(Throwable e) {
                runOnView(TopicContract.View::showError);
            }
        });
    }
}
