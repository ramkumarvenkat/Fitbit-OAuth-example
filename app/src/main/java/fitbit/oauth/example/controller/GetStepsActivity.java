package fitbit.oauth.example.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import io.oauth.OAuthRequest;


public class GetStepsActivity extends ActionBarActivity {

    private static final String GET_STEPS_URL = "/1/user/-/activities/steps/date/today/1d.json";

    /*private static final String FITBIT_BASE_URL = "https://api.fitbit.com";
    private static final RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(FITBIT_BASE_URL)
            .build();*/

    private TextView resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(fitbit.oauth.example.R.layout.activity_getsteps);

        resultsView = (TextView) findViewById(fitbit.oauth.example.R.id.textView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(fitbit.oauth.example.R.menu.menu_challenge, menu);
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

    public void startChallenge(View view) {
        /*FitbitAPIInterface fitAPIClient = restAdapter.create(FitbitAPIInterface.class);
        fitAPIClient.getSteps(token, new Callback<FitbitGetActivities>() {

            @Override
            public void success(FitbitGetActivities fitbitGetActivities, Response response) {
                List<FitbitActivitiesLogStep> fitbitActivitiesLogStepList = fitbitGetActivities.getActivitiesLogSteps();
                System.out.println(fitbitActivitiesLogStepList.size());
                System.out.println(fitbitActivitiesLogStepList.get(0).getValue());
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                System.out.println(retrofitError.getMessage());
                Log.v("RunBabyRun", retrofitError.getMessage());
            }
        });*/

        new GetActivitiesTask().execute(GET_STEPS_URL);

    }

    private class GetActivitiesTask extends AsyncTask<String, Void, JSONObject> {

        private JSONObject result = null;

        protected JSONObject doInBackground(String... urls) {

            MainActivity.oAuthData.http(urls[0], new OAuthRequest() {

                private URL url;
                private URLConnection con;

                @Override
                public void onSetURL(String _url) {
                    try {
                        url = new URL(_url);
                        con = url.openConnection();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onSetHeader(String header, String value) {
                    con.addRequestProperty(header, value);
                }

                @Override
                public void onReady() {

                    try {
                        BufferedReader r = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        StringBuilder total = new StringBuilder();
                        String line;
                        while ((line = r.readLine()) != null) {
                            total.append(line);
                        }

                        result = new JSONObject(total.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(String message) {
                    Log.v("RunBabyRun", message);
                }
            });

            return result;
        }

        protected void onPostExecute(JSONObject result) {
            setData(result);
        }
    }

    private void setData(JSONObject result) {
        resultsView.setText(result.toString());
    }
}
