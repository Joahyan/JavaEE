package com.briup.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class Dom4jEmployee {
	
	public static void main(String[] args) {
		List<Employee> list =null;
		
		try {
			//获取一个SAXReader对象
			SAXReader read =new SAXReader();
			//读取xml文件
			Document document =read.read(
					"/C:/Users/Administrator/Workspaces/MyEclipse 2016 CI/ECJTU-EMDC/src/com/briup/xml/employee.xml");
			//获取根节点
			Element root =document.getRootElement();
			//获取根节点下面的所有子节点
			Iterator<Element> it =root.elementIterator();
			//迭代获取子节点
			list =new ArrayList<>();			
			while (it.hasNext()) {
				//获取二级节点（employee）下面的子节点
				Element next = it.next();
				//获取<employee>里的属性值
				int id =Integer.parseInt(next.attributeValue("id"));
				String depName =next.attributeValue("depName");
				Employee e =new Employee();
				//获取文本内容
				int age = Integer.parseInt(next.elementText("age"));
				String name = next.elementText("name");
				String gender = next.elementText("gender");
				String email = next.elementText("email");
				int salary = Integer.parseInt(next.elementText("salary"));
				e.setId(id);
				e.setAge(age);
				e.setName(name);
				e.setDepName(depName);
				e.setGender(gender);
				e.setEmail(email);
				e.setSalary(salary);
				list.add(e);		
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		for (Employee e:list) {
			System.out.println(e);
		}
	}
	
}
