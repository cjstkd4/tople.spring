package project.moim.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.moim.dto.BoardList;
import project.moim.dto.Moim;
import project.moim.dto.MoimMem;
import project.moim.dto.MoimTname;
import project.moim.dto.MoimUser;
import project.moim.dto.Money;
import project.moim.dto.ReDat;
import project.moim.dto.Schedule;
import project.moim.dto.ToDo;

@Repository
public class MoimUserDAO {
	private @Autowired SqlSessionTemplate sqlSession;
	private final String NSPACE = "spring.4t.moim.";
	
/* 사용자 정보 */
	// 사용자 정보 입력
	public int insertUser(MoimUser moimUser) {
		return sqlSession.insert(NSPACE + "insertUser", moimUser);
	}
	
	// 사용자 정보 수정
	public int updateUser(MoimUser moimUser) {
		return sqlSession.update(NSPACE + "updateUser", moimUser);
	}
	
	// 회원탈퇴
	public int deleteUser(String id) {
		return sqlSession.delete(NSPACE + "deleteUser", id);
	}
	
	// 사용자 정보 조회
	public MoimUser selectUser(String id) {
		return sqlSession.selectOne(NSPACE + "selectUser", id);
	}
	
/* 모임 멤버 */
	// 입력
	public int insertMem(MoimMem moimMem) {
		return sqlSession.insert(NSPACE + "insertMem", moimMem);
	}
	
	// 수정
	public int updateMem(MoimMem moimMem) {
		return sqlSession.insert(NSPACE + "updateMem", moimMem);
	}
	
	// 아이디 탈퇴
	public int deleteUserMem(String id) {
		return sqlSession.insert(NSPACE + "deleteUserMem", id);
	}
	
	// 모임 탈퇴
	public int deleteMem(MoimMem moimMem) {
		return sqlSession.insert(NSPACE + "deleteMem", moimMem);
	}
	
	// 모임 내 사용자 id 리스트 조회
	public List<MoimMem> selectMoimMem(int moimcode) {
		return sqlSession.selectList(NSPACE + "selectMoimMem", moimcode);
	}
	
	// 사용자가 가입한 모임 리스트 조회
	public List<MoimMem> selectUserMoimList(String id) {
		return sqlSession.selectList(NSPACE + "selectUserMoimList", id);
	}
	
	
/* 게시판 */	
	// 게시판 등록
	public int insertBoard(BoardList boardList) {
		return sqlSession.insert(NSPACE + "insertBoard", boardList);
	}
	
	// 글 수정
	public int updateBoard(BoardList boardList) {
		return sqlSession.update(NSPACE + "updateBoard", boardList);
	}
	
	// 글 삭제
		// 특정 글 삭제
	public int deleteBoard(int listnum) {
		return sqlSession.delete(NSPACE + "deleteBoard", listnum);
	}
	
		// 모임 글 전부 삭제
	public int deleteBoardList(int moimcode) {
		return sqlSession.delete(NSPACE + "deleteBoardList", moimcode);
	}
	
	// 글 조회
		// 글 분류에 따른 모임 내의 글 전체 조회
	public List<BoardList> selectBoardList(Map<String, Integer> map) {
		return sqlSession.selectList(NSPACE + "selectBoardList", map);
	}
	
		// 모임 내 필독 글 조회
	public List<BoardList> selectBoardFeel(int moimcode) {
		return sqlSession.selectList(NSPACE + "selectBoardFeel", moimcode);
	}
	
		// 모임 내 일반 게시글 조회
	public List<BoardList> selectBoardNomal(int moimcode) {
		return sqlSession.selectList(NSPACE + "selectBoardNomal", moimcode);
	}
	
		// 특정 글 상세 조회
	public BoardList selectBoard(int listnum) {
		return sqlSession.selectOne(NSPACE + "selectBoard", listnum);
	}
	
	
/* 덧 글 */	
	// 덧글 작성
	public int insertReDat(ReDat reDat) {
		return sqlSession.insert(NSPACE + "insertReDat", reDat);
	}
	
	// 덧글 수정
	public int updateReDat(ReDat reDat) {
		return sqlSession.update(NSPACE + "updateReDat", reDat);
	}
	
	// 덧글 삭제
		// 원글 삭제에 따른 덧글 삭제
	public int deleteBoardReDat(int listnum) {
		return sqlSession.delete(NSPACE + "deleteBoardReDat", listnum);
	}
	
		// 모임 삭제에 따른 덧글 삭제
	public int deleteMoimRedat(int moimcode) {
		return sqlSession.delete(NSPACE + "deleteMoimRedat", moimcode);
	}
	
		// 특정 덧글 삭제
	public int deleteRedat(int renum) {
		return sqlSession.delete(NSPACE + "deleteMoimRedat", renum);
	}
	
	// 덧글 조회
		// 특정 덧글 조회
	public ReDat selectRedat(int renum) {
		return sqlSession.selectOne(NSPACE + "selectRedat", renum);
	}
	
		// 원글에 따른 덧글 리스트 조회
	public List<ReDat> selectReDatList(int listnum) {
		return sqlSession.selectList(NSPACE + "selectReDatList", listnum);
	}
	
	
/* 일정 */	
	// 일정 입력
	public int insertSchedule(Schedule schedule) {
		return sqlSession.insert(NSPACE + "insertSchedule", schedule);
	}
	
	// 일정 수정
	public int updateSchedule(Schedule schedule) {
		return sqlSession.update(NSPACE + "updateSchedule", schedule);
	}
	
	// 일정 삭제
		// 모임 일정 전체 삭제
	public int deleteMoimSchedule(int moimcode) {
		return sqlSession.delete(NSPACE + "deleteMoimSchedule", moimcode);
	}
	
