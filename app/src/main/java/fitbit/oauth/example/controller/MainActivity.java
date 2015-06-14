package fitbit.oauth.example.controller;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import io.oauth.OAuth;
import io.oauth.OAuthCallback;
import io.oauth.OAuthData;

public class MainActivity extends ActionBarActivity implements OAuthCallback {

    private OAuth o = null;

    public static OAuthData oAuthData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fitbit.oauth.example.R.layout.activity_main);

        o = new OAuth(this);
        o.initialize(""); // Initialize the oauth key
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(fitbit.oauth.example.R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == fitbit.oauth.example.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginWithFitbit(View view) {
        JSONObject opts = new JSONObject();
        try {
            opts.put("cache", true);
        } catch (JSONException e) {
            Log.v("RunBabyRun", e.getMessage());
        }

        o.popup("fitbit", opts, MainActivity.this);
    }

    @Override
    public void onFinished(OAuthData oAuthData) {

        this.oAuthData = oAuthData;

        if (oAuthData.status.equals("success")) {
            Intent intent = new Intent(this, GetStepsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
        }

    }
}
