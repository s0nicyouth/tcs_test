package news.tinkoff.syouth.tinkoffnews.main;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.HasActivityInjector;
import news.tinkoff.syouth.tinkoffnews.main.di.DaggerAppComponent;

/**
 * Created by antonivanov on 24.03.18.
 */

public class App extends DaggerApplication implements HasActivityInjector {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
