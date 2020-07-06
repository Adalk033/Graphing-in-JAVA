import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Elipse extends Canvas 
{
    private Vector<Point> vectorPuntos;
    private Vector<Point> vectorPuntos1;
    private Vector<Point> vectorPuntos2;
    private Vector<Point> vectorPuntos3; 

    public Elipse() 
    {
        vectorPuntos = new Vector<Point>();
        vectorPuntos1 = new Vector<Point>();
        vectorPuntos2 = new Vector<Point>();
        vectorPuntos3 = new Vector<Point>();
    }

    public void setPuntos(int a, int b, int incre)
    {
        double x, y, dl, d2;
        int xEntera, yEntera;

        x=0;
        y=b;
        dl= Math.pow(b, 2) - (Math.pow(a,2) *b) + (.25 * Math.pow(a,2));
        
        yEntera = (int) y;
        xEntera = (int) x;
        vectorPuntos.add(new Point(xEntera, yEntera));
        vectorPuntos1.add(new Point(xEntera, -yEntera));
        vectorPuntos2.add(new Point(-xEntera, yEntera));
        vectorPuntos3.add(new Point(-xEntera, -yEntera));

        while((Math.pow(a,2) * (y-0.5)) > (Math.pow(b,2) * (x+1)) )
        {
            if(dl<0)
                dl += Math.pow(b, 2) * ((2*x)+3);
            else
            {
                dl += Math.pow(b, 2) * ((2*x)+3) + Math.pow(a, 2) * ((-2*y)+2);
                y = y - (incre*.10);
            }
            x = x + (incre*.10);

            yEntera = (int) y;
            xEntera = (int) x;

            vectorPuntos.add(new Point(xEntera, yEntera));
            vectorPuntos1.add(new Point(xEntera, -yEntera));
            vectorPuntos2.add(new Point(-xEntera, yEntera));
            vectorPuntos3.add(new Point(-xEntera, -yEntera));
        }
        
        d2 = (Math.pow(b, 2) * Math.pow(x+0.5,2)) + (Math.pow(a,2) * Math.pow(y-1, 2)) - (Math.pow(a,2) * Math.pow(b,2));
        while(y>0)
        {
            if(d2 < 0)
            {
                d2 += (Math.pow(b, 2) * ((2*x) + 2)) + (Math.pow(a,2) * ((-2*y)+3));
                x = x + (incre*.10);
            }
            else
                d2 += Math.pow(a, 2)*((-2*y)+3);
            y = y - (incre*.10);
            yEntera = (int) y;
            xEntera = (int) x;
                
            vectorPuntos.add(new Point(xEntera, yEntera));
            vectorPuntos1.add(new Point(xEntera, -yEntera));
            vectorPuntos2.add(new Point(-xEntera, yEntera));
            vectorPuntos3.add(new Point(-xEntera, -yEntera));
        }
    }

    public void paint(Graphics g) 
    {
        int index = 0;
        g.translate(170, 170);
        g.drawLine(-200, 0, 650, 0);
        g.drawLine(0, -400, 0, 500);
        Graphics2D g2d = (Graphics2D)g;

        for(index = 0; index< vectorPuntos.size() - 1; index++)
        {
            g2d.draw(new Line2D.Float(vectorPuntos.get(index), vectorPuntos.get(index+1)));
            g2d.draw(new Line2D.Float(vectorPuntos1.get(index), vectorPuntos1.get(index+1)));
            g2d.draw(new Line2D.Float(vectorPuntos2.get(index), vectorPuntos2.get(index+1)));
            g2d.draw(new Line2D.Float(vectorPuntos3.get(index), vectorPuntos3.get(index+1)));
        }
    }
}