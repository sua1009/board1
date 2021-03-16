package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.dto.BoardDto;
import board.dto.BoardFileDto;

// DAO 로 동작함
@Mapper
public interface BoardMapper {
//	게시물 목록 불러오기
   List<BoardDto> selectBoardList() throws Exception;
//  지정한 게시물 전체 내용 불러오기
   BoardDto selectBoardDetail(int boardIdx) throws Exception;
//   지정한 게시물의 hit Count 올리기
   void updateHitCount (int boardIdx) throws Exception;
//   게시글 작성하기
   void insertBoard (BoardDto board) throws Exception;
   
//   게시글에 포함된 파일 정보 작성하기
   void insertBoardFileList(List<BoardFileDto> list) throws Exception;
   
   List<BoardFileDto> selectBoardFileList (int boardIdx) throws Exception;
   
//   게시글 수정하기 
   void updateBoard (BoardDto board) throws Exception;
//   게시글 삭제하기
   void deleteBoard (int boardIdx) throws Exception;
}