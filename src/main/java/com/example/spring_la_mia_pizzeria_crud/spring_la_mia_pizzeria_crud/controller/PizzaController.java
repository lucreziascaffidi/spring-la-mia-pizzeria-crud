package com.example.spring_la_mia_pizzeria_crud.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring_la_mia_pizzeria_crud.spring_la_mia_pizzeria_crud.model.Pizza;
import com.example.spring_la_mia_pizzeria_crud.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository repository;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = repository.findAll();
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", repository.findById(id).get());
        return "pizzas/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("create", true);
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {
        // validazioni dei dati
        // se ci sono errori di validazione torno al form
        if (bindingResult.hasErrors()) {
            model.addAttribute("create", true);
            return "pizzas/create-or-edit";
        }

        // altrimenti salva e torna in index
        // salvare i dati
        repository.save(formPizza);

        redirectAttributes.addFlashAttribute("message",
                String.format("Pizza %s has been created", formPizza.getName()));
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("create", false);
        model.addAttribute("pizza", repository.findById(id).get());
        return "pizzas/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("create", false);
            return "/pizzas/create-or-edit";
        }
        repository.save(formPizza);

        redirectAttributes.addFlashAttribute("message",
                String.format("Pizza %s has been updated successfully", formPizza.getName()));
        redirectAttributes.addFlashAttribute("messageClass", "alert-success");
        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Pizza pizza = repository.findById(id).get();

        redirectAttributes.addFlashAttribute("message",
                String.format("Pizza %s has been deleted successfully", pizza.getName()));
        redirectAttributes.addFlashAttribute("messageClass", "alert-danger");

        return "redirect:/pizzas";
    }
}
