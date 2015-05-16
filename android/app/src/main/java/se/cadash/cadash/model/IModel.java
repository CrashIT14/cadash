package se.cadash.cadash.model;


import java.util.List;

/**
 * @author Alexander Håkansson
 */
public interface IModel {
    List<Contact> getContacts();
    void signIn();
    void initialize();
    void connect();
    void disconnect();
}
