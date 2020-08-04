package fr.afpa.exercices;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import fr.afpa.exercices.config.AppConfig;
import fr.afpa.exercices.model.Books;
import fr.afpa.exercices.spring.jdbc.BooksDao;
import lombok.extern.log4j.Log4j;

@Log4j
public class Main {

	public static void main(String[] args) {
		
		// Creating a Context Application object by reading
		// the configuration of the 'AppConfiguration' class.
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		
		
		log.info("list des livres");
		BooksDao booksDao = (BooksDao) ctx.getBean("booksDao");
		
		List<Books> books = booksDao.getAllBooks();
		for (Books book : books) {
			System.out.println(book);
		}
		
		
		log.info("get nombre de books");
		System.out.println(booksDao.getNbBooks());
		
		log.info("show Truman's books");
		System.out.println(booksDao.getAuthorBooks("Truman Capote"));
		
		log.info("delete Honore de Balzac's books");
		booksDao.deleteAuthorBooks("Honore de Balzac");
		
		/*
		log.info("ajouter auteur Sabri louata");
		booksDao.addAuthor("Sabri Louatah");
		*/
		
		/*
		log.info("ajouter livre à Sabri louata");
		booksDao.addBooksForAuthor("Sabri louatah", "404");
		booksDao.addBooksForAuthor("Sabri louatah", "Les sauvages Tome 1");
		booksDao.addBooksForAuthor("Sabri louatah", "Les sauvages Tome 2");
		booksDao.addBooksForAuthor("Sabri louatah", "Les sauvages Tome 3");
		booksDao.addBooksForAuthor("Sabri louatah", "Les sauvages Tome 4");
		*/
		log.info("supprimer tome 1 et 4 de Sabri");
		booksDao.deleteSabriLouatahBooks();
		/*
		log.info("Ajouter un auteur");
		booksDao.addAuthor("Sabri Louatah");
		
		log.info("Ajouter un livre pour un auteur");
		booksDao.addBooksForAuthor("Sabri Louatah", "404");
		
		log.info("Ajouter un livre pour un auteur");
		booksDao.addBooksForAuthor("Les Sauvages Tome 1", "404");
		
		log.info("Ajouter un livre pour un auteur");
		booksDao.addBooksForAuthor("Les Sauvages Tome 2", "404");
		
		log.info("Ajouter un livre pour un auteur");
		booksDao.addBooksForAuthor("Les Sauvages Tome 3", "404");
		
		log.info("Ajouter un livre pour un auteur");
		booksDao.addBooksForAuthor("Les Sauvages Tome 4", "404");
		*/
	
	
		/*
		public int getNbBooks() {
			String query = "Select count(*) from books ";
			return jdbcTemplate.queryForObject(query, Integer.class);
		}
		
		public String getTrumanBook() {
			String query = "Select count(*) from books ";
			return jdbcTemplate.queryForObject(query, String.class);
		}
		
		public void deleteHonoreBalzacBook(String nameAuthor) {
			String query= "delete from Comments where email = ?";
			jdbcTemplate.update(query, nameAuthor);
		}
		 */

		
    }
   
}