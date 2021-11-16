package board.persistence;
import static board.persistence.JdbcUtil.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.domain.BoardDTO;
public class BoardDAO {
	private Connection con;
	public BoardDAO(Connection con) {
		this.con=con;
	}
	//글작성
	public boolean insert(BoardDTO boardDto) {
		PreparedStatement pstmt = null;
		boolean insertFlag=false;
		try {
			String sql="insert into board(bno, title, content, password, attach,name,re_ref,re_lev,re_seq)";
			sql+="values(board_seq.nextval,?,?,?,?,?,board_seq.currval,0,0)";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, boardDto.getTitle());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setString(3, boardDto.getPassword());
			pstmt.setString(4, boardDto.getAttach());
			pstmt.setString(5, boardDto.getName());
			int result = pstmt.executeUpdate();
			if(result>0) {
				insertFlag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertFlag;
	}
	//전체 목록
	public List<BoardDTO> getList(){
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql="select bno,title,name,regdate,readcount from board order by bno desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				
				boardList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}
	
	//게시글 읽기
	public BoardDTO getRow(int bno) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDTO dto = null;
		try {
			String sql="select bno,name,title,content,attach from board where bno=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setAttach(rs.getString("attach"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return dto;
	}
		//조회수 업데이트
		public boolean readCountUpdate(int bno) {
			PreparedStatement pstmt=null;
			boolean updateFlag = false;	
			try {
				String sql="update board set readcount = readcount+1 where bno=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1,bno);
				int result = pstmt.executeUpdate();
				if(result>0) {
					updateFlag=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return updateFlag;
		}
	
}
