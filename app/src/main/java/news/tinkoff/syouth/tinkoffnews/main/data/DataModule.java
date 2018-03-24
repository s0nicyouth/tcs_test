package news.tinkoff.syouth.tinkoffnews.main.data;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by antonivanov on 24.03.18.
 */

@Module
public class DataModule {
    @Provides
    PersistantDataProvider providePersistantStorage(Context context) {
        return new PersistantDataProvider(context);
    }

    @Provides
    NetworkDataProvider provideNetworkData() {
        return new NetworkDataProvider();
    }
}
