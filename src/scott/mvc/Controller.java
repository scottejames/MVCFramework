package scott.mvc;

public abstract class Controller extends BaseController
{
    public Controller(Model m, BaseView v)
    {
        super(m, v);
        // TODO Auto-generated constructor stub
    }

    public View getView()
    {
        return (View) super.getView();
    }
}
