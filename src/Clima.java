import javax.swing.plaf.synth.SynthSeparatorUI;

public class Clima {
	
	public int clima_id;
	public double humedad;
	public double viento;
	public int departamento;
	public int distrito;
	public int zona;
	
	
	public Clima() {
		
	}


	public int getClima_id() {
		return clima_id;
	}


	public void setClima_id(int clima_id) {
		this.clima_id = clima_id;
	}


	public double getHumedad() {
		return humedad;
	}


	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}


	public double getViento() {
		return viento;
	}


	public void setViento(double viento) {
		this.viento = viento;
	}


	public int getDepartamento() {
		return departamento;
	}


	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}


	public int getDistrito() {
		return distrito;
	}


	public void setDistrito(int distrito) {
		this.distrito = distrito;
	}


	public int getZona() {
		return zona;
	}


	public void setZona(int zona) {
		this.zona = zona;
	}

	
	public void printClima()
	{
		System.out.println("a");
	}
	
}
