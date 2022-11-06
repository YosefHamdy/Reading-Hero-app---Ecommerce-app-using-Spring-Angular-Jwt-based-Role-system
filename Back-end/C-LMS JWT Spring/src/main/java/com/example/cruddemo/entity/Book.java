package com.example.cruddemo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "BOOK")
@Data
@NamedQuery(name = "listBook", query = "Select e from Book e " + "Where e.isbn = :isbn or :isbn = -1 ")
public class Book {
	@Id 
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="ISBN")
	private int isbn;
	private String title;
	@Column(name="SUBTITLE")
	private String subTitle;
	private String subject;
	@Column(name="DESCRIPTION")
	private String description;
	private String Author_Name;
	private String image_url ; 
	private BigDecimal price;
//	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "book_images",
//				joinColumns = {
//						@JoinColumn(name ="book_isbn")
//				},
//				inverseJoinColumns = {
//						@JoinColumn(name = "image_id")
//				}
//				)
//	
//	private Set<ImageModel> bookImages;
//	@ManyToOne 
//    @JoinColumn(name = "category_id", nullable = false)
//    private BookCategory category;

}
