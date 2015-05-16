package se.cadash.cadash.model;

import android.media.Image;

/**
 * @author Alexander Håkansson
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String id;
    private int debt;
    private String imageUrl = null;

    public Contact(String firstName, String lastName, String id, int debt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.debt = debt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public int getDebt() {
        return this.debt;
    }

    public void setDebt(int amount) {
        this.debt = amount;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}
