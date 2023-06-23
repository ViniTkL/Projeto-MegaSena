package Classes;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;

public class Funcionalidades {

    private Menus menu = new Menus();
    private final int[] numeros = new int[6];
    private List<String> numerosOrdenados = new ArrayList<>();
    private List<String> numerosSena = new ArrayList<>();
    private List<String> numerosQuadra = new ArrayList<>();
    private List<String> numerosQuina = new ArrayList<>();

    private List<String> numerosTres = new ArrayList<>();

    private List<String> numerosDois = new ArrayList<>();
    private List<String> numerosUm = new ArrayList<>();
    private List<String> numerosNenhum = new ArrayList<>();
    private String numerosSorteioOrdenados;

    private final int[] numeroDoSorteio = new int[6];

    public void roletar() {
        Scanner lt = new Scanner(System.in);
        //recebe o numero de jogos
        int a = menu.menuInicial();
        if(a<=0){
            while(a<=0){
                System.out.println("Valor invalido, tente novamente");
                a=menu.menuInicial();
            }
        }
        int b = menu.menuAleatorio(a);
        if (b == 1) {
            jogarAleatorio(a);
            verificarJogosIguaisAleatorios(a);
        } else if (b == 2) {
            for (int j = 0; j < a; j++) {
                System.out.printf("Jogo %d:\n", j + 1);
                //for para cada numero do jogo
                for (int i = 0; i < 6; i++) {
                    System.out.printf("Digite o %d° numero : ", i + 1);
                    numeros[i] = lt.nextInt();
                    if(numeros[i]>60){
                        while(numeros[i]>60){
                            System.out.printf("Digite o %d° numero novamente : ", i + 1);
                            numeros[i] = lt.nextInt();
                        }
                    }
                }
                for (int i = 0; i < 6; i++) {
                    for (int d = i + 1; d < 6; d++) {
                        if (numeros[i] == numeros[d]) {
                            int chave = numeros[d];
                            while (numeros[i] == chave) {
                                System.out.printf("Digite novamente o numero na posição %d: ", d + 1);
                                chave = lt.nextInt();
                                numeros[d] = chave;
                            }
                        }
                    }
                }
                verificaNumerosSorteados(numeros,numeroDoSorteio);
                Arrays.sort(numeros);
                StringBuilder sb = new StringBuilder();
                for (int c : numeros){ sb.append(c+" ");}
                verificaNumerosSorteados(numeros,numeroDoSorteio);
                numerosOrdenados.add(String.valueOf(sb));
                System.out.println(numerosOrdenados.get(j));

                boolean ab = verificaExistencia();
                if (ab != false) {
                    escreveArquivo();
                }

            }
            verificarJogosIguais(a);
            menu.menuSorteio(numerosSorteioOrdenados,numerosSena,numerosQuina,numerosQuadra);
        } else {
            while ((b != 1) && (b != 2)) {
                System.out.println("Valor invalido");
                b = menu.menuAleatorio(a);
            }
        }        //for para cada jogo


    }


    public Path caminho() {
        Path caminhoArquivo = Paths.get("C:\\Users\\vinim\\OneDrive\\Desktop\\Laboratorio de progaramação\\Lançados.txt");

        return (caminhoArquivo);
    }

    public boolean verificaExistencia() {
        Path caminhoA = caminho();
        boolean exist = Files.exists(caminhoA);

        return (exist);
    }

    public void escreveArquivo() {
        Path arquivo = caminho();
        String arquivoCaminho = String.valueOf(arquivo);

        try {
            FileWriter escreveNumero = new FileWriter(arquivoCaminho);
            BufferedWriter bf = new BufferedWriter(escreveNumero);
            for (int i = 0; i < numerosOrdenados.size(); i++) {
                bf.write(numerosOrdenados.get(i));
                bf.newLine();
            }
            bf.flush();
            bf.close();

        } catch (Exception e) {

        }
    }


    public void jogarAleatorio(int a) {
        for (int j = 0; j < a; j++) {
            System.out.printf("jogo %d\t", j + 1);
            for (int i = 0; i < 6; i++) {
                numeros[i] = new SecureRandom().nextInt(60) + 1;


            }
            for (int i = 0; i < 6; i++) {
                for (int d = i + 1; d < 6; d++) {
                    if (numeros[i] == numeros[d]) {
                        int chave = numeros[d];
                        while (numeros[i] == chave) {

                            chave = new SecureRandom().nextInt(60) + 1;
                            numeros[d] = chave;
                        }
                    }
                }
            }
            Arrays.sort(numeros);
            StringBuilder sb = new StringBuilder();
            for (int c : numeros){ sb.append(c+" ");}
            verificaNumerosSorteados(numeros,numeroDoSorteio);
            numerosOrdenados.add(String.valueOf(sb));
            System.out.println(numerosOrdenados.get(j));
        }
        boolean ab = verificaExistencia();
        if (ab != false) {
            escreveArquivo();
        }
        menu.menuSorteio(numerosSorteioOrdenados,numerosSena,numerosQuina,numerosQuadra);
    }


