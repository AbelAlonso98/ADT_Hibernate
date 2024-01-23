package ejercicio3;
// Generated 23 ene 2024 23:29:07 by Hibernate Tools 6.3.1.Final

import java.sql.Date;

/**
 * Ventas generated by hbm2java
 */
public class Ventas implements java.io.Serializable {

	private short idventa;
	private Productos productos;
	private Clientes clientes;
	private Date fechaventa;
	private byte cantidad;

	public Ventas() {
	}

	public Ventas(short idventa, Productos productos, Clientes clientes, Date fechaventa, byte cantidad) {
		this.idventa = idventa;
		this.productos = productos;
		this.clientes = clientes;
		this.fechaventa = fechaventa;
		this.cantidad = cantidad;
	}

	public short getIdventa() {
		return this.idventa;
	}

	public void setIdventa(short idventa) {
		this.idventa = idventa;
	}

	public Productos getProductos() {
		return this.productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Date getFechaventa() {
		return this.fechaventa;
	}

	public void setFechaventa(Date fechaventa) {
		this.fechaventa = fechaventa;
	}

	public byte getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(byte cantidad) {
		this.cantidad = cantidad;
	}

}
