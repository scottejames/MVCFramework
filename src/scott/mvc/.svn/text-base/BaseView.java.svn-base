package scott.mvc;

import java.util.Observable;
import java.util.Observer;
import org.apache.log4j.Logger;

public abstract class BaseView implements Observer
{
    private static Logger _logger = Logger.getLogger(BaseView.class);

    private BaseView _parent;
    private Model _model;
    private IDispatcher<ViewEvent> _dispatcher = null;
    public enum VIEW_EVENT
    {
        OK_SELECTED, CANCEL_SELECTED, OPENED, CLOSED, SELECTION_CHANGED, 
        SELECTION_MODIFIED, NEW_VIEW, DELETE_VIEW, MENU_ITEM_SELECTED, OTHER
    };

    public BaseView(BaseView parent, Model model)
    {
        _parent = parent;
        _model = model;
    }

    public BaseView(Model model)
    {
        _model = model;
    }

    public void attachReciever(IReciever<ViewEvent> reciever)
    {
        _logger.debug("[attachToController] adding " + reciever + "  to view ");
        if (_dispatcher == null)
            _dispatcher = new MultiDispatcher<ViewEvent>();
        _dispatcher.addReciever(reciever);
    }

    public void dettachReciever(IReciever<ViewEvent> reciever)
    {
        if (_dispatcher != null)
        {
            _logger.debug("[dettachFromController] removing " + reciever
                    + "  to view ");
            _dispatcher.removeReciever(reciever);
        }
    }

    public void notifyOpen(BaseController c)
    {
        sendEvent(new ViewEvent(VIEW_EVENT.OPENED, c));
    }

    public void notifyClosed(BaseView v)
    {
        sendEvent(new ViewEvent(VIEW_EVENT.CLOSED, v));
    }

    public void notifySelectionChanged(ViewSelection selection)
    {
        sendEvent(new ViewEvent(VIEW_EVENT.SELECTION_CHANGED, selection));
    }
    public void notifySelectionModified(ViewSelection selection)
    {
        sendEvent(new ViewEvent(VIEW_EVENT.SELECTION_MODIFIED, selection));
    }
    

    public void notifyMenuItemSelected(Object arg)
    {
        sendEvent(new ViewEvent(VIEW_EVENT.MENU_ITEM_SELECTED, arg));
    }

    public void notifyOKSelected()
    {
        sendEvent(new ViewEvent(VIEW_EVENT.OK_SELECTED, null));
    }

    public void notifyCancelSelected()
    {
        sendEvent(new ViewEvent(VIEW_EVENT.CANCEL_SELECTED, null));
    }

    public void sendEvent(ViewEvent event)
    {
        if (_dispatcher != null)
        {
            _logger.debug("[sendEvent] view sending event " + event);
            _dispatcher.push(event);
        } else
        if (_parent != null)
        {
            // If I have a parent then send the event to them as well.
            _logger.debug("[sendEvent] view sending event  to parent" + event);
            _parent.sendEvent(event);
        }
    }


    public void update(Observable o, Object arg)
    {
        modelUpdated((Model) o);
    }

    public abstract void modelUpdated(Model m);
    public abstract void open(BaseController c);
    public abstract void close();
    protected abstract void setupNotifiers();

    // getters and setters
    protected Model getModel()
    {
        return _model;
    }

    protected void setDispatcher(IDispatcher<ViewEvent> dispatcher)
    {
        _dispatcher = dispatcher;
    }

    public BaseView getParentView()
    {
        return _parent;
    }
}
