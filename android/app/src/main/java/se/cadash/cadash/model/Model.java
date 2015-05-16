package se.cadash.cadash.model;

import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

/**
 * @author Alexander Håkansson
 */
public class Model implements IModel,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private static IModel instance = null;

    public Model() {

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
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
