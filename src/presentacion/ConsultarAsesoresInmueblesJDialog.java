package presentacion;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;

import logica.*;
import excepciones.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class ConsultarAsesoresInmueblesJDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableInmueblesAsesores;
	private FachadaPresentacion fachada;

	public static void main(String[] args) {
		try {
			ConsultarAsesoresInmueblesJDialog dialog = new ConsultarAsesoresInmueblesJDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cargaInmuebles(String cod){ 
		try{
			ArrayList<Inmueble> listaInmuebles = fachada.encontrarInmueblesPorAsesor(cod);
			InmuebleTableModel model=(InmuebleTableModel) tableInmueblesAsesores.getModel();
			Iterator<Inmueble> it= listaInmuebles.iterator();
			Inmueble in;
			model.clear();
			model.fireTableDataChanged();
			while (it.hasNext()){
				in=it.next(); 
				model.addRow(in);
			}
			this.setModal(true);
			this.setVisible(true);
		}
		catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public class InmuebleTableModel extends AbstractTableModel {
		
		private static final long serialVersionUID = 1L;
		private String[] columnNames = { "Código", "Dirección","Localidad","Fecha de Alta","Superficie","Venta/Alquiler","Propietario"};
		private ArrayList<Inmueble> data = new ArrayList<Inmueble>();
		
		public int getColumnCount(){ 
			return columnNames.length;
		}
		
		public int getRowCount(){
			return data.size();
		}
		
		public String getColumnName(int col){
			return columnNames[col];
		}

		public Object getValueAt(int row, int col){
			Inmueble in = data.get(row);
			switch(col){
				case 0: return in.getCod_Id();
				case 1: return in.getCalle();
				case 2: return in.getLocalidad();
				case 3: return in.getFecha_Alta();
				case 4: return in.getSuperficie_Total();
				case 5: return in.getVenta_Alquiler();
				case 6: return in.getCliente().getNombre();
				default: return null;
			}
		}
		
		public void clear(){
			data.clear();
		}

		public Class<? extends Object> getColumnClass(int c){
			return getValueAt(0, c).getClass();
		}
		
		public void addRow(Inmueble row){
			data.add(row); 
			this.fireTableDataChanged();
		}
		
		public void delRow(int row){
			data.remove(row); 
			this.fireTableDataChanged();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ConsultarAsesoresInmueblesJDialog() throws LogicaExcepcion{
		setResizable(false);
		setTitle("Consultar Asesores Inmuebles");
		fachada = FachadaPresentacion.getInstance();
		setBounds(130, 130, 690, 310);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 683, 281);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblAsesor = new JLabel("Asesor:");
		lblAsesor.setBounds(145, 6, 61, 16);
		contentPanel.add(lblAsesor);
		
		final JComboBox asesor = new JComboBox();
		asesor.setBounds(218, 2, 289, 27);
		contentPanel.add(asesor);
		asesor.addItem("Seleccione un asesor...");
		ArrayList<Asesor> asesores = fachada.encontrarAsesores();
		Iterator<Asesor> i = asesores.iterator();
		while(i.hasNext()){
			Asesor as = i.next();
			asesor.addItem(as.toString());
		}
		asesor.addItemListener(new ItemListener(){
	        public void itemStateChanged(ItemEvent arg0) {
	        	String as[] = asesor.getSelectedItem().toString().split("-");
	    		cargaInmuebles(as[0]);
	        }
	    });
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 34, 667, 236);
		contentPanel.add(scrollPane);
		
		tableInmueblesAsesores = new JTable(new InmuebleTableModel());
		scrollPane.setViewportView(tableInmueblesAsesores);
	}
}
