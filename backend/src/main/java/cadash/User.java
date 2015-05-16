package cadash;

import java.util.ArrayList;

/**
 * Created by parke_000 on 2015-05-16.
 */
public class User {
    private final String uid;

    public User(String uid) {
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(uid != null ? !uid.equals(user.uid) : user.uid != null);

    }

    @Override
    public int hashCode() {
        return uid != null ? uid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                '}';
    }
}
