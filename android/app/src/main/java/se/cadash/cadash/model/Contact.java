package se.cadash.cadash.model;

import android.media.Image;

/**
 * @author Alexander Håkansson
 */
public class Contact {
    private String firstName;
    private String lastName;
    private Image photo;
    private Debt debt = new Debt();

    public Contact(String firstName, String lastName, Image photo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    public Contact(String firstName, String lastName, Image photo, Debt debt) {
        this(firstName, lastName, photo);
        this.debt = debt;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Image getPhoto() {
        return photo;
    }

    public Debt getDebt() {
        return this.debt;
    }
}
