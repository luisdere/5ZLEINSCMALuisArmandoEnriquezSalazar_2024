package PruebasTextilesInterfaz2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PruebasTextilesInterfaz extends JFrame {
    private JTextField modeloField, reporteField, muestreoField, puntosField, numeroPrendaField, coloresField, totalPrendasField;
    private JButton agregarMuestreoButton, guardarButton;
    private JTable puntosTable, muestrasTable;
    private DefaultTableModel puntosTableModel, muestrasTableModel;

    public PruebasTextilesInterfaz() {
        setTitle("Inspección de medidas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel datosPanel = new JPanel(new GridLayout(9, 2));
        datosPanel.add(new JLabel("Modelo:"));
        modeloField = new JTextField();
        datosPanel.add(modeloField);
        datosPanel.add(new JLabel("Número de Reporte:"));
        reporteField = new JTextField();
        datosPanel.add(reporteField);
        datosPanel.add(new JLabel("Fecha:"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JLabel fechaLabel = new JLabel(dateFormat.format(new Date()));
        datosPanel.add(fechaLabel);
        datosPanel.add(new JLabel("Muestreo de Prendas:"));
        muestreoField = new JTextField();
        datosPanel.add(muestreoField);

        datosPanel.add(new JLabel("Firma del proveedor"));
        JTextField firmaProveedorField = new JTextField();
        datosPanel.add(firmaProveedorField);

        datosPanel.add(new JLabel("Firma del Inspector"));
        JTextField firmaInspectorField = new JTextField();
        datosPanel.add(firmaInspectorField);

        JPanel puntosPanel = new JPanel(new BorderLayout());
        puntosTableModel = new DefaultTableModel();
        puntosTableModel.addColumn("Puntos a Medir");
        puntosTableModel.addColumn("STD");
        puntosTableModel.addColumn("TOL");
        puntosTableModel.addColumn("Numero de prendas");
        puntosTableModel.addColumn("Colores");
        puntosTableModel.addColumn("Total de prendas");

        puntosTable = new JTable(puntosTableModel);
        JScrollPane scrollPane = new JScrollPane(puntosTable);
        puntosPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel muestrasPanel = new JPanel(new BorderLayout());
        muestrasTableModel = new DefaultTableModel();
        muestrasTableModel.addColumn("Número de Prenda");
        muestrasTableModel.addColumn("Colores");
        muestrasTableModel.addColumn("Total de Prendas");
        muestrasTable = new JTable(muestrasTableModel);
        JScrollPane muestrasScrollPane = new JScrollPane(muestrasTable);
        muestrasPanel.add(muestrasScrollPane, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        agregarMuestreoButton = new JButton("Agregar Muestreo");
        agregarMuestreoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int muestreo = Integer.parseInt(muestreoField.getText());
                for (int i = 0; i < muestreo; i++) {
                    puntosTableModel.addRow(new Object[]{"", "", "", "", "", ""});
                }
            }
        });
        buttonsPanel.add(agregarMuestreoButton);

        guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calcular el total de prendas
                int totalPrendas = 0;
                for (int i = 0; i < puntosTableModel.getRowCount(); i++) {
                    int numPrendas = Integer.parseInt(puntosTableModel.getValueAt(i, 3).toString());
                    totalPrendas += numPrendas;
                    puntosTableModel.setValueAt(numPrendas, i, 5); // Actualizar la columna "Total de prendas" en la fila actual
                }
                totalPrendasField.setText(String.valueOf(totalPrendas));
            }
        });
        buttonsPanel.add(guardarButton);

        mainPanel.add(datosPanel, BorderLayout.NORTH);
        mainPanel.add(puntosPanel, BorderLayout.CENTER);
        mainPanel.add(muestrasPanel, BorderLayout.SOUTH);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PruebasTextilesInterfaz frame = new PruebasTextilesInterfaz();
                frame.setVisible(true);
            }
        });
    }
}