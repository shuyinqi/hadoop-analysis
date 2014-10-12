package com.mapbar.analyzelog.service.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ReadXml {
 public String getsConnStr() {
		return sConnStr;
	}

	public void setsConnStr(String sConnStr) {
		this.sConnStr = sConnStr;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

private String drivername;
 private String sConnStr;
 private String username;
 private String password;
 private String fileName;
 public String getDrivername() {
  return drivername;
 }

 public String getSConnStr() {
  return sConnStr;
 }

 public String getUsername() {
  return username;
 }

 public String getPassword() {
  return password;
 }
 public void setDrivername(String drivername) {
  this.drivername = drivername;
 }

 public void setSConnStr(String connStr) {
  sConnStr = connStr;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 
 
 public void ReadXmlToJDBC(){
  DocumentBuilderFactory domfac=DocumentBuilderFactory.newInstance();
  try {
   DocumentBuilder dombuilder=domfac.newDocumentBuilder();
   System.out.println("readXmlToJDBC"+getFileName());
   InputStream is=new FileInputStream(getFileName());
   Document doc=dombuilder.parse(is);
   Element root=doc.getDocumentElement();
   NodeList dbinfo=root.getChildNodes();
   if(dbinfo!=null){
    for(int i=0;i<dbinfo.getLength();i++){
     Node db=dbinfo.item(i);
       for(Node node=db.getFirstChild();node!=null;node=node.getNextSibling()){
        if(node.getNodeType()==Node.ELEMENT_NODE){
        	 if("mapred.jdbc.url".equals(node.getFirstChild().getNodeValue().trim())){
        		 System.out.println("-------");
        		 System.out.println(node.getNextSibling().getNextSibling().getFirstChild().getNodeValue());
        		 setSConnStr(node.getNextSibling().getNextSibling().getFirstChild().getNodeValue());
        	 }
        	 if("mapred.jdbc.driver.class".equals(node.getFirstChild().getNodeValue().trim())){
        		 System.out.println("-------");
        		 System.out.println(node.getNextSibling().getNextSibling().getFirstChild().getNodeValue());
        		 setDrivername(node.getNextSibling().getNextSibling().getFirstChild().getNodeValue());
        	 }
        	 if("mapred.jdbc.username".equals(node.getFirstChild().getNodeValue().trim())){
        		 System.out.println("-------");
        		 System.out.println(node.getNextSibling().getNextSibling().getFirstChild().getNodeValue());
        		 setUsername(node.getNextSibling().getNextSibling().getFirstChild().getNodeValue());
        	 }
        	 if("mapred.jdbc.password".equals(node.getFirstChild().getNodeValue().trim())){
        		 System.out.println("-------");
        		 //node.getNextSibling().getNextSibling().getFirstChild().getNodeValue()
        		 setPassword(node.getNextSibling().getNextSibling().getFirstChild().getNodeValue());
        	 }
        }
      }         
       }        
     }
  } catch (ParserConfigurationException e) {
   e.printStackTrace();
  } catch (FileNotFoundException e) {
   e.printStackTrace();
  } catch (SAXException e) {
   e.printStackTrace();
  } catch (IOException e) {
   e.printStackTrace();
  }  
 } 
 
 public static void main(String[] args) {
	 String relativelyPath=System.getProperty("user.dir");
//	 ReadXml rx = new ReadXml("D:/server/trunk/log-analysis-service/src/main/resources/db-site.xml");
//	 System.out.println(rx.getDrivername());
}
}