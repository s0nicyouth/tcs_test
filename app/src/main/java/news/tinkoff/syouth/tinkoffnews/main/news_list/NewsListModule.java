package news.tinkoff.syouth.tinkoffnews.main.news_list;

import dagger.Module;
import dagger.Provides;
import news.tinkoff.syouth.tinkoffnews.main.data.NetworkDataProvider;
import news.tinkoff.syouth.tinkoffnews.main.data.PersistantDataProvider;

/**
 * Created by antonivanov on 24.03.18.
 */

@Module
public class NewsListModule {

    @Provides
    NewsListContract.Presenter provideNewsListPresenter(NewsListContract.View view, NewsListContract.Model model) {
        return new NewsListPresenter(view, model);
    }

    @Provides
    NewsListContract.Model provideNewsListModel(PersistantDataProvider provider, NetworkDataProvider networkDataProvider) {
        return new NewsListModel(provider, networkDataProvider);
    }
}
