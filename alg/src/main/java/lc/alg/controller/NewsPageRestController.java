/**
 * 
 */
package lc.alg.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static Gson gson = new Gson();
	
	/**
	 * ��ȡ��������
	 * @return
	 * @throws JsonProcessingException 
	 */
	@RequestMapping("/news_view/get_news")
	public String getNews() throws JsonProcessingException {
		System.out.println("�������ݻ�ȡ����");
		List<NewsDocs> newsDataList = mongoTemplate.findAll(NewsDocs.class);	
		System.out.println("��ȡ��������:"+newsDataList.size());
		//����б���ÿ�����ŵ�����,�������ݴ���
		for(int i=0;i<newsDataList.size();++i) {
			newsDataList.get(i).setContent(null);
			newsDataList.get(i).setContent("");
		}
		System.out.println("�������ݷ�����....");
//		Gson gson = new Gson();
//		return gson.toJson(newsDataList);
		return objectMapper.writeValueAsString(newsDataList);
	}
	
	/**
	 * ������ַ��ȡ��Ӧ��������������
	 * @param urlString
	 * @return
	 */
	@RequestMapping("/news_view/get_news_content")
	public String getNewsContent(@RequestParam String urlString) {
		System.out.println("�����������ݻ�ȡ����.........");
		System.out.println("��ǰ�������ַΪ��"+urlString);
		Query query = new Query(Criteria.where("url").is(urlString));
		NewsDocs newsDocs = null;
		if ((newsDocs = mongoTemplate.findOne(query, NewsDocs.class)) != null) {
			return gson.toJson(newsDocs.getContent());
		} else {
			return gson.toJson("");
		}
	}
	
	@RequestMapping("/news_view/del_news")
	public String delNewsByUrl(@RequestParam String url) {
		System.out.println("����ɾ������, ���յ�url="+url);
		//�����ݿ���ɾ����������
		mongoTemplate.findAndRemove(new Query(Criteria.where("url").is(url)), NewsDocs.class);
		return gson.toJson("");
	}
	
	/**
	 * �����ݿ��е����ŵ������ļ�����
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/news_view/dump_files")
	public String dumpNews() throws IOException {
	//	Path path = Paths.get(System.getProperty("user.dir"),"src","main","resources","static","text");
		List<NewsDocs> newsList = mongoTemplate.findAll(NewsDocs.class);
		System.out.println("�ļ��������ƣ�������Ŀ:"+newsList.size());
		for(int i=0;i<newsList.size();++i) {
			File file = new File(Paths.get(System.getProperty("user.dir"),"src","main","resources","static","text",i+".txt").toString());
			file.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(newsList.get(i).getContent()); //ֻд����������
			writer.flush();
			writer.close();
		}
		System.out.println("�ļ��������ƣ������ı��������.....");
		//��ҳ�淵�ؽ��
		return new Gson().toJson("�ļ�����·��:"+Paths.get(System.getProperty("user.dir"),"src","main","resources","static","text").toString());
	}
	
	@RequestMapping("/news_view/download")
	public String downloadNews(HttpServletRequest servletRequest,HttpServletResponse response) throws IOException {
		System.out.println("�����ļ����ش���.........");
		response.setCharacterEncoding(servletRequest.getCharacterEncoding());
		response.setContentType("application/octet-stream");
		FileInputStream fileInputStream=null;
		File file=new File("G:\\file_test.txt");
		fileInputStream=new FileInputStream(file);
		response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
		response.addHeader("Set-Cookie", "fileDownload=true;path=/");
		IOUtils.copy(fileInputStream, response.getOutputStream());
		response.flushBuffer();
		fileInputStream.close();
		return "";
	}
}
