import java.util.ArrayList;

public class Solucao {
  public static Solucao melhor;
  ArrayList <Integer> lista=  new ArrayList<Integer>(); 

  int totalItems = 0;
  int totalValues = 0;
  int totalWeight = 0;

  public Solucao (){
    ArrayList <Integer> lista= new ArrayList<Integer>(); 
  }

  // Construtor que copia o primeiro caminho encontrado
  public Solucao(Solucao copia){
    this.lista = new ArrayList<Integer>(copia.lista);
  }

  // Metodo que calcula o tempo do caminho passado por parametro
  public float pega_tempo(Solucao a, Map map){  
    Solucao tempo = new Solucao(a);  
    
    Item item  = null;
    int p = 0; // peso 
    item = map.getItem(map.startLin, map.startCol);
    if(item != null) p = item.getWeight();
    float time = 0;
    float div =0;
    
    for(int i = 2; i < tempo.lista.size(); i +=2){
      int lin = tempo.lista.get(i);
      int col = tempo.lista.get(i+1);
      item = map.getItem(lin, col);

      div = (float)p/10;
      time += Math.pow((1+div),2);

      if(item != null){
        p += item.getWeight();
        totalWeight += item.getWeight();
      }
     
    }
    return time;
  }

  
  public Solucao criterio(Solucao melhor, Solucao b, int criterio, Map map){
    switch (criterio){
			case 1 : melhor = caminho_mais_curto (melhor, b);
				break;
			case 2 : melhor = caminho_mais_longo (melhor, b);
				break;
			case 3 : melhor = caminho_mais_valioso (melhor, b, map);
				break;
			case 4 : melhor = caminho_mais_rapido (melhor, b, map);
				break;
    }
    return melhor;
  }

  // Metodo que calcula o criterio 1
  public Solucao caminho_mais_curto(Solucao a, Solucao b){
    int tamA = a.lista.size();
    int tamB = b.lista.size();

    if(tamA > tamB) return b;
    return a;
  }

  // Metodo que calcula o criterio 2
  public Solucao caminho_mais_longo(Solucao a, Solucao b){
    int tamA = a.lista.size();
    int tamB = b.lista.size();

    if(tamA > tamB) return a;
    return b;
  }

  // Metodo que calcula o criterio 3
  public Solucao caminho_mais_valioso(Solucao a, Solucao b, Map map){
    Solucao caminhoA = new Solucao(a);
    Solucao caminhoB = new Solucao(b);

    for(int i = 0; i < a.lista.size(); i +=2){
      int lin = caminhoA.lista.get(i);
      int col = caminhoA.lista.get(i+1);
      Item item = map.getItem(lin, col);

      if(item != null) caminhoA.totalValues += item.getValue();      
    }

    for(int i = 0; i < caminhoB.lista.size(); i +=2){
      int lin = caminhoB.lista.get(i);
      int col = caminhoB.lista.get(i+1);
      Item item = map.getItem(lin, col);

      if(item != null) caminhoB.totalValues += item.getValue();     
    }

    if(caminhoA.totalValues < caminhoB.totalValues) return b;
    return a;
  }
  
  // Metodo que calcula o criterio 4
  public Solucao caminho_mais_rapido (Solucao a, Solucao b, Map map){
    float timeA = pega_tempo(a, map);
    float timeB = pega_tempo(b, map);
    
    if(timeA > timeB) return b;
    return a;
    
  }

}