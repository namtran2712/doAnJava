package GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class defaulttablemode extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (isSelected == false && row % 2 == 0) {
            setBackground(Color.WHITE);
        }

        else if (isSelected == true) {
            setBackground(new Color(255, 202, 212));

        } else {
            setBackground(new Color(240, 240, 240));
        }
        setHorizontalAlignment(LEFT);
        // setValue
        return this;
    }

}
