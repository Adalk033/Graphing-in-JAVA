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
    int trasX=0, trasY=0;

    public Mapa(String titulo) throws IOException
    {
       super(titulo);
       
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(1,1));
       
       String c, punto_X, punto_Y;
       vectorPuntos = new Vector<Point>();
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
       addWindowListener(new CierraVentana() );//para indicar que cuando se da click a la X se cierre la ventana
       setSize(600,600);
       setVisible(false);
    }

    private class CierraVentana extends WindowAdapter //todo esto sera encargado de esconder la ventana y lo ANIQUILA!!! MIAUJUA
    {
        public void windowClosing(WindowEvent e)
        {
            setVisible(false);
            dispose();
        }
    }


    public void traslacion(int x, int y)
    {
        this.trasX = x;
        this.trasY = y;
    }

    public void paint(Graphics g) 
    {
        int index = 0;
        g.translate(100+trasX, 100+trasY);
        Graphics2D g2d = (Graphics2D)g;
        //Este ciclo va a graficar punto por punto hasta que se trace la recta completa
        for(index = 0; index < vectorPuntos.size() - 1; index++ )
            g2d.draw(new Line2D.Float(vectorPuntos.get(index), vectorPuntos.get(index + 1)));
        
    }

    public static void main(String[] args)
    {
        try
        {
            Mapa Mapa = new Mapa("Graficos en Java");
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }    
}