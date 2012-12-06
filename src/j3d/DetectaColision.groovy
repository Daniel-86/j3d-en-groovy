package j3d

import javax.media.j3d.Behavior
import javax.media.j3d.Bounds
import javax.media.j3d.Shape3D
import javax.media.j3d.WakeupCriterion
import javax.media.j3d.WakeupOnCollisionEntry
import javax.media.j3d.WakeupOnCollisionExit
import javax.media.j3d.WakeupOnCollisionMovement
import javax.media.j3d.WakeupOr
import javax.media.j3d.Node

class DetectaColision extends Behavior {

	protected WakeupCriterion[] criterios
	protected WakeupOr arrayOr
	protected Node shape
	
	DetectaColision(Node shape) {
		this.shape = shape
//		setSchedulingBounds(bounds)
	}
	
	@Override
	public void initialize() {
		criterios = new WakeupCriterion[3]
		criterios[0] = new WakeupOnCollisionEntry(shape, WakeupOnCollisionEntry.USE_GEOMETRY)
		criterios[1] = new WakeupOnCollisionExit(shape, WakeupOnCollisionExit.USE_GEOMETRY)
		criterios[2] = new WakeupOnCollisionMovement(shape, WakeupOnCollisionMovement.USE_GEOMETRY)
		arrayOr = new WakeupOr(criterios)
		wakeupOn(arrayOr)
		

	}

	@Override
	public void processStimulus(Enumeration arg0) {
		WakeupCriterion criterioTrig = (WakeupCriterion) arg0.nextElement()
		if(criterioTrig instanceof WakeupOnCollisionEntry) {
			Node nodo = ((WakeupOnCollisionEntry) criterioTrig).getTriggeringPath().getObject()
			println "Collisionando ${nodo.userData}"
		} else if(criterioTrig instanceof WakeupOnCollisionExit) {
			Node nodo = ((WakeupOnCollisionExit) criterioTrig).getTriggeringPath().getObject()
			println "Ya no colisiona ${nodo.userData}"
		} else {
			Node nodo = ((WakeupOnCollisionMovement) criterioTrig).getTriggeringPath().getObject()
			println "Esta chocando ${nodo.userData}"
		}
		wakeupOn(arrayOr)

	}

}
