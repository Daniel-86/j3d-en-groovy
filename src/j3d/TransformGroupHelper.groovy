package j3d

import javax.media.j3d.*;

class TransformGroupHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return new TransformGroup()
	}
	
	protected void aplicaAtributos (Node aNodo, Map atributos) {
		TransformGroup tg = (TransformGroup) aNodo
		List propiedades = tg.getMetaClass().getProperties().collect {it.name}
		atributos.each { llave, valor->
			if ( llave.equals("capability") && valor instanceof Integer )
				tg.setCapability( (Integer)valor )
			else if (llave in propiedades)
				tg.getMetaClass().setProperty(tg, llave, valor)
			else
				println "Tipo de propiedad desconocido ($llave->$valor) : ${valor.class}"
		}
	}

}
