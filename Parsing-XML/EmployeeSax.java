package com.briup.xml;

import java.util.ArrayList;
import java.util.List;
import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EmployeeSax {
	public static void main(String[] args) {
		SAXParserFactory factory =SAXParserFactory.newInstance();
		try {
			SAXParser parser =factory.newSAXParser();
			File file =new File("/C:/Users/Administrator/Workspaces/MyEclipse 2016 CI/ECJTU-EMDC/src/com/briup/xml/employee.xml");
			Handler handler =new Handler();
			parser.parse(file, handler);
			List<Employee>list =handler.getList();
			for (Employee employee:list) {
				System.out.println(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

class Handler extends DefaultHandler{
	private List<Employee> list;
	private Employee employee;
	//tagName：只需获取name，age，gender，salary，email这几个开始标签
	private String tagName;
	public List<Employee> getList() {
		return list;
	}
	public void setList(List<Employee> list) {
		this.list = list;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public void startDocument() throws SAXException {
		//创建list集合
		list =new ArrayList<Employee>();	
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//构建employee对象 
		if("employee".equals(qName)){//开始标签获取（"employee"）
			employee =new Employee();
			//输出ID和DepName
			int id =Integer.parseInt(attributes.getValue("id"));
			String depName =attributes.getValue("depName");
			employee.setId(id);
			employee.setDepName(depName);
		}else {
			tagName =qName;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("employee".equals(qName)) {
			list.add(employee);
		}
		tagName =null;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str =new String(ch,start,length);
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
