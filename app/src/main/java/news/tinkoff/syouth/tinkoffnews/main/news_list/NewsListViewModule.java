package news.tinkoff.syouth.tinkoffnews.main.news_list;

import dagger.Binds;
import dagger.Module;

/**
 * Created by antonivanov on 24.03.18.
 */

@Module
public abstract class NewsListViewModule {
    @Binds
    abstract NewsListContract.View provideNewsListView(NewsListView newsListView);
}
