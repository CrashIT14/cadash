package se.cadash.cadash.model;

import android.util.Log;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.PersonBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Håkansson
 */
public class Model implements IModel, ResultCallback<People.LoadPeopleResult> {


    private static IModel instance = null;

    private GoogleApiClient googleApiClient;

    private boolean isLoginIntentActive = false;

    private List<Contact> contacts = new ArrayList<Contact>();

    public Model() {

    }

    public static IModel getInstance() {
        if (instance == null) {
            instance = new Model();
        }

        return instance;
    }

    public void getContacts(BackendSyncListener listener) {
        new BackendConnetion(BackendConnetion.SERVER_REQUEST.SYNC).sync(listener, getUserEmail());
    }

    @Override
    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }

    @Override
    public void setGoogleApiClient(GoogleApiClient client) {
        this.googleApiClient = client;

        Plus.PeopleApi.loadVisible(this.googleApiClient, null).setResultCallback(this);
    }

    @Override
    public String getUserEmail() {
        if (googleApiClient != null) {
            return Plus.AccountApi.getAccountName(googleApiClient);
        } else {
            return "No user";
        }
    }

    @Override
    public Contact getContact(String id) {
        for (Contact c : contacts) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void onResult(People.LoadPeopleResult loadPeopleResult) {
        if (loadPeopleResult.getStatus().getStatusCode() == CommonStatusCodes.SUCCESS) {
            PersonBuffer personBuffer = loadPeopleResult.getPersonBuffer();
            try {
                int count = personBuffer.getCount();
                for (int i = 0; i < count; i++) {
                    Person p = personBuffer.get(i);
                    String firstName = p.getName().getFamilyName();
                    String lastName = p.getName().getGivenName();
                    String id = p.getId();
                    Person.Image img = p.getImage();

                    Contact c = new Contact(firstName, lastName, id, 0);
                    c.setImageUrl(img.getUrl());

                    contacts.add(c);
                }
            } finally {
                personBuffer.close();
            }
        } else {
            System.err.println("Couldn't get circles");
        }
    }
}
