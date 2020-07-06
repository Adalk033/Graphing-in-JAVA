import java.awt.*;
import java.awt.event.*;

public class Ventana_Circulo extends Frame
{
    Circulo circulo;
    Panel panelDatos;
    TextField incremento, radio;
    Label lblincremento, lblradio;
    Button btnGraficar;

    public Ventana_Circulo(String titulo)
    {
       super(titulo);

       circulo = new Circulo();
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(5,1));

       lblincremento = new Label("Incremento");
       lblradio = new Label("Radio");
    
       incremento = new TextField("0");
       radio = new TextField("0");
    

       btnGraficar = new Button("Graficar");
       btnGraficar.addActionListener(new BotonGrafica());

       panelDatos.add(lblincremento);
       panelDatos.add(incremento);
       panelDatos.add(lblradio);
       panelDatos.add(radio);
       panelDatos.add(btnGraficar);

       add(panelDatos,"West");
       add(circulo, "Center"); 
       addWindowListener(new CierraVentana() );//para indicar que cuando se da click a la X se cierre la ventana
       setSize(350,350);
       setVisible(true);
    }

    private class BotonGrafica implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            int incr = Integer.parseInt(incremento.getText());
            double rad = Double.parseDouble(radio.getText());
            btnGraficar.setEnabled(false);
            circulo.setPuntos(incr, rad);
            circulo.repaint();
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
        Ventana_Circulo Ventana_Circulo = new Ventana_Circulo("Graficos en Java");
    }
}