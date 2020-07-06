import java.awt.*;
import java.awt.event.*;

public class Ventana extends Frame
{
    Elipse elipse;
    Panel panelDatos;
    TextField incremento, lado_A, lado_B;
    Label lblincremento, lblA, lblB;
    Button btnGraficar;

    public Ventana(String titulo)
    {
       super(titulo);

       elipse = new Elipse();
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(7,1));

       lblincremento = new Label("Incremento");
       lblA = new Label("Largo de A");
       lblB = new Label("Ancho de B");
    
       incremento = new TextField("0");
       lado_A = new TextField("0");
       lado_B = new TextField("0");
    
       btnGraficar = new Button("Graficar");
       btnGraficar.addActionListener(new BotonGrafica());

       panelDatos.add(lblincremento);
       panelDatos.add(incremento);
       panelDatos.add(lblA);
       panelDatos.add(lado_A);
       panelDatos.add(lblB);
       panelDatos.add(lado_B);
       panelDatos.add(btnGraficar);

       add(panelDatos,"West");
       add(elipse, "Center"); 
       addWindowListener(new CierraVentana() );//para indicar que cuando se da click a la X se cierre la ventana
       setSize(440,400);
       setVisible(true);
    }

    private class BotonGrafica implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            int incr = Integer.parseInt(incremento.getText());
            int A = Integer.parseInt(lado_A.getText());
            int B = Integer.parseInt(lado_B.getText());
            btnGraficar.setEnabled(false);
            elipse.setPuntos(A, B, incr);
            elipse.repaint();
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
        Ventana Ventana = new Ventana("Graficos en Java");
    }
}