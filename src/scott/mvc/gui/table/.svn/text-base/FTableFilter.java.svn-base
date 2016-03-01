package scott.mvc.gui.table;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;
import org.apache.log4j.Logger;
import scott.mvc.gui.Utils;

public class FTableFilter implements Comparator<FTableRow>
{
    public SORT_TYPE[] _types = null;
    private static Logger _logger = Logger.getLogger(FTableView.class);
    public static final int SORT_FORWARDS = 0;
    public static final int SORT_BACKWARDS = 1;
    private int _direction = SORT_FORWARDS;
    public enum SORT_TYPE
    {
        NUMBER, DATE, STRING
    };
    public int _sortColumn = 0;


    public int compare(FTableRow c1, FTableRow c2)
    {
        return columnWiseCompare(c1, c2, _sortColumn);
    }

    public int columnWiseCompare(FTableRow c1, FTableRow c2, int column)
    {
        int rc = 0;
        try
        {
            if (column >= c1.getRowData().length)
            {
                // Give up .. trying to sort on a column that does not exist
                return 0;
            }
            SORT_TYPE type = null;
            if ((_types == null) || (_types.length < column))
            {
                // No type available .. use string
                type = SORT_TYPE.STRING;
            } else
            {
                if (column >= _types.length)
                    type = SORT_TYPE.STRING;
                else
                    type = _types[column];
            }
            String s1 = c1.getRowData()[column];
            String s2 = c2.getRowData()[column];
            
            switch (type)
            {
            case STRING:
                rc = Utils.toString(s1).compareTo(Utils.toString(s2));
                break;
            case NUMBER:
                Double l1 = Double.parseDouble(s1);
                Double l2 = Double.parseDouble(s2);
                rc = l1.compareTo(l2);
                break;
            case DATE:
                Date d1 = Utils.stringToDate(s1);
                Date d2 = Utils.stringToDate(s2);
                rc = d1.compareTo(d2);
                break;
            }
            if (_direction == SORT_BACKWARDS)
                rc = rc * -1;
        } catch (ParseException e)
        {
            _logger.error(e.toString());
            return 0;
        }
        if (rc != 0)
        {
            return rc;
        } else
        {
            return columnWiseCompare(c1, c2, ++column);
        }

    }

    public int getColumn()
    {
        return _sortColumn;
    }

    public void setColumn(int column)
    {
        _logger.debug("Set sort column to be " + column);
        _sortColumn = column;
    }

    public int getDirection()
    {
        return _direction;
    }

    public void setDirection(int _direction)
    {
        this._direction = _direction;
    }

    public void reverseDirection()
    {
        this._direction = 1 - this._direction;
    }

    public void setFilterTypes(SORT_TYPE[] list)
    {
        _types = list;
    }
}
