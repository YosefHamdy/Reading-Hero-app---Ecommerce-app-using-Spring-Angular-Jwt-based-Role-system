package com.example.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.cruddemo.entity.Book;
import com.example.cruddemo.entity.CustomerAssignedBook;
import com.example.cruddemo.entity.TransactionBookView;
import com.example.cruddemo.exceptions.BusinessException;
import com.example.cruddemo.exceptions.NoDataFoundException;
import com.example.cruddemo.service.BookService;
import com.example.cruddemo.service.CustomerAssignedBookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookRestController extends BaseController {

	@Autowired
	private BookService bookService;
	@Autowired
	private CustomerAssignedBookService customerAssignedBookService;

	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.GET, value = "/books")
	public List<Book> findAll() throws NoDataFoundException {
		try {
			return bookService.findAll();
		} catch (Exception e) {
			throw new NoDataFoundException("No Data Found Exception");
		}
	}

	// TODO : GET api/books/{bookId} pathVariable
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.GET, value = "/books/view/{bookId}")
	public Book getBook(@PathVariable int bookId) throws NoDataFoundException {
		Book theBook = null;
		try {
			theBook = bookService.findById(bookId);

		} catch (Exception e) {
			throw new NoDataFoundException("Book not availabe ");
		}
		return theBook;
	}
	


	// TODO : POST api/books Create new Book
	// Multipart request
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = {"/books/add"})
	public Book addBook(@RequestBody Book theBook
					// we use MultipartFile[] array to make sure that 
			// admin can drag,drop multiple values
			/*@RequestPart("imageFile")MultipartFile[] file*/) throws BusinessException {
		try {
			theBook.setIsbn(0);
//			Set<ImageModel> images = uploadImage(file);
//			theBook.setBookImages(images);
			bookService.save(theBook);
		} catch (Exception e) {
			throw new BusinessException("Can't add this book", new String[] {theBook.getTitle()});
		}
		return theBook;
	}
	// method to process image
//	public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
//		Set<ImageModel> imageModels = new HashSet<>();
//		
//		for (MultipartFile file : multipartFiles) {
//			ImageModel imageModel = new ImageModel(
//					file.getOriginalFilename(),
//					file.getContentType(),
//					file.getBytes()
//					);
//			imageModels.add(imageModel);
//		}
//		return imageModels;
//	}

	// TODO : PUT api/books Update an existing Book
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/books/update/{isbn}")
	public Book updateBook(@PathVariable Integer isbn,@RequestBody Book theBook) throws BusinessException {
		try {
			Book tempBook = bookService.findById(isbn);
			if (tempBook != null) { 
				theBook.setIsbn(isbn);
			bookService.save(theBook);
			}

	} catch (Exception e) {
		throw new BusinessException("Can't Update this book", new String[] {theBook.getTitle()});
	}
		return theBook;
	}

	// TODO : DELETE api/books/{bookId}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/books/delete/{bookId}")
	public String deleteBook(@PathVariable int bookId) throws NoDataFoundException{

		
		Book tempBook = bookService.findById(bookId);
//		throw exception if null
		if (tempBook == null) 
			throw new NoDataFoundException("Book Not found to Delete it");
//		tempBook.setBookImages(null);
		bookService.deleteById(bookId);
		return "Deleted Book Id - " + bookId;
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/books/issued")
	public ResponseEntity<List<TransactionBookView>> findAllIssued() throws NoDataFoundException {
		try {
		
			List<TransactionBookView> result = bookService.findByUserId(getCurrentUser().getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
		}catch (Exception e) {
			throw new NoDataFoundException("Book Not found to Delete it");
		}
	}

	
	@SuppressWarnings("unused")
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
	@RequestMapping(method = RequestMethod.POST, value = "/borrow/add")
	public String getNewBook(@RequestBody CustomerAssignedBook theBook) throws BusinessException {
		try {
			theBook.setCustomerId(getCurrentUser().getId());
			theBook.setCustomerName(getCurrentUser().getUsername());
			customerAssignedBookService.save(theBook);
		}catch (Exception e) {
			throw new BusinessException("Can't Get that book for you !");
		}
		String message = "Book info was successfully added.";
		log.info(message + theBook);
		return "Book info was successfully added" + theBook;

	}
	@RequestMapping(method = RequestMethod.GET, value = "/books/search")
	public List<Book> searchForBooks(@Param("keyword") String keyword) throws NoDataFoundException {
		try {
			return bookService.search(keyword);
		} catch (Exception e) {
			throw new NoDataFoundException("No Data Found Exception");
		}
	}
	
}
