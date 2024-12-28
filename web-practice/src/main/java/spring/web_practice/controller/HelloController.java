package spring.web_practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("bye")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-static.html")
    public String helloSpring(Model model) {
        model.addAttribute("data", "지금은 동적");
        return "hello-spring";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(name = "name") String value, Model model) {
        model.addAttribute("name", value);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name, @RequestParam("value") String value) {
        Hello hello = new Hello();
        hello.setName(name);
        hello.setValue(value);
        return hello;
    }
    static class Hello {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
