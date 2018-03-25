package news.tinkoff.syouth.tinkoffnews.main.base;

import java.lang.ref.WeakReference;

/**
 * Created by antonivanov on 25.03.18.
 */

public class BasePresenter<T> {
    public interface ViewRunner<T> {
        void run(T view);
    }

    private WeakReference<T> mView;

    protected BasePresenter(T view) {
        mView = new WeakReference<>(view);
    }

    protected void runOnView(ViewRunner<T> runner) {
        T v = mView.get();
        if (v == null)
            return;
        runner.run(v);
    }
}
