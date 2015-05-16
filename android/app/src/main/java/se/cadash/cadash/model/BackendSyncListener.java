package se.cadash.cadash.model;

import java.util.List;

/**
 * @author Alexander Håkansson
 */
public interface BackendSyncListener {
    void onContactsSynced(List<Contact> contactList);
}
