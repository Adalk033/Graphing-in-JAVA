import java.awt.*;
import java.awt.geom.*;
import java.util.Vector;
import java.math.*;

public class Algoritmo extends Canvas 
{
    private Vector<Point> vectCollec, vect1, vect2, vect3, vect4, vect5, vect6, vect7; // Esto es un manejador

    public Algoritmo()
    {
       vectCollec = new Vector<Point>();
       vect1 = new Vector<Point>();
       vect2 = new Vector<Point>();
       vect3 = new Vector<Point>();
       vect4 = new Vector<Point>();
       vect5 = new Vector<Point>();
       vect6 = new Vector<Point>();
       vect7 = new Vector<Point>();
    }

    public void setPuntos(int rax)
    {
       int y=0, x=0, d;
       //La ecuacion es Y = RAIZ( R^2 - X^2 )
       // Este ciclo lo que va a hacer es ir agregando punto por punto a la collecion
       // para despues graficar punto por punto
       x = 0;
       y = rax;
       d = 1 - rax;
       vectCollec.add(new Point(x, y));
       vect1.add(new Point(y,x));
       vect2.add(new Point(y,-x));
       vect3.add(new Point(-x,y));
       vect4.add(new Point(-x,-y));
       vect5.add(new Point(-y,-x));
       vect6.add(new Point(-y,x));
       vect7.add(new Point(x,-y));
       while(y > x)
       {
            if(d < 0)
                d += 2 * x + 3;
            else
            {
                d += 2 * (x-y) + 5;
                y--;
            }
            x++;
            vectCollec.add(new Point(x, y));
            vect1.add(new Point(y,x));
            vect2.add(new Point(y,-x));
            vect3.add(new Point(-x,y));
            vect4.add(new Point(-x,-y));
            vect5.add(new Point(-y,-x));
            vect6.add(new Point(-y,x));
            vect7.add(new Point(x,-y));
       }
    }

    public void paint(Graphics g)
    {
        int index = 0;
        g.translate(100, 150);
        g.drawLine(-100, 0, 650, 0);
        g.drawLine(0, -400, 0, 500);
        Graphics2D g2d = (Graphics2D)g;
        //Este ciclo va a graficar punto po`r punto hasta que se trace la recta completa
        for(index = 0; index < vectCollec.size() - 1; index++ )
        {
            g2d.draw(new Line2D.Float(vectCollec.get(index), vectCollec.get(index + 1)));
            g2d.draw(new Line2D.Float(vect1.get(index), vect1.get(index + 1)));
            g2d.draw(new Line2D.Float(vect2.get(index), vect2.get(index + 1)));
            g2d.draw(new Line2D.Float(vect3.get(index), vect3.get(index + 1)));
            g2d.draw(new Line2D.Float(vect4.get(index), vect4.get(index + 1)));
            g2d.draw(new Line2D.Float(vect5.get(index), vect5.get(index + 1)));
            g2d.draw(new Line2D.Float(vect6.get(index), vect6.get(index + 1)));
            g2d.draw(new Line2D.Float(vect7.get(index), vect7.get(index + 1)));
        }
    }
}