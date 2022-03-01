package com.example.form;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope(value = "singleton",proxyMode= ScopedProxyMode.TARGET_CLASS)
public class FormController {

    private final NameService stringService;

    public FormController(NameService stringService) {
        this.stringService = stringService;
    }


    @GetMapping("/form")
    public String greetingForm(Model model) {
        model.addAttribute("person", new Person());
        return "form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        stringService.add(person.getName());
        model.addAttribute("counter", stringService.getString());

        return "result";
    }

    @GetMapping("/api/{name}")
    @ResponseBody
    public String getEmployeesById(@PathVariable String name) {
        stringService.add(name);
        return "Name: " + name;

    }

}