package scott.mvc;

import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

public abstract class ChildView extends BaseView implements
IReciever<ViewEvent>
{
    private BaseController _controller;
    private static Logger _logger = Logger.getLogger(ChildView.class);
    
    public ChildView(View parentView, Model model)
    {
        this(parentView, model, SWT.NONE);

    }

    public ChildView(View parentView, Model model, int style)
    {
        super( parentView,model);
        parentView.attachReciever(this);
    }
   

    @Override
    public void modelUpdated(Model m)
    {
        
    }


    public void received(List<ViewEvent> events)
    {
        try
        {
            for (ViewEvent event : events)
            {
                _logger.debug("[recieved] processing event: " + event);
                if (event.event instanceof VIEW_EVENT)
                {
                    switch ((VIEW_EVENT) event.event)
                    {
                    case OPENED:
                        BaseController c = (BaseController)event.arg;
                        _logger.debug("Attaching childView to delegate (parent) controller");
                        attachDelegateController(c);
                        _logger.debug("Attaching childView to model");
                        getModel().attachViewToModel(this);
                        break;
                    case CLOSED:
                        _logger.debug("Detaching childView from model");
                        getModel().dettachViewFromModel(this);
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        finally
        {
            
            
        }
    }


    @Override
    public void close()
    {
        dettachReciever(_controller);

    }

    public void attachDelegateController(BaseController c)
    {
        _controller.attachDelegateController(c);
    }
    
    @Override
    public void open(BaseController c)
    {
        setupNotifiers();
        attachReciever(c); 
        _controller=c;
    }
    
    public Shell getShell()
    {
        if (getParentView() instanceof View)
        {   
            return ((View)getParentView()).getShell();
        } 
        else
        {
            // An irrelevent check but .. nice to have !
            return null;
        }
    }




}
