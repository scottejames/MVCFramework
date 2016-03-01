package scott.mvc.gui.table;

import scott.mvc.gui.table.FTableFilter.SORT_TYPE;

public abstract class FTableDefinition
{
    
    private final int NAME = 0;
    private final int WIDTH = 1;
    private final int SORT_TYPE = 2;
    private final int HIDDEN = 3;
    
    protected abstract Object [][]getTableDefinition();
    
    public int getNumberOfCoulumns()
    {
        return getTableDefinition().length;
    }
    private void getDefinitionSlice(int index, Object[] results)
    {
        Object [][] data = getTableDefinition();
        
        int i = 0;
        
        for(Object [] row: data)
        {
            results[i++] = row[index];
        }
    }
    public  String [] getColumnNames()
    {
        String results [] = new String[getNumberOfCoulumns()];
        getDefinitionSlice(NAME,results);
        return results;  
    }
    public Integer [] getColumWidths()
    {
         Integer results[] = new Integer[getNumberOfCoulumns()];
        getDefinitionSlice(WIDTH,results);
        return results;
    }
    public SORT_TYPE [] getColumSortTypes()
    {
        SORT_TYPE results[] = new SORT_TYPE[getNumberOfCoulumns()];
        getDefinitionSlice(SORT_TYPE,results);
        return results;
    }
    public SORT_TYPE [] getColumnFilters()
    {
        SORT_TYPE results[] = new SORT_TYPE[getNumberOfCoulumns()];
        getDefinitionSlice(SORT_TYPE,results);
        return results;
    }
    public Boolean [] getColumnHidden()
    {
        Boolean results[] = new Boolean[getNumberOfCoulumns()];
        getDefinitionSlice(HIDDEN,results);
        return results;
    }

    
    
    
    
    
}
