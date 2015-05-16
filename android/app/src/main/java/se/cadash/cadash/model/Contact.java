package se.cadash.cadash.model;

import android.media.Image;

/**
 * @author Alexander Håkansson
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private int debt;

    public Contact(String firstName, String lastName, String email, int debt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.debt = debt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getDebt() {
        return this.debt;
    }
}
