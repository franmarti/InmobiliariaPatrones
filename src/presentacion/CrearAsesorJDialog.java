package presentacion;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.util.ArrayList;

import excepciones.*;

@SuppressWarnings("serial")
public class CrearAsesorJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codigo;
	private JTextField nombre;
	private FachadaPresentacion fachada;
	private JTextField apellidos;


	public static void main(String[] args) {
		try {
			CrearAsesorJDialog dialog = new CrearAsesorJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CrearAsesorJDialog() throws LogicaExcepcion{
		setResizable(false);
		setTitle("Crear Asesor");
		fachada = FachadaPresentacion.getInstance();
		setTitle("CREAR ASESORES");
		setBounds(130, 130, 371, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblCdigoEm = new JLabel("C\u00F3digo:");
			lblCdigoEm.setBounds(18, 18, 61, 16);
			contentPanel.add(lblCdigoEm);
		}
		{
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(18, 51, 61, 16);
			contentPanel.add(lblNombre);
		}
		
		codigo = new JTextField();
		codigo.setBounds(95, 12, 134, 28);
		contentPanel.add(codigo);
		codigo.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(95, 45, 200, 28);
		contentPanel.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(18, 89, 61, 16);
		contentPanel.add(lblApellidos);
		
		apellidos = new JTextField();
		apellidos.setColumns(10);
		apellidos.setBounds(95, 83, 200, 28);
		contentPanel.add(apellidos);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						if(!codigo.getText().isEmpty() && !nombre.getText().isEmpty() && !apellidos.getText().isEmpty()){
							try{
								ArrayList<String> lista = new ArrayList<String>();
								lista.add(codigo.getText());
								lista.add(nombre.getText());
								lista.add(apellidos.getText());
								fachada.crearAsesor(lista);
								dispose();
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
						else{
							JOptionPane.showConfirmDialog(null, "Rellene todos los campos", "Error", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						}
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
