package news.tinkoff.syouth.tinkoffnews.main.news_list;

import java.util.List;

import news.tinkoff.syouth.tinkoffnews.main.utils.Result;

/**
 * Created by antonivanov on 24.03.18.
 */

public interface NewsListContract {

    class Topic {
        private long mId;
        private String mHeadline;
        private long mDate;

        public Topic(long id, String headline, long date) {
            mId = id;
            mHeadline = headline;
            mDate = date;
        }

        public long getId() {
            return mId;
        }

        String getHeadline() {
            return mHeadline;
        }

        long getDate() {
            return mDate;
        }
    }

    interface View {
        void setNews(List<Topic> news);
        void showError();
        void showProgress();
        void showData();
        void proceedToTopic(long id);
    }
    interface Presenter {
        void onLoad();
        void onRefresh();
        void onTopicSelected(long id);
    }
    interface Model {
        void getNews(Result<List<Topic>> result);
        void refreshNews(Result<Object> result);
    }
}
