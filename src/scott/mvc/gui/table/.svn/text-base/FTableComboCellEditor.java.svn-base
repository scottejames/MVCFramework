package scott.mvc.gui.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

public abstract class FTableComboCellEditor implements IFTableCellEditor
{

    Combo _combo = null;
    Table _table = null;
    
    public FTableComboCellEditor(Table t)
    {
        _table = t;
    }
    
    public abstract String [] getOptions();
    

    public void addListener(int eventType, Listener listener)
    {
        _combo.addListener(eventType, listener);
    }


    public void dispose()
    {
        _combo.dispose();
    }


    public Control getControl()
    {
        return _combo;
    }


    public String getText()
    {

        return _combo.getText();
    }


    public void setFocus()
    {
        createControl();

        _combo.setFocus();
        
    }


    public void setText(String s)
    {
        createControl();
        if (_combo.indexOf(s) == -1)
        {
            // For this popup .. next time its disposed this will be removed
            _combo.add(s);
        }
        _combo.select(_combo.indexOf(s));

    }


    public void createControl()
    {
        
        if ((_combo == null) || (_combo.isDisposed()))
        {
            _combo = new Combo(_table,SWT.READ_ONLY);
            for (String option: getOptions())
            {
                _combo.add(option);
            }
           
        }        
    }

    public boolean validate(String oldValue, String newValue)
    {
        return true;
    }
    
    
   
}
