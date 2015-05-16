package cadash;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by parke_000 on 2015-05-16.
 */
@RestController
public class ComController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/register")
    public boolean register(@RequestParam(value="uid", defaultValue="NULL") String uid, @RequestParam(value="email") String email) {

        return true;
    }

    @RequestMapping("/newDebt")
    public boolean newDebt(@RequestParam(value = "amount", defaultValue = 0) int amount, @RequestParam(value = "user1") String u1, @RequestParam(value = "user2") String u2) {

        return true;
    }

    @RequestMapping("/sync")
    public boolean sync(@RequestParam(value = "user") String user) {

        return true;
    }

    @RequestMapping("/remove")
    public boolean remove(@RequestParam(value = "user1") String u1, @RequestParam(value = "user2") String u2) {

        return true;
    }
}
