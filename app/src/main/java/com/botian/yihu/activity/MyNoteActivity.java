package com.botian.yihu.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.botian.yihu.R;
import com.botian.yihu.adapter.MyCollectAdapter;
import com.botian.yihu.adapter.MyNoteAdapter;
import com.botian.yihu.database.NoteData;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MyNoteActivity extends RxAppCompatActivity {
    @BindView(R.id.comment_recycler_view)
    XRecyclerView commentRecyclerView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.num)
    TextView num;
    //ObserverOnNextListener<MyCollection> listener;
    //private ACache mCache;
    //private UserInfo userInfo;
    private List<NoteData> collectList;
    MyNoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_note);
        ButterKnife.bind(this);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                collectList = DataSupport
                        .order("id desc")
                        .find(NoteData.class);

                emitter.onNext(1);

                //emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Integer>bindToLifecycle())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Log.d(TAG, "subscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        //int v=value;
                        //禁用下拉刷新和加载更多功能
                        String a=collectList.size()+"";
                        num.setText(a);
                        commentRecyclerView.setPullRefreshEnabled(false);
                        commentRecyclerView.setLoadingMoreEnabled(false);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MyNoteActivity.this);
                        commentRecyclerView.setLayoutManager(layoutManager);
                        adapter = new MyNoteAdapter(MyNoteActivity.this, collectList);
                        commentRecyclerView.setAdapter(adapter);
                        //init();
                        //Log.d(TAG, "" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.d(TAG, "error");
                    }

                    @Override
                    public void onComplete() {
                        //Log.d(TAG, "complete");
                    }
                });
        //mCache= ACache.get(this);
        //从缓存读取用户信息
        //userInfo = (UserInfo) mCache.getAsObject("userInfo");
        /*listener = new ObserverOnNextListener<MyCollection>() {
            @Override
            public void onNext(MyCollection data) {
                //禁用下拉刷新和加载更多功能
                commentRecyclerView.setPullRefreshEnabled(false);
                commentRecyclerView.setLoadingMoreEnabled(false);
                LinearLayoutManager layoutManager = new LinearLayoutManager(MyCollectionActivity.this);
                commentRecyclerView.setLayoutManager(layoutManager);
                MyCollectAdapter adapter = new MyCollectAdapter(MyCollectionActivity.this, data.getData());
                commentRecyclerView.setAdapter(adapter);
            }
        };
        int userId=userInfo.getId();
        String filter="userid,eq,"+userId;
        ApiMethods.getCollectionRecords(new ProgressObserver<MyCollection>(MyCollectionActivity.this, listener),  filter,this);
*/
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                collectList.clear();
                collectList.addAll(DataSupport
                        .order("id desc")
                        .find(NoteData.class));

                emitter.onNext(1);

                //emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(this.<Integer>bindToLifecycle())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //Log.d(TAG, "subscribe");
                    }

                    @Override
                    public void onNext(Integer value) {
                        //int v=value;
                        String a=collectList.size()+"";
                        num.setText(a);
                        adapter.notifyDataSetChanged();
                        //init();
                        //Log.d(TAG, "" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.d(TAG, "error");
                    }

                    @Override
                    public void onComplete() {
                        //Log.d(TAG, "complete");
                    }
                });
    }
}
