// ---------------------------------------------------------------------------------------------------- //
// Imports

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

//---------------------------------------------------------------------------------------------------- //
    //Classe Hash
//---------------------------------------------------------------------------------------------------- //

class Hash{
    public int contReserva;
    public int tamReserva;
    public int tamTabela;
    public Show[] tabela;
    
    public Hash(){
        this.contReserva = 0;
        this.tamTabela = 21;
        this.tamReserva = 9;
        this.tabela = new Show[(tamReserva+tamTabela)];
    }
    
    public int parseToAscii(String x){
        int resp = 0;
        for(int i = 0; i < x.length(); i++){
            resp += (int)x.charAt(i);
        }
        
        return resp;
    }
    
    public int hash(String x){
        return parseToAscii(x) % tamTabela;
    }
    
    public void inserir(Show x){
        if(x == null){
            throw new RuntimeException("Erro!");
        }
        
        int i = hash(x.getTitle());
        
        if(tabela[i] == null){
            tabela[i] = x;
        }else{
            if(contReserva < tamReserva){
                tabela[tamTabela+contReserva] = x;
                contReserva++;
            }
        }
    }
    
    public boolean pesquisar(String x){
        int i = hash(x);
        boolean resp = false;
        
        Show.comp++;
        if(tabela[i].getTitle().compareTo(x) == 0){
            resp = true;
        }else{
            for(int j = tamTabela; j < (tamTabela+contReserva); j++){
                Show.comp++;
                if(tabela[j].getTitle().compareTo(x) == 0){
                    resp = true;
                    j = tamReserva + tamTabela;
                }
            }
        }
        
        
        
        System.out.println(" (Posicao: " + i + ") " + (resp ? "SIM" : "NAO"));
        return resp;
    }
}

//---------------------------------------------------------------------------------------------------- //
    //Classe Show
//---------------------------------------------------------------------------------------------------- //

public class Show{
    // Global variables
    //public static final String FILE_PATH = "/tmp/disneyplus.csv";
    public static final String FILE_PATH = "/home/felipe/PUCMinas/AEDs/AEDs2/tp02/Q01/disneyplus.csv";
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
            PrintWriter w = new PrintWriter(matricula + "_hashReserva.txt");
            w.printf("%s\t%d\t%fms", matricula, comp, duracao);
            w.close();
        } catch (IOException e) {
            System.err.println("Erro para escrever no arquivo de log: " + e.getMessage());
        }
    }
    
    //---------------------------------------------------------------------------------------------------- //
    //Fução main 
    public static void main(String[] args) {
   
      
        Scanner sc = new Scanner(System.in);
        ArrayList<Show> listaShow = Ler();
        String id = "";
        String[] listaPesquisa = new String[100];
        Hash tabelaHash = new Hash();
        
        id = sc.nextLine();
        while (!id.equals("FIM")) {
            for (int i = 0; i < listaShow.size(); i++) {
                if (listaShow.get(i).getShow_id().equals(id)) {
                    tabelaHash.inserir(listaShow.get(i));
                }
            }
            id = sc.nextLine();
        }
        
        int j = 0;
        String titulo = sc.nextLine();
        while (!titulo.equals("FIM")) {
            listaPesquisa[j] = titulo;
            j++;
            titulo = sc.nextLine();
        }
        
        long inicioTempo = System.nanoTime();
        for(int i = 0; i < j; i++){
            tabelaHash.pesquisar(listaPesquisa[i]);
        }
        long fimTempo = System.nanoTime();
        
        double duracao = (fimTempo - inicioTempo) / 1_000_000.0; // em milisegundos
        //Escrever no arquivo de log
        arquivoLog(duracao);
        sc.close(); 
    
       
    }
}