		// 단일 일정 삭제
	public int deleteSchedule(int schnum) {
		return sqlSession.delete(NSPACE + "deleteSchedule", schnum);
	}
	
	// 일정 조회
		// 모임 내 전체 일정 조회
	public List<Schedule> selectMoimSchedule(int moimcode) {
		return sqlSession.selectList(NSPACE + "selectMoimSchedule", moimcode);
	}
	
		// 월간 일정 조회
	public List<Schedule> selectScheduleMonth(Map<String, Integer> map) {
		return sqlSession.selectList(NSPACE + "selectScheduleMonth", map);
	}
	
		// 특정 일정 조회
	public Schedule selectSchedule(int schnum) {
		return sqlSession.selectOne(NSPACE + "selectSchedule", schnum);
	}
	
	
/* 할 일 */	
	// 할 일 입력
	public int insertToDo(ToDo toDo) {
		return sqlSession.insert(NSPACE + "insertToDo", toDo);
	}
	
	// 할 일 수정
	public int updateToDo(ToDo toDo) {
		return sqlSession.update(NSPACE + "updateToDo", toDo);
	}
	
	// 할 일 삭제
		// 모임 삭제에 따른 할 일 삭제
	public int deleteMoimToDo(int moimcode) {
		return sqlSession.delete(NSPACE + "deleteMoimToDo", moimcode);
	}
	
		// 일정 삭제에 따른 할 일 삭제
	public int deleteScheduleToDo(int schnum) {
		return sqlSession.delete(NSPACE + "deleteScheduleToDo", schnum);
	}
	
		// id에 부과된 할 일 삭제
	public int deleteUserToDo(String id) {
		return sqlSession.delete(NSPACE + "deleteUserToDo", id);
	}
	
	// 할 일 조회
		// 일정에 해당하는 할 일 조회
	public List<ToDo> selectScheduleToDo(int schnum) {
		return sqlSession.selectList(NSPACE + "selectScheduleToDo", schnum);
	}
	
		// 사용자 id에 따른 일정 별 할 일 조회
	public List<ToDo> selectUserToDo(Map<String, Object> map) {
		return sqlSession.selectList(NSPACE + "selectUserToDo", map);
	}
	
	
/* 회비 */	
	// 회비 입력
	public int insertMoney(Money money) {
		return sqlSession.insert(NSPACE + "insertMoney", money);
	}
	
	// 회비 수정
	public int updateMoney(Money money) {
		return sqlSession.update(NSPACE + "updateMoney", money);
	}
	
	// 회비 삭제
		// 모임 삭제에 따른 회비 삭제
	public int deleteMoimMoney(int moimcode) {
		return sqlSession.delete(NSPACE + "deleteMoimMoney", moimcode);
	}
	
		// 일정 삭제에 따른 회비 삭제
	public int deleteScheduleMoney(int schnum) {
		return sqlSession.delete(NSPACE + "deleteScheduleMoney", schnum);
	}
	
		// id에 부과된 회비 삭제
	public int deleteUserMoney(String id) {
		return sqlSession.delete(NSPACE + "deleteUserMoney", id);
	}
	
	// 회비 조회
		// 일정에 해당하는 회비 조회
	public List<Money> selectScheduleMoney(int schnum) {
		return sqlSession.selectList(NSPACE + "selectScheduleMoney", schnum);
	}
	
		// 사용자 id에 따른 해당 일정 회비 조회
	public Money selectUserMoney(String id) {
		return sqlSession.selectOne(NSPACE + "selectUserMoney", id);
	}
	
	
/* 마지막 모임 테이블명 조회 */	
	public String selectLastTname() {
		return sqlSession.selectOne(NSPACE + "selectLastTname");
	}
	
	
/* 모임 이름 테이블 */	
	// 입력
	public int insertTname(MoimTname moimTname) {
		return sqlSession.insert(NSPACE + "insertTname", moimTname);
	}
	
	// 삭제
	public int deleteTname(int moimcode) {
		return sqlSession.delete(NSPACE + "deleteTname", moimcode);
	}
	
	// 모임 테이블명 조회
	public String selectTname(int moimcode) {
		return sqlSession.selectOne(NSPACE + "selectTname", moimcode);
	}
	
	
/* 모임 테이블 */
	// 테이블 생성
	public void createMoim(String tname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tname", tname);
		sqlSession.insert(NSPACE + "createMoim", map);
	}
	
	// 테이블 삭제
	public void dropMoim(String tname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tname", tname);
		sqlSession.delete(NSPACE + "dropMoim", map);
	}
	
	// 테이블 존재 확인
	public int selectTnameCount(String tname) {
		return sqlSession.selectOne(NSPACE + "selectTnameCount", tname);
	}
	
	
/* 모임 */	
	// 모임 정보 입력
	public int insertMoim(Map<String, Object> map) {
		return sqlSession.insert(NSPACE + "insertMoim", map);
	}
	
	// 모임 정보 수정
	public int updateMoim(Map<String, Object> map) {
		return sqlSession.insert(NSPACE + "updateMoim", map);
	}
	
	// 모임코드 조회
	public int selectMoimCode(String tname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tname", tname);
		return sqlSession.selectOne(NSPACE + "selectMoimCode", map);
	}
	
	// 모임 배너 파일명 조회
	public String selectMoimPic(String tname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tname", tname);
		return sqlSession.selectOne(NSPACE + "selectMoimPic", map);
	}
	
	// 모임 정보 조회
	public Moim selectMoim(String tname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tname", tname);
		return sqlSession.selectOne(NSPACE + "selectMoim", map);
	}
	
	// 모임 정보 및 가입 인원 수
	public Moim selectMoimNCount(String tname) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("tname", tname);
		return sqlSession.selectOne(NSPACE + "selectMoimNCount", map);
	}
}
