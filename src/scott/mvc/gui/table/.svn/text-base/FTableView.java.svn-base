package scott.mvc.gui.table;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import scott.mvc.ChildView;
import scott.mvc.Model;
import scott.mvc.View;
import scott.mvc.ViewEvent;

public class FTableView extends ChildView
{
    public enum TABLE_EVENT
    {
        TABLE_CELL_SELECTED, TABLE_ROW_SELECTED
    }
    private static Logger _logger = Logger.getLogger(FTableView.class);
    protected Table _table;
    protected TableCursor _cursor = null;
    protected int columnCount = 0;
    protected int columnSelected = 0;
    protected FTableModel _tableModel = null;
    protected View _parentView = null;
    protected int _selectedColumn = 0;
    protected int _selectedRow = 0;
    protected Menu _menu = null;
    private FTableModel _model = null;
    private Thread _updateLock = null;

    public FTableView(View parentView, FTableModel model, String layout)
    {
        // Don't re-parent this view ..
        super(parentView, model);
        _model = model;
        FTableDefinition tableDefinition = model.getTableDefinition();
        _parentView = parentView;
        _tableModel = model;
        _table = new Table(getShell(), SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL
                | SWT.BORDER | SWT.FULL_SELECTION);
        _table.setLayoutData(layout);
        String[] columns = tableDefinition.getColumnNames();
        Integer[] widths = tableDefinition.getColumWidths();
        Boolean[] hidden = tableDefinition.getColumnHidden();
        for (int i = 0; i < tableDefinition.getNumberOfCoulumns(); i++)
        {
            _logger.debug("Adding a column " + columns[i] + " width "
                    + widths[i]);
            addColumn((String) columns[i], (Integer) widths[i], hidden[i]);
        }
        _table.setHeaderVisible(true);
        _table.setLinesVisible(true);
        final TableEditor editor = new TableEditor(_table);
        // The editor must have the same size as the cell and must
        // not be any smaller than 50 pixels.
        editor.horizontalAlignment = SWT.LEFT;
        editor.grabHorizontal = true;
        editor.minimumWidth = 50;
        addCellSelectedListener();
    }

    public void setContextMenu(Menu menu)
    {
        _menu = menu;
        _table.addListener(SWT.MenuDetect, new Listener()
        {
            public void handleEvent(Event event)
            {
                _menu.setVisible(true);
            }
        });
    }

