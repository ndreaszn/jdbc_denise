package org.example;

public class Producto {

// adapted para el .sql
    private int idProducto;
    private String nombre;
    private String descripcion;
    private int idSeccion;
    private int idMarca;
    private double precio;
    private int stock;
    private String unidadMedida;
    private String codigoBarras;
    private boolean activo;
    private String nombreSeccion;
    private String nombreMarca;

    public Producto() {}

    public Producto(String nombre, String descripcion, int idSeccion, int idMarca, double precio, int stock, String unidadMedida, String codigoBarras) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idSeccion = idSeccion;
        this.idMarca = idMarca;
        this.precio = precio;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
        this.codigoBarras = codigoBarras;
        this.activo = true;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int v) { this.idProducto = v; }

    public String getNombre() { return nombre; }
    public void setNombre(String v) { this.nombre = v; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String v) { this.descripcion = v; }

    public int getIdSeccion() { return idSeccion; }
    public void setIdSeccion(int v) { this.idSeccion = v; }

    public int getIdMarca() { return idMarca; }
    public void setIdMarca(int v) { this.idMarca = v; }

    public double getPrecio() { return precio; }
    public void setPrecio(double v) { this.precio = v; }

    public int getStock() { return stock; }
    public void setStock(int v) { this.stock = v; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String v) { this.unidadMedida = v; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String v) { this.codigoBarras = v; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean v) { this.activo = v; }

    public String getNombreSeccion() { return nombreSeccion; }
    public void setNombreSeccion(String v) { this.nombreSeccion = v; }

    public String getNombreMarca() { return nombreMarca; }
    public void setNombreMarca(String v) { this.nombreMarca = v; }

    @Override
    public String toString() {
        return idProducto + " | " + nombre + " | " + precio + " € | " + stock + " uds | " + unidadMedida;
    }
}
