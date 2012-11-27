package j3d

import java.util.Map;
import javax.media.j3d.*

class BackGroundHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return new Background();
	}
	
	protected void aplicaAtributos(Node aNodo, Map atributos) {
		Background bg = (Background) aNodo
		List propiedades = bg.getMetaClass().getProperties().collect {it.name}
		atributos.each { llave, valor->
			if ( llave.equals("capability") && valor instanceof Integer )
				bg.setCapability( (Integer)valor )
			else if (llave in propiedades)
				bg.getMetaClass().setProperty(bg, llave, valor)
			else
				println "Tipo de propiedad desconocido ($llave->$valor) : ${valor.class}"
		}
	}

}
