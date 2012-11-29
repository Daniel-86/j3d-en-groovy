package j3d

import java.awt.AWTEvent
import java.awt.event.*

import javax.media.j3d.Behavior
import javax.media.j3d.Transform3D
import javax.media.j3d.TransformGroup
import javax.media.j3d.WakeupCriterion
import javax.media.j3d.WakeupOnAWTEvent
import javax.vecmath.Vector3d

class NavTecla extends Behavior {
	
	TransformGroup tg
	Map teclas
	WakeupCriterion wakeEvent
	
	NavTecla(TransformGroup tg, Map teclas){
		this.tg = tg
		this.teclas = teclas
		wakeEvent = new WakeupOnAWTEvent(KeyEvent.KEY_PRESSED)
	}
	
	NavTecla(Map atributos) {
		NavTecla(atributos.tg, atributos.teclas)
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
		double avance = 0.5
		double avanceGiro = 0.5
		
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
				switch(evento.getKeyCode()) {
					case teclas.arriba:
						Vector3d vec = new Vector3d(0, avance, 0)
						aux.setTranslation(vec)
						break
					case teclas.abajo:
						Vector3d vec = new Vector3d(0, -avance, 0)
						aux.setTranslation(vec)
						break
					case teclas.izquierda:
						Vector3d vec = new Vector3d(-avance, 0, 0)
						aux.setTranslation(vec)
						break
					case teclas.derecha:
						Vector3d vec = new Vector3d(avance, 0, 0)
						aux.setTranslation(vec)
						break
					case teclas.atras:
						Vector3d vec = new Vector3d(0, 0, -avance)
						aux.setTranslation(vec)
						break
					case teclas.adelante:
						Vector3d vec = new Vector3d(0, 0, avance)
						aux.setTranslation(vec)
						break
					default:
						break
				}
				nueva.mul(aux)
				tg.setTransform(nueva)
			}
		}
		wakeupOn(wakeEvent)
	}

}
