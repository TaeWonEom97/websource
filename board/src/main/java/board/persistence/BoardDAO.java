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
			String sql="select bno,title,name,regdate,readcount,re_lev from board order by re_ref desc,re_seq asc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
				dto.setName(rs.getString("name"));
				dto.setRegdate(rs.getDate("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRe_lev(rs.getInt("re_lev"));
				
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
			String sql="select bno,name,title,content,attach,re_ref,re_seq,re_lev from board where bno=?";
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
				
				//댓글작업 때문에 추가
				dto.setRe_ref(rs.getInt("re_ref"));
				dto.setRe_seq(rs.getInt("re_seq"));
				dto.setRe_lev(rs.getInt("re_lev"));
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
		//게시물 삭제
		public boolean delete(int bno, String password) {
			PreparedStatement pstmt = null;
			boolean deleteFlag = false;
			try {
				String sql="delete from board where bno=? and password=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, bno);
				pstmt.setString(2, password);
				int result = pstmt.executeUpdate();
				if(result>0) {
					deleteFlag=true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return deleteFlag;
		}
		
		//게시글 수정
		public boolean update(BoardDTO dto) {
			PreparedStatement pstmt = null;
			boolean updateFlag = false;
			try {
				String sql="";
				if(dto.getAttach()!=null) {
					sql="update board set title=?,content=?,attach=? where bno=? and password=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getContent());
					pstmt.setString(3,dto.getAttach());
					pstmt.setInt(4, dto.getBno());
					pstmt.setString(5, dto.getPassword());
					
				}else {
					sql="update board set title=?,content=? where bno=? and password=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, dto.getTitle());
					pstmt.setString(2, dto.getContent());
					pstmt.setInt(3,dto.getBno());
					pstmt.setString(4, dto.getPassword());
				}
				
				int result=pstmt.executeUpdate();
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
		
		//댓글 삽입 전 이전 댓글에 대한 정보 업데이트
		public boolean replyUpdate(BoardDTO dto) {
			PreparedStatement pstmt = null;
			boolean replyFlag=false;
			try {
				//원볼 글에 대한 값
				int re_ref=dto.getRe_ref();
				int re_seq=dto.getRe_seq();
				int re_lev=dto.getRe_lev();
				
				//원본글에 달려있는 기존 댓글의 re_seq 값 수정(댓글을 최신순으로 정렬)
				String sql="update board set re_seq= re_seq+1 where re_ref=? and re_seq> ?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, re_ref);
				pstmt.setInt(2, re_seq);
				int result = pstmt.executeUpdate();
				if(result>0) {
					replyFlag=true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			return replyFlag;
		}
		
		//댓글 삽입
		public boolean replyInsert(BoardDTO dto) {
			boolean insertFlag=false;
			PreparedStatement pstmt = null;
			try {
				String sql="insert into board(bno, title, content, password, attach,name,re_ref,re_seq,re_lev)";
				sql+="values(board_seq.nextval,?,?,?,null,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getPassword());
				pstmt.setString(4, dto.getName());
				pstmt.setInt(5, dto.getRe_ref());
				pstmt.setInt(6, dto.getRe_seq()+1);
				pstmt.setInt(7, dto.getRe_lev()+1);
				int result=pstmt.executeUpdate();
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
	
}