    public void verificarJogosIguais(int a) {

           for (int j = 0; j < a; j++) {
               for (int i = j + 1; i <= a; i++) {
                   try{
                   String clone = numerosOrdenados.get(i);
                       if (clone.equals(numerosOrdenados.get(j)) != false) {
                           rejogar(a, i, j);
                       }
                   }
                   catch (Exception e) {

                   }

               }
           }

    }

    public void verificarJogosIguaisAleatorios(int a) {
        for (int j = 0; j < 1; j++) {
            for (int i = j + 1; i < a; i++) {
                try{
                String clone = numerosOrdenados.get(i);
                if (clone.equals(numerosOrdenados.get(j))!=false) {
                    rejogarAleatorio(a, i,j);
                    }
                }
                catch (Exception d){

                }
            }
        }

    }


    public void rejogar(int a, int b,int c) {
        Scanner lt = new Scanner(System.in);
        for (int j = 0; j < 1; j++) {
            System.out.printf("Jogo %d:\n", c + 1);
            //for para cada numero do jogo
            for (int i = 0; i < 6; i++) {
                System.out.printf("Digite o %d° numero : ", i + 1);
                numeros[i] = lt.nextInt();
                if(numeros[i]>60){
                    while(numeros[i]>60){
                        System.out.printf("Digite o %d° numero novamente : ", i + 1);
                        numeros[i] = lt.nextInt();
                    }
                }
            }
            for (int i = 0; i < 6; i++) {
                for (int d = i + 1; d < 6; d++) {
                    if (numeros[i] == numeros[d]) {
                        int chave = numeros[d];
                        while (numeros[i] == chave) {
                            System.out.printf("Digite novamente o numero na posição %d: ", d + 1);
                            chave = lt.nextInt();
                            numeros[d] = chave;
                        }
                    }
                }
            }
            verificaNumerosSorteados(numeros,numeroDoSorteio);
            Arrays.sort(numeros);
            StringBuilder sb = new StringBuilder();
            for (int cc : numeros){ sb.append(cc+" ");}
            verificaNumerosSorteados(numeros,numeroDoSorteio);
            numerosOrdenados.add(String.valueOf(sb));
            System.out.println(numerosOrdenados.get(b));

            boolean ab = verificaExistencia();
            if (ab) {
                escreveArquivo();
            }
        }
    }

    public void rejogarAleatorio(int a, int b,int c) {
        for (int j = 0; j < a; j++) {
            System.out.printf("jogo %d\t", c + 1);
            for (int i = 0; i < 6; i++) {
                numeros[i] = new SecureRandom().nextInt(60) + 1;
            }
        for (int i = 0; i < 6; i++) {
            for (int d = i + 1; d < 6; d++) {
                if (numeros[i] == numeros[d]) {
                    int chave = numeros[d];
                    while (numeros[i] == chave) {

                        chave = new SecureRandom().nextInt(60) + 1;
                        numeros[d] = chave;
                    }
                }
            }
        }
        verificaNumerosSorteados(numeros,numeroDoSorteio);
            Arrays.sort(numeros);
            StringBuilder sb = new StringBuilder();
            for (int cc : numeros){ sb.append(cc+" ");}
            verificaNumerosSorteados(numeros,numeroDoSorteio);
            numerosOrdenados.add(String.valueOf(sb));
            System.out.println(numerosOrdenados.get(b));

        boolean ab = verificaExistencia();
        if (ab) {
            escreveArquivo();
        }
    }
}

public void sorteio(){
    for(int i=0;i<numeroDoSorteio.length;i++){
        numeroDoSorteio[i] = new SecureRandom().nextInt(60)+1;
    }
    for (int i = 0; i < 6; i++) {
        for (int d = i + 1; d < 6; d++) {
            if (numeroDoSorteio[i] == numeroDoSorteio[d]) {
                int chave = numeroDoSorteio[d];
                while (numeroDoSorteio[i] == chave) {
                    chave = new SecureRandom().nextInt(60)+1;
                    numeroDoSorteio[d] = chave;
                }
            }
        }
    }

    Arrays.sort(numeroDoSorteio);
    StringBuilder sb = new StringBuilder();
    for (int c : numeroDoSorteio){ sb.append(c+" ");}
    verificaNumerosSorteados(numeros,numeroDoSorteio);
    numerosSorteioOrdenados = String.valueOf(sb);
    if(verificaExistenciaSorteio()){
        escreveArquivoSorteio();
    }
}





public void verificaNumerosSorteados(int[] numerosDigitados,int[] numeroDoSorteio){
        Arrays.sort(numerosDigitados); Arrays.sort(numeroDoSorteio);
        int cont=0;
        if(Arrays.equals(numerosDigitados,numeroDoSorteio)){
            StringBuilder sb = new StringBuilder();
            for (int c : numerosDigitados){ sb.append(c+" ");}
            numerosSena.add(String.valueOf(sb));
        }else{
            for(int i=0;i<6;i++){
                if(numerosDigitados[i] == numeroDoSorteio[i]){
                    cont++;
                }
        }
            if(cont == 4){
                StringBuilder sb = new StringBuilder();
                for (int c : numerosDigitados){ sb.append(c+" ");}
                numerosQuadra.add(String.valueOf(sb));
            }else if(cont==5){
                StringBuilder sb = new StringBuilder();
                for (int c : numerosDigitados){ sb.append(c+" ");}
                numerosQuina.add(String.valueOf(sb));
            }else if(cont==3){
                StringBuilder sb = new StringBuilder();
                for (int c : numerosDigitados){ sb.append(c+" ");}
                numerosTres.add(String.valueOf(sb));
            }else if(cont==2){
                StringBuilder sb = new StringBuilder();
                for (int c : numerosDigitados){ sb.append(c+" ");}
                numerosDois.add(String.valueOf(sb));
            }else if(cont==1){
                StringBuilder sb = new StringBuilder();
                for (int c : numerosDigitados){ sb.append(c+" ");}
                numerosUm.add(String.valueOf(sb));
            }else if(cont==0){
                StringBuilder sb = new StringBuilder();
                for (int c : numerosDigitados){ sb.append(c+" ");}
                numerosNenhum.add(String.valueOf(sb));
            }

        }
    if(verificaExistenciaResultado()){
        escreveArquivoResultado();
    }
}

