package scott.mvc.gui.table;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;

public interface IFTableCellEditor
{
    public  String getText();
    public void setText(String s);
    public void dispose();
    public void addListener(int eventType, Listener listener);
    public Control getControl();
    public void setFocus();
    public void createControl();
    public void setCurrentEditedRow(FTableRow row);
    public boolean canEdit();
    public boolean validate(String oldValue, String newValue);
    
}
