package news.tinkoff.syouth.tinkoffnews.main.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import news.tinkoff.syouth.tinkoffnews.main.App;

/**
 * Created by antonivanov on 24.03.18.
 */

@Module
public class AppModule {
    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }
}
