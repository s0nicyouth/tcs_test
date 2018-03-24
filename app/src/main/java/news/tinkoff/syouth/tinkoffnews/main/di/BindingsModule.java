package news.tinkoff.syouth.tinkoffnews.main.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import news.tinkoff.syouth.tinkoffnews.main.news_list.NewsListModule;
import news.tinkoff.syouth.tinkoffnews.main.news_list.NewsListView;
import news.tinkoff.syouth.tinkoffnews.main.news_list.NewsListViewModule;
import news.tinkoff.syouth.tinkoffnews.main.news_topic.TopicModule;
import news.tinkoff.syouth.tinkoffnews.main.news_topic.TopicView;
import news.tinkoff.syouth.tinkoffnews.main.news_topic.TopicViewModule;

/**
 * Created by antonivanov on 24.03.18.
 */

@Module
abstract class BindingsModule {
    @ContributesAndroidInjector(modules = {
            NewsListViewModule.class,
            NewsListModule.class
    })
    abstract NewsListView bindNewsListActivity();
    @ContributesAndroidInjector(modules = {
            TopicViewModule.class,
            TopicModule.class
    })
    abstract TopicView bindTopicView();
}
