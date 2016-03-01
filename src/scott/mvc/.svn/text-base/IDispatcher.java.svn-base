package scott.mvc;

import java.util.List;


public interface IDispatcher<T>
{
    public void push(T event) throws NullPointerException; 
    public void push(List<T> events) throws NullPointerException;
    public void addReciever(IReciever<T> r);
    public void removeReciever(IReciever<T> r);

}