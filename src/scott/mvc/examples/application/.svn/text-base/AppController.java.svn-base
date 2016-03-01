package scott.mvc.examples.application;

import org.apache.log4j.Logger;

import scott.mvc.BaseView;
import scott.mvc.Controller;
import scott.mvc.Model;

public class AppController extends Controller
{

	public static final int MENU_OPTION_A = 0;
	public static final int MENU_OPTION_B = 1;

	private static Logger _logger = Logger.getLogger(AppController.class);

	public AppController(Model m, BaseView v)
	{
		super(m, v);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void menuItemSelected(int arg)
	{
		switch (arg)
		{
		case MENU_OPTION_A:
			_logger.info("menu item selected:  A");
			break;
		case MENU_OPTION_B:
			_logger.info("menu item selected:  B");
			break;
		}
	}

}
