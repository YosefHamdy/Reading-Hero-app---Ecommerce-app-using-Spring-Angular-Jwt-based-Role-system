//package com.example.cruddemo.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.Table;
//
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//@Entity
//@Table(name = "image_model")
//@Data
//@RequiredArgsConstructor
//public class ImageModel {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	private String name;
//	private String type;
//	@Column(length = 50000000)
//	@Lob
//	private byte[] picByte;
//	public ImageModel(String name, String type, byte[] picByte) {
//		this.name = name;
//		this.type = type;
//		this.picByte = picByte;
//	}
//	
//}
