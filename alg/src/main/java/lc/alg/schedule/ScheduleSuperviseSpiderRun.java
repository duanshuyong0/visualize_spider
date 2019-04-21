/**
 * 
 */
package lc.alg.schedule;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author �
 *	��ʱ���񣬹̶�ʱ������ѯ������������������浽MongoDB��
 */
public class ScheduleSuperviseSpiderRun extends QuartzJobBean{
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 *  java ִ��ϵͳ����
	 * @param cmd
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void runShellScript(String[] cmds) throws IOException, InterruptedException {
		Process pro = Runtime.getRuntime().exec(cmds);
		pro.waitFor();
		InputStream in = pro.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in));
		String line = null;
		StringBuffer messages = new StringBuffer();
		while((line = read.readLine()) != null) {
			//System.out.println(new String(line.getBytes("GBK"),"UTF-8"));
			line = new String(line.getBytes("GBK"),"UTF-8");
			System.out.println(line);
			messages.append(line);
		}
		System.out.println("**********ALL MESSAGE OUTPUT FINISH************");
	}
	

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("��ʱ����.................");
		Path path = Paths.get(System.getProperty("user.dir"),"src","main","resources","static","spider_selfdef","autorun.bat");
		String pathStr = path.toString();
		String[] cmds = {pathStr};
		try {
			runShellScript(cmds);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Date date = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//		String dateStr = dateFormat.format(date);
//		String memUsageStr = Double.toString((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())*1.0/Runtime.getRuntime().totalMemory());
//		//��ѯ���ݿ⣬��ȡ��ǰ����ץȡ�����ŵ�����
//		List<NewsDocs> list = mongoTemplate.findAll(NewsDocs.class);
//		int getNewsCount = list.size();
//		//��ѯ��ǰΪrunning״̬������
//		Query query = new Query(Criteria.where("curStatus").is("running"));
//		SpiderConfigInfo spiderConfigInfo = mongoTemplate.findOne(query, SpiderConfigInfo.class);
//		if(spiderConfigInfo != null) {
//			//����memInfo getCountInfo
//			String taskName = spiderConfigInfo.getTaskName();
//			query = new Query(Criteria.where("taskName").is(taskName));
//			SpiderRunInfo spiderRunInfo = mongoTemplate.findOne(query, SpiderRunInfo.class);
//			Map<String,String> timeMemUse = new HashMap<>();
//			timeMemUse.put(dateStr, memUsageStr);
//			spiderRunInfo.getMemInfo().add(timeMemUse);	//��ӵ�ǰʱ�����ڴ�ռ����Ϣ
//			Map<String,String> timeNewsGetInfo = new HashMap<>();
//			int getInfoListLen = spiderRunInfo.getGetCountInfo().size();
//			if(getInfoListLen > 0) {
//				Iterator<Map.Entry<String,String>> entries = spiderRunInfo.getGetCountInfo().get(getInfoListLen-1).entrySet().iterator();
//				int preNewsGetCount=0;
//				while(entries.hasNext()) {
//					preNewsGetCount = Integer.parseInt(entries.next().getValue());
//				}
//				getNewsCount -= preNewsGetCount;
//			}
//			timeNewsGetInfo.put(dateStr, Integer.toString(getNewsCount));
//			spiderRunInfo.getGetCountInfo().add(timeNewsGetInfo);
//			//����memInfo �Լ�getCountInfo �����ݿ���
//			query = new Query(Criteria.where("taskName").is(taskName));
//			Update update = new Update().set("memInfo", spiderRunInfo.getMemInfo());
//			mongoTemplate.updateFirst(query, update, SpiderRunInfo.class);
//			update = new Update().set("getCountInfo", spiderRunInfo.getGetCountInfo());
//			mongoTemplate.updateFirst(query, update, SpiderRunInfo.class);
//		}
	}
}