    public Path caminhoSorteio() {
        Path caminhoArquivoSorteio = Paths.get("C:\\Users\\vinim\\OneDrive\\Desktop\\Laboratorio de progaramação\\Sorteio.txt");

        return (caminhoArquivoSorteio);
    }

    public boolean verificaExistenciaSorteio() {
        Path caminhoA = caminhoSorteio();
        boolean exist = Files.exists(caminhoA);

        return (exist);
    }

    public void escreveArquivoSorteio() {
        Path arquivo = caminhoSorteio();
        String arquivoCaminho = String.valueOf(arquivo);

        byte[] textoByte = numerosSorteioOrdenados.getBytes();
        try {
            Files.write(arquivo, textoByte);
        }
        catch (Exception e) {

        }

    }

    public Path caminhoResultado() {
        Path caminhoArquivoResultado = Paths.get("C:\\Users\\vinim\\OneDrive\\Desktop\\Laboratorio de progaramação\\Resultado.txt");

        return (caminhoArquivoResultado);
    }

    public boolean verificaExistenciaResultado() {
        Path caminhoA = caminhoResultado();
        boolean exist = Files.exists(caminhoA);

        return (exist);
    }

    public void escreveArquivoResultado() {
        Path arquivo = caminhoResultado();
        String arquivoCaminho = String.valueOf(arquivo);

        try {
            FileWriter escreveNumero = new FileWriter(arquivoCaminho);
            BufferedWriter bf = new BufferedWriter(escreveNumero);
            bf.write("Sena");
            bf.newLine();
            for (int i = 0; i < numerosSena.size(); i++) {
                bf.write(numerosSena.get(i));
                bf.newLine();
            }
            bf.write("Quina");
            bf.newLine();
            for (int i = 0; i < numerosQuina.size(); i++) {
                bf.write(numerosQuina.get(i));
                bf.newLine();
            }
            bf.write("Quadra");
            bf.newLine();
            for (int i = 0; i < numerosQuadra.size(); i++) {
                bf.write(numerosQuadra.get(i));
                bf.newLine();
            }
            bf.write("Tres acertos");
            bf.newLine();
            for (int i = 0; i < numerosTres.size(); i++) {
                bf.write(numerosTres.get(i));
                bf.newLine();
            }
            bf.write("Dois acertos");
            bf.newLine();
            for (int i = 0; i < numerosDois.size(); i++) {
                bf.write(numerosDois.get(i));
                bf.newLine();
            }
            bf.write("Um acerto");
            bf.newLine();
            for (int i = 0; i < numerosUm.size(); i++) {
                bf.write(numerosUm.get(i));
                bf.newLine();
            }
            bf.write("Nenhum acerto");
            bf.newLine();
            for (int i = 0; i < numerosNenhum.size(); i++) {
                bf.write(numerosNenhum.get(i));
                bf.newLine();
            }
            bf.flush();
            bf.close();

        } catch (Exception e) {

        }
    }





}