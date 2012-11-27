package j3d

import javax.media.j3d.Node

class NodoHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return null;
	}
	
	protected Node creaNodo(tipoNodo) {
		Class clase = this.class.classLoader.loadClass(tipoNodo)
	}

}
