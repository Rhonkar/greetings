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
import com.microsoft.applicationinsights.TelemetryClient;

@RestController
public class GreetingController {

    @Autowired
    private GreetingRepository greetingRepository;
    @Autowired
    TelemetryClient telemetryClient;

    @RequestMapping("/greetings")
    public Iterable<Greeting> greetings() {
        telemetryClient.trackTrace("Entering /greetings GET.");
        Iterable<Greeting> allGreetings = greetingRepository.findAll();
        telemetryClient.trackMetric("GreetingCount", size(allGreetings));
        return allGreetings;
    }

    @RequestMapping("/greetings/{id}")
    public Optional<Greeting> greetingById(@PathVariable("id") Long id) {
        telemetryClient.trackTrace("Entering /greetings/{id} GET.");
        return greetingRepository.findById(id);
    }

    @RequestMapping(value = "/greetings", method = RequestMethod.POST)
    @ResponseBody
    public Greeting greetingPost(@RequestBody  Greeting greeting) {
        telemetryClient.trackTrace("Entering /greetings POST.");
        greetingRepository.save(greeting);
        return greeting;
    }

    private private static int size(Iterable<?> iterable) {
        if (iterable == null) {
            return 0;
        }

        if (iterable instanceof Collection<?>) {
            return ((Collection<?>)iterable).size();
        }

        int s = 0;
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) { 
            iterator.next();
            s++;
        }

        return s;
    }

}