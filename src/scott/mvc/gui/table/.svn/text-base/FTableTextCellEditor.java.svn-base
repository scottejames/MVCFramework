package scott.mvc.gui.table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

public class FTableTextCellEditor implements IFTableCellEditor
{
    private Table _table = null;
    private String _oldValue = null;
    private String _newValue = null;
    
    // Control
    private Text _text;

    public FTableTextCellEditor(Table t)
    {
        _table = t;
        createControl();
    }

    public String getText()
    {
        createControl();
        _newValue =  _text.getText();
        if (validate(_oldValue, _newValue) == false)
        {
            // Invalid dont change
            _newValue = _oldValue;
        }
        return _newValue;
    }

    public void setText(String s)
    {
        _oldValue = s;
        _text.setText(s);
    }


    public void dispose()
    {
        _text.dispose();
    }

    public void addListener(int eventType, Listener listener)
    {
        createControl();
        _text.addListener(eventType, listener);
    }

    public Control getControl()
    {
        createControl();
        return _text;
    }

    public void setFocus()
    {
        createControl();
        _text.setFocus();
    }

    public void createControl()
    {
        if((_text==null) || (_text.isDisposed()))
        {
            _text = new Text(_table,SWT.NONE);
        }        
    }

    public boolean validate(String oldValue, String newValue)
    {
        return true;
    }

    public void setCurrentEditedRow(FTableRow row)
    {
        // TODO Auto-generated method stub
        
    }

    public boolean canEdit()
    {
        // TODO Auto-generated method stub
        return true;
    }
    
    
    
    
}
