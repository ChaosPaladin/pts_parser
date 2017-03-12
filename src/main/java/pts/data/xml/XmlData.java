package pts.data.xml;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class XmlData {
	@XmlElements({
		@XmlElement(name = "multisell", type = XmlMultiSell.class)
	})
	public List<Object> list = new LinkedList<>();
}
