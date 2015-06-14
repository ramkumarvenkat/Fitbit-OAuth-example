package fitbit.oauth.example.model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by rvenkataraman on 10/06/15.
 */
public interface FitbitAPIInterface {

    @GET("/1/user/-/activities/steps/date/today/1d.json")
    void getSteps(@Header("oauth_token") String token, Callback<FitbitGetActivities> cb);


}
