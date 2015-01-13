package presentacion;

import java.awt.*;

import logica.*;
import excepciones.*;

import java.text.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;


@SuppressWarnings("serial")
public class CrearOfertaJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private FachadaPresentacion fachada;
	private JTextField txtPrecio;
	private JTextField textFecha;
	private JTextField textId;
	@SuppressWarnings("rawtypes")
	private JComboBox ofertasVisitas;
	private JLabel lblOfertasVisitas;
	
	public static void main(String[] args) {
		try {
			CrearOfertaJDialog dialog = new CrearOfertaJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked" })
	public void cargaOfertasVisitas(String asesor) throws LogicaExcepcion{
		try{
			
			ofertasVisitas.removeAllItems();
			if(asesor.equals("Seleccione un asesor...")){
				ofertasVisitas.setEnabled(false);
				lblOfertasVisitas.setEnabled(false);
				txtPrecio.setEnabled(false);
				txtPrecio.setText("");
				textId.setEnabled(false);
				textId.setText("");
			}
			else{
				textId.setText("");
				txtPrecio.setText("");
				ofertasVisitas.addItem("Seleccione una visita...");
				ArrayList<Visita> visitas = fachada.encontrarVisitasPorAsesor(asesor);
				Iterator<Visita> i = visitas.iterator();
				while(i.hasNext()){
					Visita v = i.next();
					Inmueble in = v.getInmueble();
							ofertasVisitas.addItem("IN: "+in.toString()+" - VIS: "+v.toString());
						
					
				}
				ofertasVisitas.setEnabled(true);
				lblOfertasVisitas.setEnabled(true);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);			
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CrearOfertaJDialog()throws LogicaExcepcion {
		
		fachada = FachadaPresentacion.getInstance();
		setResizable(false);
		setTitle("Nueva Oferta");
		setBounds(130, 130, 465, 280);
		getContentPane().setLayout(null);
		contentPanel.setBorder(null);
		contentPanel.setBounds(0, 0, 450, 203);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblAsesores = new JLabel("Asesores:");
		lblAsesores.setBounds(52, 6, 69, 16);
		contentPanel.add(lblAsesores);
		
		final JComboBox asesores = new JComboBox();
		asesores.setBounds(116, 2, 307, 27);
		contentPanel.add(asesores);
		asesores.addItem("Seleccione un asesor...");
		ArrayList<Asesor> asesor = fachada.encontrarAsesores();
		Iterator<Asesor> i = asesor.iterator();
		while(i.hasNext()){
			Asesor as = i.next();
			asesores.addItem(as.toString());
		}
		asesores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String as[] = asesores.getSelectedItem().toString().split("-");
	    		try {
					cargaOfertasVisitas(as[0]);
				} catch (LogicaExcepcion e) {
					e.printStackTrace();
				}
			}
		});
		
		lblOfertasVisitas = new JLabel("Visitas:");
		lblOfertasVisitas.setEnabled(false);
		lblOfertasVisitas.setBounds(62, 37, 46, 16);
		contentPanel.add(lblOfertasVisitas);
		
		ofertasVisitas = new JComboBox();
		ofertasVisitas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(ofertasVisitas.getSelectedItem() != null){
					if(ofertasVisitas.getSelectedItem().toString().equals("Seleccione una visita...")){
						txtPrecio.setEnabled(false);
						txtPrecio.setText("");
						textId.setEnabled(false);
						textId.setText("");
					}
					else{
						try{
							txtPrecio.setEnabled(true);
							textId.setEnabled(true);
							txtPrecio.setText("");
							textId.setText("");
						}
						catch(Exception e1){
							e1.printStackTrace();
						}
					}
				}
			}
		});
		ofertasVisitas.setEnabled(false);
		ofertasVisitas.setBounds(116, 33, 307, 27);
		contentPanel.add(ofertasVisitas);
		
		JLabel lblPrecio = new JLabel(" Precio:");
		lblPrecio.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPrecio.setBounds(37, 97, 80, 16);
		contentPanel.add(lblPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setEnabled(false);
		txtPrecio.setBounds(116, 90, 134, 28);
		contentPanel.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFecha.setBounds(72, 125, 40, 16);
		contentPanel.add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setEditable(false);
		textFecha.setBounds(116, 120, 134, 28);
		contentPanel.add(textFecha);
		textFecha.setColumns(10);
		Date now = new Date();
		DateFormat df =  DateFormat.getDateInstance(DateFormat.DEFAULT);
		textFecha.setText(df.format(now));		
		
		JLabel lblId = new JLabel("ID Oferta:");
		lblId.setHorizontalAlignment(SwingConstants.TRAILING);
		lblId.setBounds(15, 67, 98, 16);
		contentPanel.add(lblId);
		
		textId = new JTextField();
		textId.setEnabled(false);
		textId.setBounds(116, 61, 134, 28);
		contentPanel.add(textId);
		textId.setColumns(10);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBounds(0, 203, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Crear");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(!textId.getText().isEmpty() && !txtPrecio.getText().isEmpty() && !textFecha.getText().isEmpty()){
							try{
							ArrayList list = new ArrayList();
							list.add(textId.getText());
							list.add(txtPrecio.getText());
							list.add(textFecha.getText());
							list.add(fachada.encontrarVisitaPorCod(ofertasVisitas.getSelectedItem().toString().split("VIS: ")[1].split("-")[0]));
							fachada.crearOferta(list);
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
