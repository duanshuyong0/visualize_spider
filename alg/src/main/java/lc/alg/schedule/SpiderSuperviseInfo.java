/**
 * 
 */
package lc.alg.schedule;

import java.util.List;
import java.util.Map;

/**
 * @author �
 *
 */
public class SpiderSuperviseInfo {

	private String spiderName;
	private boolean isSpiderStarted;
	private boolean isSpiderStoped;
	private List<Map<String,String>> memInfo;		//��¼ʱ����ڴ��ʹ�����
	private List<Map<String,String>> getCountInfo;	//��¼ʱ����ȡ���ŵ�����
	public SpiderSuperviseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpiderSuperviseInfo(String spiderName, boolean isSpiderStarted, boolean isSpiderStoped,
			List<Map<String, String>> memInfo, List<Map<String, String>> getCountInfo) {
		super();
		this.spiderName = spiderName;
		this.isSpiderStarted = isSpiderStarted;
		this.isSpiderStoped = isSpiderStoped;
		this.memInfo = memInfo;
		this.getCountInfo = getCountInfo;
	}
	public String getSpiderName() {
		return spiderName;
	}
	public void setSpiderName(String spiderName) {
		this.spiderName = spiderName;
	}
	public boolean isSpiderStarted() {
		return isSpiderStarted;
	}
	public void setSpiderStarted(boolean isSpiderStarted) {
		this.isSpiderStarted = isSpiderStarted;
	}
	public boolean isSpiderStoped() {
		return isSpiderStoped;
	}
	public void setSpiderStoped(boolean isSpiderStoped) {
		this.isSpiderStoped = isSpiderStoped;
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
	@Override
	public String toString() {
		return "SpiderSuperviseInfo [spiderName=" + spiderName + ", isSpiderStarted=" + isSpiderStarted
				+ ", isSpiderStoped=" + isSpiderStoped + ", memInfo=" + memInfo + ", getCountInfo=" + getCountInfo
				+ "]";
	}
	
	
}
