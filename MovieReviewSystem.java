package projects;

import java.time.LocalDate;
import java.util.*;

public class MovieReviewSystem {
	private List<Movie> movies;
	private Map<String, List<Review>> reviews;

	public MovieReviewSystem() {
		movies = new ArrayList<>();
		reviews = new HashMap<>();
		initializeMovies();
	}

	private void initializeMovies() {
		movies.add(new Movie("1", "인사이드 아웃 2", "캘시 맨", "애니메이션", "2024-06-12"));
		movies.add(new Movie("2", "원더랜드", "김태용", "드라마", "2024-06-05"));
		movies.add(new Movie("3", "아쿠아맨과 로스트 킹덤", "제임스 완", "액션, 판타지", "2024-05-22"));
		movies.add(new Movie("4", "노량:죽음의 바다", "김한민", "역사, 전쟁", "2023-12-20"));
		movies.add(new Movie("5", "서울의 봄", "이준익", "War", "2023-11-22"));
		movies.add(new Movie("6", "범죄도시4", "강윤성", "Drama", "2024-04-24"));
		movies.add(new Movie("7", "명탐정 코난:괴도 키드", "타치카와 유즈루", "Action", "2024-06-05"));
		movies.add(new Movie("8", "극장판 하이큐!! 쓰레기장의 결전", "미즈노 코지", "Sci-Fi", "2024-05-15"));
		movies.add(new Movie("9", "드라이브", "니콜라스 원딩 레픈", "Thriller", "2024-06-12"));
		movies.add(new Movie("10", "도그데이즈", "김덕민", "Thriller", "2024-02-07"));
	}

	private boolean movieExists(String movieId) {
		for (Movie movie : movies) {
			if (movie.getMovieId().equals(movieId)) {
				return true;
			}
		}
		return false;
	}

	// 영화 등록
	public void addMovie(Movie movie) {
		if (movieExists(movie.getMovieId())) {
			System.out.println("이미 존재하는 영화 ID입니다.");
		} else {
			movies.add(movie);
			System.out.println("영화가 성공적으로 등록되었습니다.");
		}
	}

	public String getMovieId(String title) {
		String movieId = "";
		for (Movie movie : movies) {
			if (movie.getTitle().equals(title)) {
				movieId = movie.getMovieId();
			}
		}
		if (movieId.equals("")) {
			throw new IllegalArgumentException("존재하지 않는 영화입니다.");
		} else {
			return movieId;
		}
	}

	public boolean checkMovieContainReviewId(String movieId, Map<String, List<Review>> reviews) {
		
		if (reviews.get(movieId) != null) {
	
			return true;
		} else {
	
			return false;
		}
	}
	public boolean checkMovieContainReviewTitle(String title, Map<String, List<Review>> reviews) {
		String movieId = "";
		for (Movie movie : movies) {
			if (movie.getTitle().equals(title)) {
				movieId = movie.getMovieId();
			}
		}
		if (movieId.equals("")) {
			return false;
		}
		if (reviews.get(movieId) != null) {
	
			return true;
		} else {
	
			return false;
		}
	}


	// 리뷰 등록
	public void addReview(String movieId, String userId, String comment, double rating) throws NumberFormatException {

		try {
			if (!movieExists(movieId)) {
				throw new IllegalArgumentException("존재하지 않는 영화에는 리뷰를 등록할 수 없습니다.");
			}
			if (rating < 0.0 || rating > 5.0) {
				throw new IllegalArgumentException("평점은 0.0과 5.0 사이여야 합니다.");
			}
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e.getMessage());

		}

		Review review = new Review(userId, comment, rating);
		
		if (!this.checkMovieContainReviewId(movieId, reviews)) {
			reviews.put(movieId, new ArrayList<>());
		}
		reviews.get(movieId).add(review);
		System.out.println("리뷰가 성공적으로 등록되었습니다.");
	}

	// 리뷰 정렬
	public List<Review> getSortedReviews(String title, String option) {
		try {
			if (!checkMovieContainReviewTitle(title, reviews)) {
				System.out.println("해당 영화에 대한 리뷰가 없습니다.");
				throw new IllegalArgumentException("해당 영화에 대한 리뷰가 없습니다");
			}

			if (!option.equals("asc") &&!option.equals("desc")) {
				throw new IllegalArgumentException("정렬 순서 잘못 입력하였습니다.");
			}
			String movieId = getMovieId(title);
			List<Review> movieReviews = new ArrayList<>(reviews.get(movieId));
			
			if (option.equals("asc")) {
				movieReviews.sort(Comparator.comparingDouble(Review::getRating));
			} else {

				movieReviews.sort(Comparator.comparingDouble(Review::getRating).reversed());
			}

			return movieReviews;
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

	}

	// 메인 메서드
	public static void main(String[] args) {
		MovieReviewSystem system = new MovieReviewSystem();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("1. 영화 제목 등 ");
			System.out.println("2. 리뷰 등록");
			System.out.println("3. 리뷰 정");
			System.out.println("4 종료 ");
			System.out.print("선택: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			try {
				switch (choice) {
				case 1:
					System.out.print("영화 ID: ");
					String movieId = scanner.nextLine();
					System.out.print("영화 제목: ");
					String title = scanner.nextLine();
					System.out.print("장르: ");
					String genre = scanner.nextLine();
					System.out.print("디렉터: ");
					String director = scanner.nextLine();
					System.out.print("개봉일 (YYYY-MM-DD): ");
					String releaseDate = scanner.nextLine();
					system.addMovie(new Movie(movieId, title, director, genre, releaseDate));
					break;
				case 2:
					System.out.print("영화 ID: ");
					String reviewMovieId = scanner.nextLine();
					System.out.print("사용자 ID: ");
					String userId = scanner.nextLine();
					System.out.print("리뷰 코멘트: ");
					String comment = scanner.nextLine();
					System.out.print("평점 (1-5): ");
					double rating = scanner.nextDouble();
					scanner.nextLine(); // Consume newline
					system.addReview(reviewMovieId, userId, comment, rating);
					break;

			
				case 3:
					System.out.println("리뷰를 볼 영화의 제목: ");
					String title1 = scanner.nextLine();

					System.out.println("제목 정렬 순서 선택 (asc 또는 desc): ");
					String option = scanner.nextLine();

					List<Review> reviews = system.getSortedReviews(title1, option);
					for (Review review : reviews) {
						System.out.println("리뷰 작성자: " + review.getUserId());
						System.out.println("코멘트: " + review.getComment());
						System.out.println("평점: " + review.getRating());
						System.out.println();
					}
					break;
				case 4:
					System.out.println("프로그램을 종료합니다.");
					scanner.close();
					return;

				}

			} catch (Exception e) {
				System.out.println("오류가 발생했습니다: " + e.getMessage());
			}
		}
	}

}
