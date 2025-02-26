package Inventario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cliente extends Persona {
    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    private Usuario usuario;

    public Usuario getUsuario(){
        return this.usuario;
    }
    public void setUsuario(Usuario usuario){
         this.usuario = usuario;
    }
}
