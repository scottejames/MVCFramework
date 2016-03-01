package scott.mvc;

import java.util.List;

public interface IReciever<T>
{
   /**
    * Invoked by the target dispatcher when flushing events. 
    *  
    * @param events An array of events (size at least one) 
    *    currently pushed into the dispatcher. The elements of 
    *    the array are guaranteed to be in the order in which 
    *    they were pushed into the dispatcher (insertion-order).  
   **/
   public void received(List<T> events);
}

