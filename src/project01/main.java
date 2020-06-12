package project01;

import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		MovieSelector ms = new MovieSelector();
		MovieInfo mi1 = new MovieInfo();
		MovieInfo mi2 = new MovieInfo();
		mi2 = ms.movie("뷰티 인사이드");
		mi1 = ms.movie("어벤져스");
		
		ms.compareMovies(mi1, mi2);
	
		
		/*
		 * System.out.println(mi1.getTitle()); System.out.println(mi1.getPubDate());
		 * System.out.println(mi1.getDirector());
		 * System.out.println(mi1.getUserRating()); if(mi1.getGenre() == null) {
		 * System.out.println("qwer"); }
		 * 
		 * for(int i=0; i<mi1.getGenre().length;i++) {
		 * System.out.println(mi1.getGenre()[i]); }
		 */
	}

}
