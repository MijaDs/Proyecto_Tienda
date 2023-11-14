/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Tienda.controller;

import com.Tienda.domain.Categoria;
import com.Tienda.domain.Producto;
import com.Tienda.service.CategoriaService;
import com.Tienda.service.ProductoService;
import com.Tienda.service.impl.FirebaseStorageServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto")
public class ProductoController {

     @Autowired
     private ProductoService productoService;

     @Autowired
     private CategoriaService categoriaService;

     @GetMapping("/listado")
     public String inicio(Model model) {
          List<Producto> listadoProductos = productoService.getProductos(false);
          List<Categoria> categorias = categoriaService.getCategorias(true);
          model.addAttribute("productos", listadoProductos);
          model.addAttribute("categorias", categorias);
          model.addAttribute("totalProductos", listadoProductos.size());

          return "/producto/listado";
          //se crea folder producto y html listado
     }

     @GetMapping("/nuevo")//por ancla
     public String productoNuevo(Producto producto) {
          return "/producto/modifica";
     }

     @Autowired
     private FirebaseStorageServiceImpl firebaseStorageService;

     @PostMapping("/guardar")//generalmente por formularios
     public String productoGuardar(Producto producto,
             @RequestParam("imagenFile") MultipartFile imagenFile) {
          if (!imagenFile.isEmpty()) {
               productoService.save(producto);
               producto.setRutaImagen(
                       firebaseStorageService.cargaImagen(
                               imagenFile,
                               "producto",
                               producto.getIdProducto()));
          }
          productoService.save(producto);
          return "redirect:/producto/listado";
     }

     @GetMapping("/eliminar/{idProducto}")
     public String productoEliminar(Producto producto) {
          productoService.delete(producto);
          return "redirect:/producto/listado";
     }

     @GetMapping("/modificar/{idProducto}")
     public String productoModificar(Producto producto, Model model) {
          producto = productoService.getProducto(producto);
          List<Categoria> categorias = categoriaService.getCategorias(true);
          model.addAttribute("producto", producto);
          model.addAttribute("categorias", categorias);
          return "/producto/modifica";
     }

}
