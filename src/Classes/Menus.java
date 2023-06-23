package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menus {

    public int menuInicial(){
        //classe Scanner
        Scanner scanner = new Scanner(System.in);
        int jogos=0;
        try{
        System.out.println("Digite quantos jogos serao lancados: ");
        jogos= scanner.nextInt();
        if(jogos<=0) {
                while (jogos <= 0) {
                    System.out.println("Valor invalido tente novamente:");
                    System.out.println("Digite quantos jogos serao lancados: ");
                    jogos = scanner.nextInt();
                }
            }
        }
        catch(Exception e){

        }

        return jogos;
    }

    public int menuAleatorio(int a){
        Scanner scanner = new Scanner(System.in);
        int jogoAleatorio = 2;
        if(a>=1){
        System.out.printf("Quer jogar os valores de forma aleatoria?\n\t1-Sim\n\t2-Nao\nSelecione: ");
         jogoAleatorio = scanner.nextInt();
        }

        return jogoAleatorio;
    }

    public void menuSorteio(String numero, List<String>senna,List<String>quina,List<String>quadra){
        System.out.printf("NUMERO DO SORTEIO: %s\n", numero);

        System.out.println("Numeros ganhadores da SENNA: ");
        for(int i=0;i<senna.size();i++){
            System.out.println(senna.get(i));
        }
        System.out.println("Numeros ganhadores da QUINA: ");
        for(int i=0;i<quina.size();i++){
            System.out.println(quina.get(i));
        }
        System.out.println("Numeros ganhadores da QUADRA: ");
        for(int i=0;i<quadra.size();i++){
            System.out.println(quadra.get(i));
        }
    }
}
