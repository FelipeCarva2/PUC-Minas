// ---------------------------------------------------------------------------------------------------- //

// Imports
import java.util.*;
import java.util.Scanner;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

// ---------------------------------------------------------------------------------------------------- //

public class Show{
    // Global variables
    //public static final String FILE_PATH = "/tmp/disneyplus.csv";
    public static final String FILE_PATH = "/home/felipe/PUCMinas/AEDS2/tp02/disneyplus.csv";
    public static ArrayList<Show> allShows = new ArrayList<Show>();

    //Atributos
    private String Show_id;
    private String type;
    private String title;
    private String director;
    private String[] cast; 
    private String country;
    private Date date_added;
    private int release_year;
    private String rating; 
    private String duration; 
    private String[] listed_in; 

    // Construtor padrão
    public Show() {
        this.Show_id = "";          
        this.type = "";             
        this.title = "";            
        this.director = "";         
        this.cast = null;             
        this.country = "";          
        this.date_added = null;      
        this.release_year = 0;        
        this.rating = "";           
        this.duration = "";         
        this.listed_in = null;        
    }

    // Construtor com parâmetros
    public Show(String show_id, String type, String title, String director, String[] cast, String country,
                Date date_added, int release_year, String rating, String duration, String[] listed_in) {
        this.Show_id = show_id;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = cast;
        this.country = country;
        this.date_added = date_added;
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration;
        this.listed_in = listed_in;
    }

    // Getters e Setters
    public String getShow_id() {return Show_id;}
    public void setShow_id(String show_id) { Show_id = show_id;}
    
    public String getType() {return type;}
    public void setType(String type) {this.type = type;}
    
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    
    public String getDirector() {return director;}
    public void setDirector(String director) {this.director = director;}
    
    public String[] getCast() {return cast;}
    public void setCast(String[] cast) {this.cast = cast;}
    
    public String getCountry() {return country;}
    public void setCountry(String country) {this.country = country;}
    
    public Date getDate_added() {return date_added;}
    public void setDate_added(Date date_added) {this.date_added = date_added;}
    
    public int getRelease_year() {return release_year;}
    public void setRelease_year(int release_year) {this.release_year = release_year;}
    
    public String getRating() {return rating;}
    public void setRating(String rating) {this.rating = rating;}
    
    public String getDuration() {return duration;}
    public void setDuration(String duration) {this.duration = duration;}
    
    public String[] getListed_in() {return listed_in;}
    public void setListed_in(String[] listed_in) {this.listed_in = listed_in;}  

    //Função ler arquivo csv
    public static ArrayList<Show> Ler(){
        String linha = "", conteudo = "";
        String[] s = new String[200];
		int aux = 0;//teste
		ArrayList <Show> show = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
			while ((linha = br.readLine()) != null) {
                
                //teste
				if(aux==1){
                    int j=0;
                for(int i=0; i< linha.length(); i++){
                    
                    char c = linha.charAt(i);
                    if(c == '"'){
                        while(c != '"'){
                            c = linha.charAt(i);
                            s[j]= s[j]+c;

                        }j++; i++;
                    }else if(c==','){
                        s[j]="NaN";
                    }else{
                        while(c!=','){
                            c = linha.charAt(i);
                            s[j]= s[j]+c;  
                        }
                    }
                }
                    for(int i =0; i< 10; i++){
                        System.out.println(s[j]);

                    }
                }
                aux++;
                //teste
                
			}
		} catch (Exception e) {
			System.out.println("Erro!!");
		}
		return show;
    }

    public static void main(String[] args){
        String conteudo = "";
      
            //Scanner sc = new Scanner(System.in);
            ArrayList <Show> show = Ler();
        
    
    }
}


  



