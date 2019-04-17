/**
 * 
 */
package lc.alg.entity;

import java.util.List;
import java.util.Map;

/**
 * @author �
 *	����������Ϣ��
 */
public class SpiderConfigInfo {
	
	private String targetUrl; // �������е���ַ
	private String taskName;	// ��ǰ���������
	private Map<String,List<String>> attributeParser; // ��ȡ�������Լ���Ӧ�Ľ�������
	public SpiderConfigInfo(String targetUrl, String taskName, Map<String, List<String>> attributeParser) {
		super();
		this.targetUrl = targetUrl;
		this.taskName = taskName;
		this.attributeParser = attributeParser;
	}
	public SpiderConfigInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Map<String, List<String>> getAttributeParser() {
		return attributeParser;
	}
	public void setAttributeParser(Map<String, List<String>> attributeParser) {
		this.attributeParser = attributeParser;
	}
	@Override
	public String toString() {
		return "SpiderConfigInfo [targetUrl=" + targetUrl + ", taskName=" + taskName + ", attributeParser="
				+ attributeParser + "]";
	}
	
	
}