    public void addColumn(String name, int width, Boolean hidden)
    {
        TableColumn columns = new TableColumn(_table, SWT.NONE);
        columns.setText(name);
        if (hidden == true)
            columns.setWidth(0);
        else
            columns.setWidth(width);
        final int columnNumber = columnCount++;
        columns.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent event)
            {
                _tableModel.holdUpdates();
                _tableModel.setColumn(columnNumber);
                _tableModel.reverseDirection();
                _tableModel.releaseUpdates();
            }
        });
    }

    @Override
    public void modelUpdated(Model m)
    {
        _logger.debug("Repainting the table");
        if (!(Thread.currentThread() == _updateLock))
        {
            List<FTableRow> rows = _tableModel.getFilteredModelData();
            getShell().setRedraw(false);
            _table.removeAll();
            if (rows != null)
            {
                for (FTableRow row : rows)
                {
                    TableItem item = new TableItem(_table, 0);
                    item.setText(row.getRowData());
                    item.setData(row.getHiddenRowData());
                    if (row.getForgroutColours() != null)
                    {
                        Color[] foreColours = row.getForgroutColours();
                        for (int i = 0; i < foreColours.length; i++)
                        {
                            item.setForeground(i, foreColours[i]);
                        }
                    }
                }
            }
            getShell().setRedraw(true);
        } else
        {
            // Update ONLY the current row .. brutal updates (like the above)
            // will cause the
            // table to repaint
            final TableItem item = _table.getItem(_selectedRow);
            FTableRow row = _model.getRowByHiddenData(item.getData());
            item.setText(row.getRowData());
            if (row.getForgroutColours() != null)
            {
                Color[] foreColours = row.getForgroutColours();
                for (int i = 0; i < foreColours.length; i++)
                {
                    item.setForeground(i, foreColours[i]);
                }
            }
            _logger.debug("Not painting as updateLock was set for this thread");
        }
    }

    /***************************************************************************
     * The update lock is checked when the model is updated. This method should
     * be called to indicate that the table is updating its own model. The
     * update method will then ignore any updates comming from this thread the
     * updateLock shoudl be released directly after
     */
    public void setUpdateLock()
    {
        _updateLock = Thread.currentThread();
    }

    public void resetUpdateLock()
    {
        _updateLock = null;
    }

    @Override
    protected void setupNotifiers()
    {
    }

    public void makeTableEditable()
    {
        makeTableEditable(null);
    }

    private FTableRow getRowSelectionFromTable()
    {
        _logger
                .debug("[getRowSelection] building current row based on row number "
                        + _selectedRow);
        String[] stringArray = new String[columnCount];
        TableItem row = _table.getItem(_selectedRow);
        for (int i = 0; i < columnCount; i++)
        {
            _logger.debug("[getRowSelection] getting cell contents :"
                    + row.getText(i));
            stringArray[i] = row.getText(i);
        }
        FTableRow results = new FTableRow(stringArray, row.getData());
        return results;
    }

    public void makeTableEditable(final IFTableCellEditor[] editorArray)
    {
        final TableEditor tableEditor = new TableEditor(_table);
        tableEditor.horizontalAlignment = SWT.LEFT;
        tableEditor.grabHorizontal = true;
        _table.addMouseListener(new MouseAdapter()
        {
            public void mouseDoubleClick(MouseEvent event)
            {
                Rectangle clientArea = _table.getClientArea();
                Point pt = new Point(event.x, event.y);
                int index = _table.getTopIndex();
                while (index < _table.getItemCount())
                {
                    boolean visible = false;
                    final TableItem item = _table.getItem(index);
                    for (int i = 0; i < _table.getColumnCount(); i++)
                    {
                        Rectangle rect = item.getBounds(i);
                        if (rect.contains(pt))
                        {
                            final int column = i;
                            final IFTableCellEditor editor = editorArray[i];
                            if (editor != null)
                            {
                                editor.setCurrentEditedRow(_model
                                        .getSelectedRow());
                                if (editor.canEdit() == true)
                                {
                                    editor.createControl();
                                    Listener textListener = new Listener()
                                    {
                                        public void handleEvent(final Event e)
                                        {
                                            switch (e.type)
                                            {
                                            case SWT.FocusOut:
                                                _logger
                                                        .debug("Storing text as recieved, FOCUS OUT event");
                                                String oldText = item
                                                        .getText(_selectedColumn);
                                                String newText = editor
                                                        .getText();
                                                item.setText(column, editor
                                                        .getText());
                                                notifyEditOccuredSelected(
                                                        oldText, newText);
                                                editor.dispose();
                                                break;
                                            case SWT.Traverse:
                                                switch (e.detail)
                                                {
                                                case SWT.TRAVERSE_RETURN:
                                                    _logger
                                                            .debug("Storing text as recieved, TRAVERSE_RETRUN event");
                                                    oldText = item
                                                            .getText(_selectedColumn);
                                                    newText = editor.getText();
                                                    item.setText(column, editor
                                                            .getText());
                                                    notifyEditOccuredSelected(
                                                            oldText, newText);
                                                    // FALL THROUGH
                                                case SWT.TRAVERSE_ESCAPE:
                                                    _logger
                                                            .debug("Dispose object text as recieved, TRAVERSE_ESCAPE event (or drop through)");
                                                    editor.dispose();
                                                    e.doit = false;
                                                }
                                                break;
                                            }
                                        }
                                    };
                                    editor.addListener(SWT.FocusOut,
                                            textListener);
                                    editor.addListener(SWT.Traverse,
                                            textListener);
                                    item.getText(i);
                                    tableEditor.setEditor(editor.getControl(),
                                            item, i);
                                    editor.setText(item.getText(i));
                                    // text.selectAll();
                                    editor.setFocus();
                                }
                            }
                            return;
                        }
                        if (!visible && rect.intersects(clientArea))
                        {
                            visible = true;
                        }
                    }
                    if (!visible)
                        return;
                    index++;
                }
            }
        });
    }

    public void addCellSelectedListener()
    {
        _table.addListener(SWT.MouseDown, new Listener()
        {
            public void handleEvent(Event event)
            {
                Rectangle clientArea = _table.getClientArea();
                Point selectedPoint = new Point(event.x, event.y);
                int index = _table.getTopIndex();
                while (index < _table.getItemCount())
                {
                    boolean visible = false;
                    TableItem item = _table.getItem(index);
                    for (int i = 0; i < _table.getColumnCount(); i++)
                    {
                        Rectangle rect = item.getBounds(i);
                        if (rect.contains(selectedPoint))
                        {
                            _logger.debug("CLicked on cell  ( " + index + ","
                                    + i + ")");
                            _selectedColumn = i;
                            _selectedRow = index;
                        }
                        if (!visible && rect.intersects(clientArea))
                        {
                            visible = true;
                        }
                    }
                    if (!visible)
                        break;
                    index++;
                }
                if (_table.getSelection().length != 0)
                {
                    notifySelectionChanged();
                }
            }
        });
    }

    public void notifyEditOccuredSelected(String oldText, String newText)
    {
        if (!(oldText.equals(newText)))
        {
            // The table reflects the correct data (doesnt it?)
            FTableRow row = getRowSelectionFromTable();
            FTableRow oldRow = _model.getSelectedRow();
            _logger.debug("Edited row: " + row);
            _logger.info("[notifyEditOccredSelected]Changed cell from  "
                    + oldText + " to " + newText);
            sendEvent(new ViewEvent(TABLE_EVENT.TABLE_ROW_SELECTED, row));
            setUpdateLock();
            sendEvent(new ViewEvent(VIEW_EVENT.SELECTION_MODIFIED,
                    new FTableRowSelection(row, oldRow)));
            resetUpdateLock();
        }
    }

    public void notifySelectionChanged()
    {
        FTableRow row = getRowSelectionFromTable();
        // this will update the row in the model .. ... via the controller
        _logger.debug("Selected row: " + row);
        FTableRow oldRow = _model.getSelectedRow();
        // Ping only if the row has changed (use the row ID as a check)
        if ((oldRow == null)
                || (!row.getHiddenRowData().equals(oldRow.getHiddenRowData())))
        {
            sendEvent(new ViewEvent(TABLE_EVENT.TABLE_ROW_SELECTED, row));
            sendEvent(new ViewEvent(VIEW_EVENT.SELECTION_CHANGED,
                    new FTableRowSelection(row, oldRow)));
        }
    }

    public Table getTable()
    {
        return _table;
    }
}
