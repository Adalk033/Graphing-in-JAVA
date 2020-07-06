import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Vector;
import java.io.*;
import java.util.*;

public class Mapa extends Frame 
{
    Panel panelDatos;
    private double matriz[][] = new double[3][3];
    private double vector2[][] = new double[3][1];
    private double vector1[][] = new double[3][1];
    private BufferedReader entrada;
    private Vector<Point> vectorPuntos, vectorViewPort;
    private StringTokenizer token;
    int largo, ancho, x;
    

    public Mapa(String titulo)
    {
        super(titulo);
        panelDatos = new Panel();
        panelDatos.setLayout(new GridLayout(1, 1));
        addWindowListener(new CierraVentana());// para indicar que cuando se da click a la X se cierre la ventana
        setVisible(false);
    }

    private class CierraVentana extends WindowAdapter
    {
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
        }
    }

    public void espejar(int x) 
    {
        this.x = x;
        largo = x;
        ancho = x;
    }

    public void Espejo() throws IOException
    {
       double Sx, Sy, x,y, l,a;
       int two, x_i, y_i; 
       String c, punto_X, punto_Y;

       vectorPuntos = new Vector<Point>();
       vectorViewPort = new Vector<Point>();

       //Estas variables cambian el tamano del mapa para que se puedan hacer las pruebas
       //No se debe modificar ninguna parte del codigo ademas del valor de abajo
       
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(1,1));

       for (int index=0; index<3;index++)
       {
           for(two=0; two<3;two++)
                matriz[index][two]=0;
       }
       l = (double) largo;
       a = (double) ancho;
       Sx = (a- 0.0)/(250.0 - 4.0);
       Sy = (l - 40.0)/(250.0 - 8.0);
       matriz[0][0] = Sx;
       matriz[0][2] = (-Sx*2+0);
       matriz[1][1] = Sy;
       matriz[1][2] = (-Sy*2+0);

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
            
            x = Double.parseDouble(punto_X);
            y = Double.parseDouble(punto_Y);
            
            vector1[0][0] = x;
            vector1[1][0] = y;

            vector2[0][0] = matriz[0][0]*vector1[0][0] + matriz[0][2];
            vector2[1][0] = matriz[1][1]*vector1[1][0] + matriz[1][2];

            x_i = (int) vector2[0][0];
            y_i = (int) vector2[1][0];

            vectorViewPort.add(new Point(x_i, y_i));
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

    public void paint(Graphics g) {
        int index = 0;
        setSize(largo, largo);
        g.translate(0, 40);
        Graphics2D g2d = (Graphics2D)g;
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
        for(index = 0; index < vectorViewPort.size() - 1; index++ )
            g2d.draw(new Line2D.Float(vectorViewPort.get(index), vectorViewPort.get(index + 1)));
    }
    public static void main(String[] args)
    {
            Mapa Mapa = new Mapa("Graficos en Java");
    }   
}