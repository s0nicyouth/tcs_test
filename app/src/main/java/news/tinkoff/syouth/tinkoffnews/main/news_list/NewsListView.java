package news.tinkoff.syouth.tinkoffnews.main.news_list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import news.tinkoff.syouth.tinkoffnews.R;
import news.tinkoff.syouth.tinkoffnews.main.data.Misc;
import news.tinkoff.syouth.tinkoffnews.main.news_topic.TopicView;
import news.tinkoff.syouth.tinkoffnews.main.utils.SystemUtils;

public class NewsListView extends AppCompatActivity implements NewsListContract.View {

    @BindView(R.id.news_list) RecyclerView mRecycler;
    @BindView(R.id.swipe) SwipeRefreshLayout mSwipeRefresh;

    @Inject NewsListContract.Presenter mPresenter;

    private Adapter mAdapter = new Adapter();

    private class Adapter extends RecyclerView.Adapter<Adapter.VH> {

        private List<NewsListContract.Topic> mTopics = new ArrayList<>();

        class VH extends RecyclerView.ViewHolder {

            TextView mDate;
            TextView mText;
            LinearLayout mLayout;

            VH(LinearLayout itemView) {
                super(itemView);
                mLayout = itemView;
                mText = itemView.findViewById(R.id.news_headline);
                mDate = itemView.findViewById(R.id.date);
            }
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LinearLayout l = (LinearLayout) LayoutInflater.from(NewsListView.this).inflate(R.layout.news_topic, parent, false);
            VH vh = new VH(l);

            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.mText.setText(Html.fromHtml(mTopics.get(holder.getAdapterPosition()).getHeadline()));
            Date date = new Date(mTopics.get(holder.getAdapterPosition()).getDate());
            DateFormat format = DateFormat.getDateInstance();
            holder.mDate.setText(format.format(date));
            holder.mLayout.setOnClickListener(view -> mPresenter.onTopicSelected(mTopics.get(holder.getAdapterPosition()).getId()));
        }

        @Override
        public int getItemCount() {
            return mTopics.size();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycler.setLayoutManager(linearLayoutManager);
        mRecycler.setHasFixedSize(true);
        mRecycler.setAdapter(mAdapter);
        mRecycler.addItemDecoration(new DividerItemDecoration(this, linearLayoutManager.getOrientation()));

        mSwipeRefresh.setOnRefreshListener(() -> mPresenter.onRefresh());

        mPresenter.onLoad();
    }

    @Override
    public void setNews(List<NewsListContract.Topic> news) {
        SystemUtils.runOnUiThread(() -> {
            mAdapter.mTopics = news;
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showError() {
        SystemUtils.runOnUiThread(() -> Toast.makeText(NewsListView.this, R.string.msg_error, Toast.LENGTH_LONG).show());
    }

    @Override
    public void showProgress() {
        SystemUtils.runOnUiThread(() -> {
            mSwipeRefresh.setRefreshing(true);
            mRecycler.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    public void showData() {
        SystemUtils.runOnUiThread(() -> {
            mSwipeRefresh.setRefreshing(false);
            mRecycler.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void proceedToTopic(long id) {
        Intent topic = new Intent(this, TopicView.class);
        topic.putExtra(Misc.TOPIC_ID, id);
        startActivity(topic);
    }
}
