package lc.alg.entity;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * �������ܼ����
 * @author �
 *
 */
@Document(collection="spiderrun_info")
public class SpiderRunInfo {
	private String taskName;	//�������������
	private List<Map<String,String>> memInfo;	// �ڴ�ռ����ʱ��ı任���
	private List<Map<String,String>> getCountInfo;	//��ȡ������������ʱ��ı仯���
	private String runlog;	//����������־
	public SpiderRunInfo(String taskName, List<Map<String, String>> memInfo, List<Map<String, String>> getCountInfo,
			String runlog) {
		super();
		this.taskName = taskName;
		this.memInfo = memInfo;
		this.getCountInfo = getCountInfo;
		this.runlog = runlog;
	}
	public SpiderRunInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public List<Map<String, String>> getMemInfo() {
		return memInfo;
	}
	public void setMemInfo(List<Map<String, String>> memInfo) {
		this.memInfo = memInfo;
	}
	public List<Map<String, String>> getGetCountInfo() {
		return getCountInfo;
	}
	public void setGetCountInfo(List<Map<String, String>> getCountInfo) {
		this.getCountInfo = getCountInfo;
	}
	public String getRunlog() {
		return runlog;
	}
	public void setRunlog(String runlog) {
		this.runlog = runlog;
	}
	@Override
	public String toString() {
		return "SpiderRunInfo [taskName=" + taskName + ", memInfo=" + memInfo + ", getCountInfo=" + getCountInfo
				+ ", runlog=" + runlog + "]";
	}
}
