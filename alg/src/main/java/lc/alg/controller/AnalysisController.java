/**
 * 
 */
package lc.alg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lc.alg.entity.NewsDocs;

/**
 * @author �
 *	���ŷ������չʾ����
 */
@Controller
public class AnalysisController {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@RequestMapping("/analysis_news")
	public String analysisNews(Model model,@RequestParam String user_name) {
		model.addAttribute("user_name",user_name);
		System.out.println("���ŷ�������......");
		return "analysis";
	}
}
