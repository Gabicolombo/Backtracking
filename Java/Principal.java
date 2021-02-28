public class Principal {

  // Metodo que verifica se o caminho encontra-se livre ou bloquado
  public static boolean verifica(Map map, int lin, int col){
    try{
      if(map.free(lin, col)) return true;
    }
    catch (Exception e){}
    return false;
  }
  
  // Metodo que percorre todos os caminhos utilizando recursao
  public static void tentativa_e_erro(Solucao path, int lin, int col, Map map, int criterio ){
    if(verifica(map, lin, col)){
      map.step(lin, col);
      // Caso base
      if(map.finished(lin, col)){
        path.lista.add(lin);
        path.lista.add(col);
        // Se for a primeira vez que encontrou um caminho
        if(Solucao.melhor == null) Solucao.melhor = new Solucao(path);
        else{        // se nao for a primeira vez, faz a comparacao e coloca no atributo estatico melhor
          Solucao.melhor = new Solucao(path.criterio(Solucao.melhor, path, criterio, map));
        }

        path.lista.remove(path.lista.size()-1);
        path.lista.remove(path.lista.size()-2);
        map.desfaz_passo(lin, col);
        return;
      }

      path.lista.add(lin);
      path.lista.add(col);
      
      // recursoes
      tentativa_e_erro(path, lin-1, col, map, criterio);
      tentativa_e_erro(path, lin, col+1, map, criterio);
      tentativa_e_erro(path, lin+1, col, map, criterio);
      tentativa_e_erro(path, lin, col-1, map, criterio);
      
      path.lista.remove(path.lista.size()-1);
      path.lista.remove(path.lista.size()-1);
      map.desfaz_passo(lin, col);
    }


    return;
  }

  // Metodo que mostra o melhor caminho de acordo com o criterio e imprime a saída conforme especificações do EP.
  public static void printSolution(Map map, Solucao path){

    Item item = null;
		int totalItems = 0;
		int totalValue = 0;
		int totalWeight = 0;

		int path_size = Solucao.melhor.lista.size();
		System.out.println((path_size)/2 + " " + path.pega_tempo(Solucao.melhor, map));

		for(int i = 0; i < path_size; i += 2){

			int lin = path.lista.get(i);
			int col = path.lista.get(i+1);
			item = map.getItem(lin, col);

			System.out.println(lin + " " + col);

			if(item != null){

				totalItems ++;
				totalValue += item.getValue();
				totalWeight += item.getWeight();
      }
     
		}
    System.out.println(totalItems + " " + totalValue + " " + totalWeight);
    for(int i = 0; i < path_size; i += 2){

			int lin = path.lista.get(i);
			int col = path.lista.get(i+1);
			item = map.getItem(lin, col);
      if(item!= null) System.out.println(item.getLin() + " " + item.getCol());     
		}
    
	}
  public static void main(String [] args){
    Map map = new Map(args[0]);
    int criterio = Integer.parseInt(args[1]);

    Solucao path = new Solucao();
    int lin = map.startLin;
    int col = map.startCol;
    
    
    tentativa_e_erro(path, lin, col, map, criterio);
    printSolution(map, Solucao.melhor);
  }
}
