package se.cadash.cadash.model;


import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

/**
 * @author Alexander Håkansson
 */
public interface IModel {
    List<Contact> getContacts();
    GoogleApiClient getGoogleApiClient();
    void setGoogleApiClient(GoogleApiClient client);
    String getUserEmail();
}
