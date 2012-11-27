package j3d

import java.util.Map;

import javax.media.j3d.Node

import com.sun.j3d.utils.geometry.Box
import com.sun.j3d.utils.geometry.Cone
import com.sun.j3d.utils.geometry.Cylinder
import com.sun.j3d.utils.geometry.Sphere

class PrimitiveHelper extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return null;
	}
	
	protected Node creaNodo(String tipo, atributos) {
		switch(tipo) {
			case 'Box':
				if(atributos.x && atributos.y && atributos.z) {
					if(atributos.primFlags)
						return new Box(atributos.x, atributos.y, atributos.z, atributos.primFlags, atributos.apariencia)
					else
						return new Box(atributos.x, atributos.y, atributos.z, atributos.apariencia?:null)
				}
				else
					return new Box()
				break
			case 'Cylinder':
				if(atributos.radio && atributos.alto) {
					if(atributos.primFlags){
						if(atributos.xdiv && atributos.ydiv)
							return new Cylinder(atributos.radio, atributos.alto, atributos.primFlags, atributos.xdiv, atributos.ydiv, atributos.apariencia)
						else
							return new Cylinder(atributos.radio, atributos.alto, atributos.primFlags, atributos.apariencia)
					}
					else
						return new Cylinder(atributos.radio, atributos.alto, atributos.apariencia?:null)
				}
				else
					return new Cylinder()
				break
			case 'Sphere':
				if(atributos.radio) {
					if(atributos.primFlags){
						if(atributos.div)
							return new Sphere(atributos.radio, atributos.primFlags, atributos.div, atributos.apariencia)
						else
							return new Sphere(atributos.radio, atributos.primFlags, atributos.apariencia)
					}
					else
						return new Sphere(atributos.radio, atributos.apariencia?:null)
				}
				else
					return new Sphere()
				break
			case 'Cone':
				if(atributos.radio && atributos.alto) {
					if(atributos.primFlags){
						if(atributos.xdiv && atributos.ydiv)
							return new Cone(atributos.radio, atributos.alto, atributos.primFlags, atributos.xdiv, atributos.ydiv, atributos.apariencia)
						else
							return new Cone(atributos.radio, atributos.alto, atributos.primFlags, atributos.apariencia)
					}
					else
						return new Cone(atributos.radio, atributos.alto, atributos.apariencia?:null)
				}
				else
					return new Cone()
				break
				break
			default:
				return null
				break
		}
	}
	
	Node crea (Map atributos) {
		Node nodo = null
		if (atributos.tipo in ['Box', 'Cylinder', 'Sphere', 'Cone']) {
			nodo = creaNodo(atributos.tipo, atributos)
			aplicaAtributos(nodo, atributos)
		}
		return nodo
	}
	
	
	protected void aplicaAtributos(Node aNodo, Map atributos) {
		
		def primitiva
		String tipo = atributos.tipo
		switch(tipo) {
			case 'Box':
				primitiva = (Box) aNodo
				break
			case 'Cylinder':
				primitiva = (Cylinder) aNodo
				break
			case 'Sphere':
				primitiva = (Sphere) aNodo
				break
			case 'Cone':
				primitiva = (Cone) aNodo
				break
		}
		List propiedades = primitiva.getMetaClass().getProperties().collect {it.name}
		atributos.remove('tipo')
		atributos.remove('x')
		atributos.remove('y')
		atributos.remove('z')
		atributos.remove('primFlags')
		atributos.remove('apariencia')
		atributos.remove('radio')
		atributos.remove('alto')
		atributos.remove('xdiv')
		atributos.remove('ydiv')
		atributos.remove('div')
		atributos.each { llave, valor->
			if (llave in propiedades)
				primitiva.getMetaClass().setProperty(primitiva, llave, valor)
			else
				println "Clase: $tipo tiene propiedad desconocida ($llave->$valor) : ${valor.class}"
		}
	}
}
