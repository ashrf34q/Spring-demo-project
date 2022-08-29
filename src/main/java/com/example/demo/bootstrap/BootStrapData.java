package com.example.demo.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.domain.Author;
import com.example.demo.domain.Book;
import com.example.demo.domain.Publisher;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.PublisherRepository;




@Component
public class BootStrapData implements CommandLineRunner {

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
//		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		System.out.println("Started in bootstrap");
//		Publisher harry = new Publisher("Harry", "24605 Howard Ave", "Dearborn", "MI", "48126");
		 Publisher harry = new Publisher();
	        harry.setName("Harry");
	        harry.setCity("Dearborn");
	        harry.setState("MI");

		publisherRepository.save(harry);

        System.out.println("Publisher Count: " + publisherRepository.count());
		
		Author author = new Author("Neil", "Tyson");
		Book book = new Book("Cosmic Queries", "132498");
		author.getBooks().add(book);
		book.getAuthors().add(author);  
		
		book.setPublisher(harry);
		harry.getBooks().add(book);
		
		authorRepository.save(author);
		bookRepository.save(book);
		publisherRepository.save(harry);
		
		Author steph = new Author("Stephen", "Hawking");
		Book bhot = new Book("Brief history of time", "3452186");
		steph.getBooks().add(bhot);
		bhot.getAuthors().add(steph);
		
		bhot.setPublisher(harry);
		harry.getBooks().add(bhot);
		
		authorRepository.save(steph);
		bookRepository.save(bhot);
		publisherRepository.save(harry);
		
		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Publisher number of books: " + harry.getBooks().size());
	
	}

}
