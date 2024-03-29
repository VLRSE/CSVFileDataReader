package DarkThemeComponents;

import otherClasses.CustomizeCellRenderer;
import otherClasses.DarkThemeColor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import java.awt.*;

public class DarkTable extends JTable implements TableCellRenderer {

    public DarkTable(TableModel dm) {
        super(dm);
    }

    public DarkTable() {
        init();
    }



    public void init() {

        setBackground(DarkThemeColor.SECONDARY_BG_COLOR);
       setForeground(DarkThemeColor.PRIMARY_TEXT_COLOR);
        System.out.println(this.getBackground());
        //set the table header´s background color
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setBackground(DarkThemeColor.PRIMARY_BG_COLOR);
        tableHeader.setForeground(DarkThemeColor.PRIMARY_TEXT_COLOR);
        setGridColor(DarkThemeColor.BLUE_500_COLOR);
        updateUI();
        repaint();
        getColumnModel().setColumnMargin(20);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(Color.blue);
        tableHeader.getColumnModel().getColumn(0).setCellRenderer(new CustomizeCellRenderer());

        getModel().addTableModelListener(this);
        putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        getModel().addTableModelListener(new MyTableModelListener());

        getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setEnabled(getSelectedRowCount() > 0);
            }
        });

    }

    @Override
    public void setGridColor(Color gridColor) {
        super.setGridColor(gridColor);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        if (isSelected) {
            setBackground(DarkThemeColor.BLUE_500_COLOR);
            setForeground(DarkThemeColor.SECONDARY_TEXT_COLOR);

        } else {
            setBackground(DarkThemeColor.BLUE_500_COLOR);
            setForeground(DarkThemeColor.SECONDARY_TEXT_COLOR);
        }
        return this;
    }



    private class MyTableModelListener implements TableModelListener{

        @Override
        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();


            TableModel model = (TableModel) e.getSource();
            String columnName = model.getColumnName(column);
            Object value = model.getValueAt(row, column);
            firePropertyChange("new value", null, value );

            System.out.println("Edited row " + row + " column "+ column + " columnName " + columnName + " value " + value.toString());



        }
    }

    private class CustomizedTableModel extends AbstractTableModel {

        public CustomizedTableModel() {
            System.out.println(getColumnModel().getSelectedColumns());

            addTableModelListener(l -> {
                if( l.getType() == TableModelEvent.DELETE){
                    fireTableRowsDeleted(l.getFirstRow(), l.getLastRow());
                }

            });
        }



        @Override
        public int getRowCount() {
            return 0;
        }

        @Override
        public int getColumnCount() {
            return 0;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return null;
        }
    }


}