package Principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoBoton implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		VentanaEjercicio1 frame = new VentanaEjercicio1();
		frame.setVisible(true);
		
	}

}
