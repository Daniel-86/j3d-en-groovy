package j3d

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javax.media.j3d.*

class FromXml extends J3dNodeHelper {

	@Override
	protected Node creaNodo() {
		return null;
	}
	
	protected Node creaNodo(String archivo) {
		File xml = new File(archivo)
		return creaNodo(xml)
	}
	
	
	protected Node creaNodo(File xml) {
		Shape3D forma
		try {
			XMLDecoder de = new XMLDecoder(
										new BufferedInputStream(
											new FileInputStream(xml.getAbsolutePath())
										));
			forma=(Shape3D) de.readObject();
			de.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return forma
	}
	
	
	Node crea (Map attributos) {
		creaNodo(attributos.archivo)
	}

}
