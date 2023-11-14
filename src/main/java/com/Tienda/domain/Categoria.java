package com.Tienda.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data // creacion automatica de los atributos
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

     private static final long serialVersionUID = 1L;
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id_categoria")//espesifica como se llama la columna en sql
     //camelCase obligatorio
     private Long idCategoria; //Transforma en id_categoria
     private String descripcion;
     private String rutaImagen;
     private boolean activo;

     @OneToMany
     @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
     List<Producto> productos;

     public Categoria() {
     }
     //id se encarga la base de datos no se mete ene el constructor

     public Categoria(String descripcion, String rutaImagen, boolean activo) {
          this.descripcion = descripcion;
          this.rutaImagen = rutaImagen;
          this.activo = activo;
     }

}
