package scott.mvc.examples.application;

import scott.mvc.gui.table.FTableDefinition;
import scott.mvc.gui.table.FTableFilter.SORT_TYPE;

public class AppTableDefinition extends FTableDefinition
{	
	@Override
	protected Object[][] getTableDefinition()
	{
		 Object[][] COLUMN_DETAILS = { 
	                { "ID", 30, SORT_TYPE.NUMBER, false },
	                { "Date", 100, SORT_TYPE.DATE, false },
	                { "Payee", 300, SORT_TYPE.STRING, false } };
	        return COLUMN_DETAILS;
	}

}
