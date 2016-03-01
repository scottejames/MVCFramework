package scott.mvc.examples.application;

import scott.mvc.Model;
import scott.mvc.gui.table.FTableModel;

public class AppModel extends Model
{
    private AppTableModel _appTableModel = 
        new AppTableModel(this, new AppTableDefinition());

    
	public FTableModel getTableModel()
	{
		return _appTableModel;
	}


}
