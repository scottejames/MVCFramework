package scott.mvc.gui.table;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import scott.mvc.Model;

public abstract class FTableModel extends Model
{
    
    private static Logger _logger = Logger.getLogger(FTableView.class);
    private List<FTableRow> _data = null;
    private HashMap<Object, FTableRow> _privateDataIndex = null;
    protected FTableFilter _filter = null;
    private FTableRow _selectedRow = null;
    private FTableDefinition _tableDefinition = null;

    public FTableModel()
    {
        super();
        _data = null;
        _filter = new FTableFilter();
        
    }
    public FTableModel(Model m, FTableDefinition tableDefinition)
    {
        super(m);
        _data = null;
        _tableDefinition = tableDefinition;
        _filter = new FTableFilter();
        _filter.setFilterTypes(tableDefinition.getColumnFilters());
    }
    
    // An array of:
    //   Column names
    //   Column width
    //   Column filter (or null)
    
    public FTableDefinition getTableDefinition()
    {
        return _tableDefinition;
    }
  
    public void setColumn(int column)
    {
        _filter.setColumn(column);

        notifyUpdate();
    }

    public void reverseDirection()
    {
        _filter.reverseDirection();

        notifyUpdate();
    }

    public List<FTableRow> getModelData()
    {
        return _data;
    }

    public void setModelData(List<FTableRow> rows)
    {
        _logger.debug("Adding " + rows.size() + " rows to table model ready for a repaint");
        _data = rows;
        _privateDataIndex = new HashMap<Object,FTableRow>();
        for (FTableRow row: rows)
        {
            _privateDataIndex.put(row.getHiddenRowData(), row);
        }
    }

    public FTableRow getRowByHiddenData(Object id)
    {
        return _privateDataIndex.get(id);
    }
    public List<FTableRow> getFilteredModelData()
    {
        if (getModelData() != null)
        {
            _logger.debug("requesting table data returning");
            Collections.sort(getModelData(), _filter);

            return _data;
        } else
        {
            _logger.debug("requesting table data returning NULL");

            return null;
        }
    }

    public FTableRow getSelectedRow()
    {
        return _selectedRow;
        
    }
    public void setSelectedRow(FTableRow row)
    {
        _selectedRow = row;
     }

}
