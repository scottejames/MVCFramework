package scott.mvc;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import scott.mvc.gui.Utils;
import scott.mvc.gui.listeners.MenuListener;

public abstract class View extends BaseView
{
    private static Logger _logger = Logger.getLogger(View.class);
    
    private Shell _shell = null;
    private MenuListener _menuListener = null;

    public View(Shell parentShell, Model model)
    {
        this(parentShell, model, SWT.DIALOG_TRIM);
    }

    public View(Shell parentShell, Model model, int style)
    {
        super(model);
        _shell = new Shell(parentShell, style);
        setupNotifiers();

    }


    @Override
    protected void setupNotifiers()
    {
       
        _shell.addListener(SWT.Close, new Listener()
        {
            public void handleEvent(Event event)
            {
                notifyClosed(View.this);
            }
        });
        _menuListener = new MenuListener()
        {
            public void menuSelected(Object key)
            {
                notifyMenuItemSelected(key);
            }
        };
    }

    public Shell getShell()
    {
        return _shell;
    }

    public void open(BaseController c)
    {
        attachReciever(c);
        notifyOpen(c);
        _shell.pack();
        _shell.open();

        getModel().notifyUpdate();
        Display display = _shell.getDisplay();
        while (!_shell.isDisposed())
        {
            if (!display.readAndDispatch())
                display.sleep();
        }
        dettachReciever(c);
    }

    public void close()
    {
        notifyClosed(this);
        _shell.close();
    }

    public void displayError(String error)
    {
        Utils.displayError(error, _shell, _logger);
    }

    public void displayInfo(String info)
    {
        Utils.displayInfo(info, _shell, _logger);
    }
    public String requestFileNameFromUser(String title,String []filter)
    {
        
        FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN);
        fileDialog.setText(title==null?"Enter text":title);
        if (filter != null)
        {
            fileDialog.setFilterExtensions(filter);
        }
        String name = fileDialog.open();
        return name;
    }
    public String requestFileNameFromUser()
    {
        return requestFileNameFromUser(null,null);
    }

    public boolean isDisposed()
    {
        return _shell.isDisposed();
    }

    public MenuListener getMenuListener()
    {
        return _menuListener;
    }

    public String getName()
    {
        return "BASE_VIEW";
    }

    @Override
    public String toString()
    {
        return getName();
    }


}
