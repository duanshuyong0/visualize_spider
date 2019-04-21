/**
 * 
 */
package lc.alg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lc.alg.entity.SpiderConfigInfo;
import lc.alg.entity.SpiderMissingInfo;
import lc.alg.entity.SpiderRunInfo;

/**
 * @author �
 *	���ڴ���spider ���á������ؽ����ajax ��������
 */
@RestController
public class SpiderPageRestController {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * ��ȡ��ǰ�������е���������
	 * @param model
	 * @return
	 */
	@RequestMapping("/spider_supervise/get_curr_spiders")
	public String getCurrSpiders(Model model) {
		List<String> spiderNameList = new ArrayList<>();
		List<SpiderConfigInfo> spiderInfos = mongoTemplate.findAll(SpiderConfigInfo.class);
		System.out.println("in get current spiders: ��ѯ���е��������ݣ�" + spiderInfos);
		Iterator<SpiderConfigInfo> iter = spiderInfos.iterator();
		while(iter.hasNext()) {
			SpiderConfigInfo configInfo = iter.next();
			spiderNameList.add(configInfo.getTaskName());
			spiderNameList.add(configInfo.getCurStatus());
		}
		System.out.println("in get current spiders: ����������������Լ�״̬��" + spiderNameList);
		return new Gson().toJson(spiderNameList);
	}
	
	@RequestMapping("/spider_supervise/get_spider_runinfo")
	public String getRunInfo(Model model, @RequestParam String taskName) {
		Gson gson = new Gson();
		taskName = gson.fromJson(taskName, String.class);
		taskName = taskName.split(":")[1].trim();
		System.out.println("in getruninfo: ��ǰ��ѯ����������Ϊ��" + taskName);
		Query query = new Query(Criteria.where("taskName").is(taskName));
		SpiderRunInfo spiderRunInfo = mongoTemplate.findOne(query, SpiderRunInfo.class);
		return new Gson().toJson(spiderRunInfo);
	}
	
	@RequestMapping("/spider_change_cfg/get_spiderinfo_by_taskName")
	public String getSpiderInfoByTaskname(@RequestParam String taskName) {
		System.out.println("in getSpiderInfoByTaskname, taskName="+taskName);
		//�����ݿ��ѯ���������������attributeParser����Ϣ��������json��ʽ����
		Query query = new Query(Criteria.where("taskName").is(taskName));
		SpiderConfigInfo spiderConfigInfo = mongoTemplate.findOne(query, SpiderConfigInfo.class);
		Gson gson = new Gson();
		return gson.toJson(spiderConfigInfo);
	}
	
	/**
	 * 
	 * @param config: json �ַ�����ʽ�Ĳ���
	 * @return
	 */
	@RequestMapping("/spider_change_cfg/update_cfg")
	public String updateConfig(@RequestParam String config,@RequestParam String taskName) {
		Gson gson = new Gson();
		String taskname = gson.fromJson(taskName, String.class);
		List<Map<String,String>> attributeParser = new ArrayList<Map<String,String>>();
		ArrayList<String> getConfigList = gson.fromJson(config, ArrayList.class);
		System.out.println("taskname="+taskname+", configlist="+getConfigList);
		for(int i=0;i*2<getConfigList.size();i++) {
			Map<String,String> mp = new HashMap<String,String>();
			mp.put(getConfigList.get(2*i), getConfigList.get(2*i+1));
			attributeParser.add(mp);
		}
		//�����ø��µ����ݿ���
		Query query = new Query(Criteria.where("taskName").is(taskname));
		Update update = new Update().set("attributeParser",attributeParser);
		mongoTemplate.updateFirst(query, update, SpiderConfigInfo.class);
		return "";
	}
	
	@RequestMapping("/spider_supervise/get_missinginfo")
	public String getMissingInfos() {
		List<SpiderMissingInfo> missingInfoList = mongoTemplate.findAll(SpiderMissingInfo.class);
		return new Gson().toJson(missingInfoList);
	}
}
