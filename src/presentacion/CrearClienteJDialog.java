package presentacion;

import java.awt.FlowLayout;

import excepciones.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class CrearClienteJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nif;
	private JTextField nombre;
	private JTextField apellidos;
	private FachadaPresentacion fachada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CrearClienteJDialog dialog = new CrearClienteJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearClienteJDialog() throws LogicaExcepcion{
		setResizable(false);
		fachada = FachadaPresentacion.getInstance();
		setTitle("Crear Cliente");
		setBounds(130, 130, 304, 179);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 305, 153);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblNif = new JLabel("NIF:");
		lblNif.setBounds(22, 11, 61, 16);
		contentPanel.add(lblNif);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(22, 41, 61, 16);
		contentPanel.add(lblNombre);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(22, 71, 71, 16);
		contentPanel.add(lblApellidos);
		
		nif = new JTextField();
		nif.setBounds(94, 5, 134, 28);
		contentPanel.add(nif);
		nif.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(94, 35, 134, 28);
		contentPanel.add(nombre);
		nombre.setColumns(10);
		
		apellidos = new JTextField();
		apellidos.setBounds(94, 65, 190, 28);
		contentPanel.add(apellidos);
		apellidos.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(10, 104, 274, 40);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!nif.getText().isEmpty() && !nombre.getText().isEmpty() && !apellidos.getText().isEmpty()){
							try{
								ArrayList<String> lista = new ArrayList<String>();
								lista.add(nif.getText());
								lista.add(nombre.getText());
								lista.add(apellidos.getText());
								fachada.crearCliente(lista);
								dispose();
							}
							catch(Exception e1){
									e1.printStackTrace();
							}
						}
						else{
							JOptionPane.showConfirmDialog(null, "Rellene todos los campos", "Error", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
