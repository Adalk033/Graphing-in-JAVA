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
    int point_X, point_Y;
    double x_prima, y_prima, escalarX, escalarY;

    public Mapa(String titulo)
    {
        super(titulo);

        panelDatos = new Panel();
        panelDatos.setLayout(new GridLayout(1, 1));

        addWindowListener(new CierraVentana());// para indicar que cuando se da click a la X se cierre la ventana
        setSize(600, 600);
        setVisible(false);
    }

    private class CierraVentana extends WindowAdapter
    {
        public void windowClosing(WindowEvent e) 
        {
            setVisible(false);
            dispose();
        }
    }

    public void escalado(double x, double y) 
    {
        escalarX = x;
        escalarY = y;
    }

    public void crearVector() throws IOException
    {
        String c, punto_X, punto_Y;
        vectorPuntos = new Vector<Point>();
        try {
            entrada = new BufferedReader(new FileReader("Coordenadas.txt")); // Bueffered Reader lee toda la cadena de 6
                                                                             // bites y los junta
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no se encuentra");
        }
        try {
            while ((c = entrada.readLine()) != null) {

                token = new StringTokenizer(c, ",");
                punto_X = token.nextToken();
                punto_Y = token.nextToken();
                x_prima = Double.parseDouble(punto_X) * escalarX;
                y_prima = Double.parseDouble(punto_Y) * escalarY;
                point_X = (int) x_prima;
                point_Y = (int) y_prima;
                vectorPuntos.add(new Point(point_X, point_Y));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }
    }

    public void paint(Graphics g) 
    {
        int index = 0;
        g.translate(300, 300);
        try 
        {
            crearVector();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        Graphics2D g2d = (Graphics2D)g;
        g.translate(-250, -250);
        for(index = 0; index < vectorPuntos.size() - 1; index++ )
            g2d.draw(new Line2D.Float(vectorPuntos.get(index), vectorPuntos.get(index + 1)));
    }
}