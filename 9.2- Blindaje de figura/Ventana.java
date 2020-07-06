import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Ventana extends Frame 
{
    Mapa mapa;
    Panel panelDatos;
    Button btnGraficar, cuadrante2;
    TextField xmin, xmax, ymin, ymax;
    Label lxmin, lxmax, lymin, lymax;

    public Ventana(String titulo) throws IOException
    {
       super(titulo);

       mapa = new Mapa("Estrella");
       mapa.setVisible(false);
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(5,2));
       lxmin = new Label("X-MIN");
       lxmax = new Label("X-MAX");
       lymax = new Label("Y-MAX");
       lymin = new Label("Y-MIN");

    
       xmin = new TextField("0");
       xmax = new TextField("0");
       ymin = new TextField("0");
       ymax = new TextField("0");

       btnGraficar = new Button("Dibujar Estrella completa");
       cuadrante2 = new Button("Dibujar Blindaje");

       btnGraficar.addActionListener(new BotonGrafica());
       cuadrante2.addActionListener(new BotonGrafica2());

       panelDatos.add(lxmax);
       panelDatos.add(xmax);
       panelDatos.add(lymax);
       panelDatos.add(ymax);
       panelDatos.add(lxmin);
       panelDatos.add(xmin);
       panelDatos.add(lymin);
       panelDatos.add(ymin);
       panelDatos.add(btnGraficar);
       panelDatos.add(cuadrante2);

       add(panelDatos,"Center");
       addWindowListener(new CierraVentana() );//para indicar que cuando se da click a la X se cierre la ventana

       setSize(300,350);
       setVisible(true);
       
    }

    private class BotonGrafica implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            mapa.setVisible(false);
            mapa.comoDibujar(1);
            mapa.repaint();
            mapa.setVisible(true);
        }
    }

    private class BotonGrafica2 implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            mapa.setVisible(false);
            mapa.comoDibujar(2);
            int uno, dos, tres, cuatro;
            uno = Integer.parseInt(xmax.getText());
            dos = Integer.parseInt(ymax.getText());
            tres = Integer.parseInt(xmin.getText());
            cuatro = Integer.parseInt(ymin.getText());
            mapa.datos(uno, dos, tres, cuatro);
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