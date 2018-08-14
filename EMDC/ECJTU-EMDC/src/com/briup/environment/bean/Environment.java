package com.briup.environment.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Environment implements Serializable{
	/*
	 * �����洢ʵ���࣬�����������ࣨ�¶ȣ�ʪ�ȣ�������̼������ǿ�ȣ�
	 * ���Ͷ�id����ݮ��id��ʵ����ģ��id����������ַ������������
	 * ָ���ţ�״̬������ֵ���ɼ�ʱ��
	 */
	
	private String name;//������������
	private String srcId;//���Ͷ�id
	private String desId;//��ݮ��ϵͳid
	private String devId;//ʵ��������ģ��Id��1-8��
	private String sersorAddress;//ģ���ϴ�������ַ
	private int count;//����������
	private String cmd;//����ָ���� 3�������� 16��������
	private int status;//״̬ Ĭ��1��ʾ�ɹ�
	private float data;//����ֵ
	private Timestamp gather_date;//�ɼ�ʱ��
	public Environment(String name, String srcId, String desId, String devId, String sersorAddress, int count,
			String cmd, int status, float data, Timestamp gather_date) {
		super();
		this.name = name;
		this.srcId = srcId;
		this.desId = desId;
		this.devId = devId;
		this.sersorAddress = sersorAddress;
		this.count = count;
		this.cmd = cmd;
		this.status = status;
		this.data = data;
		this.gather_date = gather_date;
	}
	public Environment() {

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSrcId() {
		return srcId;
	}
	public void setSrcId(String srcId) {
		this.srcId = srcId;
	}
	public String getDesId() {
		return desId;
	}
	public void setDesId(String desId) {
		this.desId = desId;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getSersorAddress() {
		return sersorAddress;
	}
	public void setSersorAddress(String sersorAddress) {
		this.sersorAddress = sersorAddress;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public float getData() {
		return data;
	}
	public void setData(float data) {
		this.data = data;
	}
	public Timestamp getGather_date() {
		return gather_date;
	}
	public void setGather_date(Timestamp gather_date) {
		this.gather_date = gather_date;
	}
	@Override
	public String toString() {
		return "Environment [name=" + name + ", srcId=" + srcId + ", desId=" + desId + ", devId=" + devId
				+ ", sersorAddress=" + sersorAddress + ", count=" + count + ", cmd=" + cmd + ", status=" + status
				+ ", data=" + data + ", gather_date=" + gather_date + "]";
	}
	public void setGather_date(String string) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
