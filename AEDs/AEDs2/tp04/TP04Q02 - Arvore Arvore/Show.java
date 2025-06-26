// ---------------------------------------------------------------------------------------------------- //
// Imports

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

// ---------------------------------------------------------------------------------------------------- //

//---------------------------------------------------------------------------------------------------- //
    //Classe No2
//---------------------------------------------------------------------------------------------------- //
class No2{
    public Show elemento;
    public No2 dir;
    public No2 esq;
    
    public No2(Show elemento){
        this.elemento = elemento;
        this.dir = null;
        this.esq = null;
    }
}
//---------------------------------------------------------------------------------------------------- //
    //Classe No
//---------------------------------------------------------------------------------------------------- //
class No{
    public int elemento;
    public No esq;
    public No dir;
    public No2 raiz;
    
    public No(int elemento){
        this.elemento = elemento;
        this.dir = null;
        this.esq = null;
        this.raiz = null;
    }
}

//---------------------------------------------------------------------------------------------------- //
    //Classe ArvoreDeArvore
//---------------------------------------------------------------------------------------------------- //
class ArvoreDeArvore{
    public No raiz;
    
    public ArvoreDeArvore(){
        this.raiz = null;
        int[] lista = { 7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14 };
        for(int i = 0; i < 15; i++){
            inserir(lista[i]);
        }
    }
    
    public void inserir(int x){
        raiz = inserir(x, raiz);
    }
    
    public No inserir(int x, No i) {
		if(i == null) {
		    return new No(x);
		} else if(x < i.elemento) {
			i.esq = inserir(x, i.esq);
		} else if(x > i.elemento) {
			i.dir = inserir(x, i.dir);
		} else{
		    throw new RuntimeException("Erro!");
		}
		
		return i;
	}
	
	public void inserirNaArvore(Show x){
	    int mod = x.getRelease_year() % 15;
	    raiz = inserirNaArvore(x, raiz, mod);
	}
	
	public No inserirNaArvore(Show x, No i, int mod){
	    if (i == null) {
            return new No(mod); 
        }
	    if(mod < i.elemento) {
			i.esq = inserirNaArvore(x, i.esq, mod);
		} else if(mod > i.elemento) {
			i.dir = inserirNaArvore(x, i.dir, mod);
		} else if(mod == i.elemento){
		    i.raiz = inserir2(x, i.raiz);
		}
		
		return i;
	}
	
	public No2 inserir2(Show x, No2 i){
	    if(i == null) {
		    return new No2(x);
		} else if(x.getTitle().compareTo(i.elemento.getTitle()) < 0) {
			i.esq = inserir2(x, i.esq);
		} else if(x.getTitle().compareTo(i.elemento.getTitle()) > 0) {
			i.dir = inserir2(x, i.dir);
		} else{
		    throw new RuntimeException("Erro!");
		}
		
		return i;
	}
	
	public boolean pesquisar(String x){
	    System.out.print("raiz ");
	    boolean resp = pesquisar(x, raiz);
	    System.out.println(resp ? "SIM" : "NAO");
	    return resp;
	}
	
	public boolean pesquisar(String x, No i){
	    if (i == null) {
            return false;
        }
        if (pesquisar2(x, i.raiz)){
            return true;
        }
        System.out.print("ESQ ");
        if (pesquisar(x, i.esq)){
            return true;
        }
        System.out.print("DIR ");
        return pesquisar(x, i.dir);
	}
	
	public boolean pesquisar2(String x, No2 i){
        if(i == null){
            return false;
        }else{
            Show.comp++;
            if(x.compareTo(i.elemento.getTitle()) < 0){
                System.out.print("esq ");
                return pesquisar2(x, i.esq);
            }else if(x.compareTo(i.elemento.getTitle()) > 0){
                System.out.print("dir ");
                return pesquisar2(x, i.dir);
            }else{
                return true;
            } 
        }
	}
}
//---------------------------------------------------------------------------------------------------- //
    //Classe Show
//---------------------------------------------------------------------------------------------------- //

public class Show{
    // Global variables
    public static final String FILE_PATH = "/tmp/disneyplus.csv";
    //public static final String FILE_PATH = "/home/felipe/PUCMinas/AEDs/AEDs2/tp02/Q01/disneyplus.csv";
    public static int mov = 0, comp = 0; //Variaveis para contar movimentações e comparações

