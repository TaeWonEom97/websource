package book.service;

import java.sql.Connection;

import book.persistence.BookDAO;

import static book.persistence.JdbcUtil.*;
public class BookDeleteSerivce {
	public boolean bookDelete(String code) {
		Connection con = getConnection();
		BookDAO dao = new BookDAO(con);
		boolean deleteFlag=dao.delete(code);
		if(deleteFlag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return deleteFlag;
		
	}
}
