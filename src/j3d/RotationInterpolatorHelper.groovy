package j3d

import java.util.Map;

import javax.media.j3d.*

class RotationInterpolatorHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return null
	}
	
	protected Node creaNodo(Alpha a, TransformGroup tg){
		return new RotationInterpolator(a, tg)
	}
	
	Node crea(Map atributos) {
		Node resultado
		if (atributos.alpha && atributos.target)
			resultado = creaNodo(atributos.alpha, atributos.target)
		atributos.remove("alpha")
		atributos.remove("target")
//		println atributos
		aplicaAtributos(resultado, atributos)
		return resultado
	}
	
	
	protected void aplicaAtributos(Node aNodo, Map atributos) {
		RotationInterpolator r = (RotationInterpolator) aNodo
		Map propiedades = r.getProperties()
		atributos.each { llave, valor->
			if (propiedades.containsKey(llave))
				r.getMetaClass().setProperty(r, llave, valor)
			else
				println "Tipo de propiedad desconocido $llave->$valor:${valor.class}"
		}
	}

}
