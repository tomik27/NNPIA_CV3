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
    public String hateForm(Model model) {
        model.addAttribute("person", new Person());
        return "form";
    }

    @PostMapping("/form")
    public String hateSubmit(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        stringService.add(person.getName());
        model.addAttribute("counter", stringService.getString());

        return "resultForm";
    }

    @GetMapping("/showNames")
    public String showResult( Model model) {
        model.addAttribute("counter", stringService.getString());
        return "showNames";
    }

    @GetMapping("/addNameByPath/{name}")
    @ResponseBody
    public String addNameByPath(@PathVariable String name) {
        stringService.add(name);
        return "Name: " + name;

    }

}