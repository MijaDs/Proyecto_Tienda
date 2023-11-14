package com.Tienda.service.impl;

import com.Tienda.dao.CategoriaDao;
import com.Tienda.domain.Categoria;
import com.Tienda.service.CategoriaService;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

     @Autowired
     private CategoriaDao categoriaDao;

     @Override
     @Transactional(readOnly = true)
     public List<Categoria> getCategorias(boolean activos) {
          List<Categoria> categorias = categoriaDao.findAll();
          if (activos) {
               categorias.removeIf(cate -> !cate.isActivo());
          }
          return categorias;
     }

     public Categoria getCategoria(Categoria categoria) {
          return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
     }

     @Override
     @Transactional
     //inserta o actualiza
     public void save(Categoria categoria) {
          categoriaDao.save(categoria);
     }

     @Override
     @Transactional
     public void delete(Categoria categoria) {
          categoriaDao.delete(categoria);
     }

     @Override
     @Transactional(readOnly = true)
     public List<Categoria> getCategoriaPorDescripcion(String descripcion) {
          return categoriaDao.findByDescripcionIgnoreCase(descripcion);
     }

}
