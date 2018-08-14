package com.briup.environment.util;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.briup.environment.client.Client;
import com.briup.environment.client.Gather;
import com.briup.environment.server.DBStore;
import com.briup.environment.server.Server;



public class ConfigurationImpl implements Configuration{
	private Map<String,EmdcModule> map=new HashMap<String,EmdcModule>();
	public ConfigurationImpl() {
		this("src/emdc.xml");
	}

	public ConfigurationImpl(String filePath){
		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
		try { 
			File file =new File("D:\\work_idea\\briup\\src\\emdc.xml");
			DocumentBuilder builder =factory.newDocumentBuilder();
			Document document =builder.parse(file);
            Element root=document.getDocumentElement();
            NodeList nList =root.getChildNodes();
//	           	Element element1= (Element) nList1.item(0);
//	            String Server_class =element1.getAttribute("class");
//	            String Server_port =element1.getElementsByTagName("port").item(0).getTextContent();
////	            System.out.println(Server_class);
////	            System.out.println(Server_port);
//
//	            NodeList nList2 =document.getElementsByTagName("dbstore");
//	            Element element2= (Element) nList2.item(0);
//	            String dbstore_class =element2.getAttribute("class");
//	            String dbstore_url =element2.getElementsByTagName("url").item(0).getTextContent();
//	            String dbstore_driver=element2.getElementsByTagName("driver").item(0).getTextContent();
//	            String dbstore_userName=element2.getElementsByTagName("userName").item(0).getTextContent();
//	            String dbstore_passWord=element2.getElementsByTagName("passWord").item(0).getTextContent();
////	            System.out.println(dbstore_class);
////	            System.out.println(dbstore_url);
//	              System.out.println(dbstore_driver);
////	            System.out.println(dbstore_userName);
////	            System.out.println(dbstore_passWord);
//
//	            NodeList nList3 =document.getElementsByTagName("client");
//	            Element element3= (Element) nList3.item(0);
//	            String client_class =element3.getAttribute("class");
//	            String client_ip =element3.getElementsByTagName("ip").item(0).getTextContent();
//	            String client_port=element3.getElementsByTagName("port").item(0).getTextContent();
////	            System.out.println(client_class);
////	            System.out.println(client_ip);
////	            System.out.println(client_port);
//
//	            NodeList nList4 =document.getElementsByTagName("gather");
//	            Element element4= (Element) nList4.item(0);
//	            String gather_class =element4.getAttribute("class");
//	            String gather_srcfile =element4.getElementsByTagName("src-file").item(0).getTextContent();
//	            String gather_recordfile=element4.getElementsByTagName("recored-file").item(0).getTextContent();
////	            System.out.println(gather_class);
////	            System.out.println(gather_recordfile);
////	            System.out.println(gather_srcfile);
//
//	            NodeList nList5 =document.getElementsByTagName("logger");
//	            Element element5= (Element) nList5.item(0);
//	            String logger_class =element5.getAttribute("class");
//	            String logfile =element5.getElementsByTagName("log-file").item(0).getTextContent();
//
//	            System.out.println(logger_class);
//	            System.out.println(logfile);
//
//	            NodeList nList6 =document.getElementsByTagName("backup");
//	            Element element6= (Element) nList6.item(0);
//	            String backup_class =element6.getAttribute("class");
//	            String backupfile =element6.getElementsByTagName("backup-file").item(0).getTextContent();
//	            System.out.println(backup_class);
//	            System.out.println(backupfile);
		    
	           	String key1="";
	            String value2="";
	            String key2="";
	            String value1="";
	            String c="";
	            for (int i = 1; i <nList.getLength(); i+=2) {
	                Node node =nList.item(i);
	                key1 =node.getNodeName();
//	                System.out.println(key1);
	                value1= String.valueOf(node.getAttributes().item(0).getNodeValue());
//	                System.out.println(value1);
	                c= String.valueOf(node.getAttributes().item(0).getNodeName());
//	                System.out.println(key1+"   "+c+":"+value1);
	                NodeList nList2=nList.item(i).getChildNodes();
	                EmdcModule emdc  = (EmdcModule)Class.forName(key1).newInstance();
	                Properties properties = new Properties();
	                for (int j=1;j<nList2.getLength();j+=2) {
	                    Node node2 =nList2.item(j);
//	                    System.out.println(node2.getNodeName());
	                    key2=node2.getNodeName();
	                    value2 = node2.getTextContent();
//	                System.out.println(key2+"   "+value2);
	               properties.put(key2,value2);
	                }
	                map.put(key1, emdc);
	            }

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Log getLogger() throws Exception {
		return (Log)map.get("log");
	}

	@Override
	public Server getServer() throws Exception {
		return (Server)map.get("server");
	}

	@Override
	public Client getClient() throws Exception {
		return (Client)map.get("client");
	}

	@Override
	public DBStore getDbStore() throws Exception {
		
		return (DBStore)map.get("dbstore");
	}

	@Override
	public Gather getGather() throws Exception {

		return (Gather)map.get("gather");
	}

	@Override
	public BackUP getBackUP() throws Exception {

		return (BackUP)map.get("backup");
	}



}
