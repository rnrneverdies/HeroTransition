package rnr.tests.prueba1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private Toolbar mToolbar;
    protected RecyclerView mRecyclerView;
    protected TestAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().requestFeature(android.view.Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Home Activity");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new TestAdapter(new ArrayList<String>());
        mRecyclerView.setAdapter(mAdapter);
        final ActionBarActivity selfContext = this;
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(selfContext, DetailActivity.class);

                        View imageView = view.findViewById(R.id.imageView);
                        TextView textView = (TextView)view.findViewById(R.id.textView);

                        Bundle extras = new Bundle();
                        extras.putInt("position", position);
                        extras.putString("text", textView.getText().toString());
                        intent.putExtras(extras);
                        String name = ViewCompat.getTransitionName(view);
                        ActivityOptionsCompat options =
                                ActivityOptionsCompat.makeSceneTransitionAnimation(selfContext,
                                        Pair.create(view, ViewCompat.getTransitionName(view))
                                        ,Pair.create(imageView, ViewCompat.getTransitionName(imageView))
                                        ,Pair.create((View)textView, ViewCompat.getTransitionName(textView))
                                );
                        ActivityCompat.startActivity(selfContext, intent, options.toBundle());
                    }
                })
        );

        producer(200);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void producer(final int count) {
        new Thread() {
            public void run() {
                for (int i = 0; i < count; i++) {
                    final int i2 = i;
                    try {
                        Thread.sleep(100);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    mAdapter.add("This is element #" + i2, i2);
                                } catch (Exception e2) {
                                    Log.e("Error Adding Card", e2.toString());
                                }
                            }
                        });
                        Thread.sleep(50);
                    } catch (Exception e) {
                        Log.e("Error Waiting", e.toString());
                    }
                }
            }
        }.start();
    }
}
