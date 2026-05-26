package org.example;

public class Producto {

    private int id;
    private String nombre;
    private String descripcion;
    private int idSeccion;
    private int idMarca;
    private double precio;
    private int stock;
    private String codigoBarras;

    public Producto(String nombre, String descripcion, int idSeccion, int idMarca, double precio, int stock, String codigoBarras) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idSeccion = idSeccion;
        this.idMarca = idMarca;
        this.precio = precio;
        this.stock = stock;
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idSeccion=" + idSeccion +
                ", idMarca=" + idMarca +
                ", precio=" + precio +
                ", stock=" + stock +
                ", codigoBarras='" + codigoBarras + '\'' +
                '}';
    }
}

