package se.cadash.cadash.model;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import java.util.List;

/**
 * @author Alexander Håkansson
 */
public class Model implements IModel {


    private static IModel instance = null;

    private GoogleApiClient googleApiClient;

    private boolean isLoginIntentActive = false;

    public Model() {

    }

    public static IModel getInstance() {
        if (instance == null) {
            instance = new Model();
        }

        return instance;
    }

    @Override
    public List<Contact> getContacts() {
        return null;
    }

    @Override
    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    @Override
    public void setGoogleApiClient(GoogleApiClient client) {
        this.googleApiClient = client;
    }

    @Override
    public String getUserEmail() {
        if (googleApiClient != null) {
            return Plus.AccountApi.getAccountName(googleApiClient);
        } else {
            return "No user";
        }
    }
}
