package scott.mvc;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class MultiDispatcher<T> implements IDispatcher<T>
{
    private static Logger _logger = Logger.getLogger(MultiDispatcher.class);

    private final List<IReciever<T>> receiver = new ArrayList<IReciever<T>>();
    public MultiDispatcher()
    {
    }
    public void addReciever(IReciever<T> r)
    {
        _logger.debug("[addReciever[ adding reciever "  + r );
        receiver.add(r);
    }
    public void removeReciever(IReciever<T> r)
    {
        _logger.debug("[removeReciever[ removing reciever "  + r );

        receiver.remove(r);
    }
    private void send(List<T> events)
    {
        for (IReciever<T> r : receiver)
        {
            _logger.debug("[send] sending events to reciever "  + r );

            r.received(events);
        }
    }

    public void push(T object) throws NullPointerException
    {
        if (object == null)
            throw new NullPointerException();
        List<T> events = new ArrayList<T>();
        events.add(object);
        this.send(events);
    }

    public void push(List<T> events) throws NullPointerException
    {
        this.send(events);
    }
}
