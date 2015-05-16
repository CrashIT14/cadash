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
    public boolean register(@RequestParam(value="name", defaultValue="World") String name) {
        return true;
    }

    @RequestMapping("/newDebt")
    public boolean newDebt() {

        return true;
    }

    @RequestMapping("/sync")
    public boolean sync() {

        return true;
    }

    @RequestMapping("/remove")
    public boolean remove() {

        return true;
    }
}
