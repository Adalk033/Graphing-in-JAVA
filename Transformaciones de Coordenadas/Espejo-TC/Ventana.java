import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Ventana extends Frame 
{
    Mapa mapa;
    Panel panelDatos;
    Button btnGraficar, cuadrante2, cuadrante3, cuadrante4;

    public Ventana(String titulo) throws IOException
    {
       super(titulo);

       mapa = new Mapa("Mapa de Mexico");
        
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(3,1));

       btnGraficar = new Button("INICIO");
       cuadrante2 = new Button("Espejo en Y");
       cuadrante4 = new Button("Espejo en X");

       btnGraficar.addActionListener(new BotonGrafica());
       cuadrante2.addActionListener(new BotonGrafica2());
       cuadrante4.addActionListener(new BotonGrafica4());

       panelDatos.add(btnGraficar);
       panelDatos.add(cuadrante2);
       panelDatos.add(cuadrante4);

       add(panelDatos,"Center");
       addWindowListener(new CierraVentana() );//para indicar que cuando se da click a la X se cierre la ventana

       setSize(200,250);
       setVisible(true);
    }

    private class BotonGrafica implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            mapa.setVisible(false);
            mapa.espejar(1, 1);
            mapa.repaint();
            mapa.setVisible(true);
        }
    }

    private class BotonGrafica2 implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            mapa.setVisible(false);
            mapa.espejar(-1, 1);
            mapa.repaint();
            mapa.setVisible(true);
        }
    }

    private class BotonGrafica4 implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            mapa.setVisible(false);
            mapa.espejar(1, -1);
            mapa.repaint();
            mapa.setVisible(true);
        }
    }

    private class CierraVentana extends WindowAdapter //todo esto sera encargado de esconder la ventana y lo ANIQUILA!!! MIAUJUA
    {
        public void windowClosing(WindowEvent e)
        {
            setVisible(false);
            dispose();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            Ventana Ventana = new Ventana("Graficos en Java");
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }  
}