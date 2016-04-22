import java.io.IOException;

public class Analizer {

	public static void main(String[] args) throws IOException {
		Cliente cliente = new Cliente();
		while(true){
		cliente.conectar();
		cliente.consultar();
		}
	}

}
