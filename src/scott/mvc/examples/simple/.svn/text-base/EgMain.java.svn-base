package scott.mvc.examples.simple;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class EgMain
{
	public static Shell shell = null;
	public static Display display = null;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		display = new Display();
		shell = new Shell(display);

		EgModel model = new EgModel();
		EgView view = new EgView(shell, model);
		EgController controller = new EgController (model,
				view);
		view.open(controller);

	}
}
