package news.tinkoff.syouth.tinkoffnews.main.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by antonivanov on 24.03.18.
 */

public class SystemUtils {
    public static void runOnUiThread(Runnable r) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            new Handler(Looper.getMainLooper()).post(r);
        } else {
            r.run();
        }
    }
}
