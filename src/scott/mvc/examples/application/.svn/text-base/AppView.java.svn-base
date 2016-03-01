package scott.mvc.examples.application;

import net.miginfocom.swt.MigLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;

import scott.mvc.Model;
import scott.mvc.View;
import scott.mvc.gui.Utils;
import scott.mvc.gui.table.FTableController;
import scott.mvc.gui.table.FTableView;

public class AppView extends View
{
    private Menu _menuBar = null;
    private AppModel _model = null;


	public AppView(Shell parentShell, AppModel model)
	{
		super(parentShell, model);
		_model =  model;
		
		MigLayout layout = new MigLayout("");
        layout.setColumnConstraints("[1000,grow]");
        layout.setRowConstraints("[600,grow]");
        getShell().setLayout(layout);
        
        createMenuBars();
        createTable();
	}

	private void createTable()
	{
		 FTableView table = new FTableView(this, _model.getTableModel(),
         	"grow,hmin 100,wmin 200");
		 
	        FTableController tableCont = new FTableController(
	                _model.getTableModel(), table);
	        table.open(tableCont);

	}

	private void createMenuBars()
	{
	    _menuBar = new Menu(getShell(), SWT.BAR);
        getShell().setMenuBar(_menuBar);
        
        Menu fileMenu = Utils.addMenu(getShell(), _menuBar, "File");
        Utils.addMenuItem(fileMenu, "Menu A", getMenuListener(),
                AppController.MENU_OPTION_A);
        Utils.addMenuItem(fileMenu, "Menu B", getMenuListener(),
                AppController.MENU_OPTION_B);
		
	}

	@Override
	public void modelUpdated(Model m)
	{
		// TODO Auto-generated method stub
		
	}

}
