package news.tinkoff.syouth.tinkoffnews.main.news_topic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import news.tinkoff.syouth.tinkoffnews.R;
import news.tinkoff.syouth.tinkoffnews.main.data.Misc;
import news.tinkoff.syouth.tinkoffnews.main.news_list.NewsListContract;
import news.tinkoff.syouth.tinkoffnews.main.utils.SystemUtils;

public class TopicView extends AppCompatActivity implements TopicContract.View {

    @BindView(R.id.topic) TextView mTopic;

    @Inject TopicContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_view);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter.onLoad(getIntent().getExtras().getLong(Misc.TOPIC_ID));
        mTopic.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public void showContent(String res) {
        SystemUtils.runOnUiThread(() -> mTopic.setText(Html.fromHtml(res)));
    }

    @Override
    public void showError() {
        SystemUtils.runOnUiThread(() -> Toast.makeText(TopicView.this, R.string.msg_error, Toast.LENGTH_LONG).show());
    }
}
