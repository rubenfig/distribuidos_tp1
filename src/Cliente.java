import com.google.gson.Gson;
import java.io.*;
import java.net.*;
import java.util.Properties;
import java.util.Scanner;


public class Cliente {

	final Gson gson = new Gson();
    Socket unSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    InputStream inImg=null;	
    
    public Cliente()
    {
    	
    }
	
	public Integer conectar() throws IOException{
        try {
            unSocket = new Socket("localhost", 4444);
            // enviamos nosotros
            out = new PrintWriter(unSocket.getOutputStream(), true);
            //viene del servidor
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
           
            return 1;
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            return -1;
        } catch (IOException e) {
            System.err.println("No se pudo conectar con el servidor");
            return -2;
        }
    }
	
	public int consultar () throws IOException
    {
		Scanner on= new Scanner(System.in);
    	
        String fromServerjson;
        System.out.println("Elija una opcion");
        System.out.println("1 Clima");
        System.out.println("2 Tractor");
        System.out.println("3 Satelite");
        System.out.println("4 Cotizador");
        
        String entrada= on.next();
        String fromUser="{\"data\":\""+entrada+"\"}";;
        
        
    
       out.println(fromUser);
       unSocket.setSoTimeout(50000);
      
      try{
       
            fromServerjson = in.readLine();             
            System.out.println(fromServerjson);
        
            if(entrada=="1" && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Clima C= new Clima();
                if (properties.getProperty("id")!=null)
                     C.setClima_id(Integer.parseInt( properties.getProperty("id")));
                else
                    C.setClima_id(-1);
                
                if (properties.getProperty("hum")!=null)
                    C.setHumedad(Integer.parseInt( properties.getProperty("hum")));
                else
                   C.setHumedad(-1);
                	
                if (properties.getProperty("vie")!=null)
                    C.setViento(Integer.parseInt( properties.getProperty("vie")));
                else
                   C.setViento(-1);
                
                if (properties.getProperty("tem")!=null)
                    C.setDepartamento(Integer.parseInt( properties.getProperty("tem")));
                else
                   C.setDepartamento(-1);
                
                if (properties.getProperty("dis")!=null)
                    C.setDistrito(Integer.parseInt( properties.getProperty("dis")));
                else
                   C.setDistrito(-1);
              
                if (properties.getProperty("dep")!=null)
                    C.setDepartamento(Integer.parseInt( properties.getProperty("dep")));
                else
                   C.setDepartamento(-1);
                            
                if (properties.getProperty("zon")!=null)
                    C.setZona(Integer.parseInt( properties.getProperty("zon")));
                else
                   C.setZona(-1);
            }
            else if(entrada=="2" && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Tractor T= new Tractor();
                if (properties.getProperty("id")!=null)
                    T.setTractor_id(Integer.parseInt( properties.getProperty("id")));
                else
                    T.setTractor_id(-1);
                
                if (properties.getProperty("coord_x")!=null)
                    T.setCoord_x(Integer.parseInt( properties.getProperty("coord_x")));
                else
                    T.setCoord_x(-1);
                
                if (properties.getProperty("coord_y")!=null)
                    T.setCoord_y(Integer.parseInt( properties.getProperty("coord_y")));
                else
                    T.setCoord_y(-1);
                
                if (properties.getProperty("alt")!=null)
                    T.setAltura(Integer.parseInt( properties.getProperty("alt")));
                else
                    T.setAltura(-1);
                
                if (properties.getProperty("hum")!=null)
                    T.setHumedad(Integer.parseInt( properties.getProperty("hum")));
                else
                    T.setHumedad(-1);
                
                if (properties.getProperty("pes")!=null)
                    T.setPeso(Integer.parseInt( properties.getProperty("pes")));
                else
                    T.setPeso(-1);
                
                if (properties.getProperty("tem")!=null)
                    T.setTemperatura(Integer.parseInt( properties.getProperty("tem")));
                else
                    T.setTemperatura(-1);
            }
            else if(entrada=="3" && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Satelite S= new Satelite();
                if (properties.getProperty("id")!=null)
                   S.setSatelite_id(Integer.parseInt( properties.getProperty("id")));
                else
                    S.setSatelite_id(-1);
                
                if (properties.getProperty("coord_x")!=null)
                    S.setCoord_x(Integer.parseInt( properties.getProperty("coord_x")));
                else
                	S.setCoord_x(-1);
                
                if (properties.getProperty("coord_y")!=null)
                    S.setCoord_y(Integer.parseInt( properties.getProperty("coord_y")));
                else
                    S.setCoord_y(-1);
                
                if (properties.getProperty("dis")!=null)
                    S.setDistrito(Integer.parseInt( properties.getProperty("dis")));
                else
                   S.setDistrito(-1);
                
                if (properties.getProperty("dep")!=null)
                   S.setDepartamento(Integer.parseInt( properties.getProperty("dep")));
                else
                   S.setDepartamento(-1);
             
            }
            else if(entrada=="3" && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Cotizador Coti= new Cotizador();
                if (properties.getProperty("precio")!=null)
                   Coti.setPrecio(Integer.parseInt( properties.getProperty("precio")));
                else
                	Coti.setPrecio(-1);
            }
                
            else {
            	out.close();
            	in.close();
            	unSocket.close();
            	return -1; 
        }        
            
        out.close();
        in.close();
        unSocket.close();
        
      }catch (SocketTimeoutException ste) 
            {
            System.out.println("TimeOut: El paquete se asume perdido.");
            out.close();
            in.close();
            unSocket.close(); 
            return -3;
            }
    return 1;
    }
	
	
	
	
	
}
