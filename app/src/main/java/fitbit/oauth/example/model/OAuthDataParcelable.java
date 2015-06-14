package fitbit.oauth.example.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.oauth.OAuthData;

/**
 * Created by rvenkataraman on 11/06/15.
 */
public class OAuthDataParcelable implements Parcelable {

    public OAuthData oAuthData;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(oAuthData);
    }

    public static final Parcelable.Creator<OAuthDataParcelable> CREATOR = new Parcelable.Creator<OAuthDataParcelable>() {
        public OAuthDataParcelable createFromParcel(Parcel in) {
            return new OAuthDataParcelable(in);
        }

        public OAuthDataParcelable[] newArray(int size) {
            return new OAuthDataParcelable[size];
        }
    };

    public OAuthDataParcelable() { }

    public OAuthDataParcelable(Parcel in) {
        oAuthData = (OAuthData) in.readValue(null);
    }
}
