package scott.mvc.examples.application;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class AppMain
{
    public static Color RED = null;
    public static Color GRAY = null;
    public static Color BLACK = null;
    
    
    public  static Shell shell = null;
    public static Display display = null;
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		
	      display = new Display();
	        shell = new Shell(display);
	         RED = display.getSystemColor(SWT.COLOR_RED);
	        GRAY = display.getSystemColor(SWT.COLOR_GRAY);
	        BLACK = display.getSystemColor(SWT.COLOR_BLUE);
	        
	       AppModel model = new AppModel();
	        AppView view = new AppView(shell, model);
	        AppController controller = new AppController(
	                model, view);
	        view.open(controller);


	}
}
