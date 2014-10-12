package com.mapbar.analyzelog.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlParseUtil {
	public XmlParseUtil() {
	}

	public NodeList getNodes(String urlData,String xname) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		NodeList nodes=null;
		try {
			InputStream is = new ByteArrayInputStream(urlData.getBytes());
			InputSource inputSource = new InputSource(is);
			XPathFactory xFactory = XPathFactory.newInstance();
			XPath xpath = xFactory.newXPath();
			nodes = (NodeList) xpath.evaluate(xname, inputSource,
					XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodes;
	}
	
	public List<String> getItem(NodeList nodes1){
		List<String> list1=new ArrayList<String>();
		for(int i=0;i<nodes1.getLength();i++){
			list1.add(nodes1.item(i).getTextContent());
		}
		return list1;
	}
}