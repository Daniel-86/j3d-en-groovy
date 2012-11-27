package j3d

import java.util.Map;

import javax.media.j3d.*

import com.sun.j3d.utils.geometry.ColorCube

class ColorCubeHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return new ColorCube()
	}
	
	protected Node creaNodo(double escala) {
		return new ColorCube(escala)
	}
	
	Node crea (Map atributos) {
		Node resultado
		if (atributos.get("escala"))
			resultado = creaNodo( atributos.get("escala") )
		else
			resultado = creaNodo()
		atributos.remove('escala')
		aplicaAtributos(resultado, atributos)
		return resultado
	}
	
	protected void aplicaAtributos (Node aNodo, Map atributos) {
		ColorCube c = (ColorCube) aNodo
		Map propiedades = c.getProperties()
		atributos.each { llave, valor->
			if ( llave.equals("capability") && valor instanceof Integer )
				c.setCapability( (Integer)valor )
			else if (propiedades.containsKey(llave))
				c.getMetaClass().setProperty(c, llave, valor)
			else
				println "Tipo de propiedad desconocido ($llave->$valor) : ${valor.class}"
		}
	}

}
