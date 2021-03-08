package board.dto;

import lombok.Data;

// 롬복 라이브러리를 사용하여 기본적으로 필요한 getter/setter를 자동으로 생성함
@Data
public class BoardDto {
	private int boardIdx;
	private String title;
	private String contents;
	private int hitCnt;
	private String createdId;
	private String createdDatetime;
	private String updaterId;
	private String updatedDatetime;
	private String deletedYn;
}
