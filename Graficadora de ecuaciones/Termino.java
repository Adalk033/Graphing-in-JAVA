/**
* 
*
* @version 3/19/2019
*/

public class Termino
{
    private double Coeficiente;
    private int Exponente;
    
    public Termino(double coef, int exp){
        this.Coeficiente = coef;
        this.Exponente = exp;
    }
    
    public double evalua(double x){
        return Coeficiente*(Math.pow(x,Exponente));
    }

    public int getExponente(){
        return Exponente;
    }
    
    public double getCoeficiente(){
        return Coeficiente;
    }
}
