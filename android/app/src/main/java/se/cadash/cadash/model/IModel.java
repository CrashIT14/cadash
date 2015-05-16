package se.cadash.cadash.model;


import android.content.Context;

import java.util.List;

/**
 * @author Alexander Håkansson
 */
public interface IModel {
    List<Contact> getContacts();
    void addSignInListener(SignInListener listener);
    void signIn();
    void initialize();
    void connect();
    void disconnect();
    void setContext(Context context);
}
