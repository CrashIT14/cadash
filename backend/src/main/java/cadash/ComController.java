package cadash;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


/**
 * Created by Hampus Dahlin on 2015-05-16.
 */
@RestController
public class ComController {
    private DataModel model;

    @RequestMapping("/register")
    public boolean register(@RequestParam(value="email") String email) {
        User toReg = new User(email);
        model.register(toReg);
        return true;
    }

    @RequestMapping("/newDebt")
    public boolean newDebt(@RequestParam(value = "amount", defaultValue = "0") int amount, @RequestParam(value = "user1") String u1, @RequestParam(value = "user2") String u2) {
        User user1 = new User(u1);
        User user2 = new User(u2);
        Debt toAdd = new Debt(user1, user2, amount);
        model.addDebt(toAdd);
        return true;
    }

    @RequestMapping("/sync")
    public ArrayList<Debt> sync(@RequestParam(value = "user") String user) {
        User u = new User(user);
        return model.getDebts(u);
    }

    @RequestMapping("/remove")
    public boolean remove(@RequestParam(value = "user1") String u1, @RequestParam(value = "user2") String u2) {
        User user1 = new User(u1);
        User user2 = new User(u2);
        Debt d = new Debt(user1, user2, 0);
        model.removeDebt(d);
        return true;
    }
}
