package presentacion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.*;


/**
 * Representa la pantalla inicial, donde se selecciona toda la configuración del juego
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class PantallaInicial extends JPanel{
	
	public static final String fondoIni = "recursos/Fondo 1.png";
	public static final String fondoSeleccion = "recursos/Fondo 2.png";
	public static final String fondoControles = "recursos/instruccionesFondo.png";
	
	private BufferedImage fondo;
	protected JButton player1, player2, playerCPU, atras, imagencontrol, curioso, mimo, destructor, abrir, controles, salir, nivel1, nivel2, nivel3, nivel4 , nivel5;
	private boolean niveles, elementos;
	protected int numJugadores;
	
	//Para pantalla de seleccionar elementos:
	protected JCheckBox azul, amarillo, anaranjado, gris, negro, rojo, rosado, verde, sBMas, sBMenos, sPlatMas, sPlatMenos, sEspecial, sPegajosa;
	protected JButton aceptar;
	protected JComboBox<String> colorPlataforma1, colorPlataforma2;
	
	protected int[] bloquesSelecionados;
	protected int[] sorpresasSelecionados;
	protected int colorJ1, colorJ2;
	
	public PantallaInicial(String imagen)  {
		super(null);
		prepareElementosInicio();
		setFondo(imagen);
	}
	
	private void setFondo(String root) {
		try {
			fondo = ImageIO.read(new File(root));
		}catch(IOException e){
			e.printStackTrace();
		}		
	}

	public void prepareElementosInicio(){
		removeAll();
		setFondo(PantallaInicial.fondoIni);
		
		player1 = new Boton("player1", 172, 283);
		add(player1);
		
		player2 = new  Boton("player2", 374, 283);
		add(player2);
		
		playerCPU = new  Boton("playerCPU", 576, 283);
		add(playerCPU);
		
		controles = new  Boton("controles", 172, 400);
		add(controles);
	
		abrir = new  Boton("abrir", 374, 400);
		add(abrir);
		
		salir = new  Boton("salir", 576, 400);
		add(salir);
		
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawImage(fondo, 0, 0, this);
    }

	public void prepareElementosCPU() {
		removeAll();
		curioso = new Boton("CPUcurioso", 172, 357);
		add(curioso);
		
		mimo = new  Boton("CPUmimo", 374, 357);
		add(mimo);
		
		destructor = new  Boton("CPUdestructor", 576, 357);
		add(destructor);
		
		atras = new  Boton("atras", 10, 10);
		add(atras);
		
		repaint();
	}
	
	public void prepareElementosControl() {
		removeAll();
		atras = new  Boton("atras", 10, 10);
		add(atras);
		setFondo(PantallaInicial.fondoControles);
		repaint();
	}
	
	
	private void tipoBloques() {
		add(new Boton("BloqueRojo",42,300));
		add(new Boton("BloqueGris",142,300));
		add(new Boton("BloqueNaranja",242,300));
		add(new Boton("BloqueVerde",342,300));
		add(new Boton("BloqueAmarillo",442,300));
		add(new Boton("BloqueAzul",542,300));
		add(new Boton("BloqueRosado",642,300));
		add(new Boton("BloqueNegro",742,300));
				
		
		rojo = new JCheckBox("",true);
		rojo.setOpaque(false);
		rojo.setForeground(Color.WHITE);
		rojo.setBounds(35, 325, 20, 20);
		add(rojo);
		
		
		gris = new JCheckBox("",true);
		gris.setOpaque(false);
		gris.setForeground(Color.WHITE);
		gris.setBounds(135, 325, 20, 20);
		add(gris);
		
		
		anaranjado = new JCheckBox("",true);
		anaranjado.setOpaque(false);
		anaranjado.setForeground(Color.WHITE);
		anaranjado.setBounds(235, 325, 20, 20);
		add(anaranjado);
		

		verde = new JCheckBox("",true);
		verde.setOpaque(false);
		verde.setForeground(Color.WHITE);
		verde.setBounds(335, 325, 20, 20);
		add(verde);
		
		
		amarillo = new JCheckBox("",true);
		amarillo.setOpaque(false);
		amarillo.setForeground(Color.WHITE);
		amarillo.setBounds(435, 325, 20, 20);
		add(amarillo);
		
		
		azul = new JCheckBox("",true);
		azul.setOpaque(false);
		azul.setForeground(Color.WHITE);
		azul.setBounds(535, 325, 20, 20);
		add(azul);
		
		
		rosado = new JCheckBox("",true);
		rosado.setOpaque(false);
		rosado.setForeground(Color.WHITE);
		rosado.setBounds(635, 325, 20, 20);
		add(rosado);
		
		
		negro = new JCheckBox("",true);
		negro.setOpaque(false);
		negro.setForeground(Color.WHITE);
		negro.setBounds(735, 325, 20, 20);
		add(negro);
		
		int[] todos = {1,1,1,1,1,1,1,1};
		bloquesSelecionados = todos;
		
	}
	
	private void colorPlataformas() {
		colorPlataforma1 = new JComboBox<String>();
		colorPlataforma1.addItem("Rojo");
		colorPlataforma1.addItem("Naranja");
		colorPlataforma1.addItem("Morado");
		colorPlataforma1.addItem("Rosado");
		colorPlataforma1.addItem("Verde");
		colorPlataforma1.setSelectedIndex(0);
		colorJ1 = 0;
		colorPlataforma1.setBounds(45, 440, 90, 30);
		add(colorPlataforma1);
		colorPlataforma2 = new JComboBox<String>();
		if (numJugadores==2) {
			colorPlataforma2.addItem("Rojo");
			colorPlataforma2.addItem("Naranja");
			colorPlataforma2.addItem("Morado");
			colorPlataforma2.addItem("Rosado");
			colorPlataforma2.addItem("Verde");
			colorPlataforma2.setSelectedIndex(1);
			colorJ2 = 1;
			colorPlataforma2.setBounds(190, 440, 90, 30);
			add(colorPlataforma2);
		}
	}
	
	
	public void tipoSorpresas() {
		add(new Boton("SorpresaBola-", 390, 400));
		add(new Boton("SorpresaBola+",450,400));
		add(new Boton("SorpresaPlataforma-",510,400));
		add(new Boton("SorpresaPlataforma+",390,460));
		add(new Boton("SorpresaPlataformaEspecial",450,460));
		add(new Boton("SorpresaPlataformaPegajosa",510,460));
		
		sBMenos = new JCheckBox("",true);
		sBMenos.setOpaque(false);
		sBMenos.setForeground(Color.WHITE);
		sBMenos.setBounds(390, 423, 20, 20);
		add(sBMenos);
		
		sBMas = new JCheckBox("",true);
		sBMas.setOpaque(false);
		sBMas.setForeground(Color.WHITE);
		sBMas.setBounds(450, 423, 20, 20);
		add(sBMas);
		
		sPlatMas = new JCheckBox("",true);
		sPlatMas.setOpaque(false);
		sPlatMas.setForeground(Color.WHITE);
		sPlatMas.setBounds(510, 423, 20, 20);
		add(sPlatMas);
		
		
		sPlatMenos = new JCheckBox("",true);
		sPlatMenos.setOpaque(false);
		sPlatMenos.setForeground(Color.WHITE);
		sPlatMenos.setBounds(390, 483, 20, 20);
		add(sPlatMenos);
		
		sEspecial = new JCheckBox("",true);
		sEspecial.setOpaque(false);
		sEspecial.setForeground(Color.WHITE);
		sEspecial.setBounds(450, 483, 20, 20);
		add(sEspecial);
		
		sPegajosa = new JCheckBox("",true);
		sPegajosa.setOpaque(false);
		sPegajosa.setForeground(Color.WHITE);
		sPegajosa.setBounds(510, 483, 20, 20);
		add(sPegajosa);
		
		int[] todos = {1,1,1,1,1,1};
		sorpresasSelecionados = todos;
	}
	
	
	public void prepareElegirElementos() {
		removeAll();
		elementos  = true;
		
		tipoBloques();
		colorPlataformas();
		tipoSorpresas();
		
		prepareAccionesPlataformas();
		prepareAccionesBloques();
		prepareAccionesSorpresas();
		
		aceptar = new Boton("aceptar", 650, 420);
		add(aceptar);
		
		atras = new  Boton("atras", 10, 10);
		add(atras);
		
		repaint();
	}

	public void prepareElegirNivel() {
		removeAll();
		niveles  = true;
		nivel1 = new Boton("nivel1", 172, 357);
		add(nivel1);
		
		nivel2 = new  Boton("nivel2", 374, 357);
		add(nivel2);
		
		nivel3 = new  Boton("nivel3", 576, 357);
		add(nivel3);
		
		nivel4 = new  Boton("nivel4",  249, 441);
		add(nivel4);
		
		nivel5 = new  Boton("nivel5", 451, 441);
		add(nivel5);
		
		atras = new  Boton("atras", 10, 10);
		add(atras);
		
		repaint();
	}
	public void prepareAccionesPlataformas() {
		colorPlataforma1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				colorJ1 = colorPlataforma1.getSelectedIndex();
			}
		});
		
		colorPlataforma2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				colorJ2 = colorPlataforma2.getSelectedIndex();
			}
		});
	}
	
	private void prepareAccionesSorpresas() {
		sPlatMenos.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					sorpresasSelecionados[0] = 1;
				}else {
					sorpresasSelecionados[0] = 0;
				}
			}
		});
		
		sPlatMas.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					sorpresasSelecionados[1] = 1;
				}else {
					sorpresasSelecionados[1] = 0;
				}
			}
		});
		
		sEspecial.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					sorpresasSelecionados[2] = 1;
				}else {
					sorpresasSelecionados[2] = 0;
				}
			}
		});
		
		sPegajosa.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					sorpresasSelecionados[3] = 1;
				}else {
					sorpresasSelecionados[3] = 0;
				}
			}
		});
		
		sBMas.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					sorpresasSelecionados[4] = 1;
				}else {
					sorpresasSelecionados[4] = 0;
				}
			}
		});
		
		sBMenos.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					sorpresasSelecionados[5] = 1;
				}else {
					sorpresasSelecionados[5] = 0;
				}
			}
		});
		
	}

	private void prepareAccionesBloques() {
		rojo.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					bloquesSelecionados[0] = 1;
				}else {
					bloquesSelecionados[0] = 0;
				}
			}
		});
		
		gris.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					bloquesSelecionados[1] = 1;
				}else {
					bloquesSelecionados[1] = 0;
				}
			}
		});
		
		anaranjado.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					bloquesSelecionados[2] = 1;
				}else {
					bloquesSelecionados[2] = 0;
				}
			}
		});
		
		verde.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					bloquesSelecionados[3] = 1;
				}else {
					bloquesSelecionados[3] = 0;
					
				}
			}
		});

		negro.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					bloquesSelecionados[4] = 1;
				}else {
					bloquesSelecionados[4] = 0;
				}
			}
		});
		
		azul.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					bloquesSelecionados[5] = 1;
					sBMas.setSelected(true);
					sBMenos.setSelected(true);
					sPlatMas.setSelected(true);
					sPlatMenos.setSelected(true);
					sEspecial.setSelected(true);
					sPegajosa.setSelected(true);
				}else {
					bloquesSelecionados[5] = 0;
					sBMas.setSelected(false);
					sBMenos.setSelected(false);
					sPlatMas.setSelected(false);
					sPlatMenos.setSelected(false);
					sEspecial.setSelected(false);
					sPegajosa.setSelected(false);
				}
			}
		});
			
		rosado.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					bloquesSelecionados[6] = 1;
				}else {
					bloquesSelecionados[6] = 0;
				}
			}
		});
		
		amarillo.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					bloquesSelecionados[7] = 1;
				}else {
					bloquesSelecionados[7] = 0;
				}
			}
		});
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.drawImage(fondo, 0, 0, this);
		if(niveles){
			g.setColor(Color.white);
			g.setFont(new Font("Century Gothic", Font.BOLD, 55));
			g.drawString("Selecione el nivel:", 190, 300);
			niveles  = false;
		}
		if(elementos){
			g.setColor(Color.white);
			g.setFont(new Font("Century Gothic", Font.BOLD, 30));
			g.drawString("Selecione elementos:", 15, 250);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 20));
			g.drawString("Bloques:", 15, 280);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 15));
			g.drawString("Rojo", 62, 340);

			g.setFont(new Font("Century Gothic", Font.BOLD, 15));
			g.drawString("Gris", 162, 340);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 15));
			g.drawString("Naranja", 262, 340);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 15));
			g.drawString("Verde", 362, 340);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 15));
			g.drawString("Amarillo", 462, 340);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 15));
			g.drawString("Azul", 562, 340);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 15));
			g.drawString("Rosado", 662, 340);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 15));
			g.drawString("Negro", 762, 340);
			
			
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 20));
			g.drawString("Color de la(s) plataforma(s):", 15, 390);
			
			g.setFont(new Font("Century Gothic", Font.BOLD, 20));
			g.drawString("Jugador 1:", 35, 430);
			if (numJugadores == 2) {
				g.setFont(new Font("Century Gothic", Font.BOLD, 20));
				g.drawString("Jugador 2:", 180, 430);
			}
			g.setFont(new Font("Century Gothic", Font.BOLD, 20));
			g.drawString("Sorpresas:", 410, 390);
			
			elementos = false;	
		}
		paintComponents(g);
	}
}