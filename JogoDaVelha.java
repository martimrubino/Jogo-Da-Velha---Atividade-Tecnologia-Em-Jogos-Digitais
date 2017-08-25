/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;

import java.util.Scanner;

/**
 *
 * @author fabio.aglubacheski
 */
public class JogoDaVelha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        game();
        
    }
    /*
    Preenche a matriz 3x3 com espacos em brancos e retorna a funcao
    */
    public static char [ ][ ] initialize() {
        // declarar uma matriz de char
        // 3x3
        char M[][]=new char[3][3];
        // inicializa a matriz com espaco em branco
        for(int i=0; i < M.length; i++)// linha
            for(int j=0;j<M[0].length ;j++)// coluna
                M[i][j]=' ';
        return M;
    }
    /*
    Joga na posicao informada. Se a posição conseguiu ser preenchida, step deve
    retornar true e, caso não puder ser preenchida, false.
    */
    public static boolean step (char M[ ][ ], int lin, int col, char gamer){
        // limitar se lin e col são validos
        if( lin >= M.length ||
            col >= M[0].length ||
            lin < 0 ||
            col < 0)
            return false;
        
        // testa se posicao esta vazia
        if( M[lin][col]!=' ')
            return false;
        
        M[lin][col]= gamer; 
        return true;
    }
    /*
    Verifica o status do jogo. -1 (o jogo pode continuar), 0 (ocorreu um empate), 
    1 (O venceu) e 2 (X venceu).
    */
    public static int status (char M[ ][ ]){
        if(statusGamer(M,'O')==true)
            return 1;
        if(statusGamer(M,'X')==true)
            return 2;
         for(int i=0; i < M.length; i++)// linha
            for(int j=0;j<M[0].length ;j++)// coluna
                // se achar espaco posso 
                // continuar jogando, jogo pode
                // continuar (-1)
                if(M[i][j]==' ')
                    return -1;
        // Nenhum jogador ganhou
        // Não achei espaco em branco
        // game empatado
        return 0;
    }
    public static boolean statusGamer(char M[ ][ ], char gamer){
        // verifica se O venceu
        for(int i=0;i<M.length;i++){
            // verifica linhas
            if(M[i][0]==gamer&&M[i][1]==gamer&&M[i][2]==gamer)
                return true;
            // verifica colunas
            if(M[0][i]==gamer&&M[1][i]==gamer&&M[2][i]==gamer)
                return true;
        }
        // verifica diagonal principal
        if(M[0][0]==gamer&&M[1][1]==gamer&&M[2][2]==gamer)
            return true;
        // verifica diagonal secundaria
        if(M[0][2]==gamer&&M[1][1]==gamer&&M[2][0]==gamer)
            return true;
        return false;
    }
    /*
    Printa tabuleiro
    */
    public static void print(char M[][]) {
        
        // inicializa a matriz com espaco em branco
        for(int i=0; i < M.length; i++){// linha
            for(int j=0;j<M[0].length ;j++)// coluna
                System.out.print(M[i][j]+"|");
            
            System.out.println("\n------");
        }
    }
    /*
    Implemente um procedimento para executar a lógica deste jogo. Suponha que o 
    jogador O sempre começa. A cada jogada, a matriz do jogo deverá ser exibida 
    na tela. Ao final do jogo, seu procedimento deve mostrar o estado a que se 
    chegou (vitória ou empate).
    */
    public static void game(){
            Scanner ler = new Scanner(System.in);
            char tabuleiro[][];
            int jogoAtivo=-1, lin, col;
            char gamer = 'O';
            tabuleiro = initialize();
            while( jogoAtivo==-1){
                print(tabuleiro);
                System.out.println("Vez do jogador "+gamer);
                System.out.print("digite a linha:");
                lin = ler.nextInt();
                System.out.print("digite a coluna:");
                col = ler.nextInt();
                
                // joga 
                // se step retornar false volta
                // ao inicio do laco
                if( ! step(tabuleiro,lin,col,gamer)){
                    System.out.println("Digite coluna e linhas validos e escolha uma posicao vazia.");
                    continue;
                }
                //retorna 
                //-1 = pode continuar
                // 0 = velha
                // 1 = jogador O ganhou
                // 2 = jogador X ganhou;
                jogoAtivo = status(tabuleiro);

                if(gamer=='O')
                    gamer='X';
                else
                    gamer='O';
                
            }
            String resposta[]={"deu velha","jogador O ganhou" ,"jogador X ganhou"};
            System.out.println(resposta[jogoAtivo]);
    }
    
}
