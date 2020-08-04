package fr.afpa.exercices.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import fr.afpa.exercices.model.Books;
import lombok.extern.log4j.Log4j;

	@Repository
	@Log4j
	public class BooksDao {
		
		@Autowired
		JdbcTemplate jdbcTemplate;

		public List<Books> getAllBooks() {
			String query = "Select * from books ";
			return jdbcTemplate.query(query, new BooksMapper());
		}

		private static final class BooksMapper implements RowMapper<Books> {
			public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
				Books book = new Books();
				book.setAuthorId(rs.getInt("authorId"));
				book.setTitle(rs.getString("title"));
				book.setId(rs.getInt("id"));
				return book;
			}
		}

		/**
		 * nombre commentairs
		 * 
		 * @return
		 */
		public int getNbBooks() {
			String query = "SELECT count(*) FROM books ";
			return jdbcTemplate.queryForObject(query, Integer.class);
		}
		
		public int getIdAuthor(String nameAuthor) {
			String queryid= "SELECT Id FROM authors WHERE Name = "+"\""+nameAuthor+"\"";
			return jdbcTemplate.queryForObject(queryid, Integer.class);
		}
		
		public List<Books> getAuthorBooks(String nameAuthor) {
			int id = getIdAuthor(nameAuthor);
			String query = "SELECT * FROM books WHERE AuthorId = "+id;
			return jdbcTemplate.query(query, new BooksMapper());
		}
		
		public void deleteAuthorBooks(String nameAuthor) {
			int id = getIdAuthor(nameAuthor);
			 String query= "DELETE FROM books WHERE AuthorId = "+id;
			 jdbcTemplate.execute(query);
		}
		
		public void addAuthor(String nameAuthor) {
			String query= "INSERT INTO authors (Name) VALUES "+"(\""+nameAuthor+"\")";
			jdbcTemplate.execute(query);
		}
	
		public void addBooksForAuthor(String nameAuthor, String nameBook) {
			int id = getIdAuthor(nameAuthor);
			String query= "INSERT INTO books (Title, AuthorId) VALUES "+"(\""+nameBook+"\", "+id+")";
			jdbcTemplate.execute(query);
		}
		
		public void deleteSabriLouatahBooks() {
			int id = getIdAuthor("Sabri Louatah");
			 String query= "DELETE FROM books WHERE AuthorId = "+id+" AND title = \"Les sauvages Tome 1\"";
			 String query2= "DELETE FROM books WHERE AuthorId = "+id+" AND title = \"Les sauvages Tome 4\"";

			 jdbcTemplate.execute(query);
			 jdbcTemplate.execute(query2);
		}
		
		
		
	
	
	}
