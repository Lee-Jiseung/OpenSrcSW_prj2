package project01;

public class MovieInfo {
	private String title;
	private int pubDate;
	private String director;
	private float userRating;
	private int[] genre;
	
	public void setTitle(String t) {
		this.title = t;
	}
	
	public void setPubDate(int pd) {
		this.pubDate = pd;
	}
	
	public void setDirector(String d) {
		this.director = d;
	}
	
	public void setUserRating(float r) {
		this.userRating = r;
	}
	public void setGenre(int[] g) {
		this.genre = g;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getPubDate() {
		return pubDate;
	}
	
	public String getDirector() {
		return director;
	}
	
	public float getUserRating() {
		return userRating;
	}
	public int[] getGenre() {
		return genre;
	}
}
