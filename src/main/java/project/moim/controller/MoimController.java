package project.moim.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import project.moim.dto.Moim;
import project.moim.service.MoimService;

@Controller
public class MoimController {
	final String base_path = "E:\\Android_3rd_jack\\android_project\\workspace\\moim.4t.spring\\src\\main\\webapp\\storage";
	
	private @Autowired MoimService service;

	/*	http://192.168.0.93:8080/moim.4t.spring/testID.tople
	 * 	파라미터로 사용자 id를 보낸다. (.tople?id=사용자id)
	 * 	결과 값은 JSON
	 * 	{
	 * 		"id":1146095951,
	 * 		"properties": {"nickname":"Skitter","test":"정보 입력 테스트"},
	 * 		"kakao_account":{"has_age_range":false,
	 * 			"age_range_needs_agreement":false,
	 * 			"has_birthday":false,
	 * 			"birthday_needs_agreement":false,
	 * 			"has_gender":false,
	 * 			"gender_needs_agreement":false
	 * 		}
	 * 	}
	 */
	// id로 사용자 정보 조회
	@ResponseBody
	@RequestMapping(value = "testID.tople", produces = "text/plain;charset=UTF-8")
	public String userInfo(HttpServletRequest request) {
		String id = request.getParameter("id");

		return service.selectUser(id);
	}

	
	// 사용자 정보 수정
	
	
	
	/*
	 * 	http://192.168.0.93:8080/moim.4t.spring/testMoimUsets.tople
	 * 	파라미터로 모임코드를 보낸다.
	 * 	결과값은 JSON
	 * 	{
	 *	    "users": [
	 *	        {
	 *	            "permit": 3,
	 *	            "fav": "true",
	 *	            "id": 1149515249,
	 *	            "kakao_account": {
	 *	                "age_range": "20~29",
	 *	                "birthday": "0210",
	 *	                "has_birthday": true,
	 *	                "gender_needs_agreement": false,
	 *	                "gender": "male",
	 *	                "age_range_needs_agreement": false,
	 *	                "birthday_needs_agreement": false,
	 *	                "has_gender": true,
	 *	                "has_age_range": true
	 *	            },
	 *	            "properties": {
	 *	                "profile_image": "http://k.kakaocdn.net/dn/busyvS/btqxxKrRbgK/Tg81qEk2ALYuKdk19DgK9k/profile_640x640s.jpg",
	 *	                "nickname": "송승재",
	 *	                "thumbnail_image": "http://k.kakaocdn.net/dn/busyvS/btqxxKrRbgK/Tg81qEk2ALYuKdk19DgK9k/profile_110x110c.jpg"
	 *	            }
	 *	        },
	 *	        {
	 *	            "permit": 3,
	 *	            "fav": "true",
	 *	            "id": 1150321350,
	 *	            "kakao_account": {
	 *	                "age_range": "20~29",
	 *	                "birthday": "0210",
	 *	                "has_birthday": true,
	 *	                "gender_needs_agreement": false,
	 *	                "gender": "male",
	 *	                "age_range_needs_agreement": false,
	 *	                "birthday_needs_agreement": false,
	 *	                "has_gender": true,
	 *	                "has_age_range": true
	 *	            },
	 *	            "properties": {
	 *	                "profile_image": "http://k.kakaocdn.net/dn/bBqypm/btqxEhQsa5t/UB6FvCFLhSiQbI2jqtJzL0/profile_640x640s.jpg",
	 *	                "nickname": "승재",
	 *	                "region": "서울",
	 *	                "thumbnail_image": "http://k.kakaocdn.net/dn/bBqypm/btqxEhQsa5t/UB6FvCFLhSiQbI2jqtJzL0/profile_110x110c.jpg"
	 *	            }
	 *	        }
	 *		]
	 *	}	
	 */
	// 모임코드로 사용자 정보 리스트 조회
	@ResponseBody
	@RequestMapping(value = "testMoimUsets.tople", produces = "text/plain;charset=UTF-8")
	public String selectMoimUserList(HttpServletRequest request) {
		int moimcode = Integer.parseInt(request.getParameter("moimcode"));
		
		String result = service.selectMoimUsersInfo(moimcode);
		
		return result;
	}
	
