package j3d

import java.util.Map;

import javax.media.j3d.*

class BehaviorHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return null;
	}
	
	
	Node crea(Map atributos) {
//		if( !(atributos.tipo in ['KeyNavigatorBehavior', 'NavTecla']) )
//			return null
		String tipo
		switch(atributos.tipo) {
			case 'NavTecla':println "RECONOCIDO"
				tipo = "j3d.${atributos.tipo}"
				break
			default:
				return null
		}
//		def clasePrueba = this.class.classLoader.loadClass('String')
		def clase = this.class.classLoader.loadClass(tipo)
		Node behavior = clase.newInstance(atributos.tg, atributos.teclas)
		atributos.remove('tg')
		atributos.remove('teclas')
		aplicaAtributos(behavior, atributos)
		
		return behavior
	}
	
	protected void aplicaAtributos (Node aNodo, Map atributos) {
		def behavior
		switch(atributos.tipo) {
			case 'NavTecla':
				behavior = (NavTecla) aNodo
				break
		}
		atributos.remove('tipo')
		List propiedades = behavior.getMetaClass().getProperties().collect {it.name}
		atributos.each { llave, valor->
			if (llave in propiedades)
				behavior.getMetaClass().setProperty(behavior, llave, valor)
			else
				println "Tipo de propiedad desconocido ($llave->$valor) : ${valor.class}"
		}
	}

}
