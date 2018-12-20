package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;

    @RequestMapping(value = "")
    public String index(Model model) { //Integer id) {

        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Categories");


        return "category/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCategoryForm(Model model) {
        model.addAttribute("title", "Add Category");
        model.addAttribute(new Category());
        model.addAttribute("categories", categoryDao.findAll());
        return "category/add";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCategoryForm(Model model, @ModelAttribute @Valid Category ccategory,
                                         Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Category");
            model.addAttribute("categories", categoryDao.findAll());
            return "category/add";
        }

        categoryDao.save(ccategory);
        return "redirect:";
    }

}
