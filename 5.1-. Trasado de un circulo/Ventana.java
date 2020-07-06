import java.awt.*;
import java.awt.event.*;

public class Ventana extends Frame
{
    Algoritmo algoritmo;
    Panel panelDatos;
    TextField radio;
    Label lblincremento, lblradio;
    Button btnGraficar;

    public Ventana(String titulo)
    {
       super(titulo);

       algoritmo = new Algoritmo();
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(5,1));

       lblradio = new Label("Radio");
    
       radio = new TextField("0");
    

       btnGraficar = new Button("Graficar");
       btnGraficar.addActionListener(new BotonGrafica());

       panelDatos.add(lblradio);
       panelDatos.add(radio);
       panelDatos.add(btnGraficar);

       add(panelDatos,"West");
       add(algoritmo, "Center"); 
       addWindowListener(new CierraVentana() );//para indicar que cuando se da click a la X se cierre la ventana
       setSize(350,350);
       setVisible(true);
    }

    private class BotonGrafica implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            int rad = Integer.parseInt(radio.getText());
            btnGraficar.setEnabled(false);
            algoritmo.setPuntos(rad);
            algoritmo.repaint();
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
        Ventana ventana = new Ventana("Graficos en Java");
    }
}