// ---------------------------------------------------------------------------------------------------- //
// Imports

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.*;

// ---------------------------------------------------------------------------------------------------- //

public class Show{
    // Global variables
    public static final String FILE_PATH = "/tmp/disneyplus.csv";
    //public static final String FILE_PATH = "/home/felipe/PUCMinas/AEDS2/tp02/Q01/disneyplus.csv";
    //public static ArrayList<Show> allShows = new ArrayList<Show>();  //Não utilizado
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
		showzinho.setCast(Separa(atrib.get(4)));
		showzinho.setCountry(atrib.get(5));
		
        //Tratamento da data
        try {
            if(!atrib.get(6).equals("NaN")) {
                String dataNormalizada = atrib.get(6).replace(",", "").trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy", Locale.ENGLISH);
                showzinho.setDate_added(dateFormat.parse(dataNormalizada));
            } else {
                showzinho.setDate_added(null);
            }
        } catch (Exception e) {
            System.err.println("Erro ao converter data: " + atrib.get(6));
            showzinho.setDate_added(null);
        }

		showzinho.setRelease_year(Integer.parseInt(atrib.get(7)));
		showzinho.setRating(atrib.get(8));
		showzinho.setDuration(atrib.get(9));
		showzinho.setListed_in(Separa(atrib.get(10)));
		
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
    
    //+-+-+--+-+-+-+Função para imprimir todos os shows de uma lista +-+-+--+-+-+-+

