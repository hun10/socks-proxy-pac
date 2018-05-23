package urbanowicz.proxypac;

import java.net.Inet4Address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class App {
    private static String host;
    private static String port = "1080";

    @RequestMapping("/proxy.pac")
    @ResponseBody
    String home() {
        return "function FindProxyForURL(url, host) { return \"SOCKS " + host + ":" + port + "\"; }";
    }

    public static void main(String... args) throws Exception {
        if (args.length > 0) {
            host = args[0];
        } else {
            host = Inet4Address.getLocalHost().getHostAddress();
        }

        if (args.length > 1) {
            port = args[1];
        }

        SpringApplication.run(App.class, args);
    }
}
