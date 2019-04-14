package model;


import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(name="Book.findAll",
            query="SELECT b FROM Book b"),
    @NamedQuery(name="Book.countAll",
    		query="SELECT COUNT(b) FROM Book b"),
    @NamedQuery(name="Book.findByTitle",
                query="SELECT b FROM Book b WHERE b.title = :title")
}) 
/*
 * Vu que l'entité hérite de la class Item, on peut laisser JPA mettre le contenu des classes filles
 * CD et Book dans une même table appelé Item sans causé de Dicscrimation majeur
 * 
 * 
 * @SecondaryTables({
 *  	@SecondaryTable(name = "isbn"),
 *  	@SecondaryTable(name = "illustrations")
 * })
 * 
*/
@Table(name = "BOOK")
@Access(AccessType.FIELD)
public class Book extends Item implements Serializable {

    private static final long serialVersionUID = 1L;
    
    //@Column(table = "isbn")
    @Column
    private String isbn;
    @Column
    private Integer nbOfPage;
    //@Column(table = "illustrations")
    @Column
    private Boolean illustrations;
    
    // Constructors
    public Book() {}
    
    public Book(String title, Float price, String description, String isbn, Integer nbOfPage, Boolean illustrations) {
        super();
        this.title = title;
        this.price = price;
        this.description = description;
        this.isbn = isbn;
        this.nbOfPage = nbOfPage;
        this.illustrations = illustrations;
    }


    // Getters and Setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNbOfPage() {
        return nbOfPage;
    }

    public void setNbOfPage(Integer nbOfPage) {
        this.nbOfPage = nbOfPage;
    }

    public Boolean getIllustrations() {
        return illustrations;
    }

    public void setIllustrations(Boolean illustrations) {
        this.illustrations = illustrations;
    };
    
    

}
