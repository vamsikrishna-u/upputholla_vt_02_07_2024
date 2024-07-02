package com.example.urlshortner.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Url {

   

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String shortUrl;

    @Column(nullable = false)
    private String destinationUrl;

    @Column(nullable = false)
    private LocalDateTime expiryDate;

	public Url() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Url(Long id, String shortUrl, String destinationUrl, LocalDateTime expiryDate) {
		super();
		this.id = id;
		this.shortUrl = shortUrl;
		this.destinationUrl = destinationUrl;
		this.expiryDate = expiryDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getDestinationUrl() {
		return destinationUrl;
	}

	public void setDestinationUrl(String destinationUrl) {
		this.destinationUrl = destinationUrl;
	}

	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", shortUrl=" + shortUrl + ", destinationUrl=" + destinationUrl + ", expiryDate="
				+ expiryDate + "]";
	}
    
    
}
