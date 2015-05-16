package cadash;

import java.util.ArrayList;

/**
 * Created by parke_000 on 2015-05-16.
 */
public class DataModel {
    private ArrayList<Debt> debts;
    private ArrayList<User> users;

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

    public void removeDebt(Debt debt){
        debts.remove(debt);
    }

    public ArrayList<Debt> getDebts(User user) {
        ArrayList<Debt> userDebts = new ArrayList<Debt>();
        for(Debt d : debts){
            if(d.getU1().equals(user) || d.getU2().equals(user)){
                userDebts.add(d);
            }
        }
    }
}
