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
		Node behavior
		switch(atributos.tipo) {
			case 'NavTecla':println "RECONOCIDO"
				tipo = "j3d.${atributos.tipo}"
				behavior = new NavTecla(atributos.tg, atributos.teclas, atributos.branch)
				break
			case 'colision':
				behavior = new DetectaColision(atributos.shape)
				break
			default:
				return null
		}
//		def clase = this.class.classLoader.loadClass(tipo)
//		Node behavior = clase.newInstance(atributos.tg, atributos.teclas)
		atributos.remove('tg')
		atributos.remove('teclas')
		atributos.remove('shape')
		atributos.remove('branch')
		aplicaAtributos(behavior, atributos)
		
		return behavior
	}
	
	protected void aplicaAtributos (Node aNodo, Map atributos) {
		def behavior
		switch(atributos.tipo) {
			case 'NavTecla':
				behavior = (NavTecla) aNodo
				break
			case 'colision':
				behavior = (DetectaColision) aNodo
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
