/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nrodas.archivosp1.entidades;

import com.nrodas.archivosp1.apoyo.Conversor;
import com.nrodas.archivosp1.conexion.Conexion;
import com.nrodas.archivosp1.listas.Lista;
import com.nrodas.archivosp1.listas.Matriz;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Clase que maneja lo relacionado con los Reportes (Hace Consultas Tambien)
 * @author lroda
 */
public class Reporte {
    
    /**
     * Atributos
     */
    private Matriz lb;
    
    
    /*Metodos para los reportes*/
    //Metodo para ver las sucursales con mas ventas

    /**
     *
     */
    public void calcularVentasPorSucursal() {
        this.lb = new Matriz(); // Se crea una nueva instancia de la matriz
        /*
        * Se formula una Query que cuente veces se repite un id de una Sucursal en un registro de venta
        * Indica que lo ordende de mayor a menor por el numero de id de la sucursal y que lo agrupe por el nombre de la misma
        */
        String consulta = "SELECT COUNT(s.sucursal), p.nombreInmueble FROM ControlInmuebles.Instalacion AS p INNER JOIN ControlTienda.Venta AS s ON s.sucursal = p.idSucursal GROUP BY p.nombreInmueble ORDER BY COUNT(s.sucursal) DESC";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se realiza la llamada
            ResultSet resultado = preSt.executeQuery(); //Se ejecuta la Query
            while (resultado.next()) { //Recorre el resultset hasta ya no encontrar mas registros
                Lista lista = new Lista(null); //Crea una nueva lista
                lista.agregarNodo(resultado.getInt(1)); //Agrega nodos a la lista
                lista.agregarNodo(resultado.getString(2));
                this.lb.agregarNuevaLista(lista); //Agrega la lista a la matriz
            } 
            resultado.close(); //Cierra el resultset
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un error por si lo hubiera
        }
        //return arreglo; //Retorna el arreglo
    }
    
    //Metodo para ver las sucursales con mas ventas

    /**
     *
     */
    public void calcularIngresosPorSucursal() {
        this.lb = new Matriz();
        /*
        * Se formula una Query que cuente veces se repite un id de una Sucursal en un registro de venta
        * Indica que lo ordende de mayor a menor por el numero de id de la sucursal y que lo agrupe por el nombre de la misma
        */
        String consulta = "SELECT SUM(s.total), p.nombreInmueble FROM ControlInmuebles.Instalacion AS p INNER JOIN ControlTienda.Venta AS s ON s.sucursal = p.idSucursal GROUP BY p.nombreInmueble ORDER BY SUM(s.total) DESC";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se realiza la llamada
            ResultSet resultado = preSt.executeQuery(); //Se ejecuta la Query
            while (resultado.next()) { //Recorre el resultset hasta ya no encontrar mas registros
                Lista lista = new Lista(null); //Crea una nueva lista
                lista.agregarNodo(resultado.getDouble(1)); //Agrega nodos a la lista
                lista.agregarNodo(resultado.getString(2));
                this.lb.agregarNuevaLista(lista); //Agrega la lista a la matriz
            } 
            resultado.close(); //Cierra el resultset
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un error por si lo hubiera
        }
    }
    
    //Funcion que determina que clientes generan mas ingresan

    /**
     *
     */
    public void buscarClientesGanancias() {
        this.lb = new Matriz(); //Se crea una nueva instancia de la matriz
        /*
        * Query formulada para sumar el total de ganancias que genera cada cliente esto sucediendo cada vez que un nit coincida
        * Exigiendo que lo agrupe por el nombre y el nit y que lo ordene de mayor a menor dependiendo del total de su gasto
        */
        String consulta = "SELECT SUM(p.total), p.nitCli, s.nombreCliente, s.apellidoCliente FROM ControlTienda.Venta AS p INNER JOIN ControlTienda.Cliente AS s ON s.nit = p.nitCli GROUP BY s.nombreCliente, p.nitCli, s.apellidoCliente ORDER BY SUM(p.total) DESC LIMIT 10";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se realiza la llamada
            ResultSet resultado = preSt.executeQuery(); //Se ejecuta la Query
            while (resultado.next()) { //Recorre el resultset hasta ya no encontrar mas registros
                Lista lista = new Lista(null); //Crea una nueva lista
                lista.agregarNodo(resultado.getDouble(1)); //Agrea nodos a la lista
                lista.agregarNodo(resultado.getInt(2));
                lista.agregarNodo(resultado.getString(3) + " " + resultado.getString(4));
                lb.agregarNuevaLista(lista); //Agrega la lista a la matriz
            } 
            resultado.close(); //Se cierra el resultset para optimizar la aplicacion
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un error por si lo hubiera
        }
    }
    
    //Funcion que busca a los tres empleados con mas ventas

    /**
     *
     */
    public void contarEmpleadosVentas() {
        this.lb = new Matriz(); //Se crea una nueva instancia de la matriz
        /*
        * Se formula la Query para contar cuantas veces vendio un empleado 
        * Se ordena que lo agrupe por el id del empleado y su nombre y que lo ordene por la cantidad de manera decendiente
        */
        String consulta = "SELECT COUNT(p.codigoEmpleado), s.idUsuario, s.nombreEmpleado, p.sucursal FROM ControlTienda.Venta AS p INNER JOIN ControlPersonal.Empleado AS s ON s.idUsuario = p.codigoEmpleado GROUP BY s.idUsuario, p.sucursal ORDER BY COUNT(p.codigoEmpleado) DESC LIMIT 3";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se realiza la llamada
            ResultSet resultado = preSt.executeQuery(); //Se ejecuta la Query
            while (resultado.next()) { //Recorre el resultset hasta ya no encontrar mas registros
                //Llena La matriz
                Lista lista = new Lista(null); //Se crea una nueva lista
                lista.agregarNodo(resultado.getInt(1)); //Se le agrega los datos a esa lista
                lista.agregarNodo(resultado.getLong(2));
                lista.agregarNodo(resultado.getString(3));
                lista.agregarNodo(Conversor.determinarNombreSucursal(resultado.getInt(4)));
                this.lb.agregarNuevaLista(lista); //Se agrega la lista a la matriz
            } 
            resultado.close(); //Se cierra el resultset para optimizar la aplicaicon
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Muestra un error por si lo hubiera
        }
    }
    
    //Metodo que averigua los empleados con mas ingresos

    /**
     *
     */
    public void sumarEmpleadosIngresos() {
        this.lb = new Matriz(); //Se crea una nueva instancia de la matriz
        /*
        * Se formula la Query que recoja los empleados con mas ingresos
        * Se ordenara de manera decendente guidandose por la suma de sus ventas y se agrupara por el id del Empleado y por la sucursal en la que esta
        */
        String consulta = "SELECT p.sucursal, s.idUsuario, s.nombreEmpleado, SUM(p.total) FROM ControlTienda.Venta AS p INNER JOIN ControlPersonal.Empleado AS s ON p.codigoEmpleado = s.idUsuario GROUP BY s.idUsuario, p.sucursal ORDER BY SUM(p.total) DESC LIMIT 3";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la llamada
            ResultSet resultado = preSt.executeQuery(); //Se obtiene un resultset
            while (resultado.next()) { //Valida el tamaño del mismo
                Lista lista = new Lista(null); //Crea una nueva lista
                lista.agregarNodo(Conversor.determinarNombreSucursal(resultado.getInt(1))); //Se agregan nodos a la lista
                lista.agregarNodo(resultado.getLong(2));
                lista.agregarNodo(resultado.getString(3));
                lista.agregarNodo(resultado.getDouble(4));
                this.lb.agregarNuevaLista(lista); //Se agrega la lista a la matriz
            }
            resultado.close(); //Se cierra el result para oprimizar      
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Imprime el error en consola
        }
    }
    
    //Metodo que busca los 10 productos mas vendidos

    /**
     *
     */
    public void buscarProductosVendidos() {
        this.lb = new Matriz(); //Se crea una nueva instancia de la Matriz
        /*
        * Se selecciona la columna codigoPdt y descripcion de la tabla DetalleVenta
        * Se cre una nueva columna para el resultado que sumara la cantidad de veces que se compro ese producto
        * Se agrupara de acuerdo al codigo del producto y a su descripcion y se ordenara de manera descendente con un limite de 10
        */
        String consulta = "SELECT codigoPdt, descripcion, SUM(cantidad) FROM ControlTienda.DetalleVenta GROUP BY codigoPdt, descripcion ORDER BY SUM(cantidad) DESC LIMIT 10";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la call
            ResultSet resultado = preSt.executeQuery(); //Se obtiene ul resultset
            while (resultado.next()) { //Valida que contiene elementos
                Lista lista = new Lista(null); //Se crea una nueva lista
                lista.agregarNodo(resultado.getString(1)); //Se aniaden nuevos nodos
                lista.agregarNodo(resultado.getString(2));
                lista.agregarNodo(resultado.getInt(3));
                this.lb.agregarNuevaLista(lista); //se agrega la lista a la matriz
            }
            resultado.close(); //Se cierra el resultado para optimizar       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage());//Muestra el error
        }
    }
    
    //Metodo que busca los 10 productos mas vendidos

    /**
     *
     */
    public void contarProductosIngresos() {
        this.lb = new Matriz(); //Se crea una nueva instancia de la Matriz
        /*
        * Se selecciona la columna codigoPdt y descripcion de la tabla DetalleVenta
        * Se cre una nueva columna para el resultado que sumara el precio que se pago cuantas veces aparezca el codigo de un producto
        * Se agrupara de acuerdo al codigo del producto y a su descripcion y se ordenara de manera descendente con un limite de 10
        */
        String consulta = "SELECT codigoPdt, descripcion, SUM(subTotal) FROM ControlTienda.DetalleVenta GROUP BY codigoPdt, descripcion ORDER BY SUM(subTotal) DESC LIMIT 10";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la call
            ResultSet resultado = preSt.executeQuery(); //Se obtiene ul resultset
            while (resultado.next()) { //Valida que contiene elementos
                Lista lista = new Lista(null); //Se crea una nueva lista
                lista.agregarNodo(resultado.getString(1)); //Se aniaden nuevos nodos
                lista.agregarNodo(resultado.getString(2));
                lista.agregarNodo(resultado.getInt(3));
                this.lb.agregarNuevaLista(lista); //se agrega la lista a la matriz
            }
            resultado.close(); //Se cierra el resultado para optimizar       
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Se muestra un error en la consola
        }
    }
    
    //Metodo que busca los 5 productos mas vendidos por el id de sucursal

    /**
     *
     * @param idSucursal El id de la sucursal a evaluar
     */
    public void buscarTop5Productos(int idSucursal) {
        this.lb = new Matriz(); //Crea una nueva instancia para la matriz
        //Se formula una consulta que seleccione los productos por id de inventario y que sume sus cantidades 
        String consulta = "SELECT codigoPdt, descripcion, SUM(cantidad) FROM ControlTienda.DetalleVenta WHERE inventario = ? GROUP BY codigoPdt, descripcion  ORDER BY SUM(cantidad) DESC LIMIT 5;";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se realiza la llamada
            preSt.setInt(1, idSucursal);  //Se sustituye los datos
            ResultSet resultado = preSt.executeQuery(); //Se realiza la Query con un resultado
            while (resultado.next()) { //Valida la cantidad de resultados
                Lista lista = new Lista(null); //Se crea una nueva lista y se llena
                lista.agregarNodo(resultado.getString(1));
                lista.agregarNodo(resultado.getString(2));
                lista.agregarNodo(resultado.getInt(3));
                this.lb.agregarNuevaLista(lista); //Se agrega la lista a la matriz
            }
            resultado.close(); //Se cierra el resultset
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Se muestra un error por si lo hubiera
        }
    }
    
    //Metodo que busca los 5 productos mas vendidos por el id de sucursal

    /**
     *
     * @param idSucursal El id de la sucursal a evaluar
     */
    public void buscarTop5ProductosIng(int idSucursal) {
        this.lb = new Matriz(); //Se crea una nueva instacia de la matriz
        //Se formula una Query que agrupe por el codigo pdt de cierto inventario y que sume sus ganacias ordenandolos de manera descendiente
        String consulta = "SELECT codigoPdt, descripcion, SUM(subTotal) FROM ControlTienda.DetalleVenta WHERE inventario = ? GROUP BY codigoPdt, descripcion  ORDER BY SUM(subTotal) DESC LIMIT 5;";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) {
            preSt.setInt(1, idSucursal); //Se sustituye los datos
            ResultSet resultado = preSt.executeQuery(); //Se realiza la Query con un resultado
            while (resultado.next()) { //Valida cuantos resultados dio
                Lista lista = new Lista(null); //Se crea una nueva lista y se llena
                lista.agregarNodo(resultado.getString(1));
                lista.agregarNodo(resultado.getString(2));
                lista.agregarNodo(resultado.getInt(3));
                this.lb.agregarNuevaLista(lista); //Se agrega la lista a la matriz
            }
            resultado.close(); //Se cierra el resultset
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Se muestra un error por si lo hubiera
        }
    }
    
    //Metodo que busca los productos por numero de inventario

    /**
     *
     * @param inventario El inventario del que se quieren ver los productos
     */
    public void retornarProductosInventario(int inventario) {
        this.lb = new Matriz(); //Se crea una nueva instancia de la matriz
        //Se formula una Query que recoja los productos por numero de inventario
        String consulta = "SELECT codigoPdt, descripcion, cantidad, precioUnitario FROM ControlTienda.Producto WHERE inventario = ?";
        try (PreparedStatement preSt = Conexion.getConexionDB().prepareCall(consulta)) { //Se prepara la call
            preSt.setInt(1, inventario); //Se sustituyen los datos
            ResultSet resultado = preSt.executeQuery(); //Se realiza la Query
            while (resultado.next()) { //Valida los resultados disponibles
                Lista lista = new Lista(null); //Se crea una nueva lista
                if (resultado.getInt(3) == 0 && inventario != 1) {
                    resultado.next();
                }
                lista.agregarNodo(resultado.getString(1));
                lista.agregarNodo(resultado.getString(2));
                lista.agregarNodo(resultado.getInt(3));
                lista.agregarNodo(resultado.getDouble(4));
                this.lb.agregarNuevaLista(lista); //Se agrega la lista a la Matriz
            }
            resultado.close(); //Se cierra el resultado
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un Problema" + e.getMessage()); //Se imprime por si hubiera un error
        }
    }

    /**
     *
     * @return la matriz de reporte
     */
    public Matriz getLb() {
        return lb;
    }

    /**
     *
     * @param lb La nueva matriz
     */
    public void setLb(Matriz lb) {
        this.lb = lb;
    } 
}
