package com.cmc.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Book", schema = "book_management")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "Uid", nullable = false, unique=true)

  @NotNull
  private int uid;
  
  @Column(name = "Code", nullable = false, unique=true)

  @NotNull
  private String code;
  
  @Column(name = "Name", nullable = false)
  @NotNull

  private String name;
  
  @Column(name = "Description", nullable = false)
  @NotNull

  private String description;
  
  @Column(name = "Category")
  @NotNull

  private String category;
  
  @Column(name = "Author", nullable = false)
  @NotNull

  private String author;
  
  @Column(name = "Publisher", nullable = false)
  @NotNull

  private String publisher;
  
  @Column(name = "CreateUser", nullable = false)
  @NotNull

  private String createUser;
  
  @NotNull

  @Column(name = "CreateDate", nullable = false)
  private String createDate;
  
  @NotNull

  @Column(name = "UpdateUser", nullable = false)
  private String updateUser;
  
  @NotNull

  @Column(name = "UpdateDate", nullable = false)
  private String updateDate;
  
}
