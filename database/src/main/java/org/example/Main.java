package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ProductManager manager = new ProductManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("--------------------------------------------");
        System.out.println("    GESTIÓN DE PRODUCTOS – HIPERMERCADO     ");
        System.out.println("--------------------------------------------");

        boolean ejecutando = true;

        while (ejecutando) {

            System.out.println();
            System.out.println("------------------------------------------");
            System.out.println("  1. Buscar producto por ID               ");
            System.out.println("  2. Crear producto nuevo                 ");
            System.out.println("  3. Filtrar productos por categoría      ");
            System.out.println("  4. Eliminar un producto                 ");
            System.out.println("  5. Productos de una marca               ");
            System.out.println("  0. Salir                                ");
            System.out.println("------------------------------------------");
            System.out.print("Elige una opción: ");

            int opcion = Integer.parseInt(sc.nextLine().trim());

            System.out.println();

            switch (opcion) {
                // buscar por id
                case 1 -> {
                    System.out.print("ID del producto (ej. 1, 4, 52): ");
                    int id = Integer.parseInt(sc.nextLine().trim());

                    Producto p = manager.buscarPorId(id);

                    if (p != null) {
                        System.out.println("Producto encontrado:");
                        System.out.println(p);
                    } else {
                        System.out.println("No existe ningún producto con ID " + id);
                    }
                }

                // crear producto
                case 2 -> {
                    System.out.println("-- Nuevo producto --");

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Descripción: ");
                    String descripcion = sc.nextLine();

                    System.out.print("ID de sección: ");
                    int idSeccion = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("ID de marca: ");
                    int idMarca = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Precio (ej. 1.99): ");
                    double precio = Double.parseDouble(sc.nextLine().trim().replace(",", "."));

                    System.out.print("Stock inicial: ");
                    int stock = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Unidad de medida (g / kg / ml / l / ud / pack): ");
                    String unidad = sc.nextLine().trim().toLowerCase();

                    System.out.print("Código de barras (13 dígitos): ");
                    String barras = sc.nextLine().trim();

                    Producto nuevo = new Producto(nombre, descripcion, idSeccion, idMarca, precio, stock, unidad, barras);
                    boolean ok = manager.crearProducto(nuevo);

                    if (ok) {
                        System.out.println("Producto creado correctamente con ID: " + nuevo.getIdProducto());
                        System.out.println(nuevo);
                    } else {
                        System.out.println("No se pudo crear el producto.");
                    }
                }

                // filtrar por cat
                case 3 -> {
                    System.out.print("Nombre de la categoría: ");
                    String categoria = sc.nextLine().trim();

                    List<Producto> lista = manager.filtrarPorCategoria(categoria);

                    if (lista.isEmpty()) {
                        System.out.println("No se encontraron productos en la categoría '" + categoria + "'.");
                    } else {
                        System.out.println(lista.size() + " producto(s) encontrado(s):");
                        for (Producto p : lista) {
                            System.out.println(p);
                        }
                    }
                }

                // eliminar prod
                case 4 -> {
                    System.out.print("ID del producto a eliminar: ");
                    int id = Integer.parseInt(sc.nextLine().trim());

                    Producto p = manager.buscarPorId(id);
                    if (p == null) {
                        System.out.println("No existe ningún producto con ID " + id);
                        break;
                    }

                    System.out.println("Producto a eliminar: " + p.getNombre() + " (" + p.getPrecio() + " €)");
                    System.out.print("Confirmas la eliminación? (s/n): ");
                    String resp = sc.nextLine().trim().toLowerCase();

                    if (resp.equals("s")) {
                        boolean ok = manager.eliminarProducto(id);
                        if (ok) {
                            System.out.println("Producto eliminado correctamente.");
                        } else {
                            System.out.println("No se pudo eliminar el producto.");
                        }
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                }

                // productos de una marca
                case 5 -> {
                    System.out.print("Nombre de la marca: ");
                    String marca = sc.nextLine().trim();

                    List<Producto> lista = manager.buscarPorMarca(marca);

                    if (lista.isEmpty()) {
                        System.out.println("No se encontraron productos de la marca '" + marca + "'.");
                    } else {
                        System.out.println(lista.size() + " producto(s) de '" + marca + "':");
                        for (Producto p : lista) {
                            System.out.println(p);
                        }
                    }
                }

                // exit
                case 0 -> {
                    ejecutando = false;
                    System.out.println("Hasta pronto!");
                }

                default -> System.out.println("Opción no válida. Elige entre 0 y 5.");
            }
        }

        sc.close();
    }
}
