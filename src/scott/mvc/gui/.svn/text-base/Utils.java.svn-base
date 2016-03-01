package scott.mvc.gui;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import scott.mvc.gui.listeners.MenuListener;
import scott.mvc.gui.listeners.TreeItemListener;
import scott.mvc.gui.table.FTableView;




public class Utils
{
    private static Logger _logger = Logger.getLogger(Utils.class);

    
    public static void displayError(String msg, Shell shell)
    {
        displayError(msg, shell, null);
    }

    public static void displayError(String msg, Shell shell, Logger logger)
    {

        if (logger != null)
            logger.error(msg);
        else
            _logger.error(msg);

        MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
        box.setMessage(msg);
        box.open();
    }
    
    public static void displayInfo(String msg, Shell shell, Logger logger)
    {

        if (logger != null)
            logger.error(msg);
        else
            _logger.error(msg);

        MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
        box.setMessage(msg);
        box.open();
    }

    public static Label createLabel(Object parent, String text, Object layout)
    {
        return createLabel(parent, text, layout, SWT.LEFT);
    }

    public static Composite getComposite(Object c)
    {
        if (c instanceof Control)
            return (Composite) c;

        return (Composite) ((TabItem) c).getControl();
    }

    public static Label createLabel(Object parent, String text, Object layout,
            int style)
    {
        final Label b = new Label(getComposite(parent), style
                | SWT.DOUBLE_BUFFERED);
        b.setText(text==null?"":text);
        b.setLayoutData(layout != null ? layout : text);

        return b;
    }
    public static Text createTextField(Object parent, String text, Object layout)
    {
        return createTextField(parent,text,layout,true);
    }
    
    public static Text createTextField(Object parent, String text, Object layout,boolean editable)
    {       
            final Text b = new Text(getComposite(parent), SWT.SINGLE | SWT.BORDER | SWT.DOUBLE_BUFFERED);
            b.setText(text==null?"":text);
            b.setLayoutData(layout != null ? layout : text);

            b.setEditable(editable);
            return b;
    }

    public static Button createButton(Object parent, String text, Object layout)
    {
        return createButton(getComposite(parent), text, layout, false);
    }

    public static Button createButton(Object parent, String text,
            Object layout, boolean bold)
    {
        Button b = new Button(getComposite(parent), SWT.PUSH
                | SWT.NO_BACKGROUND | SWT.DOUBLE_BUFFERED);
        b.setText(text.length() == 0 ? "\"\"" : text);
        b.setLayoutData(layout != null ? layout : text);

        return b;
    }

    public static FTableView createTable(Object parent, Object[][] columns,
            Object layout)
    {
  //     FTable table = new FTable(getComposite(parent),columns,layout);
        return null;
    }
    
    

 
    

    public static MenuItem addMenuItem(Menu parent,String name , final MenuListener listener ,  final Object key)
    {
        MenuItem result = new MenuItem(parent, SWT.NULL);
        result.setText(name);
        result.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
              listener.menuSelected(key);
            }
        });
        return result;
    }
    
    public static  Menu addMenu(Shell _shell, Menu _parent, String _name)
    {
        MenuItem title = new MenuItem(_parent, SWT.CASCADE);
        title.setText(_name);
        
        Menu result = new Menu(_shell, SWT.DROP_DOWN);
        title.setMenu(result);
        
        return result;
    }
    
    public static Combo createCombo(Object parent, String[] texts, Object layout)        
    {
        Combo b = new Combo(getComposite(parent), SWT.DROP_DOWN);                
        for (int i = 0; i < texts.length; i++)                       
            b.add(texts[i]);                
        b.setLayoutData(layout);
        return b; 
    }
    
    public static void editNodeByTreeItem(final TreeItem item,final TreeItemListener listener)
    {
        // Create the editor and set its attributes
        final TreeEditor editor = new TreeEditor(item.getParent());
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;
        // Create a text field to do the editing
        final Text text = new Text(item.getParent(), SWT.NONE);
        text.setText(item.getText());
        text.selectAll();
        text.setFocus();
        // If the text field loses focus, set its text into the tree
        // and end the editing session
        text.addFocusListener(new FocusAdapter()
        {
            public void focusLost(FocusEvent event)
            {
                item.setText(text.getText());
                listener.treeItemChanged(item.getText(),text.getText());
                text.dispose();
            }
        });
        // If they hit Enter, set the text into the tree and end the editing
        // session. If they hit Escape, ignore the text and end the editing
        // session
        text.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent event)
            {
                switch (event.keyCode)
                {
                case SWT.CR:
                    item.setText(text.getText());
                    listener.treeItemChanged(item.getText(),text.getText());
                    text.dispose();
                case SWT.ESC:
                    // End editing session
       
                    text.dispose();
                    break;
                }
            }
        });
        // Set the text field into the editor
        editor.setEditor(text, item);
    }
    
    
    public static Tree addTree(Shell shell, Object layout)
    {
        return null;
        
    }
    
    public static TreeItem addTreeItem(Shell shell, Tree parent,Object layout)
    {
        return null;
    }

    public static String toString(Object o)
    {
        if (o == null)
        {
            return "";
        } else
        {
            return o.toString();
        }
    }
    
    public static String boolToString(Boolean b)
    {
        if (b == null)
        {
            return " ";
            
        } else
        if (b == true)
        {
            return "T";
        } else
        {
            return "F";
        }
        
    }
    

    public static String dateToString(Date d)
    {
        DateFormat df =
            DateFormat.getDateInstance();
        
       return df.format(d);
    }
    public static Date stringToDate(String s) throws ParseException
    {
        DateFormat df =
            DateFormat.getDateInstance();
        return df.parse(s);
    }
}
