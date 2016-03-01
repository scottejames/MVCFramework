package scott.mvc;

import java.util.Observable;
import java.util.Observer;
import org.apache.log4j.Logger;

public abstract class Model extends Observable implements Observer
{
    private static Logger _logger = Logger.getLogger(Model.class);
    private Model _parentModel  = null;
    
    // Context used to indicate that the model  update is non specific
    protected enum CONTEXT {NULL, SELECTION,CUSTOM1,CUSTOM2,CUSTOM3};
    
    public Model()
    {
        
    }
    
    public Model(Model model)
    {
        attachToModel(model);
        _parentModel = model;
    }
    
    public Model getParentModel()
    {
        return _parentModel;
    }
    
    public void attachViewToModel(BaseView v)
    {
        _logger
                .debug("[attachToView] attaching model " + this + " to view "
                        + v);
        
        // This view will be notified when the model changes.
        addObserver(v);
    }

    public void dettachViewFromModel(BaseView v)
    {
        _logger.debug("[dettachFromView] detaching model " + this
                + " from view " + v);
        deleteObserver(v);
    }
    public void attachToModel(Model m)
    {
        _logger
                .debug("[attachToView] attaching model " + this + " to model "
                        + m);
        // This model will be notified when the model changes, as 'this' is a model 
        // look at the update method in this class to see what happens.
        m.addObserver(this);
    }

    public void dettachFromModel(Model m)
    {
        _logger.debug("[dettachFromView] detaching model " + this
                + " from model " + m);
        
       m. deleteObserver(this);
    }
    
    public String getName()
    {
        return "BASE_MODEL";
    }

    public String toString()
    {
        return getName();
    }
    
    
    /***
     * This method is called when something I am observing changes, typically this is for 
     * nested models (appModel)
     */
    public  void update(Observable o, Object arg)
    {
        _logger.debug("Model has changed notifying observers");
        
        // React to the change, this method should be overloaded in classes that want to 
        // use this mechanism.  Thus appModel (notifies) changes
        
        dependentModelUpdated((Model)o,(CONTEXT)arg);
        
        // Change people listening to me that I have changed
        
        notifyUpdate(CONTEXT.NULL);

    }
    
    protected  void dependentModelUpdated(Model m,CONTEXT context)
    {
        _logger.error("Observer is not listening!!!");

    }
    
    private boolean isDirty = false;
    private boolean holdUpdates = false;
    
    public void holdUpdates()
    {
        _logger.debug("holding updates");
        holdUpdates = true;
    }
    public void releaseUpdates()
    {
        _logger.debug("releasing updates");

        holdUpdates=false;
        if (isDirty)
            notifyUpdate();
    }
    
    public void notifyUpdate()
    {
        notifyUpdate(CONTEXT.NULL);
    }
    
    public void  notifyUpdate(CONTEXT context)
    {
        if (holdUpdates==false)
        {
            _logger.debug(this +" is notify observers that the model has changed there " + this.countObservers() + "  observers");
            setChanged();
            notifyObservers(context);
           
        } else
        { 
            isDirty = true;
        } 
    }
}
