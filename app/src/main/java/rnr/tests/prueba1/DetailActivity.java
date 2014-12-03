package rnr.tests.prueba1;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class DetailActivity extends ActionBarActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    getWindow().requestFeature(android.view.Window.FEATURE_CONTENT_TRANSITIONS);
    getWindow().requestFeature(android.view.Window.FEATURE_ACTIVITY_TRANSITIONS);
    getWindow().requestFeature(android.view.Window.FEATURE_ACTION_BAR_OVERLAY);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mToolbar);
    getSupportActionBar().setTitle("Detail Activity");
    Bundle extras = getIntent().getExtras();
    int mPositionRef = extras.getInt("position");
    View base = findViewById(R.id.detail_layout);
    View image = findViewById(R.id.imageView);
    TextView text = (TextView) findViewById(R.id.txtLabel);
    text.setText(extras.getString("text"));
    ViewCompat.setTransitionName(base, "cardViewTransition" + mPositionRef);
    ViewCompat.setTransitionName(image, "imageTransition" + mPositionRef);
    ViewCompat.setTransitionName(text, "textTransition" + mPositionRef);
}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
}
