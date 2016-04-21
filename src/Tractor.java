public class Tractor {
	
	
	public int tractor_id;
	public double coord_x;
	public double coord_y;
	public double altura;
	public double humedad;
	public double peso;
	public double temperatura;
	
	public void printTractor()
	{
		System.out.println("\t\tTractor");
		System.out.println("ID tractor: "+this.tractor_id);
		System.out.println("Coord_x: "+this.coord_x);
		System.out.println("Coord_y: "+this.coord_y);
		System.out.println("Humedad: "+this.humedad);
		System.out.println("Altura: "+this.altura);
		System.out.println("Peso: "+this.peso);
		System.out.println("Temperatura: "+this.temperatura);
	}
	
	
	
	public Tractor()
	{
	}

	public int getTractor_id() {
		return tractor_id;
	}

	public void setTractor_id(int tractor_id) {
		this.tractor_id = tractor_id;
	}

	public double getCoord_x() {
		return coord_x;
	}

	public void setCoord_x(double coord_x) {
		this.coord_x = coord_x;
	}

	public double getCoord_y() {
		return coord_y;
	}

	public void setCoord_y(double coord_y) {
		this.coord_y = coord_y;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getHumedad() {
		return humedad;
	}

	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	
	
	
	
	
}
