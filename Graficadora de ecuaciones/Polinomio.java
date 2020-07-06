/**
* 
* 

* @version 3/17/2019
*/
public class Polinomio
{
    private Termino terminos[];
    private int tam=0;
    public Polinomio(int grado){
        terminos = new Termino[grado];
    }
    
    public void agregaTermino(Termino Term){
        terminos[tam] = Term;
        tam++;
    }
    
    public double evalua(double x){
        double res = 0.0;
        Termino temp;
        for (int i = 0;i<terminos.length;i++){
            temp = terminos[i];
            res += temp.evalua(x);
        }
        return res;
    }
    
    public String toString()
    {
       String poli="";
         for (int i = 0;i<terminos.length;i++){
            if(i>0)
                poli= poli + "+";
            poli= poli + "" + terminos[i].getCoeficiente()+ "X ^" + terminos[i].getExponente();
        }
        return poli;//(terminos[0].getCoeficiente() + " X ^ " + terminos[0].getExponente());
       
    }
}
