package cadash;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Hampus Dahlin on 2015-05-16.
 */
@RestController
public class ComController {
    private DataModel model = new DataModel();
    private String apikey = "AIzaSyABIqk3govw_RXzCjUkhzWDZk4Xdi-e3yM";

    public ComController() {
        //test();
    }

    @RequestMapping("/register")
    public boolean register(@RequestParam(value="email") String email, @RequestParam(value = "uid", defaultValue="") String uid) {
        User toReg = new User(email);
        model.register(toReg);
        //@TODO Send tickle to users
        return true;
    }

    @RequestMapping("/newDebt")
    public boolean newDebt(@RequestParam(value = "amount", defaultValue = "0") int amount, @RequestParam(value = "user1") String u1, @RequestParam(value = "user2") String u2) {
        User user1 = new User(u1);
        User user2 = new User(u2);
        model.register(user1);
        model.register(user2);
        Debt toAdd = new Debt(user1, user2, amount);
        model.addDebt(toAdd);
        //@TODO Send tickle to users
        return true;
    }

    @RequestMapping("/sync")
    public String sync(@RequestParam(value = "user") String user) {
        User u = new User(user);
        StringBuilder b = new StringBuilder();

        for(Debt d : model.getDebts(u)){
            b.append(d.getU1().toString()+":"+d.getU2().toString()+":"+d.getAmount()+";");
        }

        return b.toString();
    }

    @RequestMapping("/remove")
    public boolean remove(@RequestParam(value = "user1") String u1, @RequestParam(value = "user2") String u2) {
        User user1 = new User(u1);
        User user2 = new User(u2);
        Debt d = new Debt(user1, user2, 0);
        model.removeDebt(d);
        //@TODO Send tickle to users
        return true;
    }


    private void tickle(User user){
        String message = "";

        try {
            URL url = new URL("https://android.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            ObjectOutputStream out = new ObjectOutputStream(conn.getOutputStream());
            out.writeObject(apikey+"");
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private void test() {

        DataModel tmpmodel;
        User u1, u2, u3;
        Debt d1, d2, d3, d4, d5;


        tmpmodel = new DataModel();
        u1 = new User("a");
        u2 = new User("b");
        u3 = new User("c");
        d1 = new Debt(u1,u2,10);
        d2 = new Debt(u1,u2,-10);
        d3 = new Debt(u2,u1,20);
        d4 = new Debt(u2,u1,-10);
        d5 = new Debt(u1,u3,50);

        if(d1.isCombineable(d2) && d1.isCombineable(d3) && d1.isCombineable(d4) && d2.isCombineable(d3) &&
                d2.isCombineable(d4) && d3.isCombineable(d4) && !d1.isCombineable(d5) && !d2.isCombineable(d5) && !d3.isCombineable(d5) && !d4.isCombineable(d5)){
            System.out.println("combineable works");
        }else {
            System.out.println("combineable doesn't work");
        }

        d1.combinewith(d2);

        if(!(d1.getAmount() == 0)){
            System.out.println("combinewith 1 doesn't work");
        }else {
            d1.combinewith(d3);
            if(!(d1.getAmount() == -20)){
                System.out.println("combinewith 2 doesn't work");
            } else {
                d1.combinewith(d4);
                if(!(d1.getAmount() == -10)){
                    System.out.println("combinewith 3 doesn't work");
                } else {
                    System.out.println("combinewith works!");
                }
            }
        }
    }
}