    public static void ImprimirLista( ArrayList<Show> show){
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);//Tratamento da data
        for(int i=0; i< show.size();i++){
         

                String dataFormatada = (show.get(i).date_added != null) ? outputFormat.format(show.get(i).date_added) : "NaN"; //Tratamento da data
                System.out.print("=> " + show.get(i).Show_id);
                System.out.print(" ## ");
                System.out.print(show.get(i).title);
                System.out.print(" ## ");
                System.out.print(show.get(i).type);
                System.out.print(" ## ");
                System.out.print(show.get(i).director);
                System.out.print(" ## ");
                System.out.print(show.get(i).cast);
                System.out.print(" ## ");
                System.out.print(show.get(i).country);
                System.out.print(" ## ");
                System.out.print(dataFormatada);
                System.out.print(" ## ");
                System.out.print(show.get(i).release_year);
                System.out.print(" ## ");
                System.out.print(show.get(i).rating);
                System.out.print(" ## ");
                System.out.print(show.get(i).duration);
                System.out.print(" ## ");
                System.out.print(show.get(i).listed_in); 
                System.out.println(" ## ");

            
        }
         
    }

    //+-+-+--+-+-+-+Função para imprimir a serie desejada+-+-+--+-+-+-+
    public static void ImprimirPorId(String id, ArrayList<Show> show){
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);//Tratamento da data
        for(int i=0; i< show.size();i++){
            if(show.get(i).getShow_id().equals(id)){

                String dataFormatada = (show.get(i).date_added != null) ? outputFormat.format(show.get(i).date_added) : "NaN"; //Tratamento da data
                System.out.print("=> " + show.get(i).Show_id);
                System.out.print(" ## ");
                System.out.print(show.get(i).title);
                System.out.print(" ## ");
                System.out.print(show.get(i).type);
                System.out.print(" ## ");
                System.out.print(show.get(i).director);
                System.out.print(" ## ");
                System.out.print(show.get(i).cast);
                System.out.print(" ## ");
                System.out.print(show.get(i).country);
                System.out.print(" ## ");
                System.out.print(dataFormatada);
                System.out.print(" ## ");
                System.out.print(show.get(i).release_year);
                System.out.print(" ## ");
                System.out.print(show.get(i).rating);
                System.out.print(" ## ");
                System.out.print(show.get(i).duration);
                System.out.print(" ## ");
                System.out.print(show.get(i).listed_in); 
                System.out.println(" ## ");

            }
        }
         
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
    public static ArrayList<Show> Ler(){
        String linha = "";
		int aux = 0;
		ArrayList <Show> show = new ArrayList<>(); 

		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
			while ((linha = br.readLine()) != null) {

                if(aux >= 1){
					//System.out.println(linha + '\n');
					show.add(TrasInObj(linha));
				}

				aux++;
                
                
			}
            br.close();
		} catch (Exception e) {
			System.out.println("Erro ao ler arquivo!!");
		}
		return show;
        
    }

    //+-+-+--+-+-+-+ Função Pesquisa Sequencial +-+-+--+-+-+-+
    public static void pesqSeq(ArrayList<Show> lista, String title){
        boolean resp = false;
        int n = lista.size();
  
        for(int i = 0; i < n; i++){
           if(lista.get(i).title.equals(title)){
               resp = true;
               i = n;
            }
            comp++;
        }
         
        if(resp){
            System.out.println("SIM");
        }else{
            System.out.println("NAO");
        }
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
     //+-+-+--+-+-+-+ Algoritmo de Seleção(title) +-+-+--+-+-+-+
     public static void Selecao(ArrayList<Show> lista) {
        int n = lista.size();
        for (int i = 0; i < 10; i++) {
            int menor = i;
            for (int j = i + 1; j < n; j++) {
                if (lista.get(j).getTitle().compareToIgnoreCase(lista.get(menor).getTitle()) < 0) {
                    menor = j;
                }
                comp++;
            }
            swap(lista, menor, i); 
        }
    }
//+-+-+--+-+-+-+ Algoritmo de Inserção(type) +-+-+--+-+-+-+
    public static void Insecao(ArrayList<Show> lista) {
        int n = lista.size();
        for (int i = 1; i < n; i++) {
            Show tmp = lista.get(i);
            int j = i - 1;
    
            // Compara type (e title em caso de empate)
            while ((j >= 0) && (lista.get(j).getType().compareToIgnoreCase(tmp.getType()) > 0 || (lista.get(j).getType().equalsIgnoreCase(tmp.getType()) && lista.get(j).getTitle().compareToIgnoreCase(tmp.getTitle()) > 0))) {
                lista.set(j + 1, lista.get(j));
                j--;
                comp++;
                mov++;  
            }
            lista.set(j + 1, tmp);
            mov++; 
        }
    }
    //+-+-+--+-+-+-+ Algoritmo Heapsort (director) +-+-+--+-+-+-+
public static void heapSort(ArrayList<Show> lista) {
    int n = lista.size();
    
    ArrayList<Show> heap = new ArrayList<>(n+1);
    heap.add(null);
    heap.addAll(lista);

    // Construção do heap
    for(int tamHeap = 2; tamHeap <= n; tamHeap++){
        construir(heap, tamHeap);
    }

    // Ordenação propriamente dita
    int tamHeap = n;
    while(tamHeap > 1){
        swap(heap, 1, tamHeap--);
        reconstruir(heap, tamHeap);
    }

    // Copiar os elementos ordenados de volta para a lista original
    lista.clear();
    lista.addAll(heap.subList(1, n+1));
}

private static void construir(ArrayList<Show> heap, int tamHeap) {
    int i = tamHeap;
    while(i > 1 && compareShows(heap.get(i), heap.get(i/2)) > 0){
        comp++; 
        swap(heap, i, i/2);
        i /= 2;
    }
    if(i > 1) comp++;
}

private static void reconstruir(ArrayList<Show> heap, int tamHeap) {
    int i = 1;
    while(i <= (tamHeap/2)){
        int filho = getMaiorFilho(heap, i, tamHeap);
        comp++; 
        
        if(compareShows(heap.get(filho), heap.get(i)) > 0){
            comp++; 
            swap(heap, i, filho);
            i = filho;
        }else{
            comp++; 
            i = tamHeap + 1;
        }
    }
}

private static int getMaiorFilho(ArrayList<Show> heap, int i, int tamHeap) {
    comp++; 
    
    if (2*i == tamHeap) {
        return 2*i;
    } else {
        comp++;
        return (compareShows(heap.get(2*i), heap.get(2*i+1)) > 0) ? 2*i : 2*i+1;
    }
}

private static int compareShows(Show a, Show b) {
    comp++;
    
    // Primeiro compara director
    int cmpDirector = a.getDirector().compareToIgnoreCase(b.getDirector());
    if (cmpDirector != 0) return cmpDirector;
    
    // Em caso de empate, compara title
    return a.getTitle().compareToIgnoreCase(b.getTitle());
}
 //+-+-+--+-+-+-+ Termina aqui o Algoritmo Heapsort (director) +-+-+--+-+-+-+
 
 
 //+-+-+--+-+-+-+ Algoritmo Counting Sort (realeas_year) +-+-+--+-+-+-+
 public static void countingSort(ArrayList<Show> lista) {
    if (lista.isEmpty()) return;

    // Encontrar o ano mínimo e máximo
    int minAno = lista.get(0).getRelease_year();
    int maxAno = lista.get(0).getRelease_year();
    for (Show show : lista) {
        if (show.getRelease_year() < minAno) minAno = show.getRelease_year();
        if (show.getRelease_year() > maxAno) maxAno = show.getRelease_year();
        comp += 2;
    }

    // Criar array de contagem
    int range = maxAno - minAno + 1;
    int[] count = new int[range];
    ArrayList<ArrayList<Show>> countShows = new ArrayList<>(range);
    
    for (int i = 0; i < range; i++) {
        countShows.add(new ArrayList<>());
    }

    // Contar ocorrências e agrupar shows por ano
    for (Show show : lista) {
        int index = show.getRelease_year() - minAno;
        count[index]++;
        countShows.get(index).add(show);
        mov++;
    }

    // Ordenar cada grupo por título (usando Selection Sort)
    for (ArrayList<Show> group : countShows) {
        if (!group.isEmpty()) {
            Selecao(group);
        }
    }

    // Reconstruir a lista ordenada
    lista.clear();
    for (int i = 0; i < range; i++) {
        lista.addAll(countShows.get(i));
        mov += countShows.get(i).size();
    }
}

 //+-+-+--+-+-+-+ Algoritmo Merge Sort (duration) +-+-+--+-+-+-+
public static void mergeSort(ArrayList<Show> lista) {
    if (lista.size() > 1) {
        int meio = lista.size() / 2;
        
        ArrayList<Show> esquerda = new ArrayList<>(lista.subList(0, meio));
        ArrayList<Show> direita = new ArrayList<>(lista.subList(meio, lista.size()));
        
        mergeSort(esquerda);
        mergeSort(direita);
        
        merge(lista, esquerda, direita);
    }
}

public static void merge(ArrayList<Show> lista, ArrayList<Show> esquerda, ArrayList<Show> direita) {
    int i = 0, j = 0, k = 0;
    
    while (i < esquerda.size() && j < direita.size()) {
        comp++;
        //compara a duração
        int cmpDuration = esquerda.get(i).getDuration().compareTo(direita.get(j).getDuration());
        
        if (cmpDuration < 0) {
            lista.set(k++, esquerda.get(i++));
            mov++;
        } else if (cmpDuration > 0) {
            lista.set(k++, direita.get(j++));
            mov++;
        } else {
            //Se a duração for igual, compara por titulo
            comp++;
            int cmpTitle = esquerda.get(i).getTitle().compareTo(direita.get(j).getTitle());
            if (cmpTitle <= 0) {
                lista.set(k++, esquerda.get(i++));
                mov++;
            } else {
                lista.set(k++, direita.get(j++));
                mov++;
            }
        }
    }
    
  
    while (i < esquerda.size()) {
        lista.set(k++, esquerda.get(i++));
        mov++;
    }
    
    while (j < direita.size()) {
        lista.set(k++, direita.get(j++));
        mov++;
    }
}

    //---------------------------------------------------------------------------------------------------- //
    //Fução main 
    public static void main(String[] args){
    
      //Crio as variaveis
        Scanner sc = new Scanner(System.in);
        ArrayList <Show> show = Ler();
        ArrayList <Show> lista = new ArrayList<>();
		String id = sc.nextLine();
        String title;
        double inicio, fim, duracao=0;///
        
        //Loop para criar o sub-array 
        while (!id.equals("FIM")) {  
            for(int i=0; i< show.size();i++){
                if(show.get(i).getShow_id().equals(id)){
                    lista.add(show.get(i).clone());
                }
            }
            id = sc.nextLine();      
        }
        //Aqui ordena pelo metodo Selection sort e cronometra o tempo de execução 
        inicio = System.nanoTime();
        Selecao(lista);
        fim = System.nanoTime();
        duracao += (fim - inicio)/1000000;

        for(int i=0;i<10;i++){
           ImprimirPorId(lista.get(i).Show_id, lista);
        }
    
        
        
        //System.out.println(duracao + " milisegundos"    +   "\n"    +   "Comparacoes: " + comp    +   "\n" + "Movimentacoes: " + mov);///
        
		

            
        sc.close();
    }
}


  



