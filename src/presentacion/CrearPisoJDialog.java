package presentacion;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import excepciones.*;
import logica.*;

@SuppressWarnings("serial")
public class CrearPisoJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField codigo;
	private JTextField direccion;
	private JTextField localidad;
	private JTextField fecha;
	private JTextField superficie;
	private JTextField habitaciones;
	private FachadaPresentacion fachada;


	public static void main(String[] args) {
		try {
			CrearPisoJDialog dialog = new CrearPisoJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CrearPisoJDialog() throws LogicaExcepcion {
		setResizable(false);
		setTitle("Crear Piso");
		
		fachada = FachadaPresentacion.getInstance();
		
		setBounds(130, 130, 453, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C�digo:");
		lblCdigo.setBounds(18, 6, 61, 16);
		contentPanel.add(lblCdigo);
		
		JLabel lblDireccin = new JLabel("Direcci�n:");
		lblDireccin.setBounds(18, 34, 72, 16);
		contentPanel.add(lblDireccin);
		
		JLabel lblNewLabel = new JLabel("Localidad:");
		lblNewLabel.setBounds(18, 64, 72, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(18, 92, 61, 16);
		contentPanel.add(lblFecha);
		
		JLabel lblSuperficie = new JLabel("Superficie:");
		lblSuperficie.setBounds(18, 120, 72, 16);
		contentPanel.add(lblSuperficie);
		
		JLabel lblNmHabit = new JLabel("N�m. Habit.:");
		lblNmHabit.setBounds(18, 148, 89, 16);
		contentPanel.add(lblNmHabit);
		
		JLabel lblPropietario = new JLabel("Propietario:");
		lblPropietario.setBounds(18, 176, 72, 16);
		contentPanel.add(lblPropietario);
		
		JLabel lblAsesor = new JLabel("Asesor:");
		lblAsesor.setBounds(18, 204, 61, 16);
		contentPanel.add(lblAsesor);
		
		codigo = new JTextField();
		codigo.setBounds(105, 0, 134, 28);
		contentPanel.add(codigo);
		codigo.setColumns(10);
		
		direccion = new JTextField();
		direccion.setBounds(105, 28, 280, 28);
		contentPanel.add(direccion);
		direccion.setColumns(10);
		
		localidad = new JTextField();
		localidad.setBounds(105, 58, 134, 28);
		contentPanel.add(localidad);
		localidad.setColumns(10);
		
		fecha = new JTextField();
		fecha.setBounds(105, 86, 134, 28);
		contentPanel.add(fecha);
		fecha.setColumns(10);
		
		superficie = new JTextField();
		superficie.setBounds(105, 114, 134, 28);
		contentPanel.add(superficie);
		superficie.setColumns(10);
		
		habitaciones = new JTextField();
		habitaciones.setBounds(105, 142, 134, 28);
		contentPanel.add(habitaciones);
		habitaciones.setColumns(10);
		
		final JComboBox propietario = new JComboBox();
		propietario.setBounds(105, 172, 280, 27);
		contentPanel.add(propietario);
		ArrayList<Cliente> propietarios = fachada.encontrarClientes();
		Iterator<Cliente> c = propietarios.iterator();
		while(c.hasNext()){
			Cliente cl = c.next();
			propietario.addItem(cl.toString());
		}
		
		final JComboBox asesor = new JComboBox();
		asesor.setBounds(105, 200, 280, 27);
		contentPanel.add(asesor);
		ArrayList<Asesor> asesores = fachada.encontrarAsesores();
		Iterator<Asesor> i = asesores.iterator();
		while(i.hasNext()){
			Asesor as = i.next();
			asesor.addItem(as.toString());
		}
		
		
		ButtonGroup grupoButton = new ButtonGroup();
		
		JRadioButton Venta = new JRadioButton("Venta");
		Venta.setBounds(105, 233, 141, 23);
		contentPanel.add(Venta);
		
		final JRadioButton Alquiler = new JRadioButton("Alquiler");
		Alquiler.setBounds(105, 258, 141, 23);
		contentPanel.add(Alquiler);
		
		grupoButton.add(Alquiler);
		grupoButton.add(Venta);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		{
			JButton Crear = new JButton("Crear");
			Crear.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg){
					if(!codigo.getText().isEmpty() && !direccion.getText().isEmpty() && !localidad.getText().isEmpty()
							&& !fecha.getText().isEmpty() && !superficie.getText().isEmpty() && !habitaciones.getText().isEmpty())
					{	
						String opcion;
						if(Alquiler.isSelected()) opcion = "Alquiler";
						else opcion = "Venta";
						String as[] = asesor.getSelectedItem().toString().split("-");
						String cl[] = propietario.getSelectedItem().toString().split("-");
						Cliente c;
						Asesor a;
						try {
							c = fachada.encontrarClientePorCod(cl[0]);
							a = fachada.encontrarAsesorPorCod(as[0]);
							ArrayList lista = new ArrayList();
							lista.add("Piso");
							lista.add(codigo.getText());
							lista.add(direccion.getText());
							lista.add(localidad.getText());
							lista.add(fecha.getText());
							lista.add(superficie.getText());
							lista.add(opcion);
							lista.add(habitaciones.getText());
							lista.add(c);
							lista.add(a);						

							fachada.crearPiso(lista);
							dispose();
							
						}
	
						catch (Exception e) {
							e.printStackTrace();
						}
					}
					else{
						JOptionPane.showConfirmDialog(null, "Rellene todos los campos", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}	
				}
			});
			Crear.setActionCommand("OK");
			buttonPane.add(Crear);
				getRootPane().setDefaultButton(Crear);
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
