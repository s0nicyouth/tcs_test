package news.tinkoff.syouth.tinkoffnews.main.data;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import news.tinkoff.syouth.tinkoffnews.main.utils.LogUtils;

/**
 * Created by antonivanov on 24.03.18.
 */

public class PersistantDataProvider {

    private Context mContext;

    PersistantDataProvider(Context context) {
        mContext = context;
    }

    public void Store(String str, String fName) throws IOException {
        LogUtils.D("Storing to cache on " + Thread.currentThread().getName());
        FileOutputStream fileOutputStream = mContext.openFileOutput(fName, Context.MODE_PRIVATE);
        fileOutputStream.write(str.getBytes());
        fileOutputStream.close();
    }

    public String Load(String fName) throws IOException {
        LogUtils.D("Trying to load from cache on " + Thread.currentThread().getName());
        FileInputStream fileInputStream = mContext.openFileInput(fName);
        byte[] res = new byte[(int) mContext.getFileStreamPath(fName).length()];
        fileInputStream.read(res);
        fileInputStream.close();
        return new String(res);
    }

    public void clearData() {
        clearDataInternal(mContext.getFilesDir());

    }

    private static void clearDataInternal(File dir) {
        for (String c : dir.list()) {
            File child = new File(dir, c);
            if (child.isDirectory())
                clearDataInternal(child);
            child.delete();
        }
    }
}
