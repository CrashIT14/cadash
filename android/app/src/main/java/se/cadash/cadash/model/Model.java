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
    private Context context;
    private GoogleApiClient googleApiClient;

    public Model() {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope("profile"))
                .build();
    }

    public static IModel getInstance() {
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

    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
