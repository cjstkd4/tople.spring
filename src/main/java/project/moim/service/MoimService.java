package project.moim.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import project.moim.dao.MoimUserDAO;
import project.moim.dto.Moim;
import project.moim.dto.MoimMem;
import project.moim.dto.MoimTname;

@Service
public class MoimService {
	final String base_path = "E:\\Android_3rd_jack\\android_project\\workspace\\moim.4t.spring\\src\\main\\webapp\\storage";
	private final String app_key = "54002ac0d722d2da02e369fe41470e75";

	private @Autowired MoimUserDAO dao;
	
	RestTemplate restTemplate;
	
	public MoimService() {
		restInit();
	}
	
	private void restInit() {
		HttpComponentsClientHttpRequestFactory factory 
			= new HttpComponentsClientHttpRequestFactory(); 
		
		factory.setReadTimeout(5000);
		factory.setConnectTimeout(3000);
		
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setMaxConnTotal(100);
		builder.setMaxConnPerRoute(5);
		
		HttpClient client = builder.build();
		
		factory.setHttpClient(client);
		
		restTemplate = new RestTemplate(factory);
	}
	
	// 단일 사용자 정보 요청
	public String selectUser(String id) {
		final String url = "https://kapi.kakao.com/v2/user/me";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.valueOf("application/x-www-form-urlencoded;charset=utf-8"));
		headers.set("Authorization", "KakaoAK " + app_key);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map.add("target_id_type", "user_id");
		map.add("target_id", id);
		
		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String,Object>>(map, headers);
		
		ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

		return response.getBody();
	}
	
	// 다중 사용자 정보 요청
	public String selectMoimUsersInfo(int moimcode) {
		List<MoimMem> members = dao.selectMoimMem(moimcode);
		
		JSONObject json = new JSONObject();
		
		List<JSONObject> users = new ArrayList<JSONObject>();
		
		for (MoimMem moimMem : members) {
			JSONObject user = new JSONObject(selectUser(moimMem.getId()));
			
			user.put("fav", moimMem.getFav());
			user.put("permit", moimMem.getPermit());
			
			users.add(user);
		}
		
		json.put("users", users);
		
		return json.toString();
	}
	
	// 사용자 정보 저장
	
	
	// 로그인시 모임리스트 요청
	public String seletctListFormId(String id, String url) {
		List<MoimMem> moimMemList = dao.selectUserMoimList(id);
		
		JSONObject json = new JSONObject();
		List<Moim> items = new ArrayList<Moim>();
		
		for (MoimMem moimMem : moimMemList) {
			String tname = dao.selectTname(moimMem.getMoimcode());
			Moim moim = dao.selectMoimNCount(tname);
			
			moim.setPic(url + moim.getPic());
			
			moim.setFav(moimMem.getFav());
			moim.setPermit(moimMem.getPermit());
			
			items.add(moim);
		}
		
		json.put("items", items);
		
		return json.toString();
	}
	

	// 모임 생성
	public String createMoim(Moim moim, String id) {
		String result = "";
		
		String exitLastTname = dao.selectLastTname();
		String temp = exitLastTname.substring(2);
		int serial = Integer.parseInt(temp);
		serial++;
		
		String tname = "zz" + serial;
		
		dao.createMoim(tname);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tname", tname);
		map.put("moim", moim);
		
		if (dao.insertMoim(map) > 0) {
			int moimcode = dao.selectMoimCode(tname);
			
			if (moimcode > 0) {
				MoimTname moimTname = new MoimTname();
				moimTname.setMoimcode(moimcode);
				moimTname.setTname(tname);
				
				if (dao.insertTname(moimTname) < 1) {
					result = "테이블명 + 모임코드 등록 실패";
				}
				
				MoimMem mem = new MoimMem();
				mem.setId(id);
				mem.setMoimcode(moimcode);
				mem.setPermit(moim.getPermit());
				mem.setFav(moim.getFav());
				
				if (dao.insertMem(mem) > 0) {
					result = "OK";
				} else {
					result = "모임장  모임멤버 등록 실패";
				}
			}		
		} else {
			result = "모임 정보 입력 실패";
		}
		
		JSONObject json = new JSONObject();
		
		json.put("result", result);
		
		return json.toString();
	}
	
	// 모임 정보 수정
	public String updateMoim(Moim moim) {
		String result = "OK";
		
		String tname = dao.selectTname(moim.getMoimcode());
		
		String fileName = dao.selectMoimPic(tname);
		String filePath = base_path + "/moimImg";
		
		File oldBanner = new File(filePath, fileName);
		if (oldBanner.exists()) {
			oldBanner.delete();
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tname", tname);
		map.put("moim", moim);
		
		if (dao.updateMoim(map) < 1) {
			result = "모임 정보 수정 실패";
		}
		
		JSONObject json = new JSONObject();
		json.put("result", result);
		
		return json.toString();
	}
	
	// 모임 리스트(정보 + 인원)
	public String selectMoimItems(String moim) {
		JSONObject object = new JSONObject(moim);
		JSONArray list = object.getJSONArray("list");
		
		JSONObject json = new JSONObject();
		List<Moim> items = new ArrayList<Moim>();
		
		for (int i = 0; i < list.length(); i++) {
			JSONObject temp = (JSONObject) list.get(i);
			int moimcode = temp.getInt("moimcode");
			
			String tname = dao.selectTname(moimcode);
			
			Moim moim2 = dao.selectMoimNCount(tname);
			
			items.add(moim2);
		}
		
		json.put("items", items);
		
		return json.toString();
	}
	
	// 특정 모임 정보 요청
	public String selectMoim(int moimcode, String url) {
		String tname = dao.selectTname(moimcode);
		
		Moim moim = dao.selectMoim(tname);
		moim.setPic(url + moim.getPic());
		
		JSONObject object = new JSONObject();
		
		ArrayList<Moim> list = new ArrayList<Moim>();
		list.add(moim);
		
		object.put("items", list);
		
		return object.toString();
	}
	
	
}
