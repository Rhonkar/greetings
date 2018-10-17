package myapi;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;

@RestController
public class GreetingController {

    private static final ArrayList<Greeting> stupidGreetingList = new ArrayList<Greeting>();

    @RequestMapping("/greeting")
    public Greeting[] greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return stupidGreetingList.toArray(new Greeting[stupidGreetingList.size()]);
    }

    @PostMapping("/greeting")
    public String greetingPost(@ModelAttribute Greeting greeting) {
        stupidGreetingList.add(greeting);
        return "greeting";
    }

}