	/*
	 * 	http://192.168.0.93:8080/moim.4t.spring/testCreateMoim.tople
	 * 	post 방식으로 모임생성 사용자ID, 모임정보를 보낸다.
	 * 	
	 * 	결과값은 {"result":"OK"}
	 */
	// 모임 생성
	@ResponseBody
	@RequestMapping(value = "testCreateMoim.tople", produces = "text/plain;charset=UTF-8")
	public String createMoim(MultipartHttpServletRequest request) {
		String id = request.getParameter("id");
		String loca = request.getParameter("loca");
		String moimname = request.getParameter("moimname");
		String prod = request.getParameter("prod");
		String color = request.getParameter("color");
		String fav = "true";
		int permit = 1;
		String pic = null;
		
		if (request.getFile("pic") != null) {
			MultipartFile file = request.getFile("pic");
			pic = file.getOriginalFilename();
			
			String picpath = base_path + "/moimImg";
			
			File banner = new File(picpath, pic);
			
			try {
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(banner));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Moim moim = new Moim();
		moim.setColor(color);
		moim.setFav(fav);
		moim.setLoca(loca);
		moim.setMoimname(moimname);
		moim.setPermit(permit);
		moim.setProd(prod);
		moim.setPic(pic);
		
		String result = service.createMoim(moim, id);
		
		return result;
	}
	
	
	/*
	 * 	http://192.168.0.93:8080/moim.4t.spring/testUpdateMoim.tople
	 * 	모임정보를 보낸다.
	 * 	
	 * 	결과값은 {"result":"OK"}
	 */
	// 모임 정보 수정
	@ResponseBody
	@RequestMapping(value = "testUpdateMoim.tople", produces = "text/plain;charset=UTF-8")
	public String updateMoim(MultipartHttpServletRequest request) {
		int moimcode = Integer.parseInt(request.getParameter("moimcode"));
		String loca = request.getParameter("loca");
		String moimname = request.getParameter("moimname");
		String prod = request.getParameter("prod");
		String color = request.getParameter("color");
		String pic = null;
		
		if (request.getFile("pic") != null) {
			MultipartFile file = request.getFile("pic");
			pic = file.getOriginalFilename();
			
			String picpath = base_path + "/moimImg";
			
			File banner = new File(picpath, pic);
			
			try {
				FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(banner));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		Moim moim = new Moim();
		moim.setMoimcode(moimcode);
		moim.setColor(color);
		moim.setLoca(loca);
		moim.setMoimname(moimname);
		moim.setProd(prod);
		moim.setPic(pic);
		
		String result = service.updateMoim(moim);
		
		return result;
	}
	
	
	/*	http://192.168.0.93:8080/moim.4t.spring/testMoimItems.tople
	 * 	파라미터는 사용자ID
	 * 	결과 값은 JSON - 모임 가입 인원수를 포함한다.
	 * 	{
	 * 		"items":[
	 * 			{
	 * 				"prod":"정동진 해돋이 보면서 고기 먹자",
	 * 				"loca":"강원도",
	 * 				"color":"#333",
	 * 				"moimname":"정동진 해돋이",
	 * 				"count":1,
	 * 				"permit": 3,
	 * 				"fav": "true",
	 * 				"pic":"http://192.168.0.93:8080//moim.4t.spring/storage/moimImg/banner.jpg",
	 * 				"moimcode":1
	 * 			},
	 * 			{
	 * 				"prod":"정동진 해돋이 보면서 고기 먹자",
	 * 				"loca":"강원도",
	 * 				"color":"#333",
	 * 				"moimname":"정동진 해돋이",
	 * 				"count":0,
	 * 				"permit": 3,
	 * 				"fav": "true",
	 * 				"pic":"http://192.168.0.93:8080//moim.4t.spring/storage/moimImg/banner.jpg",
	 * 				"moimcode":2
	 * 			},
	 * 			{
	 * 				"prod":"정동진 해돋이 보면서 고기 먹자",
	 * 				"loca":"강원도",
	 * 				"color":"#333",
	 * 				"moimname":"정동진 해돋이",
	 * 				"count":0,
	 * 				"permit": 3,
	 * 				"fav": "true",
	 * 				"pic":"http://192.168.0.93:8080//moim.4t.spring/storage/moimImg/banner.jpg",
	 * 				"moimcode":3
	 * 			}
	 * 		]
	 * 	}
	 */
	// 사용자 ID로 가입된 모임 리스트 얻어오기
	@ResponseBody
	@RequestMapping(value = "testMoimItems.tople", produces = "text/plain;charset=UTF-8")
	public String moimItems(HttpServletRequest request) {
		String id = request.getParameter("id");
		String url = "http://" + request.getServerName() + ":" 
					 + request.getServerPort() + "/" +  request.getContextPath()
					 + "/storage/moimImg/";
		
		return service.seletctListFormId(id, url);
	}

	
	/*	http://192.168.0.93:8080/moim.4t.spring/testMoim.tople
	 * 	파라미터는 moimcode
	 * 	결과 값은 JSON - 모임 가입 인원수는 포함하지 않는다.
	 *	{
	 *	    "items": [
	 *	        {
	 *	            "loca": "경기도",
	 *	            "prod": "활빈당 찾자",
	 *	            "color": "kaki",
	 *	            "moimname": "홍길동 모임",
	 *	            "count": 0,
	 *	            "permit": 0,
	 *	            "pic": "http://192.168.0.93:8080//moim.4t.spring/storage/moimImg/treethum.png",
	 *	            "moimcode": 7
	 *	        }
	 *	    ]
	 *	}
	 * 
	 */
	// 특정 모임 정보 조회
	@ResponseBody
	@RequestMapping(value = "testMoim.tople", produces = "text/plain;charset=UTF-8")
	public String moimInfo(HttpServletRequest request) {
		int moimcode = Integer.parseInt(request.getParameter("moimcode"));
		String url = "http://" + request.getServerName() + ":" 
				 + request.getServerPort() + "/" +  request.getContextPath()
				 + "/storage/moimImg/";

		return service.selectMoim(moimcode, url);
	}

	
	
	// 사진 URL 테스트
	@ResponseBody
	@RequestMapping(value = "test.tople", produces = "text/plain;charset=UTF-8")
	public String test(HttpServletRequest request) {
		String url = "테스트중";
		
		url = "http://" + request.getServerName() + ":" 
				+ request.getServerPort() + "/" +  request.getContextPath()
				+ "/storage/moimImg/";
		
		System.out.println(url);
		
		return url + "treethum.png";
	}
	
	/**	<<테스트>>
	 * http://192.168.0.93:8080/moim.4t.spring/test.tople post 방식으로 카카오 accesstoken을
	 * 포함하여 호출하면 REST API 방식으로 카카오서버로부터 사용자 정보를 조회한다.
	 * 
	 * @RequestMapping(value="test.tople")
	 * 
	 * @ResponseBody public String userInfo(HttpServletRequest request) { String url
	 * = "https://kapi.kakao.com/v2/user/me"; String token =
	 * request.getParameter("access_token");
	 * 
	 * System.out.println(token);
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	 * headers.set("Authorization", "Bearer " + token);
	 * 
	 * HttpEntity<String> entity = new HttpEntity<String>("Headers", headers);
	 * 
	 * System.out.println(entity.toString());
	 * 
	 * ResponseEntity<String> response = restTemplate.postForEntity(url, entity,
	 * String.class);
	 * 
	 * System.out.println(response.getBody());
	 * 
	 * return response.getBody(); }
	 **/
}
