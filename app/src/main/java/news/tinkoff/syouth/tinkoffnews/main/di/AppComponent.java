package news.tinkoff.syouth.tinkoffnews.main.di;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import news.tinkoff.syouth.tinkoffnews.main.App;
import news.tinkoff.syouth.tinkoffnews.main.data.DataModule;
import news.tinkoff.syouth.tinkoffnews.main.news_list.NewsListModule;
import news.tinkoff.syouth.tinkoffnews.main.news_list.NewsListViewModule;
import news.tinkoff.syouth.tinkoffnews.main.news_topic.TopicModule;
import news.tinkoff.syouth.tinkoffnews.main.news_topic.TopicViewModule;

/**
 * Created by antonivanov on 24.03.18.
 */

@Component(modules = {
        AndroidSupportInjectionModule.class,
        NewsListViewModule.class,
        NewsListModule.class,
        BindingsModule.class,
        AppModule.class,
        DataModule.class,
        TopicModule.class,
        TopicViewModule.class
})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<App> {}
}
