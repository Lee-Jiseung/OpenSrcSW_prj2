package project01;

import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class MovieSelector {
//	private String moviename;
//	
//	MovieSelector(String name) {
//		this.moviename = name;
//	}
	
	public MovieInfo movie(String name) throws IOException {
		ApiExamSearchMovie moviesearch = new ApiExamSearchMovie();
		ApiExamSearchMovie moviesearch2 = new ApiExamSearchMovie();
		String information = moviesearch.search(name);
		
		MovieInfo mi = new MovieInfo();
		
		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject =  (JSONObject) jsonParser.parse(information);
			int num_samename = (int) (long) jsonObject.get("total");
			JSONArray infoArray = (JSONArray)jsonObject.get("items");
			JSONObject obj=null;
			
			if(num_samename != 1) {
				for(int i=0;i<infoArray.size();i++) {
				    System.out.println("=item_"+i+"==========================================");
				    JSONObject itemObject = (JSONObject) infoArray.get(i);
				    System.out.println("title :\t"+itemObject.get("title"));
				    System.out.println("subtitle :\t"+itemObject.get("subtitle"));
				    System.out.println("director :\t"+itemObject.get("director"));
				    System.out.println("pubDate :\t"+itemObject.get("pubDate")+"\n");
				}
				Scanner scanner = new Scanner(System.in);
				System.out.print("몇번째 영화인가요? : ");
				int input = scanner.nextInt();
				scanner.close();
				
				obj = (JSONObject)infoArray.get(input);
			} else {
				obj = (JSONObject) infoArray.get(0);
			}
			String link = (String) obj.get("link");

			int genre[] = new int[28];
			for(int i=0;i<genre.length;i++) {
				if(moviesearch2.searchGenre(name, link, i+1) == true) {
					genre[i] = 1;
				}
			}
			saveGenre(genre);
			mi.setTitle((String)obj.get("title"));
			mi.setPubDate(Integer.parseInt((String)obj.get("pubDate")));
			mi.setDirector((String)obj.get("director"));
			mi.setUserRating(Float.parseFloat((String)obj.get("userRating")));
			mi.setGenre(genre);
			
		} catch (ParseException e) {
			e.printStackTrace();  
		}
		return mi;
	}
	
	public void compareMovies(MovieInfo mi1, MovieInfo mi2) {
		if(mi1.getUserRating() > mi2.getUserRating()) {
			System.out.println(mi1.getTitle() + "이(가) " + mi2.getTitle() + "보다 평점이 높습니다.");
		} else if(mi1.getUserRating() < mi2.getUserRating()) {
			System.out.println(mi2.getTitle() + "이(가) " + mi1.getTitle() + "보다 평점이 높습니다.");
		} else {
			System.out.println(mi1.getTitle() + "와(과) " + mi2.getTitle() + "의 평점이 같습니다.");
		}
		
		int[] loadgenre = loadGenre();
		ArrayList<Integer> genretype1 = new ArrayList<Integer>();
		ArrayList<Integer> genretype2 = new ArrayList<Integer>();
		int[] genre1 = mi1.getGenre();
		int[] genre2 = mi2.getGenre();
		
		for(int i=0;i>28;i++) {
			if(genre1[i] == 1) {
				genretype1.add(i);
			}
		}
		for(int i=0;i>28;i++) {
			if(genre2[i] == 1) {
				genretype2.add(i);
			}
		}
		int preference1 = 0;
		int preference2 = 0;
		for(int i=0;i<genretype1.size();i++) {
			preference1 += genretype1.get(i);
		}
		for(int i=0;i<genretype2.size();i++) {
			preference2 += genretype2.get(i);
		}
		
		if(preference1 > preference2) {
			System.out.println(mi1.getTitle() + "이(가) " + mi2.getTitle() + "보다 사용자의 장르 취향에 적합합니다.");
		} else if(preference1 < preference2) {
			System.out.println(mi2.getTitle() + "이(가) " + mi1.getTitle() + "보다 사용자의 장르 취향에 적합합니다.");
		} else {
			System.out.println(mi1.getTitle() + "와(과) " + mi2.getTitle() + "모두 사용자의 장르 취향에 적합합니다.");
		}
	}
	
	private int[] loadGenre(){
		int loadgenre[] = new int[28];
		try {
			File genrefile = new File("genre.txt");
			FileReader filereader = new FileReader(genrefile);
			BufferedReader bufreader = new BufferedReader(filereader);
			String line = "";
			
			int i=0;
			while((line = bufreader.readLine()) != null) {
				loadgenre[i++] = Integer.parseInt(line);
			}
			bufreader.close();
			return loadgenre;
		} catch(FileNotFoundException e) {
			System.out.println("error");
		} catch(IOException e) {
			System.out.println(e);
		}
		
		
		return null;
	}
	
	private void saveGenre(int[] genre) throws IOException {
		int[] loadgenre = loadGenre();
		File file = new File("genre.txt");
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			for(int i=0;i<28;i++) {
				int g = genre[i] + loadgenre[i];
				pw.println(g);
			}
			pw.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
