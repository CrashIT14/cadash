package cadash;

/**
 * Created by Hampus Dahlin on 2015-05-16.
 */
public class User {
    private final String email;

    public User(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    public String getEmail() {
        return email;
    }
}
