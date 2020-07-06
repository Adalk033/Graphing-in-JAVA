import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Ventana extends Frame {
    Mapa mapa;
    Panel panelDatos;
    TextField puntox1, puntoy1;
    Label lblPunto1, lblPunto2;
    Button btnGraficar;

    public Ventana(String titulo) throws IOException
    {
       super(titulo);

       mapa = new Mapa("Mapa de Mexico");
        
       panelDatos = new Panel();
       panelDatos.setLayout(new GridLayout(5,1));

       lblPunto1 = new Label("Escalado en X:");
       lblPunto2 = new Label("Escalado en Y:");

       puntox1 = new TextField("0");
       puntoy1 = new TextField("0");   

       btnGraficar = new Button("Graficar");
       btnGraficar.addActionListener(new BotonGrafica());

       panelDatos.add(lblPunto1);
       panelDatos.add(puntox1);
       panelDatos.add(lblPunto2);
       panelDatos.add(puntoy1);
       panelDatos.add(btnGraficar);

       add(panelDatos,"Center");
       addWindowListener(new CierraVentana() );//para indicar que cuando se da click a la X se cierre la ventana

       setSize(200,350);
       setVisible(true);
    }

    private class BotonGrafica implements ActionListener
    {
        public void actionPerformed( ActionEvent event) 
        {
            double x1 = Double.parseDouble(puntox1.getText());
            double y1 = Double.parseDouble(puntoy1.getText());
            mapa.setVisible(false);
            mapa.escalado(x1, y1);
            mapa.repaint();
            mapa.setVisible(true);
        }
    }

    private class CierraVentana extends WindowAdapter 
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