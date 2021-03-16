package board.service;

import java.util.Iterator;
import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.common.FileUtil;
import board.dto.BoardDto;
import board.dto.BoardFileDto;
import board.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
//	private Logger log = LoggerFactory.getLogger(this.getClass());
	
   @Autowired
   private BoardMapper boardMapper;
   
   @Autowired
   private FileUtil fileUtil;
   
   @Override
   public List<BoardDto> selectBoardList() throws Exception {
      return boardMapper.selectBoardList();
   }
   
   @Override
   public BoardDto selectBoardDetail(int boardIdx) throws Exception {
//	   게시글 상태보기 클릭 시 View Count 올림
	   boardMapper.updateHitCount(boardIdx);
	   
//	   지정한 게시물의 상세 정보 가져오기
//	   게시물의 상세 정보를 가져오는 시점에는 첨부된 파일에 대한 정보가 없음
//	   fileList 멤버 변수의 값은 null임
	   BoardDto board = boardMapper.selectBoardDetail(boardIdx);

//	   지정한 게시물에 첨부될 파일 리스트 가져오기
	   List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
//	   가져온 첨부 파일 리스트를 기존의 게시물 정보에 추가하기
	   board.setFileList(fileList);
	   
	   return board;
   }
   
   @Override
   public void insertBoard(BoardDto board, MultipartHttpServletRequest uploadFiles) throws Exception {
	   log.debug("------BoardService의 insert 실행 이전----------");
	   log.debug("출력값" + Integer.toString(board.getBoardIdx()) );
	   //	  기존의 게시물 등록
	   boardMapper.insertBoard(board);
	   
	   log.debug("------BoardService의 insert 실행 이후----------");
	   log.debug("출력값" + Integer.toString(board.getBoardIdx()) );
//	   fileUtil 클래스를 통해서 생성한 파일 정보 받아오기 (서버에 파일 저장 기능 포함)
	   List<BoardFileDto> fileList = fileUtil.parseFileInfo(board.getBoardIdx(), uploadFiles);

//	   데이터 베이스에 업로드된 파일 정보 저장
//	   CollectionUtils 클래스는 스프링 프레임워크에서 지원하는 유틸 중 하나임, 여기서는 fileList.isEmpty()를 사용해도 상관 없음 
	   if (CollectionUtils.isEmpty(fileList) == false) {
		   boardMapper.insertBoardFileList(fileList);
	   }
	   

   }
   
   @Override
   public void updateBoard(BoardDto board) throws Exception {
	   boardMapper.updateBoard(board);
   }
   
   @Override
   public void deleteBoard(int boardIdx) throws Exception{
	   boardMapper.deleteBoard(boardIdx);
   }
}