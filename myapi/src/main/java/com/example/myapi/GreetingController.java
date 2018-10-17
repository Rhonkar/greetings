package myapi;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;

@RestController
public class GreetingController {

    private static final ArrayList<Greeting> stupidGreetingList = new ArrayList<Greeting>();

    @RequestMapping("/greetings")
    public Greeting[] greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return stupidGreetingList.toArray(new Greeting[stupidGreetingList.size()]);
    }

    @RequestMapping("/greetings/{id}")
    public Greeting greetingById(@PathVariable("id") Integer id) {
        return stupidGreetingList
            .stream()
            .filter(g -> g.getId() == id)
            .findFirst()
            .orElse(null);
    }

    @RequestMapping(value = "/greetings", method = RequestMethod.POST)
    @ResponseBody
    public Greeting greetingPost(@RequestBody  Greeting greeting) {
        greeting.setId(stupidGreetingList.size() + 1);
        stupidGreetingList.add(greeting);
        return greeting;
    }

}