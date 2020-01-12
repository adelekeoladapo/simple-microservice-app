package com.dapo.ratingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("/ratings")
public class RatingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}

	private List<Rating> ratingList = Arrays.asList(
			new Rating(1L, 1L, 2),
			new Rating(2L, 1L, 3),
			new Rating(3L, 2L, 4),
			new Rating(4L, 2L, 5)
	);

	@GetMapping("/{bookId}")
	public List<Rating> findRatingsByBookId(@PathVariable Long bookId) {
		return bookId == null || bookId.equals(0L) ? Collections.EMPTY_LIST : ratingList.stream().filter(r -> r.getBookId().equals(bookId)).collect(Collectors.toList());
	}

	@GetMapping("/all")
	public List<Rating> findAllRatings() {
		return ratingList;
	}

}

class Rating {
	private Long id;
	private Long bookId;
	private int stars;

	public Rating(Long id, Long bookId, int stars) {
		this.id = id;
		this.bookId = bookId;
		this.stars = stars;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
}
