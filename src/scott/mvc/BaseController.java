package scott.mvc;

import java.util.List;
import org.apache.log4j.Logger;
import scott.mvc.BaseView.VIEW_EVENT;

/**
 * 
 * BaseController implements the behaviour aspect of the MVCFramework
 * 
 * @author scott
 *
 */
public abstract class BaseController implements IReciever<ViewEvent>
{
    private static Logger _logger = Logger.getLogger(BaseController.class);
    private Model _model = null;
    private BaseView _view = null;
    private BaseController _delegateController = null;

    /**
     * Constructs a new controller from the given model and view
     * @param m
     * @param v 
     */
    public BaseController(Model m, BaseView v)
    {
        _model = m;
        _view = v;
    }

    
    /* (non-Javadoc)
     * @see scott.mvc.IReciever#received(java.util.List)
     */
    public void received(List<ViewEvent> events)
    {
        for (ViewEvent event : events)
        {
            _logger.debug("[recieved] processing event: " + event);
            // Pass all non controller events to the derived class
            if (!(event.event instanceof VIEW_EVENT))
            {
                _logger
                        .debug("[recieved] non controller event so passing to client");
                handleEvent(event);
            } else
            {
                
                // If a delegate controller is given then get it to handle all the VIEW EVENT events
                // this means that 'this' is only reponsible for non core vents.  This is useful for 
                // specalist controlers for child views (e.g. FTable)
                if (_delegateController != null)
                {
                    _logger
                            .debug("[recieved] controller event being passed to DELEGATE");
                    _delegateController.received(events);
                } else
                {
                    _logger.info("[recieved] controller event - " + event.event + "(" + event.arg + ")");
                    switch ((VIEW_EVENT) event.event)
                    {
                    case OPENED:
                        _model.attachViewToModel(_view);
                        viewOpened();
                        break;
                    case CLOSED:
                        _model.dettachViewFromModel(_view);
                        viewClosed((BaseView)event.arg);
                        break;
                    case SELECTION_CHANGED:
                        viewSelectionChanged((ViewSelection) event.arg);
                        break;
                    case SELECTION_MODIFIED:
                        viewSelectionModified((ViewSelection) event.arg);
                        break;
                    case MENU_ITEM_SELECTED:
                        menuItemSelected((Integer) event.arg);
                        break;
                    case OK_SELECTED:
                        okSelected();
                        break;
                    case CANCEL_SELECTED:
                        cancelSelected();
                        break;
                    default:
                        _logger
                                .error("[recieved] Unable to process controller event so passing to client");
                        break;
                    }
                }
            }
        }
    }



    protected void handleEvent(ViewEvent event)
    {
        _logger.error("Unsupported event " + event);
    }

    // The following methods can be overriden by derived classes to implement
    // behaviour based
    // on events occuring
    protected void menuItemSelected(int arg)
    {
    }

    protected void viewSelectionChanged(ViewSelection selection)
    {
    }
    
    protected void viewSelectionModified(ViewSelection selection)
    {
        
    }

    protected void viewOpened()
    {
    }

    protected void viewClosed(BaseView v)
    {
    }

    protected void cancelSelected()
    {
    }

    protected void okSelected()
    {
        getView().close();
    }

    public Model getModel()
    {
        return _model;
    }

    public BaseView getView()
    {
        return _view;
    }

    public String getName()
    {
        return "BASE_CONTROLLER";
    }

    public String toString()
    {
        return getName();
    }

    public void attachDelegateController(BaseController c)
    {
        _delegateController = c;
    }
}
