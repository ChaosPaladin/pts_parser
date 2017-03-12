package pts.data;

import java.io.File;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

public class Main {

	public static File file(String arg, String...args) {
		return Paths.get(arg, args).toFile();
	}
	
	public static void main(String...args) {
		JAXB.marshal(Data.from("multisell.txt"), file("xml0", "multisell.xml"));
	}
}
