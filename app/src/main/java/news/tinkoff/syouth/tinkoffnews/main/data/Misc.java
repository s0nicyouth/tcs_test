package news.tinkoff.syouth.tinkoffnews.main.data;

/**
 * Created by antonivanov on 24.03.18.
 */

public class Misc {
    public static final String TOPIC_ID = "T_ID";

    public static class DataResult {
        public enum Source {
            NETWORK,
            CACHE
        }

        public Source src;
        public String val;

        public DataResult(Source s, String v) {
            src = s;
            val = v;
        }
    }
}
