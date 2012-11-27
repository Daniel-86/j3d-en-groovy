package j3d

import java.beans.XMLDecoder
import java.util.Map
import javax.media.j3d.*

import groovy.io.*
import com.sun.j3d.loaders.objectfile.ObjectFile

class FromObj extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return null;
	}
	
	protected Node creaNodo(String archivo) {
		ObjectFile obj = new ObjectFile()
		return obj.load(archivo).getSceneGroup()
	}
	
	protected Node creaNodo(File archivo) {
		ObjectFile obj = new ObjectFile()
		return obj.load(archivo.absolutePath).getSceneGroup()
	}
	
	Node crea (Map attributos) {
		creaNodo(attributos.archivo)
	}
	
//	protected void aplicaAtributos(Node aNodo, Map atributos) {
//		
//		def luz
//		String tipo = atributos.tipoLuz
//		switch(tipo) {
//			case 'AmbientLight':
//				luz = (AmbientLight) aNodo
//				break
//			case 'DirectionalLight':
//				luz = (DirectionalLight) aNodo
//				break
//			case 'PointLight':
//				luz = (PointLight) aNodo
//				break
//		}
//		luz.setEnable(true)
//		List propiedades = luz.getMetaClass().getProperties().collect {it.name}
//		atributos.remove('tipoLuz')
//		atributos.each { llave, valor->
//			if (llave in propiedades)
//				luz.getMetaClass().setProperty(luz, llave, valor)
//			else
//				println "Clase: $tipo tiene propiedad desconocida ($llave->$valor) : ${valor.class}"
//		}
//	}
}
