package scott.mvc;

import java.util.ArrayList;
import java.util.List;

/**
 * A Dispatcher implementation which sends any submitted message 
 * immediately to the Receiver within the same thread. 
 * @param <T>
 */
public class DirectDispatcher<T> implements IDispatcher<T>
{
   private IReciever<T> receiver;
   
   public DirectDispatcher(IReciever<T> receiver) throws 
                                             NullPointerException
   {
      if (receiver == null) throw new NullPointerException();
      this.receiver = receiver;
   }
   
   public void push(T object) throws NullPointerException
   {
      if (object == null) throw new NullPointerException(); 
      List<T>  events =new ArrayList<T>();
      events.add(object);
      this.receiver.received(events);
   }
   

public void push(List<T> events) throws NullPointerException
{
    this.receiver.received(events);    
}


public void addReciever(IReciever<T> r)
{
    if (receiver == null) throw new NullPointerException();
    this.receiver = r;    
}


public void removeReciever(IReciever<T> r)
{
    this.receiver = null;
}
}