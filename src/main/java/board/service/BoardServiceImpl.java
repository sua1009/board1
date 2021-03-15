package board.service;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardDto;
import board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
   @Autowired
   private BoardMapper boardMapper;
   
   @Override
   public List<BoardDto> selectBoardList() throws Exception {
      return boardMapper.selectBoardList();
   }
   
   @Override
   public BoardDto selectBoardDetail(int boardIdx) throws Exception {
	   boardMapper.updateHitCount(boardIdx);
	   
	   BoardDto board = boardMapper.selectBoardDetail(boardIdx);
	   return board;
   }
   
   @Override
   public void insertBoard(BoardDto board, MultipartHttpServletRequest files) throws Exception {
//	   boardMapper.insertBoard(board);
	   
	   if (ObjectUtils.isEmpty(files) == false) {
		   Iterator<String> iterator = files.getFileNames();
		   String fileName;
		   
		   while(iterator.hasNext()) {
			   fileName = iterator.next();
			   List<MultipartFile> filsList = files.getFiles(fileName);
			   
			   for (MultipartFile file : filsList) {
				   log.debug("==== start file infomation ====");
				   log.debug("file name : " + file.getOriginalFilename());
				   
				   log.debug("file name: " + file.getSize());
	   				log.debug("file content type	: " + file.getContentType());
				   log.debug("==== end file information.=====\n");
				   
			   }
		   }
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