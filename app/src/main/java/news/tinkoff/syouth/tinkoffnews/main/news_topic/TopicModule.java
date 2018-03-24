package news.tinkoff.syouth.tinkoffnews.main.news_topic;

import dagger.Module;
import dagger.Provides;
import news.tinkoff.syouth.tinkoffnews.main.data.NetworkDataProvider;
import news.tinkoff.syouth.tinkoffnews.main.data.PersistantDataProvider;

/**
 * Created by antonivanov on 24.03.18.
 */

@Module
public class TopicModule {
    @Provides
    TopicContract.Presenter providePresenter(TopicContract.Model model, TopicContract.View view) {
        return new TopicPresenter(model, view);
    }

    @Provides
    TopicContract.Model provideModel(NetworkDataProvider network, PersistantDataProvider persistant) {
        return new TopicModel(network, persistant);
    }
}
