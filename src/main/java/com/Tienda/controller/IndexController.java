package com.Tienda.controller;

import com.Tienda.service.ItemService;
import com.Tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author mija2
 */
@Controller
public class IndexController {

     @Autowired
     ProductoService productoService;
     @Autowired
     private ItemService itemService;

     @RequestMapping("/")
     public String page(Model model) {
          var listaProductos = productoService.getProductos(true);
          model.addAttribute("productos", listaProductos);
          return "index";
     }

}
