package j3d

import java.awt.AWTEvent
import java.awt.event.*

import javax.media.j3d.Behavior
import javax.media.j3d.Transform3D
import javax.media.j3d.TransformGroup
import javax.media.j3d.WakeupCriterion
import javax.media.j3d.WakeupOnAWTEvent

class NavTecla extends Behavior {
	
	TransformGroup tg
	Map teclas
	WakeupCriterion wakeEvent
	
	NavTecla(TransformGroup tg, Map teclas){
		this.tg = tg
		this.teclas = teclas
		wakeEvent = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED)
	}

	@Override
	public void initialize() {
		wakeupOn(wakeEvent)
	}

	@Override
	public void processStimulus(Enumeration criteria) {
		
		WakeupCriterion criterio;
		WakeupOnAWTEvent criterioAWT;
		AWTEvent[] eventos;
		
		while(criteria.hasMoreElements()) {
			criterio = (WakeupCriterion)criteria.nextElement()
			if( !(criterio instanceof WakeupOnAWTEvent))
				return
			criterioAWT = (WakeupOnAWTEvent) criterio
			eventos = criterioAWT.getAWTEvent()
			eventos.each { KeyEvent evento->
				if(evento.getID() != KeyEvent.KEY_PRESSED)
					return
					
				Transform3D nueva = new Transform3D()
				Transform3D aux = new Transform3D()
				
				tg.getTransform(nueva)
			}
		}	
	}

}
