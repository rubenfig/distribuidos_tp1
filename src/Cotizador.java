
public class Cotizador {


	public double precio;

	
	public Cotizador()
	{
		
	}
	
	
	public double getprecio()
	{
		return this.precio;
	}
	
	public void setprecio(double p)
	{
		this.precio=p;
	}
	
	
	public void printCotizador()
	{
		System.out.println("\t\tCOTIZADOR");
		System.out.println("Precio: "+this.precio);
	}
	
}
