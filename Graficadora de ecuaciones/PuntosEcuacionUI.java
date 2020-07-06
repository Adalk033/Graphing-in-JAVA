/**
 * Calculadora en SWING
 * 
 * @version 3.0
 */
import java.awt.*;
import java.awt.event.*;       //Para usar el modelo de eventos JAVA 1.1
import javax.swing.*;          //Para utilizar la interfaz de SWING



public class PuntosEcuacionUI extends JFrame
{
    private JButton BotPolinomio, BotTermino, BotGraficar;//botones que van a aparecer
    
    private JTextField campoFuncion, campoNoPuntos;
    
    //private JPanel pad, displ; //pad y botón de borrado
    
    
    
    private String campoFnum, sign;
    
    public PuntosEcuacionUI()
    {
        super("Graficadora");//titulo vent
        BotPolinomio = new JButton("agrega Polinomio"); 
        BotTermino = new JButton("agrega Termino");
        BotGraficar = new JButton("Graficar");
        
        
        
        BotPolinomio.addActionListener(new BotonAccion());//registrar como generador de eventos action
        BotTermino.addActionListener(new BotonAccion());
        BotGraficar.addActionListener(new Graficar());
        
        
        campoFuncion = new JTextField ("x^3");
        campoNoPuntos = new JTextField("Holi");
        campoFuncion.setBounds(10,150,150,40);
        campoNoPuntos.setBounds(10,450,150,40);
       // campoFuncion.setEnabled(true);
        add(campoFuncion);
        add(campoNoPuntos);
        
        setLayout(null);
        
        
       
        BotPolinomio.setBounds(10,200,150,40);
        BotTermino.setBounds(10,500,150,40);
        BotGraficar.setBounds(10,700,150,40);
        add(BotPolinomio);
        add(BotTermino);
        add(BotGraficar);
        
        
        
        
        
        addWindowListener(new CW());//Se registran como generadres de eventos
        //Cuando ocurre un evento de ventana se genera un objeto CW para lidiar con él.
        
        setSize(300,350);
        setVisible(true);
    }
    
    private class CW extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            setVisible(false); //Sustituye al viejo método hide()
            dispose();
        }
    }
    
    
    private class BotonAccion implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            campoFnum = campoFuncion.getText();
                if( campoFnum.equals("") )
                    campoFnum = "";
                campoFnum = campoFnum + e.getActionCommand();
                campoFuncion.setText( campoFnum ); 
        }
    }
    
    private class Graficar implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            PuntosEcuacion.main();
        }
    }
    
   
    public static void main (String args[])
    {
        PuntosEcuacionUI calc = new PuntosEcuacionUI();
        // ESTO ES DE LA VERSIÓN VIEJA Y SE SUSTITUYE CON A3*
                //  calc.resize(350,350);
                //  calc.show();
        //Y CAMBIA A:
        calc.setSize(1000,1000);
        calc.setVisible(true);
    }
    
    
}
