
/**
* 
* 

* @version 3/17/2019
*/
public class Punto <E>
{
    private E x, y;
    
    public Punto(E x, E y)
    {
       this.x=x;
       this.y=y;
    }
    
    public E getX()
    {
        return this.x;
    }
    
    public void setX(E x)
    {
        this.x=x;//(x > 0) ? x : 0;
    }
    
    public E getY()
    {
        return this.y;
    }
    
    
    public void setY(E y)
    {
        this.y=y;//(y > 0) ? y : 0;
    }
    
    
    
    
    
}
