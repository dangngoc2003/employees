package com.example.springinit.controller;

import com.example.springinit.ICrudService;
import com.example.springinit.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private ICrudService<Employee> iCrudService;

    @GetMapping
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView("employee/list");
        modelAndView.addObject("employees",iCrudService.findAll());
        return modelAndView;
    }
    @GetMapping ("/create")
    public  String createForm(Model model){
        model.addAttribute("employee",new Employee());
        return "employee/create";
    }
    @PostMapping("/create")
    public  String createEmployee(@ModelAttribute Employee employee, RedirectAttributes attributes){
        iCrudService.save(employee);
        attributes.addFlashAttribute("employees",iCrudService.findAll());

        return "redirect:/employees";
    }
    @GetMapping("/{id}/update")
    public  String updateForm(Model model,@PathVariable Integer id){
    model.addAttribute("employee",iCrudService.findById(id));
    return "employee/update";

    }
    @PostMapping("/{id}/update")
    public String updateEmployee(@ModelAttribute Employee employee,RedirectAttributes attributes ){
        iCrudService.update(employee);
        attributes.addFlashAttribute("employee",iCrudService.findAll());
        return "redirect:/employees";
    }
    @GetMapping("/{id}/delete")
    public String deleteEmployee(Model model,@PathVariable Integer id){
        iCrudService.deleteById(id);
        model.addAttribute("message", "Delete success");
        return "redirect:/employees";
    }
}
