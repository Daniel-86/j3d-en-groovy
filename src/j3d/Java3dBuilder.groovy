package j3d

import java.util.Map
import javax.media.j3d.*;
import groovy.util.BuilderSupport;

/**
 * Clase constructora de arboles para ser renderizados por Java 3D
 * @author Daniel Jiménez Ortega
 *
 */
class Java3dBuilder extends BuilderSupport {
	
	HashMap<String, J3dNodeHelper> objetos
	
	public Java3dBuilder() {
		objetos = [:]
		objetos.put("branchGroup", new BranchGroupHelper())
		objetos.put("transformGroup", new TransformGroupHelper())
		objetos.put("colorCube", new ColorCubeHelper())
		objetos.put("rotationInterpolator", new RotationInterpolatorHelper())
		objetos.put("fromxml", new FromXml())
		objetos.put("fromobj", new FromObj())
		objetos.put("light", new LightHelper())
		objetos.put("backgrnd", new BackGroundHelper())
		objetos.put("complejo", new ObjetoComplejo())
		objetos.put("primitiva", new PrimitiveHelper())
		objetos.put("behavior", new BehaviorHelper())
	}

	
	/**
	 * Crea una relación padre-hijo. El padre debe ser un Java3D Group y el hijo un Java3D Node
	 */
	@Override
	protected void setParent(Object parent, Object child) {
		if (parent instanceof Group) {
			( (Group)parent ).addChild( (Node)child )
		}

	}
	
	
	protected SceneGraphObject creaObjeto(String nombre) {
		J3dNodeHelper c = objetos.get(nombre)
		return (SceneGraphObject)c.crea([:])
	}
	
	protected SceneGraphObject creaObjeto(String nombre, Map atributos) {
		J3dNodeHelper c = objetos.get(nombre)
		return (SceneGraphObject)c.crea(atributos)
	}

	@Override
	protected Object createNode(Object name) {
		SceneGraphObject resultado = null
		SceneGraphObject actual = (SceneGraphObject)getCurrent()
		String sNombre = (String)name
		resultado = creaObjeto(sNombre)
		return resultado
	}

	@Override
	protected Object createNode(Object name, Object value) {
		SceneGraphObject resultado = null
		String sNombre = (String)name
		SceneGraphObject actual = (SceneGraphObject)getCurrent()
		resultado = creaObjeto(sNombre)
		return resultado
	}

	@Override
	protected Object createNode(Object name, Map attributes) {
		SceneGraphObject resultado = null
		String sNombre = (String)name
		return creaObjeto(sNombre, attributes)
	}

	@Override
	protected Object createNode(Object name, Map attributes, Object value) {
		SceneGraphObject resultado = null
		SceneGraphObject actual = (SceneGraphObject)getCurrent()
		String sNombre = (String)name
		resultado = creaObjeto(sNombre, attributes)
		return resultado
	}

}
