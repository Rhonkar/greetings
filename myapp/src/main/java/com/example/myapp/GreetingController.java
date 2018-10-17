package myapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greetingForm(@RequestParam(value="content", defaultValue="Default content") String content, Model model) {
        Greeting greeting = new Greeting();
        greeting.setContent(content);
        model.addAttribute("greeting", greeting);
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String url = getApiBaseUrl() + "/greetings";
        restTemplate.postForObject(url, greeting, Greeting.class);
        Greeting[] greetings = restTemplate.getForObject(url, Greeting[].class);
        model.addAttribute("greetings", greetings);
        return "result";
    }

    private String getApiBaseUrl() {
        String url = System.getenv("API_BASE_URL");
        if (url == null) {
            return "http://api:8080";
        }

        // trim trailing slash:
        return url.replaceAll("/$","");
    }
    
}