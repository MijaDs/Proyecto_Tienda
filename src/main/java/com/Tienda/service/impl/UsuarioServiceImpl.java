/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Tienda.service.impl;

import com.Tienda.dao.UsuarioDao;
import com.Tienda.domain.Rol;
import com.Tienda.domain.Usuario;
import com.Tienda.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserDatailsService")
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

     @Autowired
     private UsuarioDao usuarioDao;
     @Autowired
     private HttpSession session;

     @Override
     @Transactional(readOnly = true)
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          //buscar el usuario por el username
          Usuario usuario = usuarioDao.findByUsername(username);
          //si no existe llama una excepcion
          if (usuario == null) {
               throw new UsernameNotFoundException(username);
          }
          //remover atributos
          //si llego aca es porque el usuario existe
          session.removeAttribute("usuarioImagen");
          session.setAttribute("usuarioImagen", usuario.getRutaImagen());
          //trasnformar roles an GrantedAuthority
          var roles = new ArrayList<GrantedAuthority>();
          for (Rol item : usuario.getRoles()) {
               roles.add(new SimpleGrantedAuthority(item.getNombre()));
          }
          //retorna el user (clase UserDetails)
          return new User(usuario.getUsername(), usuario.getPassword(), roles);
     }

     @Override

     public Usuario getUsuarioPorUsername(String username) {
          return usuarioDao.findByUsername(username);
     }

}
