/**
* 
* 
* @author Juan Pblo Velazquez Caballero - 24500566
* @version 3/17/2019
*/

import java.util.Vector; //Libreria para poder usar vectores
import java.awt.geom.*;
import java.awt.*;
import javax.swing.JFrame;
public class PuntosEcuacion extends JFrame
{
    private Vector<Punto> puntos;//vector de tipo punto
    private Polinomio poli;
    
    
    public PuntosEcuacion(Polinomio Poli,double Linf,double Lsup,double Inc)
    {
        super("Area de Dibujo2D");
        
        this.poli = Poli;
        puntos = new Vector();
       
         if(Linf<Lsup){
            for(double i=Linf;i<=Lsup;i+=Inc)
                puntos.addElement(new Punto (i,poli.evalua(i)));
        }
        else 
            puntos.addElement(new Punto (Linf, poli.evalua(Linf)));
            
        
        }
        
        
    
    
     public Vector getPuntosEcuacion(){
        return this.puntos;
    }
    
    public Punto getPunto(int num){
        return this.puntos.elementAt(num);
    }
    
    /* private void imprimir(){
        System.out. println("X: \t\t Y:");
        for(int i = 0;i < puntos.size();i++){
            System.out.println(puntos.elementAt(i).getX() 
            + "\t\t" + puntos.elementAt(i).getY());
        }
    }*/
    
    public boolean handleEvent(Event e)
    {
        if(e.id == Event.WINDOW_DESTROY) //preguntar que si lp que esta pasando es un cirre de ventana (evento)
           {
               hide(); //esconderla
               dispose(); //liberar los recursos, matarla
               return true;
            }
        return super.handleEvent(e);        
        
    }
   public void paint(Graphics gc)
   {
       //DIBUJA EL PLANO
       Graphics2D g2d = (Graphics2D) gc;
       g2d.setStroke(new BasicStroke(2.0f));
       for(int i=0; i<=1000; i+=100)
            gc.drawLine(i,0,i,1000);
       for(int j=0; j<=1000; j+=100)
            gc.drawLine(0,j,1000,j);
       //TERMINA DE DIBUJAR EL PLANO
            
       //dibujar eje X y Y
       g2d.setStroke(new BasicStroke(5.0f));
       g2d.setColor(Color.BLUE);
       g2d.drawLine(0,500,1000,500);
       g2d.drawLine(500,0,500,1000);
       //termina de dibujar
       
       //INDICA EJES
       Font fuente = new Font("Arial", Font.BOLD,50);
       g2d.setFont(fuente);
       g2d.setColor(Color.RED);
       g2d.drawString("Y", 510, 70);
       g2d.drawString("X", 950, 550);
       
       g2d.translate(500,500);//pone el punto en el medio
       
       
       //Funciones
       g2d.setColor(Color.MAGENTA);
      
       g2d.setStroke(new BasicStroke(5.0f));
       GeneralPath polilinea = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                                               puntos.size());
                           
       polilinea.moveTo(100*((double)puntos.elementAt(0).getX()), 100*(-1*((double)puntos.elementAt(0).getY())));
       
       for(int Z=1;Z< puntos.size();Z++)
           polilinea.lineTo(100*((double)puntos.elementAt(Z).getX()), 100*(-1*((double)puntos.elementAt(Z).getY())));
      // polilinea.closePath();   
      
       g2d.draw(polilinea);
       
       g2d.setStroke(new BasicStroke(1.0f));
       g2d.setColor(Color.ORANGE);
       g2d.drawString(poli.toString(), -490,-400);
       
       
    }                                                                                                   
    
    public static void main()
    {
        Polinomio polinom;
        polinom = new Polinomio(1);
        polinom.agregaTermino(new Termino(3, 2));
       /* polinom.agregaTermino(new Termino(1, 1));
        polinom.agregaTermino(new Termino(1, 3));*/
        PuntosEcuacion vent = new PuntosEcuacion(polinom, -100,100,.1);
        vent.resize(1000,1000);
        vent.show();
    }
}