package myapi;

import java.util.Optional;
import java.util.ArrayList;
import java.lang.Iterable;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class GreetingController {

    @Autowired
	private GreetingRepository greetingRepository;

    @RequestMapping("/greetings")
    public Iterable<Greeting> greetings() {
        return greetingRepository.findAll();
    }

    @RequestMapping("/greetings/{id}")
    public Optional<Greeting> greetingById(@PathVariable("id") Long id) {
        return greetingRepository.findById(id);
    }

    @RequestMapping(value = "/greetings", method = RequestMethod.POST)
    @ResponseBody
    public Greeting greetingPost(@RequestBody  Greeting greeting) {
        greetingRepository.save(greeting);
        return greeting;
    }

}