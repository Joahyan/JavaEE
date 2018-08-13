package com.briup.xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EmployeeDom {
	private static List<Employee> list =new ArrayList<Employee>();
	private static Employee employee;
	public static void main(String[] args) throws SAXException, IOException {
		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder =factory.newDocumentBuilder();
			File file =new File(
					"/C:/Users/Administrator/Workspaces/MyEclipse 2016 CI/ECJTU-EMDC/src/com/briup/xml/employee.xml");
			Document document =builder.parse(file);
			NodeList nl =
					document.getElementsByTagName("employee");
			for (int i = 0; i < nl.getLength(); i++) {
				employee =new Employee();
				Element e = (Element) nl.item(i);
				int id =Integer.parseInt(e.getAttribute("id"));
				String depName =e.getAttribute("depName");
				employee.setId(id);
				employee.setDepName(depName);
	
				NodeList nl2 =nl.item(i).getChildNodes();//返回所以子节点
				for (int j = 0; j <nl2.getLength() ; j++) {
					if (nl2.item(j).getNodeType()==Node.ELEMENT_NODE) {//获取元素
						//获取所有子节点
						String tagName =nl2.item(j).getNodeName();	//获取标签名
						String str =nl2.item(j).getTextContent();
						if ("name".equals(tagName)) {
							employee.setName(str);
						}else if("age".equals(tagName)) {
							employee.setAge(Integer.parseInt(str));
						}else if("gender".equals(tagName)) {
							employee.setGender(str);
						}else if("email".equals(tagName)) {
							employee.setEmail(str);;
						}else if("salary".equals(tagName)) {
							employee.setSalary(Integer.parseInt(str));
						}
					}
				}
				list.add(employee);
				System.out.println(employee);
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
}
