/**
 * 
 */
package lc.alg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lc.alg.entity.User;
import lc.alg.entity.UserInfoForm;


/**
 * @author �
 *
 */
@Controller
public class IndexPageController {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping(value="/")
	public String directIndexPage(Model model) {
		model.addAttribute("user",new User());
		return "index";
	}
	
	/***
	 * 
	 * @param User
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login")
	public String login2MainPage(@ModelAttribute User user,Model model) {
		System.out.println("��ñ����ݣ�" + user);
		Criteria criteria = new Criteria();
		criteria.and("name").is(user.getName());
		criteria.and("passwd").is(user.getPasswd());
		Query query = new Query(criteria);
		if(mongoTemplate.findOne(query, User.class) == null) {
			System.out.println("�û���Ϣ���ݿ���֤ʧ��!!");
			model.addAttribute("response","fail");
			return "index";
		}else {
			System.out.println("�û���Ϣ���ݿ���֤�ɹ�!!");
			model.addAttribute("response",new UserInfoForm(true,true,true,true,true,true,true));
			System.out.println("model : " + model.toString());
			return "user";
		}
	}
	
	/***
	 * ת���û���Ϣ�������
	 */
	@RequestMapping(value="/user")
	public String userInfoControl(@ModelAttribute User user,@ModelAttribute UserInfoForm userInfoForm,Model model) {
		System.out.println("ת���û���Ϣ�������....");
		return "user";
	}
	
	
//	/**
//	 * �û������¼��������ַ
//	 */
//	@RequestMapping(value="/login")
//	public String loginControl(@RequestBody String reqData,@ModelAttribute User user,Model model) {
//		System.out.println("controller ���յ����ݣ�" + reqData);
//		return "index";
//	}
}