    //Atributos
    private String Show_id;
    private String type;
    private String title;
    private String director;
    private ArrayList<String> cast = new ArrayList<>();
    private String country;
    private Date date_added;
    private int release_year;
    private String rating; 
    private String duration; 
    private ArrayList<String> listed_in = new ArrayList<>(); 
    

    // Construtor padrão
    public Show() {
        this.Show_id = "";          
        this.type = "";             
        this.title = "";            
        this.director = "";         
        this.cast = new ArrayList<>();           
        this.country = "";          
        this.date_added = null;      
        this.release_year = 0;        
        this.rating = "";           
        this.duration = "";         
        this.listed_in = new ArrayList<>();       
    }

    // Construtor com parâmetros
    public Show(String show_id, String type, String title, String director, ArrayList<String> cast, String country, Date date_added, int release_year, String rating, String duration, ArrayList<String> listed_in) {
        this.Show_id = show_id;
        this.type = type;
        this.title = title;
        this.director = director;
        this.cast = new ArrayList<>(cast);
        this.country = country;
        this.date_added = date_added;    
        this.release_year = release_year;
        this.rating = rating;
        this.duration = duration;
        this.listed_in = new ArrayList<>(listed_in);
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
    
    public ArrayList<String> getCast() { return new ArrayList<>(cast); }
    public void setCast(ArrayList<String> cast) { this.cast = new ArrayList<>(cast); }
    
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
    
    public ArrayList<String> getListed_in() { return new ArrayList<>(listed_in); }
    public void setListed_in(ArrayList<String> listed_in) { this.listed_in = new ArrayList<>(listed_in); }
// ---------------------------------------------------------------------------------------------------- //
//Funções

    //+-+-+--+-+-+-+ Função transformar uma string em um objeto +-+-+--+-+-+-+
    public static Show TrasInObj(String linha) {
    Show showzinho = new Show();
    int i = 0;
    ArrayList<String> atrib = new ArrayList<>();

    while (i < linha.length()) {
        if (linha.charAt(i) == '"') {
            i++;
            StringBuilder aux = new StringBuilder();
            while (i + 1 < linha.length()) {
                if (linha.charAt(i) == '"') {
                    if (linha.charAt(i + 1) == ',') {
                        i++;
                        break;
                    }
                    i++;
                }
                aux.append(linha.charAt(i));
                i++;
            }
            atrib.add(aux.toString());
        } else {
            if (i < linha.length() && linha.charAt(i) == ',') {
                if (i + 1 < linha.length() && linha.charAt(i + 1) == ',') {
                    atrib.add("NaN");
                }
                i++;
            } else {
                StringBuilder aux = new StringBuilder();
                while (i < linha.length() && linha.charAt(i) != ',') {
                    aux.append(linha.charAt(i));
                    i++;
                }
                atrib.add(aux.toString());
            }
        }
    }

    if (atrib.size() < 11 || atrib.get(2) == null || atrib.get(2).trim().isEmpty()) {
        return null; // Linha mal formatada ou título nulo
    }

    try {
        showzinho.setShow_id(atrib.get(0));
        showzinho.setType(atrib.get(1));
        showzinho.setTitle(atrib.get(2));
        showzinho.setDirector(atrib.get(3));
        showzinho.setCast(Separa(atrib.get(4)));
        showzinho.setCountry(atrib.get(5));

        if (!atrib.get(6).equals("NaN")) {
            String dataNormalizada = atrib.get(6).replace(",", "").trim();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH);
            showzinho.setDate_added(dateFormat.parse(dataNormalizada));
        } else {
            showzinho.setDate_added(null);
        }

        showzinho.setRelease_year(Integer.parseInt(atrib.get(7)));
        showzinho.setRating(atrib.get(8));
        showzinho.setDuration(atrib.get(9));
        showzinho.setListed_in(Separa(atrib.get(10)));

    } catch (Exception e) {
        System.err.println("Erro ao processar linha: " + linha);
        return null;
    }

    return showzinho;
}

    
    //+-+-+--+-+-+-+ Função para separar elementos por virgula +-+-+--+-+-+-+
    public static ArrayList<String> Separa(String s){
        ArrayList<String> separado = new ArrayList<>();
        int i = 0;

        while (i < s.length()) {
            if (s.equals("NaN")) {
                separado.add("NaN");
                break;
            } else {
                StringBuilder aux = new StringBuilder();
                while (i < s.length() && s.charAt(i) != ',') {
                    aux.append(s.charAt(i));
                    i++;
                }
                separado.add(aux.toString());
            }
            i += 2;
        }
        Collections.sort(separado);
        return separado;
    }
    //+-+-+--+-+-+-+ Funação para clonar +-+-+--+-+-+-+
    public Show clone() {
        Show clone = new Show();
        clone.setShow_id(this.getShow_id());
        clone.setType(this.getType());
        clone.setTitle(this.getTitle());
        clone.setDirector(this.getDirector());
        clone.setCast(new ArrayList<>(this.getCast()));
        clone.setCountry(this.getCountry());
        clone.setDate_added(this.getDate_added() != null ? new Date(this.getDate_added().getTime()) : null);
        clone.setRelease_year(this.getRelease_year());
        clone.setRating(this.getRating());
        clone.setDuration(this.getDuration());
        clone.setListed_in(new ArrayList<>(this.getListed_in()));
        return clone;
    }

   
    //+-+-+--+-+-+-+ Função ler arquivo csv +-+-+--+-+-+-+
    public static ArrayList<Show> Ler() {
    String linha = "";
    int aux = 0;
    ArrayList<Show> show = new ArrayList<>();

    try {
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        while ((linha = br.readLine()) != null) {
            if (aux >= 1) {
                Show temp = TrasInObj(linha);
                if (temp != null && temp.getTitle() != null && !temp.getTitle().isEmpty()) {
                    show.add(temp);
                }
            }
            aux++;
        }
        br.close();
    } catch (Exception e) {
        System.out.println("Erro ao ler arquivo!!");
    }

    return show;
}



