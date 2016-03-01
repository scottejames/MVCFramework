package scott.mvc.gui.table;

import org.apache.log4j.Logger;
import scott.mvc.Controller;
import scott.mvc.ViewEvent;
import scott.mvc.gui.table.FTableView.TABLE_EVENT;

public  class FTableController extends Controller
{
    private static Logger _logger = Logger.getLogger(FTableController.class);

    public FTableController(FTableModel m, FTableView v)
    {
        super(m, v);
    }
    
    @Override
    protected void handleEvent(ViewEvent event)
    {
        if (!(event.event instanceof TABLE_EVENT))
        {
            _logger.info("[handleEvent]  unable to handle event so passing up");
            super.handleEvent(event);
        } else
        {
            switch ((TABLE_EVENT) event.event)
            {
    
            case TABLE_ROW_SELECTED:
                rowSelected((FTableRow)event.arg);
                break;
            }
        }
    }


    
    
    protected  void rowSelected(FTableRow row)
    {
        _logger.debug("[rowSelected] row selected, binding to the model row: " + row);
        ((FTableModel)getModel()).setSelectedRow(row);
    }




}
