package j3d

import javax.media.j3d.*;

class TransformGroupHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return new TransformGroup()
	}
	
	protected void aplicaAtributos (Node aNodo, Map atributos) {
		TransformGroup tg = (TransformGroup) aNodo
		Map propiedades = tg.getProperties()
		atributos.each { llave, valor->
			if ( llave.equals("capability") && valor instanceof Integer )
				tg.setCapability( (Integer)valor )
			else if (propiedades.containsKey(llave))
				tg.getMetaClass().setProperty(tg, llave, valor)
			else
				println "Tipo de propiedad desconocido ($llave->$valor) : ${valor.class}"
		}
	}

}
