/**
 * 
 */
package lc.alg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import lc.alg.entity.NewsDocs;

/**
 * @author �
 *
 */
@RestController
public class NewsPageRestController {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * ��ȡ��������
	 * @return
	 */
	@RequestMapping("/news_view/get_news")
	public String getNews() {
		System.out.println("�������ݻ�ȡ����");
		List<NewsDocs> newsDataList = mongoTemplate.findAll(NewsDocs.class);	
		System.out.println("��ȡ��������:"+newsDataList.size());
		Gson gson = new Gson();
		return gson.toJson(newsDataList);
	}
}
