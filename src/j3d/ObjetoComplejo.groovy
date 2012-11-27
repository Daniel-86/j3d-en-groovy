package j3d

import java.util.Map;
import javax.media.j3d.*
import groovy.io.*

class ObjetoComplejo extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return null;
	}
	
	protected Node creaNodo(String directorio, String tipo) {
		File dir = new File(directorio)
		Java3dBuilder j3b = new Java3dBuilder()
		BranchGroup bg = j3b.branchGroup(){
			switch(tipo) {
				case 'xml':
					dir.eachFileMatch(FileType.FILES, ~/.*\.xml/) { xml->
						fromxml(archivo:xml)
					}
					break
				case 'obj':
					dir.eachFileMatch(FileType.FILES, ~/.*\.obj/) { obj->
						fromobj(archivo:obj)
					}
					break
				default:
					null
					break	
			}
		}
		return bg
	}
	
	Node crea (Map atributos) {
		Node resultado
		resultado = creaNodo(atributos.directorio, atributos.tipo)
		return resultado	
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
////		Map propiedades = luz.getProperties()
////		DirectionalLight.getMetaClass().getProperties().each {println it.name}
//		List propiedades = luz.getMetaClass().getProperties().collect {it.name}
//		atributos.remove('tipoLuz')
//		atributos.each { llave, valor->
////			if (propiedades.containsKey(llave))
//			if (llave in propiedades)
//				luz.getMetaClass().setProperty(luz, llave, valor)
////			else if (llave.equals('direction') && tipo.equals('DirectionalLight'))
////				luz.setDirection(valor)
//			else
//				println "Clase: $tipo tiene propiedad desconocida ($llave->$valor) : ${valor.class}"
//		}
}
