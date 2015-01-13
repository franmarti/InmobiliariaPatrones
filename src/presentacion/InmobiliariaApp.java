package presentacion;

import java.awt.EventQueue;

import javax.swing.*;

import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InmobiliariaApp {

	private JFrame frmInmobiliaria;
	private FabricaPresentacion fabrica;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InmobiliariaApp window = new InmobiliariaApp();
					window.frmInmobiliaria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InmobiliariaApp() {
		initialize();
		initDominio();
	}
	
	private void initDominio(){
		try{
			fabrica = FabricaPresentacion.getInstance();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmInmobiliaria = new JFrame();
		frmInmobiliaria.setResizable(false);
		frmInmobiliaria.setTitle("Inmobiliaria");
		frmInmobiliaria.setBounds(100, 100, 450, 305);
		frmInmobiliaria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmInmobiliaria.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		JMenu mnInmuebles = new JMenu("Inmuebles");
		menuBar.add(mnInmuebles);
		
		JMenuItem mntmCrearPiso = new JMenuItem("Crear Piso");
		mntmCrearPiso.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
					fabrica.crearInterfaz("Crear Piso");
			}
		});
		mnInmuebles.add(mntmCrearPiso);
		
		JMenuItem mntmCrearNaveIndustrial = new JMenuItem("Crear Nave Industrial");
		mntmCrearNaveIndustrial.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				fabrica.crearInterfaz("Crear Nave Industrial");
			}
		});
		mnInmuebles.add(mntmCrearNaveIndustrial);
		
		JMenuItem mntmConsultarInmueble = new JMenuItem("Consultar Inmueble");
		mntmConsultarInmueble.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				fabrica.crearInterfaz("Consultar Inmueble");
			}
		});
		mnInmuebles.add(mntmConsultarInmueble);
		
		JMenuItem mntmConsultarVisitasofertas = new JMenuItem("Consultar Visitas/Ofertas");
		mntmConsultarVisitasofertas.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				fabrica.crearInterfaz("Consultar Visitas/Ofertas");
			}
		});
		mnInmuebles.add(mntmConsultarVisitasofertas);
		
		JMenuItem mntmConsultaInmueblesDisponibles = new JMenuItem("Consulta Inmuebles Disponibles");
		mntmConsultaInmueblesDisponibles.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				fabrica.crearInterfaz("Consulta Inmuebles Disponibles");
			}
			
		});
		mnInmuebles.add(mntmConsultaInmueblesDisponibles);
		
		JSeparator separator = new JSeparator();
		mnInmuebles.add(separator);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnInmuebles.add(mntmSalir);
		
		JMenu mnCliente = new JMenu("Cliente");
		menuBar.add(mnCliente);
		
		JMenuItem mntmCrearCliente = new JMenuItem("Crear Cliente");
		mnCliente.add(mntmCrearCliente);
		
		JMenuItem mntmListarClientesinmuebles = new JMenuItem("Listar Clientes-Inmuebles");
		mnCliente.add(mntmListarClientesinmuebles);
		mntmListarClientesinmuebles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fabrica.crearInterfaz("Listar Clientes-Inmuebles");
			}
		});
		mntmCrearCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fabrica.crearInterfaz("Crear Cliente");
			}
		});
		
		JMenu mnAsesor = new JMenu("Asesor");
		menuBar.add(mnAsesor);
		
		JMenuItem mntmCrearAsesor = new JMenuItem("Crear Asesor");
		mnAsesor.add(mntmCrearAsesor);
		
		JMenuItem mntmListarAsesoresinmuebles = new JMenuItem("Listar Asesores-Inmuebles");
		mnAsesor.add(mntmListarAsesoresinmuebles);
		
		JMenu mnVisita = new JMenu("Visita");
		menuBar.add(mnVisita);
		
		JMenuItem mntmNuevaVisita = new JMenuItem("Nueva visita");
		mntmNuevaVisita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fabrica.crearInterfaz("Nueva Visita");
			}
		});
		mnVisita.add(mntmNuevaVisita);
		
		JMenu mnOferta = new JMenu("Oferta");
		menuBar.add(mnOferta);
		
		JMenuItem mntmNuevaOferta = new JMenuItem("Nueva oferta");
		mntmNuevaOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fabrica.crearInterfaz("Nueva Oferta");
			}
		});
		mnOferta.add(mntmNuevaOferta);
		
		JMenu mnNewMenu = new JMenu("Transacción");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNuevaTransaccin = new JMenuItem("Nueva transacción");
		mntmNuevaTransaccin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fabrica.crearInterfaz("Nueva Transaccion");
			}
		});
		mnNewMenu.add(mntmNuevaTransaccin);
		
		mntmListarAsesoresinmuebles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fabrica.crearInterfaz("Consultar AsesoresInmuebles");
			}
		});
		mntmCrearAsesor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fabrica.crearInterfaz("Crear Asesor");
			}
		});
	}

}
