package org.example;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {

    ProductManager manager = new ProductManager();

    // buscarPorId

    @Test
    void buscarPorId_existente_devuelveProducto() {
        Producto p = manager.buscarPorId(1);

        assertNotNull(p);
        assertEquals(1, p.getIdProducto());
        assertEquals("Monster Energy Mango Loko", p.getNombre());
    }

    @Test
    void buscarPorId_noExistente_devuelveNull() {
        Producto p = manager.buscarPorId(9999);

        assertNull(p);
    }

    // crearProducto

    @Test
    void crearProducto_correcto_devuelveTrue() {
        Producto nuevo = new Producto("producto test", "test", 1,1,1.99,10, "ud", "9999999999991");

        boolean resultado = manager.crearProducto(nuevo);

        assertTrue(resultado);
        assertTrue(nuevo.getIdProducto() > 0);

        manager.eliminarProducto(nuevo.getIdProducto());
    }

    @Test
    void crearProducto_codigoBarrasDuplicado_devuelveFalse() {
        Producto duplicado = new Producto("producto duplicado", "test",1, 1, 1.99, 10, "ud", "5060517883021");

        boolean resultado = manager.crearProducto(duplicado);

        assertFalse(resultado);
    }

    // filtrarPorCategoria

    @Test
    void filtrarPorCategoria_existente_devuelveLista() {
        List<Producto> lista = manager.filtrarPorCategoria("Bebidas");

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        for (Producto p : lista) {
            assertEquals("Bebidas", p.getNombreSeccion());
        }
    }

    @Test
    void filtrarPorCategoria_noExistente_devuelveListaVacia() {
        List<Producto> lista = manager.filtrarPorCategoria("nonexistentsection");

        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }

    // eliminarProducto

    @Test
    void eliminarProducto_existente_devuelveTrue() {
        Producto p = new Producto("producto a eliminar", "test", 1, 1, 1.00, 1, "ud", "9999999999992");
        manager.crearProducto(p);

        boolean resultado = manager.eliminarProducto(p.getIdProducto());

        assertTrue(resultado);
        assertNull(manager.buscarPorId(p.getIdProducto()));
    }

    @Test
    void eliminarProducto_noExistente_devuelveFalse() {
        boolean resultado = manager.eliminarProducto(9999);

        assertFalse(resultado);
    }

    // buscarPorMarca

    @Test
    void buscarPorMarca_existente_devuelveLista() {
        List<Producto> lista = manager.buscarPorMarca("Monster");

        assertNotNull(lista);
        assertFalse(lista.isEmpty());
        for (Producto p : lista) {
            assertTrue(p.getNombreMarca().contains("Monster"));
        }
    }

    @Test
    void buscarPorMarca_noExistente_devuelveListaVacia() {
        List<Producto> lista = manager.buscarPorMarca("nonexistentbrand");

        assertNotNull(lista);
        assertTrue(lista.isEmpty());
    }
}