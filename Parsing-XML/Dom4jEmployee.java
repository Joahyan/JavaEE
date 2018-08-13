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
			//��ȡһ��SAXReader����
			SAXReader read =new SAXReader();
			//��ȡxml�ļ�
			Document document =read.read(
					"/C:/Users/Administrator/Workspaces/MyEclipse 2016 CI/ECJTU-EMDC/src/com/briup/xml/employee.xml");
			//��ȡ���ڵ�
			Element root =document.getRootElement();
			//��ȡ���ڵ�����������ӽڵ�
			Iterator<Element> it =root.elementIterator();
			//������ȡ�ӽڵ�
			list =new ArrayList<>();			
			while (it.hasNext()) {
				//��ȡ�����ڵ㣨employee��������ӽڵ�
				Element next = it.next();
				//��ȡ<employee>�������ֵ
				int id =Integer.parseInt(next.attributeValue("id"));
				String depName =next.attributeValue("depName");
				Employee e =new Employee();
				//��ȡ�ı�����
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
