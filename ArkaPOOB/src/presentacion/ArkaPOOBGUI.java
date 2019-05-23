package presentacion;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aplicacion.ArkaPOOB;
import aplicacion.ArkaPOOBException;
import aplicacion.Bloque;

/**
 * Representa la interfaz gráfica del usuario
 * @author Juan Sebastian Frásica y Juan Sebastián Gómez
 *
 */
public class ArkaPOOBGUI extends JFrame implements Runnable, KeyListener{
	private PantallaInicial inicial;
	private Tablero tablero;
	private JPanel principal;
	private CardLayout layout;
	private ArkaPOOB juego;
	//Hilo
	private Thread t;
	//Sonidos
	private Clip ini,fin,niv;
	//Diseño
	private JMenuBar barraMenu;
	private JMenu menu;
	private JMenuItem nuevo, abrir, guardar, salir, importar;
	private Icon icono;
	
	
	public ArkaPOOBGUI()  {
		super("ArkaPOOB");
		prepareElementos();
		prepareAcciones();
		addKeyListener(this);
		setFocusable(true);
		icono = new ImageIcon("recursos/logoPequeño.png");
		ImageIcon icono = new ImageIcon("recursos/logo.png");
		Image icon = icono.getImage();
		setIconImage(icon);
	}
	
