package scott.mvc.gui.table;

import org.apache.log4j.Logger;
import scott.mvc.ViewSelection;

public class FTableRowSelection extends ViewSelection
{
    @SuppressWarnings("unused")
	private static Logger _logger = Logger.getLogger(FTableView.class);

    public FTableRow selectedRow;
    public FTableRow oldRow;
    
    public FTableRowSelection(FTableRow row,FTableRow old)
    {
        selectedRow = row;
        oldRow = old;
    }
    
    public String toString()
    {
        return "(" + selectedRow + ")";
    }
}
