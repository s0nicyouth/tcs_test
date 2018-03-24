package news.tinkoff.syouth.tinkoffnews.main.news_topic;

import dagger.Binds;
import dagger.Module;

/**
 * Created by antonivanov on 24.03.18.
 */

@Module
public abstract class TopicViewModule {
    @Binds
    abstract TopicContract.View providesView(TopicView view);
}
