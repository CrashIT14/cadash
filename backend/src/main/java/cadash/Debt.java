package cadash;

import javax.jws.soap.SOAPBinding;

/**
 * Created by Hampus Dahlin on 2015-05-16.
 */
public class Debt {
    private final User u1; //I
    private final User u2; //you
    private int amount; //positive if u1 owes u2, negative otherwise

    public Debt(User to, User from, int amount) {
        this.u1 = to;
        this.u2 = from;
        this.amount = amount;
    }

    /**
     * Adds the amounts of two debts together.
     * @param other
     */
    public void combinewith(Debt other) {
        if((amount > 0 && this.getU1().equals(other.getU1())) || amount < 0 && this.getU1().equals(other.getU2())){ // u1 owes u2
            this.amount += other.amount;
        }else{// u2 owes u1
            this.amount -= other.amount;
        }
    }

    public boolean isCombineable(Debt other) {
        if(!this.getU1().equals(other.getU1()) && !this.getU1().equals(other.getU2())){
            return false;
        }
        if (!this.getU2().equals(other.getU1()) && !this.getU2().equals(other.getU2())){
            return false;
        }
        return true;
    }

    public User getU1() {
        return u1;
    }

    public User getU2() {
        return u2;
    }

    /**
     * amount is positive if u1 owes u2, negative otherwise
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Debt{" +
                "u1=" + u1 +
                ", u2=" + u2 +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Debt debt = (Debt) o;

        if (amount != debt.amount) return false;
        if (u1 != null ? !u1.equals(debt.u1) : debt.u1 != null) return false;
        return !(u2 != null ? !u2.equals(debt.u2) : debt.u2 != null);
    }

    @Override
    public int hashCode() {
        int result = u1 != null ? u1.hashCode() : 0;
        result = 31 * result + (u2 != null ? u2.hashCode() : 0);
        result = 31 * result + amount;
        return result;
    }
}
