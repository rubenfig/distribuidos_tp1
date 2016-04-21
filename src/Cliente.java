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
        String opcion;
        if(entrada=="4")
        {
        	System.out.println("elija una opcion");
        	System.out.println("tomate");
        	System.out.println("soja");
        	System.out.println("locote");
        	opcion= on.next();
        }
        else {
        	System.out.println("ingrese ID");
        	opcion= on.next();
        }
        	
        
        String fromUser="{\"data\":\""+entrada+"\",\"opcion\":\""+opcion+"\"}";
        
        
    
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
                
                if (properties.getProperty("humedad")!=null)
                    C.setHumedad(Integer.parseInt( properties.getProperty("humedad")));
                else
                   C.setHumedad(-1);
                	
                if (properties.getProperty("viento")!=null)
                    C.setViento(Integer.parseInt( properties.getProperty("viento")));
                else
                   C.setViento(-1);
                
                if (properties.getProperty("temperatura")!=null)
                    C.setDepartamento(Integer.parseInt( properties.getProperty("temperatura")));
                else
                   C.setDepartamento(-1);
                
                if (properties.getProperty("distrito")!=null)
                    C.setDistrito(Integer.parseInt( properties.getProperty("distrito")));
                else
                   C.setDistrito(-1);
              
                if (properties.getProperty("departemento")!=null)
                    C.setDepartamento(Integer.parseInt( properties.getProperty("departemento")));
                else
                   C.setDepartamento(-1);
                            
                if (properties.getProperty("zona")!=null)
                    C.setZona(Integer.parseInt( properties.getProperty("zona")));
                else
                   C.setZona(-1);
                C.printClima();
            }
            else if(entrada=="2" && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Tractor T= new Tractor();
                if (properties.getProperty("id")!=null)
                    T.setTractor_id(Integer.parseInt( properties.getProperty("id")));
                else
                    T.setTractor_id(-1);
                
                if (properties.getProperty("posX")!=null)
                    T.setCoord_x(Integer.parseInt( properties.getProperty("posX")));
                else
                    T.setCoord_x(-1);
                
                if (properties.getProperty("posY")!=null)
                    T.setCoord_y(Integer.parseInt( properties.getProperty("posY")));
                else
                    T.setCoord_y(-1);
                
                if (properties.getProperty("altura")!=null)
                    T.setAltura(Integer.parseInt( properties.getProperty("altura")));
                else
                    T.setAltura(-1);
                
                if (properties.getProperty("humedad")!=null)
                    T.setHumedad(Integer.parseInt( properties.getProperty("humedad")));
                else
                    T.setHumedad(-1);
                
                if (properties.getProperty("peso")!=null)
                    T.setPeso(Integer.parseInt( properties.getProperty("peso")));
                else
                    T.setPeso(-1);
                
                if (properties.getProperty("temperatura")!=null)
                    T.setTemperatura(Integer.parseInt( properties.getProperty("temperatura")));
                else
                    T.setTemperatura(-1);
                
                T.printTractor();
            }
            else if(entrada=="3" && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Satelite S= new Satelite();
                if (properties.getProperty("id")!=null)
                   S.setSatelite_id(Integer.parseInt( properties.getProperty("id")));
                else
                    S.setSatelite_id(-1);
                
                if (properties.getProperty("posX")!=null)
                    S.setCoord_x(Integer.parseInt( properties.getProperty("posX")));
                else
                	S.setCoord_x(-1);
                
                if (properties.getProperty("posY")!=null)
                    S.setCoord_y(Integer.parseInt( properties.getProperty("posY")));
                else
                    S.setCoord_y(-1);
                
                if (properties.getProperty("distrito")!=null)
                    S.setDistrito(Integer.parseInt( properties.getProperty("distrito")));
                else
                   S.setDistrito(-1);
                
                if (properties.getProperty("departamento")!=null)
                   S.setDepartamento(Integer.parseInt( properties.getProperty("departemento")));
                else
                   S.setDepartamento(-1);
                S.printSatelite();
                
            }
            else if(entrada=="3" && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Cotizador Coti= new Cotizador();
                if (properties.getProperty("precio")!=null)
                   Coti.setprecio(Integer.parseInt(properties.getProperty("precio")));
                else
                	Coti.setprecio(-1);
                
                Coti.printCotizador();
                
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
