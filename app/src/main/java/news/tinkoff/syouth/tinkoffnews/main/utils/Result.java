package news.tinkoff.syouth.tinkoffnews.main.utils;

/**
 * Created by antonivanov on 24.03.18.
 */

public interface Result<T> {
    void onSuccess(T res);
    void onError(Throwable e);
}
