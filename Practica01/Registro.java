package Practica01;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

class Persona {
    String nombre;
    String edad;
    String ID;


    public Persona(String nombre, String edad2, String iD2) {
        this.nombre = nombre;
        this.edad = edad2;
        this.ID = iD2;
    }

    public Persona(String nombre2, String edad2) {
    
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + "ID:" + ID;
    }
}

public class Registro extends JFrame {

    private ArrayList<Persona> personas = new ArrayList<>();
    private JTextArea areaTexto;

    public Registro() {
        
        setTitle("Registro de Personas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        areaTexto = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(areaTexto);
        JButton btnAgregar = new JButton("Agregar Persona");
        JButton btnEditar = new JButton("Editar Persona");
        JButton btnMostrar = new JButton("Mostrar Personas");
        JButton btnEliminar = new JButton("Eliminar Persona");

       
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnMostrar);
        panelBotones.add(btnEliminar);

        add(panelBotones, BorderLayout.SOUTH);

        
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPersona();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPersona();
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPersonas();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPersona();
            }
        });
    }

    private void agregarPersona() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
        String edad = JOptionPane.showInputDialog("Ingrese la edad:");
        String ID = JOptionPane.showInputDialog("Ingrese su ID:");

        personas.add(new Persona(nombre, edad, ID));
        JOptionPane.showMessageDialog(this, "Persona agregada correctamente.");
    }

    private void editarPersona() {
        if (personas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay personas registradas.");
            return;
        }

        String[] opciones = new String[personas.size()];
        for (int i = 0; i < personas.size(); i++) {
            opciones[i] = personas.get(i).nombre;
        }

        String nombreSeleccionado = (String) JOptionPane.showInputDialog(this,
                "Seleccione la persona a editar:", "Editar Persona",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        for (Persona persona : personas) {
            if (persona.nombre.equals(nombreSeleccionado)) {
                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");
                String nuevaEdad = JOptionPane.showInputDialog("Ingrese la nueva edad:");

                persona.nombre = nuevoNombre;
                persona.edad = nuevaEdad;

                JOptionPane.showMessageDialog(this, "Persona editada correctamente.");
                return;
            }
        }
    }

    private void mostrarPersonas() {
        if (personas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay personas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Persona persona : personas) {
            sb.append(persona.toString()).append("\n");
        }
        areaTexto.setText(sb.toString());
    }

    private void eliminarPersona() {
        if (personas.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay personas registradas.");
            return;
        }

        String[] opciones = new String[personas.size()];
        for (int i = 0; i < personas.size(); i++) {
            opciones[i] = personas.get(i).nombre;
        }

        String nombreSeleccionado = (String) JOptionPane.showInputDialog(this,
                "Seleccione la persona a eliminar:", "Eliminar Persona",
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        personas.removeIf(persona -> persona.nombre.equals(nombreSeleccionado));
        JOptionPane.showMessageDialog(this, "Persona eliminada correctamente.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }
}