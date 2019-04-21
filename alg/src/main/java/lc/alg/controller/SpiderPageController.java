/**
 * 
 */
package lc.alg.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import lc.alg.entity.SpiderConfigForm;
import lc.alg.entity.SpiderConfigInfo;
import lc.alg.entity.SpiderMissingInfo;
import lc.alg.entity.User;

/**
 * @author �
 *
 */
@Controller
public class SpiderPageController {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping("/new_spider")
	public String createNewSpiderController(@ModelAttribute User user,Model model,HttpServletRequest request) {
		model.addAttribute("user_name",request.getParameter("user_name"));
		System.out.println("createnew spider control���յ�  model: " + model.toString());
		return "new_spider";
	}
	
	@RequestMapping("/start_spider")
	public String startNewSpider(Model model,@RequestParam String send_data) {
		System.out.println("start new spider ,��ȡ��post���� : " + send_data);
		Gson gson = new Gson();
		SpiderConfigForm spiderConfigForm = gson.fromJson(send_data, SpiderConfigForm.class);
		System.out.println("ת��������ݣ�" + spiderConfigForm);
		//�������������Ϣ��ӵ�mongodb��
		SpiderConfigInfo spiderConfigInfo = spiderConfigForm.getSpiderConfigInfo();
		spiderConfigInfo.setCurStatus("running");	// ���õ�ǰ״̬Ϊrunning
		mongoTemplate.save(spiderConfigInfo, "spider_config");
		//ɾ��missing_info �е����м�¼
		mongoTemplate.dropCollection(SpiderMissingInfo.class);
		return "new_spider";
	}
	
	@RequestMapping("/spider_supervise")
	public String superviseSpider(Model model,@RequestParam String user_name) {
		model.addAttribute("user_name",user_name);
		return "spider_supervise";
	}
	
	/**
	 * �޸����������
	 */
	@RequestMapping("/spider_change_cfg")
	public String changeConfig(Model model,@RequestParam String user_name,@RequestParam String taskName) {
		System.out.println("in spider change cfg control, user_name="+user_name+","
				+ "taskname="+taskName);
		model.addAttribute("user_name",user_name);
		model.addAttribute("taskName",taskName);
		//��ѯ��ǰ�����������Ϣ�����ݵ�ǰ�˵�ҳ��
		Query query = new Query(Criteria.where("taskName").is(taskName));
		SpiderConfigInfo spiderConfigInfo = mongoTemplate.findOne(query, SpiderConfigInfo.class);
		model.addAttribute("spider_config",spiderConfigInfo);
		return "spider_change_cfg";
	}
	
	@RequestMapping("/spider_change_cfg/cfg_help")
	public String configHelp(Model model,@RequestParam String user_name) {
		model.addAttribute("user_name",user_name);
		return "spider_cfg_help";
	}
	
}
