package cadash;

import java.util.ArrayList;

/**
 * Created by parke_000 on 2015-05-16.
 */
public class DataModel {
    ArrayList<Debt> debts;
    ArrayList<User> users;
    public DataModel() {
        this.debts = new ArrayList<Debt>();
        this.users = new ArrayList<User>();
    }

    public void addDebt(Debt debt){
        this.debts.add(debt);
    }

    public void register(User user){
        this.users.add(user);
    }
}
