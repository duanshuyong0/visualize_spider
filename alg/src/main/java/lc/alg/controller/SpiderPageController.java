/**
 * 
 */
package lc.alg.controller;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import lc.alg.entity.SpiderConfigForm;
import lc.alg.entity.SpiderConfigInfo;
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
		mongoTemplate.save(spiderConfigInfo, "spider_config");
		return "new_spider";
	}
}