     //+-+-+--+-+-+-+ Função swap de show +-+-+--+-+-+-+
    public static void swap(ArrayList<Show> lista, int i, int j) {
        if (i != j) { 
            Show temp = lista.get(i); 
            lista.set(i, lista.get(j)); 
            lista.set(j, temp); 
            mov += 3;
        }
    }
     
     //+-+-+--+-+-+-+ Função para retornar a serie pesquisa +-+-+--+-+-+-+
     public static Show procurar(ArrayList<Show> showzinho, String id){
        for (int i = 0; i < showzinho.size(); i++) {
            if (showzinho.get(i).getShow_id().equals(id)) {
                return showzinho.get(i);
            }
        }
        return null;
    }

    //+-+-+--+-+-+-+ Função para criar o log +-+-+--+-+-+-+
    public static void arquivoLog(double duracao){
        String matricula = "1543536";
        try {
            PrintWriter w = new PrintWriter(matricula + "_arvoreBinaria.txt");
            w.printf("%s\t%d\t%fms", matricula, comp, duracao);
            w.close();
        } catch (IOException e) {
            System.err.println("Erro para escrever no arquivo de log: " + e.getMessage());
        }
    }
    
    //---------------------------------------------------------------------------------------------------- //
    //Fução main 
    public static void main(String[] args) {
       
        int j = 0;
        Scanner sc = new Scanner(System.in);
        ArrayList<Show> listaShow = Ler();
        String id = "";
        
        Show[] lista = new Show[2000];
        String[] listaPesquisa = new String[100];
        ArvoreDeArvore arvore = new ArvoreDeArvore();
        
        id = sc.nextLine();
        while (!id.equals("FIM")) {
            for (int i = 0; i < listaShow.size(); i++) {
                if (listaShow.get(i).getShow_id().equals(id)) {
                    lista[j] = listaShow.get(i);
                    arvore.inserirNaArvore(listaShow.get(i));
                    j++;
                }
            }
            id = sc.nextLine();
        }
        
        j = 0;
        String titulo = sc.nextLine();
        while (!titulo.equals("FIM")) {
            listaPesquisa[j] = titulo;
            j++;
            titulo = sc.nextLine();
        }
        
        long inicioTempo = System.nanoTime();
        for(int i = 0; i < j; i++){
            arvore.pesquisar(listaPesquisa[i]);
        }
        long fimTempo = System.nanoTime();
        
        double duracao = (fimTempo - inicioTempo) / 1_000_000.0; // em milisegundos
        //Escrever no arquivo de log
        arquivoLog(duracao);
        sc.close();
       
    }
}
//Teste de correção
//tese




