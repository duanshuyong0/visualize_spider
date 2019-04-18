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
	private String type; // �ڴ棺 mem, ÿСʱץȡ������������count
	private List<Map<String,String>> info; // <ʱ�䣬�ڴ�ʹ��> ��<ʱ�䣬 ÿСʱץȡ����������>
	public SpiderRunInfo(String type, List<Map<String, String>> info) {
		super();
		this.type = type;
		this.info = info;
	}
	public SpiderRunInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Map<String, String>> getInfo() {
		return info;
	}
	public void setInfo(List<Map<String, String>> info) {
		this.info = info;
	}
	@Override
	public String toString() {
		return "SpiderRunInfo [type=" + type + ", info=" + info + "]";
	}
	
	
	
}
