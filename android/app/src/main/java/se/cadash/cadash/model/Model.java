package se.cadash.cadash.model;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import java.util.List;

/**
 * @author Alexander Håkansson
 */
public class Model implements IModel,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static IModel instance = null;
    private static Context context;
    private GoogleApiClient googleApiClient;

    public Model() {

    }

    public static IModel getInstance(Context context) {

        Model.context = context;

        if (instance == null) {
            instance = new Model();
        }

        return instance;
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public List<Contact> getContacts() {
        return null;
    }

    @Override
    public void addSignInListener(SignInListener listener) {

    }

    @Override
    public void signIn() {

    }

    @Override
    public void initialize() {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope("profile"))
                .build();
    }

    @Override
    public void connect() {
        googleApiClient.connect();
    }

    @Override
    public void disconnect() {
        googleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
