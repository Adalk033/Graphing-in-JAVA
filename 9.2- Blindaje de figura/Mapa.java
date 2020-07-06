import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Vector;
import java.io.*;
import java.util.*;

public class Mapa extends Frame 
{
    Panel panelDatos;
    private BufferedReader entrada;
    private Vector<Point> vectorPuntos;
    private StringTokenizer token;
    int largo, ancho, x, xmin, xmax, ymax, ymin;

    public static final int INSIDE = 0;
    public static final int LEFT   = 1;
    public static final int RIGHT  = 2;
    public static final int BOTTOM = 4;
    public static final int TOP    = 8;
    

    public Mapa(String titulo)
    {
        super(titulo);
        panelDatos = new Panel();
        panelDatos.setLayout(new GridLayout(1, 1));
        addWindowListener(new CierraVentana());// para indicar que cuando se da click a la X se cierre la ventana
        setSize(900,800);
        setVisible(false);
    }

    private class CierraVentana extends WindowAdapter
    {
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
        }
    }

    public void comoDibujar(int x) 
    {
        this.x = x;
    }

    public void Espejo() throws IOException
    {
       String c, punto_X, punto_Y;

       vectorPuntos = new Vector<Point>();

       //Estas variables cambian el tamano del mapa para que se puedan hacer las pruebas
       //No se debe modificar ninguna parte del codigo ademas del valor de abajo
       
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(1,1));
       try
       {
          entrada = new BufferedReader(new FileReader("Coordenadas.txt")); //Bueffered Reader lee toda la cadena de 6 bites y los junta
       }catch( FileNotFoundException e)
       {
           System.out.println("El archivo no se encuentra");
       }
       try
       {
          while( (c = entrada.readLine()) != null )
          {            
              
            token = new StringTokenizer(c, ",");
            punto_X = token.nextToken();
            punto_Y = token.nextToken();

            vectorPuntos.add(new Point(Integer.parseInt(punto_X), Integer.parseInt(punto_Y)));
          }
        }catch(IOException e)
        {
            e.printStackTrace();
        }    
        finally
        {
           if(entrada != null)
           {
                entrada.close();
           }
        }
    }

    public void datos(int x2, int y2, int x1, int y1)
    {
        this.xmax = x2;
        this.ymax = y2;
        this.xmin = x1;
        this.ymin = y1;
    }
    
    public void Recorte() throws IOException
    {
        String c, punto_X, punto_Y;

       vectorPuntos = new Vector<Point>();

       //Estas variables cambian el tamano del mapa para que se puedan hacer las pruebas
       //No se debe modificar ninguna parte del codigo ademas del valor de abajo
       
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(1,1));
       try
       {
          entrada = new BufferedReader(new FileReader("Coordenadas.txt")); //Bueffered Reader lee toda la cadena de 6 bites y los junta
       }catch( FileNotFoundException e)
       {
           System.out.println("El archivo no se encuentra");
       }
       try
       {
          while( (c = entrada.readLine()) != null )
          {            
              
            token = new StringTokenizer(c, ",");
            punto_X = token.nextToken();
            punto_Y = token.nextToken();


            vectorPuntos.add(new Point(Integer.parseInt(punto_X), Integer.parseInt(punto_Y)));
          }
        }catch(IOException e)
        {
            e.printStackTrace();
        }    
        finally
        {
           if(entrada != null)
           {
                entrada.close();
           }
        }
    }

    public void paint(Graphics g) 
    {
        int index = 0;
        g.translate(178, 450);
        Graphics2D g2d = (Graphics2D)g;
        if (x == 1)
        {   
            try 
            {
                Espejo();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            //Este ciclo va a graficar punto por punto hasta que se trace la recta completa
            g2d.setColor(Color.RED);
            for(index = 0; index < vectorPuntos.size() - 1; index++ )
                g2d.draw(new Line2D.Float(vectorPuntos.get(index), vectorPuntos.get(index + 1)));
        }
        else
        {
            try 
            {
                Recorte();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            g2d.setColor(Color.black);
            for(index = 0; index < vectorPuntos.size() - 1; index++ )
                g2d.draw(new Line2D.Float(vectorPuntos.get(index), vectorPuntos.get(index + 1)));

            g.translate(-178, -450);
            g2d.setColor(Color.white);
            g2d.fillRect(xmin, ymin, xmax-xmin, ymax-ymin);
        }
    }
    public static void main(String[] args)
    {
            Mapa Mapa = new Mapa("Graficos en Java");
    }   
}
