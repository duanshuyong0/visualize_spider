/**
 * 
 */
package lc.alg.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lc.alg.entity.SpiderConfigInfo;

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
			spiderNameList.add(iter.next().getTaskName());
		}
		System.out.println("in get current spiders: �����������������" + spiderNameList);
		return new Gson().toJson(spiderNameList);
	}
}
