package news.tinkoff.syouth.tinkoffnews.main.news_topic;

import news.tinkoff.syouth.tinkoffnews.main.utils.Result;

/**
 * Created by antonivanov on 24.03.18.
 */

public interface TopicContract {

    interface View {
        void showContent(String res);
        void showError();
    }
    interface Presenter {
        void onLoad(long id);
    }
    interface Model {
        void getContent(long id, Result<String> result);
    }
}
