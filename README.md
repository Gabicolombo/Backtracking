# Backtracking
Backtracking é um algoritmo que olha todos os caminhos possíveis e escolhe aquele melhor que o usuário deseja.
<br>Esse projeto funciona da seguinte forma:
<br>O usuário colocará no arquivo txt a matriz com o mapa do labirinto, onde o X representa um obstáculo.
<br>O arquivo txt deve vir da seguinte forma:
<br><numero de linhas do labirinto> <numero de colunas do labirinto>
<br><linha 0>
<br><linha 1>
<br><linha 2>
<br>(...)
<br><número de itens>
<br><linha item 0> <coluna item 0> <valor item 0> <peso do item 0>
<br><linha item 1> <coluna item 1> <valor item 1> <peso do item 1>
<br><linha item 2> <coluna item 2> <valor item 2> <peso do item 2>
<br>(...)
<br><linha posição de partida> <coluna posição de partida>
<br><linha posição de destino> <coluna posição de destino>
<br>O tipo de caminho melhor a ser escolhido será de acordo com o critério escolhido pelo o usuário na linha de comando.
1. Critério 1: Caminho mais curto. Isto é, que passe pelo menor número possível de casas.
2. Critério 2: Caminho mais longo. Isto é, que passe pelo maior número possível de casas (sem passar duas vezes pela a mesma casa).
3. Critério 3: Caminho mais valioso. Isto é, que maximiza o valor dos itens coletados.
4. Critério 4: Caminho mais rápido. Ou seja, aquele minimiza o tempo que se leva para chegar no destino.
---
<br>No arquivo **java** para compilar é necessário os seguintes comandos:
1. javac Principal.java
2. java Principal mapa1.txt 2
