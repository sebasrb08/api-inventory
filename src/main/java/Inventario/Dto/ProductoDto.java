package Inventario.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoDto {

    private String nombre;

    private String sku;

    private String  descripcion;

    private double precio;

    private int cantidad;

    private String image;

    private long id_categoria;


}
