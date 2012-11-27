package j3d

import java.util.Map;

import javax.media.j3d.*;

class BranchGroupHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return (Node)new BranchGroup()
	}
	
	protected void aplicaAtributos (Node aNodo, Map atributos) {
		BranchGroup bg = (BranchGroup) aNodo
		Map propiedades = bg.getProperties()
		atributos.each { llave, valor->
			if ( llave.equals("capability") && valor instanceof Integer )
				bg.setCapability( (Integer)valor )
			else if (propiedades.containsKey(llave))
				bg.getMetaClass().setProperty(bg, llave, valor)
			else
				println "Tipo de propiedad desconocido ($llave->$valor) : ${valor.class}"
		}
	}

}
