package j3d

import javax.media.j3d.*

abstract class J3dNodeHelper {
	public J3dNodeHelper() {
		
	}
	
	abstract protected Node creaNodo()
	
	protected void aplicaAtributos(Node aNodo, Map atributos) {
		
	}
	
	Node crea(Map atributos) {
		Node resultado = creaNodo()
		aplicaAtributos(resultado, atributos)
		return resultado
	}

}
