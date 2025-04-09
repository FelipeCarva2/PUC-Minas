// ---------------------------------------------------------------------------------------------------- //
// Imports
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
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
// ---------------------------------------------------------------------------------------------------- //
//Funções

    //Função transformar uma string em um objeto
    public static Show TrasInObj(String linha){
        Show showzinho = new Show();
        int i=0;        
        ArrayList<String> atrib = new ArrayList<>();
       
        while (i<linha.length()) {
            //Testa se um conjunto de dados
            if(linha.charAt(i)=='"'){
                 i++;
                 StringBuilder aux = new StringBuilder();
                 while (i+1<linha.length()) {

                      //testo se é fim do conjunto, se for para, se não continua a leitura
                     if(linha.charAt(i) == '"'){
                         if (linha.charAt(i+1) == ','){
                            i++;
                            break;
                         }
                        i++;
                    }

                    aux.append(linha.charAt(i));
					i++;                       
                    }
                    atrib.add(aux.toString()); 
            //Se não for um conjunto de dados realiza esse else              
            }else{
            //Testa se é um campo vazio ,, NaN
                 if (i < linha.length() && linha.charAt(i) == ',') {
				    if(linha.charAt(i+1) == ','){
					        atrib.add("NaN");
		            }
                    i++;
                //Se não cair em nenhma das condições anteriores quer dizer que é um dado unitario a ser lido
                }else{
                    StringBuilder aux = new StringBuilder();
					while(i < linha.length() && linha.charAt(i) != ','){
						aux.append(linha.charAt(i));
						i++;
					}
                    atrib.add(aux.toString());
                }
            }

            
        }
        //teste para ver se todos os atributos foram separados em strings
        //for (int j = 0; j < atrib.size(); j++){ System.out.println(atrib.get(j)); } System.out.println("\n");
        
        showzinho.setShow_id(atrib.get(0));
		showzinho.setType(atrib.get(1));
		showzinho.setTitle(atrib.get(2));
		showzinho.setDirector(atrib.get(3));
		//showzinho.setCast(splitArrays(atrib.get(4)));
		showzinho.setCountry(atrib.get(5));
		//showzinho.setDate_added(atrib.get(6));
        try {
            if(!atrib.get(6).equals("NaN")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy"); 
                showzinho.setDate_added(dateFormat.parse(atrib.get(6)));
            } 
        } catch (Exception e) {
            System.err.println("Erro ao converter data: " + atrib.get(6));
            showzinho.setDate_added(null);
        }
		showzinho.setRelease_year(Integer.parseInt(atrib.get(7)));
		showzinho.setRating(atrib.get(8));
		showzinho.setDuration(atrib.get(9));
		//showzinho.setLISTED_IN(atrib.get(10));
		
         



        return showzinho;
    }
    //Função ler arquivo csv
    public static ArrayList<Show> Ler(){
        String linha = "";
		int aux = 0;
		ArrayList <Show> show = new ArrayList<>(); 

		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
			while ((linha = br.readLine()) != null) {

                if(aux >= 1){
					System.out.println(linha + '\n');
					show.add(TrasInObj(linha));
				}

				aux++;
                
                
			}
            br.close();
		} catch (Exception e) {
			System.out.println("Erro!!");
		}
		return show;
        
    }

    //---------------------------------------------------------------------------------------------------- //
    //Fução main 
    public static void main(String[] args){
    
      
        Scanner sc = new Scanner(System.in);
        ArrayList <Show> show = Ler();
        System.out.println("Digite o id para retornar o titulo:");
		int id = sc.nextInt();
		System.out.println(show.get(id - 1).title);
        
           
        sc.close();
    }
}


  



