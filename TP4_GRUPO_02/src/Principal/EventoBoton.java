package Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventoBoton implements ActionListener{

	JFrame Ventana = new JFrame();
	
	public EventoBoton(JFrame ventana) {
		Ventana = ventana;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Ventana.setVisible(true);
		
	}

}
