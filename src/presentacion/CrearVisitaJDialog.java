package presentacion;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import excepciones.*;
import logica.*;

@SuppressWarnings("serial")
public class CrearVisitaJDialog  extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField fecha;
	private FachadaPresentacion fachada;
	@SuppressWarnings("rawtypes")
	private JComboBox cliente;
	@SuppressWarnings("rawtypes")
	private JComboBox inmueble;
	private JLabel lblInmueble;
	private JLabel lblCliente;
	private JLabel lblEscribe;
	private JTextField ID;
	private JLabel lblID;
	

	public static void main(String[] args) {
		try {
			CrearVisitaJDialog dialog = new CrearVisitaJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  CrearVisitaJDialog() throws LogicaExcepcion{
		setResizable(false);
		fachada = FachadaPresentacion.getInstance();
		setTitle("Crear Visita");
		setBounds(130, 130, 553, 288);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		{
			JLabel lblAsesor = new JLabel("Seleccione asesor:");
			lblAsesor.setBounds(18, 20, 201, 16);
			contentPanel.add(lblAsesor);
		}
		{
			lblInmueble = new JLabel("Seleccione inmueble:");
			lblInmueble.setBounds(18, 60, 201, 16);
			contentPanel.add(lblInmueble);
			lblInmueble.setEnabled(false);
		}
		{
			lblCliente = new JLabel("Seleccione cliente:");
			lblCliente.setBounds(18, 100, 150, 16);
			contentPanel.add(lblCliente);
			lblCliente.setEnabled(false);
		}
		{
			lblEscribe = new JLabel("Escribe fecha de visita:");
			lblEscribe.setBounds(18, 140, 150, 16);
			contentPanel.add(lblEscribe);
			lblEscribe.setEnabled(false);
		}
		fecha = new JTextField();
		fecha.setBounds(190, 135, 134, 28);
		contentPanel.add(fecha);
		fecha.setColumns(10);
		fecha.setEnabled(false);
		
		
		
		cliente = new JComboBox();
		cliente.setBounds(190, 95, 289, 27);
		contentPanel.add(cliente);
		cliente.addItem("Seleccione un cliente...");
		cliente.setEnabled(false);
		
		
		final JComboBox asesor = new JComboBox();
		asesor.setBounds(190, 15, 289, 27);
		contentPanel.add(asesor);
		asesor.addItem("Seleccione un asesor...");
		ArrayList<Asesor> asesores = fachada.encontrarAsesores();
		Iterator<Asesor> p = asesores.iterator();
		while(p.hasNext()){
			Asesor as = p.next();
			asesor.addItem(as.toString());
		}	
		asesor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String as[]=asesor.getSelectedItem().toString().split("-");
				cargaInmueblesClientes(as[0]);
			}
		});

		inmueble = new JComboBox();
		
		inmueble.setBounds(190, 55, 289, 27);
		contentPanel.add(inmueble);
		inmueble.addItem("Seleccione un inmueble...");
		inmueble.setEnabled(false);
			
		ID = new JTextField();
		ID.setEnabled(false);
		ID.setBounds(190, 175, 134, 28);
		contentPanel.add(ID);
		ID.setColumns(10);
			
		lblID = new JLabel("ID visita");
		lblID.setEnabled(false);
		lblID.setBounds(18, 187, 150, 16);
		contentPanel.add(lblID);	
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg) {
						if(!fecha.getText().isEmpty() && !ID.getText().isEmpty()){
							try{
								String codCliente = cliente.getSelectedItem().toString().split("-")[0];
								String codInmueble = inmueble.getSelectedItem().toString().split("-")[0];
								String codAsesor = asesor.getSelectedItem().toString().split("-")[0];
								ArrayList lista = new ArrayList();
								lista.add(ID.getText());
								lista.add(fecha.getText());
								lista.add(fachada.encontrarInmueblePorCod(codInmueble));
								lista.add(fachada.encontrarClientePorCod(codCliente));
								lista.add(fachada.encontrarAsesorPorCod(codAsesor));
								fachada.crearVisita(lista);
								dispose();
							}
							catch(Exception e){
								e.printStackTrace();
							}
						}
						else {
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
	@SuppressWarnings("unchecked")
	public void cargaInmueblesClientes(String asesor){
		try{
			inmueble.removeAllItems();
			cliente.removeAllItems();
			if(asesor.equals("Seleccione un asesor...")){
				inmueble.setEnabled(false);
				cliente.setEnabled(false);
				lblInmueble.setEnabled(false);
				lblCliente.setEnabled(false);
				fecha.setEnabled(false);
				lblEscribe.setEnabled(false);
				lblID.setEnabled(false);
				ID.setEnabled(false);
			}
			else{
				fecha.setText("");
				inmueble.addItem("Seleccione un inmueble...");
				ArrayList<Inmueble> inmuebles = fachada.encontrarInmueblesPorAsesor(asesor);
				Iterator<Inmueble> i = inmuebles.iterator();
				while(i.hasNext()){
					Inmueble in = i.next();
					inmueble.addItem(in.toString());
				}
				cliente.addItem("Seleccione un cliente...");
				ArrayList<Cliente> clientes = fachada.encontrarClientes();
				Iterator<Cliente> c = clientes.iterator();
				while(c.hasNext()){
					Cliente cl = c.next();
					cliente.addItem(cl.toString());
				}
				inmueble.setEnabled(true);
				cliente.setEnabled(true);
				lblInmueble.setEnabled(true);
				lblCliente.setEnabled(true);
				fecha.setEnabled(true);
				lblEscribe.setEnabled(true);
				lblID.setEnabled(true);
				ID.setEnabled(true);
				}
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

