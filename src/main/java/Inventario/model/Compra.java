package Inventario.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate fecha;

    private double total;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente") // Relación con Persona
    private Cliente cliente;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "detalle_compra",
    joinColumns = @JoinColumn(name = "id_compra"),
    inverseJoinColumns = @JoinColumn(name = "id_detalle_producto") 
    )
    @JsonManagedReference
    private List<DetalleProducto> detalleProductos; 

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
