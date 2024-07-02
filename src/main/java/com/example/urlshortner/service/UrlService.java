package com.example.urlshortner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urlshortner.model.Url;
import com.example.urlshortner.repository.UrlRepository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    private static final String BASE_URL = "http://localhost:8080/";
    private static final int SHORT_URL_LENGTH = 6;
    private static final int EXPIRY_MONTHS = 10;

    public String shortenUrl(String destinationUrl) {
        String shortUrl = generateShortUrl();
        LocalDateTime expiryDate = LocalDateTime.now().plusMonths(EXPIRY_MONTHS);
        Url url = new Url(null, shortUrl, destinationUrl, expiryDate);
        urlRepository.save(url);
        return BASE_URL + shortUrl;
    }

    public boolean updateShortUrl(String shortUrl, String newDestinationUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NoSuchElementException("Short URL not found"));
        url.setDestinationUrl(newDestinationUrl);
        urlRepository.save(url);
        return true;
    }

    public String getDestinationUrl(String shortUrl) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NoSuchElementException("Short URL not found"));
        return url.getDestinationUrl();
    }

    public boolean updateExpiry(String shortUrl, int daysToAdd) {
        Url url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new NoSuchElementException("Short URL not found"));
        url.setExpiryDate(url.getExpiryDate().plusDays(daysToAdd));
        urlRepository.save(url);
        return true;
    }

    private String generateShortUrl() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);

        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(characters.charAt(random.nextInt(characters.length())));
        }
        return shortUrl.toString();
    }
}