	private void prepareElementos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(new Dimension(900, 590));
		setLocationRelativeTo(null);
	    prepareElementosInicial(); 
	    prepareElementosMenu();
	}

	private void prepareElementosMenu() {
		barraMenu = new JMenuBar();
		menu = new JMenu("Menu");
		nuevo = new JMenuItem("Nuevo");
		abrir = new JMenuItem("Abrir");
		guardar = new JMenuItem("Guardar");
		salir = new JMenuItem("Salir");
		importar = new JMenuItem("Importar");
		menu.add(nuevo);
		menu.addSeparator();
		menu.add(abrir);
		menu.addSeparator();
		menu.add(guardar);
		menu.addSeparator();
		menu.add(importar);
		menu.addSeparator();
		menu.add(salir);
		barraMenu.add(menu);
		setJMenuBar(barraMenu);
	}

	private void prepareElementosInicial() {
		layout = new CardLayout();
		setSize(new Dimension(900, 590));
		principal = new JPanel(layout);
		inicial = new PantallaInicial(PantallaInicial.fondoIni);
		add(principal);
		principal.add(inicial,"tini");
		layout.show(principal, "tini");
		sonidoInicial();
	}
	
	private void prepareElementosJuego(int jugadores){
		setSize(new Dimension(900, 640));
		tablero = new Tablero(jugadores);
		principal.add(tablero, "tjue");
		t = new Thread(this);
		prepareJugadores();
		actualizar();
		actualizarBloques();
		tablero.setPausa(juego.enPausa());
		layout.show(principal, "tjue");
		t.start();
	}
	
	private void prepareJugadores(){
		for (int i = 0; i < juego.numeroDeJugadores(); i++){
			tablero.addJugador();
			tablero.getJugador(i).setRoot(juego.getJugador(i).getRoot());
			ArrayList<Sprite> vidas = tablero.getVidas();
			for(int j = i*3;j <= 3+((i*3)-1);j++){
				vidas.get(j).setRoot("recursos/PlataformaNormalBasica"+juego.getJugador(i).getColor()+".png");
			}
		}
		actualizarJugadores();
	}
	
	private void prepareAcciones() {
		prepareAccionesMenu();
		inicial.player1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial.numJugadores = 1;
				iniciar(1,0);
			}
		});
		
		inicial.player2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial.numJugadores = 2;
				iniciar(2,0);
			}
		});
		
		inicial.playerCPU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial.numJugadores = 2;
				inicial.prepareElementosCPU();
				prepareAccionesCPU();
			}
		});
		
		inicial.salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		
		inicial.controles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial.prepareElementosControl();
				prepareAccionesControl();
			}
		});
		
		inicial.abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();
			}
		});
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				salir();
			}
		});
		
	}
	
	private void prepareAccionesMenu() {
		salir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				salir();
			}
		});
		guardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		
		abrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				abrir();
			}
		});
		
		nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(new Dimension(900, 590));
				if(juego!=null)juego.endGame();
				reiniciar();
			}
		});
	}

	private void guardar() {
		if(juego == null) {
			JOptionPane.showMessageDialog(null, ArkaPOOBException.SIN_JUEGO, "¡Cuidado!", JOptionPane.WARNING_MESSAGE, icono);
		}else {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
			int result = chooser.showSaveDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				File f = new File("./"+chooser.getSelectedFile().getName()+".dat");
				try {
					juego.salvar(f);
				}catch(ArkaPOOBException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "¡Cuidado!", JOptionPane.WARNING_MESSAGE, icono);
				}
			}
		}
	}
	
	private void abrir(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File f = fileChooser.getSelectedFile();
			try{
				ArkaPOOB.abra(f);
				juego  = ArkaPOOB.getJuego();
				detenerSonidos();
				prepareElementosJuego(juego.numeroDeJugadores());
				actualizar();
			}catch(ArkaPOOBException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "¡Cuidado!", JOptionPane.WARNING_MESSAGE, icono);
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void salir(){
		int result = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Confirmacion de salida: ",
				JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION)
			System.exit(0);
		else if (result == JOptionPane.NO_OPTION)
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	private void prepareAccionesControl() {
		inicial.atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(new Dimension(900, 590));
				inicial.prepareElementosInicio();
				prepareAcciones();
			}
		});
	}
	
	private void prepareAccionesFin() {
		tablero.reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(new Dimension(900, 590));
				juego.endGame();
				reiniciar();
			}
		});
	}
	
	//Preparar Acciones del modo contra CPU
	private void prepareAccionesCPU() {
		inicial.destructor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciar(2,1);
			}
		});
		
		inicial.curioso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciar(2,2);
			}
		});
		
		inicial.mimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				iniciar(2,3);
			}
		});
		
		inicial.atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(new Dimension(900, 590));
				inicial.prepareElementosInicio();
				prepareAcciones();
			}
		});
	}
	
	private void prepareAccionesElementos() {
		inicial.aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ponerElementosJuego();
			}
		});
		
		inicial.atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(new Dimension(900, 590));
				inicial.prepareElementosInicio();
				prepareAcciones();
			}
		});
		
	}
	
	private void prepareAccionesNivel() {
		inicial.nivel1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nivel(1);
			}
		});
		
		inicial.nivel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nivel(2);
			}
		});
		
		inicial.nivel3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nivel(3);
			}
		});
		
		inicial.nivel4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nivel(4);
			}
		});
		
		inicial.nivel5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nivel(5);
			}
		});
		
		inicial.atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setSize(new Dimension(900, 590));
				ponerElementos();
			}
		});
	}
	
	private void iniciar(int jugadores,int maquina){
		ArkaPOOB.nuevoJuego();
		juego = ArkaPOOB.getJuego();
		juego.prepareJugadores(jugadores, maquina);
		ponerElementos();
	}
	
	private void ponerElementos(){
		inicial.prepareElegirElementos();
		prepareAccionesElementos();
		//hablar con aplicacion :)
	}
	
	private void ponerElementosJuego() {
		try {
			juego.colores(colores());
			juego.bloquesParaJugar(inicial.bloquesSelecionados);
			juego.sorpresasParaJugar(inicial.sorpresasSelecionados);
			juego.crearNivel();
			ponerNivel();
		}catch(ArkaPOOBException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "¡Cuidado!", JOptionPane.WARNING_MESSAGE, icono);
		}
	}
	
	private int[] colores() {
		int[] colores; 
		if(juego.numeroDeJugadores() == 2) {
			colores = new int[2];
			colores[0] = inicial.colorJ1;
			colores[1] = inicial.colorJ2;
		}else {
			colores = new int[1];
			colores[0] = inicial.colorJ1;
		}
		return colores;
	}

	private void ponerNivel(){
		inicial.prepareElegirNivel();
		prepareAccionesNivel();
	}
	
	
	private void nivel(int nivel){
		ini.stop();
		juego.setNivel(nivel);
		prepareElementosJuego(juego.numeroDeJugadores());
	}
	
	private void reiniciar(){
		ini.stop();
		sonidoInicial();
		inicial.prepareElementosInicio();
		prepareAcciones();
		layout.show(principal, "tini");
		inicial.repaint();
		try {
			if (t != null)t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			while(!juego.gameOver()&&!juego.finished()){
				actualizar();
				actualizarBloques();
				cargaNivel();
				Thread.sleep(3000);
				quitarNivel();
				while(!juego.nivelAcabado() && !juego.gameOver()) {
					if(!juego.enPausa()){
						if (juego.creoBola())Thread.sleep(1000);
						juego.mover();
						if (juego.actualizarBloques()) {
							actualizarBloques();
						}
						actualizar();
					}
				}
			}
			terminar(juego.gameOver());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	private void cargaNivel() {
		tablero.inicioNivel(juego.getNivel());
		sonidoNivel();
	}
	private void quitarNivel() {
		tablero.quitarNivel();
	}

	public void terminar(boolean gameOver){
		tablero.End(gameOver);
		sonidoFin();
		prepareAccionesFin();
	}


	private void actualizar() {
		actualizarBola();
		actualizarPuntaje();
		actualizarVidas();
		actualizarSorpresas();
		if(juego.numeroDeJugadores() == 2 && !juego.getJugador(1).esHumano())actualizarJugadores();
		tablero.repaint();
	}
	
	private void actualizarSorpresas() {
		for(int i = 0; i < juego.numeroDeSorpresas(); i++){
			Sprite s;
			try {
				s = tablero.getSorpresa(i);
			} catch (IndexOutOfBoundsException ex) {
				tablero.addSorpresa();
				s = tablero.getSorpresa(i);
			}
			if(juego.getSorpresa(i).isVisible()){
				s.setX(juego.getSorpresa(i).getX());
				s.setY(juego.getSorpresa(i).getY());
				s.setRoot(juego.getSorpresa(i).getImagen());
			}
			s.setVisible(juego.getSorpresa(i).isVisible());
		}
	}

	private void actualizarJugadores(){
		for(int i = 0; i < juego.numeroDeJugadores(); i++){
			Sprite s = tablero.getJugador(i);
			s.setX(juego.getJugador(i).getX());
			s.setY(juego.getJugador(i).getY());
			s.setRoot(juego.getJugador(i).getRoot());
		}
	}
	
	private void actualizarVidas(){
		for(int i = 0; i < juego.numeroDeJugadores(); i++){
			int vidasAct = juego.getJugador(i).getVidas();
			ArrayList<Sprite> vidas = tablero.getVidas();
			for(int j = i*3;j <= 3+((i*3)-1);j++)vidas.get(j).setVisible(true);
			for(int j = i*3;j <= 3+((i*3)-1)-(vidasAct);j++) vidas.get(j).setVisible(false);
		}
	}
	
	private void actualizarPuntaje(){
		for(int i = 0; i < juego.numeroDeJugadores(); i++){
			tablero.setPuntaje(i, juego.getJugador(i).getPuntaje());
		}
	}
	
	private void actualizarBloques() {
		Bloque[] bloques = juego.getBloques(); 
		for (int i = 0; i < bloques.length; i++) {
			Sprite s;
			try {
				s = tablero.getBloque(i);
			} catch (IndexOutOfBoundsException ex) {
				tablero.addBloque();
				s = tablero.getBloque(i);
			}
			if (juego.getBloque(i).isVisible()) {
				s.setX(bloques[i].getX());
				s.setY(bloques[i].getY());
				s.setRoot(bloques[i].getImagen());
			} 
			s.setVisible(bloques[i].isVisible());
		}
		
	}
	
	private void actualizarBola(){
		Sprite s;
		s = tablero.getBola();
		if(s == null) {
			tablero.addBola();
			s = tablero.getBola();
		}
		s.setX(juego.getBola().getX());
		s.setY(juego.getBola().getY());
		s.setRoot(juego.getBola().getImagen());
		s.setVisible(juego.getBola().isVisible());
	}
	
	private void sonidoInicial() {
		try {
			FileInputStream is = new FileInputStream("recursos/sonidos/music.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			ini = (Clip) AudioSystem.getLine(info);
			ini.open(ais);
			ini.start();
			ini.loop(Clip.LOOP_CONTINUOUSLY);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sonidoNivel() {
		try {
			FileInputStream is = new FileInputStream("recursos/sonidos/nivel.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			niv = (Clip) AudioSystem.getLine(info);
			niv.open(ais);
			niv.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sonidoFin() {
		try {
			FileInputStream is = new FileInputStream("recursos/sonidos/fin.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
			DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
			fin = (Clip) AudioSystem.getLine(info);
			fin.open(ais);
			fin.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private void detenerSonidos() {
		if(ini!=null) {
			ini.stop();
		}
		if(fin!=null) {
			fin.stop();
		}
		if(niv!=null) {
			niv.stop();
		}
	}

	public static void main(String args[]) {
		ArkaPOOBGUI juego = new ArkaPOOBGUI();
		juego.setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(juego != null && tablero != null){	
			if(keyCode ==  KeyEvent.VK_P){
				juego.pausa();
				tablero.pausa();
			}
			
			if(keyCode == KeyEvent.VK_D) juego.JugadorNRight(0);
			if(keyCode == KeyEvent.VK_A) juego.JugadorNLeft(0);
			if(keyCode == KeyEvent.VK_SPACE) juego.soltarBola(0);
			
			
			if(juego.numeroDeJugadores() == 2 && juego.getJugador(1).esHumano()){
				if(keyCode == KeyEvent.VK_RIGHT) juego.JugadorNRight(1);
				if(keyCode == KeyEvent.VK_LEFT) juego.JugadorNLeft(1);
				if(keyCode == KeyEvent.VK_ENTER) juego.soltarBola(1);
			}
			actualizarJugadores();
		}
	}
	

	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		/*int keyCode = e.getKeyCode();
		if(juego != null && tablero != null){	
			if(keyCode ==  KeyEvent.VK_P){
				juego.pausa();
				tablero.pausa();
			}
			
			if(keyCode == KeyEvent.VK_D) juego.JugadorNRight(0);
			if(keyCode == KeyEvent.VK_A) juego.JugadorNLeft(0);
			if(keyCode == KeyEvent.VK_SPACE) juego.soltarBola(0);
			
			
			if(juego.numeroDeJugadores() == 2 && juego.getJugador(1).esHumano()){
				if(keyCode == KeyEvent.VK_RIGHT) juego.JugadorNRight(1);
				if(keyCode == KeyEvent.VK_LEFT) juego.JugadorNLeft(1);
				if(keyCode == KeyEvent.VK_ENTER) juego.soltarBola(1);
			}
			actualizarJugadores();
		}
		*/
	}
}