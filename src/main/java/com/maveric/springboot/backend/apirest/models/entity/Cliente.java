package com.maveric.springboot.backend.apirest.models.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="clientes")
public class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY )
    private Long id;


    @NotEmpty( message = "No puede ser vacio ")
    @Size(min = 3, max = 15, message = "El tamaño tiene que ser entre 3 y 15 Caracteres")
    @Column(nullable=false)
    private String nombre;


    @NotEmpty( message = "No puede ser vacio ")
    private String apellido;


    @NotEmpty( message = "No puede ser vacio ")
    @Email( message = "Debe ser una direccion de correo bien formada")
//    @Column(nullable = false, unique = true)
    private String email;

    @NotNull(message = "No puede ser vacio")
    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;


    private String foto;
//    @PrePersist
//    public void prePersist() {
//        createAt = new Date();
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    private static final long serialVersionUID = 1L;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
