package scott.mvc.gui.table;

import org.apache.log4j.Logger;
import org.eclipse.swt.graphics.Color;

public  class FTableRow
{
    @SuppressWarnings("unused")
	private static Logger _logger = Logger.getLogger(FTableView.class);
    protected Color[] _foreColour = null;
    
    protected String[] _data = null;
    protected Object _hiddenData = null;
    
    
    public FTableRow(String[] data,Object hidden)
    {
        _data = data;
        _hiddenData = hidden;
         _foreColour = null;
    }
    public FTableRow(String[] data,Color[] color,Object hidden)
    {
        _data = data;
        _hiddenData = hidden;
        _foreColour = color;
        
    }
    public  String[] getRowData()
    {
        return _data;
    }
    public Color[] getForgroutColours()
    {
        return _foreColour;
    }
    public Object getHiddenRowData()
    {
        return _hiddenData;
    }
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Table Row: ");
        for(String row:_data)
        {
            buffer.append(" [" + row + "]");
        }
        buffer.append(" KEY: " + _hiddenData.toString());
        
        return buffer.toString();
    }
}
