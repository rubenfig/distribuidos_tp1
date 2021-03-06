import com.google.gson.Gson;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Properties;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


public class Cliente {

	final Gson gson = new Gson();
    Socket unSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    InputStream inImg=null;	
    Scanner on=null;
    
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
    		on = new Scanner(System.in);
           
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
		
    	
        String fromServerjson;
        System.out.println("Elija una opcion");
        System.out.println("1 Clima");
        System.out.println("2 Tractor");
        System.out.println("3 Satelite");
        System.out.println("4 Cotizador");
        String entrada= on.next();
        String opcion;
        if(entrada.equals("4"))
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
        
            if(entrada.equals("1") && fromServerjson != null)
            {	
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Clima C= new Clima();
                if (properties.getProperty("id")!=null)
                     C.setClima_id(Integer.parseInt( properties.getProperty("id")));
                else
                    C.setClima_id(-1);
                
                if (properties.getProperty("humedad")!=null)
                    C.setHumedad(Double.parseDouble( properties.getProperty("humedad")));
                else
                   C.setHumedad(-1);
                	
                if (properties.getProperty("viento")!=null)
                    C.setViento(Double.parseDouble( properties.getProperty("viento")));
                else
                   C.setViento(-1);
                
                if (properties.getProperty("temperatura")!=null)
                    C.setTemperatura(Double.parseDouble( properties.getProperty("temperatura")));
                else
                   C.setTemperatura(-1);
                
                if (properties.getProperty("distrito")!=null)
                    C.setDistrito(Integer.parseInt( properties.getProperty("distrito")));
                else
                   C.setDistrito(-1);
              
                if (properties.getProperty("departamento")!=null)
                    C.setDepartamento(Integer.parseInt( properties.getProperty("departamento")));
                else
                   C.setDepartamento(-1);
                            
                if (properties.getProperty("zona")!=null)
                    C.setZona(Integer.parseInt( properties.getProperty("zona")));
                else
                   C.setZona(-1);
                C.printClima();
            }
            else if(entrada.equals("2") && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Tractor T= new Tractor();
                if (properties.getProperty("id")!=null)
                    T.setTractor_id(Integer.parseInt( properties.getProperty("id")));
                else
                    T.setTractor_id(-1);
                
                if (properties.getProperty("posX")!=null)
                    T.setCoord_x(Double.parseDouble( properties.getProperty("posX")));
                else
                    T.setCoord_x(-1);
                
                if (properties.getProperty("posY")!=null)
                    T.setCoord_y(Double.parseDouble( properties.getProperty("posY")));
                else
                    T.setCoord_y(-1);
                
                if (properties.getProperty("altura")!=null)
                    T.setAltura(Double.parseDouble( properties.getProperty("altura")));
                else
                    T.setAltura(-1);
                
                if (properties.getProperty("humedad")!=null)
                    T.setHumedad(Double.parseDouble( properties.getProperty("humedad")));
                else
                    T.setHumedad(-1);
                
                if (properties.getProperty("peso")!=null)
                    T.setPeso(Double.parseDouble( properties.getProperty("peso")));
                else
                    T.setPeso(-1);
                
                if (properties.getProperty("temperatura")!=null)
                    T.setTemperatura(Double.parseDouble( properties.getProperty("temperatura")));
                else
                    T.setTemperatura(-1);
                
                T.printTractor();
            }
            else if(entrada.equals("3") && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Satelite S= new Satelite();
                if (properties.getProperty("id")!=null)
                   S.setSatelite_id(Integer.parseInt( properties.getProperty("id")));
                else
                    S.setSatelite_id(-1);
                
                if (properties.getProperty("posX")!=null)
                    S.setCoord_x(Double.parseDouble( properties.getProperty("posX")));
                else
                	S.setCoord_x(-1);
                
                if (properties.getProperty("posY")!=null)
                    S.setCoord_y(Double.parseDouble( properties.getProperty("posY")));
                else
                    S.setCoord_y(-1);
                
                if (properties.getProperty("distrito")!=null)
                    S.setDistrito(Integer.parseInt( properties.getProperty("distrito")));
                else
                   S.setDistrito(-1);
                
                if (properties.getProperty("departamento")!=null)
                   S.setDepartamento(Integer.parseInt( properties.getProperty("departamento")));
                else
                   S.setDepartamento(-1);
                S.printSatelite();
                if (properties.getProperty("imagen")!=null){
                	byte[] decoded= Base64.getDecoder().decode(properties.getProperty("imagen"));
                	FileOutputStream imageOutFile = new FileOutputStream(
        					"salida.png");

        			imageOutFile.write(decoded);

        			imageOutFile.close();
        			SwingUtilities.invokeLater(new Runnable()
        		    {
        		      public void run()
        		      {
        		        JFrame editorFrame = new JFrame("Image Demo");
        		        editorFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        		        
        		        BufferedImage image = null;
        		        try
        		        {
        		          image = ImageIO.read(new File("salida.png"));
        		        }
        		        catch (Exception e)
        		        {
        		          e.printStackTrace();
        		          System.exit(1);
        		        }
        		        ImageIcon imageIcon = new ImageIcon(image);
        		        JLabel jLabel = new JLabel();
        		        jLabel.setIcon(imageIcon);
        		        editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

        		        editorFrame.pack();
        		        editorFrame.setLocationRelativeTo(null);
        		        editorFrame.setVisible(true);
        		      }
        		    });
                }
                
            }
            else if(entrada.equals("4") && fromServerjson != null)
            {
                Properties properties = gson.fromJson(fromServerjson, Properties.class);
                Cotizador Coti= new Cotizador();
                if (properties.getProperty("precio")!=null)
                   Coti.setprecio(Double.parseDouble(properties.getProperty("precio")));
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
