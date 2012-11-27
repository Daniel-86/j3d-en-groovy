package j3d

import java.util.Map;

import javax.media.j3d.*

class LightHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return null;
	}
	
	protected Node creaNodo(String tipo) {
		switch(tipo) {
			case 'AmbientLight':
				return new AmbientLight()
				break
			case 'DirectionalLight':
				return new DirectionalLight()
				break
			case 'PointLight':
				return new PointLight()
				break
			default :
				return null
				break
		}
	}
	
	Node crea (Map atributos) {
		Node resultado = null
		if(atributos.tipoLuz in ['AmbientLight', 'DirectionalLight', 'PointLight']) {
			resultado = creaNodo(atributos.tipoLuz)
			aplicaAtributos(resultado, atributos)
		}
		return resultado
	}
	
	
	protected void aplicaAtributos(Node aNodo, Map atributos) {
		
		def luz
		String tipo = atributos.tipoLuz
		switch(tipo) {
			case 'AmbientLight':
				luz = (AmbientLight) aNodo
				break
			case 'DirectionalLight':
				luz = (DirectionalLight) aNodo
				break
			case 'PointLight':
				luz = (PointLight) aNodo
				break
		}
		luz.setEnable(true)
		List propiedades = luz.getMetaClass().getProperties().collect {it.name}
		atributos.remove('tipoLuz')
		atributos.each { llave, valor->
			if (llave in propiedades)
				luz.getMetaClass().setProperty(luz, llave, valor)
			else
				println "Clase: $tipo tiene propiedad desconocida ($llave->$valor) : ${valor.class}"
		}
	}

}
