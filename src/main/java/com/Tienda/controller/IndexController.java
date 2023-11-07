package com.Tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mija2
 */
@Controller
public class IndexController {

     @RequestMapping("/")
     public String page(Model model) {
          model.addAttribute("attribute", "value");
          return "index";
     }

}
