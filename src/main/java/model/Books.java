package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Books {


    @Id
    private Long bookID;
    private String title;
    private String author;
    private String publicationYear;

    private int quantity;
    private Double originalPrice;
    private Double sellingPrice;
    private Double salesTaxPrice;

    public Books(Long bookID, String title, String author, String publicationYear, int quantity, Double originalPrice, Double sellingPrice, Double salesTaxPrice) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
        this.originalPrice = originalPrice;
        this.sellingPrice = sellingPrice;
        this.salesTaxPrice = salesTaxPrice;
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Double getSalesTaxPrice() {
        return salesTaxPrice;
    }

    public void setSalesTaxPrice(Double salesTaxPrice) {
        this.salesTaxPrice = salesTaxPrice;
    }
}
