import j3d.*

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GraphicsConfiguration

import javax.media.j3d.*
import javax.swing.JFrame
import javax.vecmath.*

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior
import com.sun.j3d.utils.universe.*


class testJ3dBuilder extends JFrame {
	
	private SimpleUniverse u
	private BranchGroup escena
	
	private Canvas3D creaUniverso() {
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration()
		Canvas3D c = new Canvas3D(config)
		u = new SimpleUniverse(c)
		
		return c
	}
	
	
	private ajustaVista(Canvas3D c) {
		u.getViewingPlatform().setNominalViewingTransform()
		OrbitBehavior orbit = new OrbitBehavior(c, OrbitBehavior.REVERSE_ALL)
		orbit.setSchedulingBounds(new BoundingSphere(new Point3d(0,0,0), 100))
		u.getViewingPlatform().setViewPlatformBehavior(orbit)
	}
	
	private BranchGroup creaEscena() {
		TransformGroup tg
		Java3dBuilder j3b = new Java3dBuilder()
		BranchGroup raiz

		raiz = j3b.branchGroup() {
			tg = transformGroup( capability:TransformGroup.ALLOW_TRANSFORM_WRITE, userData:"MiTG" ) {
//				colorCube(escala:0.4f)
//				fromxml(archivo:'banca.xml')
//				complejo(directorio:'banca1', tipo:'xml')
//				primitiva(tipo:'Box', x:1, y:2, z:3)
//				primitiva(tipo:'Cone', alto:4, radio:0.5)
//				primitiva(tipo:'Cylinder', alto:4, radio:0.5)
//				primitiva(tipo:'Sphere', radio:1.5, div:128)
//				fromobj(archivo:'cubo1.obj')
				complejo(directorio:'cubo5', tipo:'obj')
			}
			rotationInterpolator ( alpha:new Alpha(-1, 4000),
									target:tg,
									schedulingBounds:new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0)
								)
			light (tipoLuz:'AmbientLight',
					influencingBounds:new BoundingSphere(new Point3d(0.0,0.0,0.0),100)
					)
//			light (tipoLuz:'DirectionalLight',
//					influencingBounds:new BoundingSphere(new Point3d(0.0,0.0,0.0),100),
//					direction:new Vector3f(10, -10, -10),
//					userData:'luzDireccional'
//					)
//			light (tipoLuz:'DirectionalLight',
//				influencingBounds:new BoundingSphere(new Point3d(0.0,0.0,0.0),100),
//				direction:new Vector3f(-10, 10, 10),
//				userData:'luzDireccional'
//				)
			backgrnd(color:new Color3f(0.3,0.3,0),
					applicationBounds:new BoundingSphere(new Point3d(0.0,0.0,0.0),100),
				)
		}

		return raiz
	}
	
	
	public testJ3dBuilder() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Hola Builders");
		setPreferredSize(new Dimension(400,400))
		setSize(new Dimension(400,400))
		
		Canvas3D c = creaUniverso()
		u.addBranchGraph(creaEscena())
		ajustaVista(c)
		
		getContentPane().setLayout(new BorderLayout())
		getContentPane().add(c, BorderLayout.CENTER)
		pack()
	}

	
	static main(args) {
		def vent = new testJ3dBuilder()
		vent.setVisible(true)
	}

}
