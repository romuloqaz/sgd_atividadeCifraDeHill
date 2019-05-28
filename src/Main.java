import java.io.FileNotFoundException;

public class Main {
    static char[] gradeFinal;

    public static void main(String[] args) throws FileNotFoundException {

        // cria um novo objeto para pegar os valores da chave e mensagem
        // baseados em arquios .txt
        Chave inKey = new Chave("chave.txt");
        Mensagem mensagem= new Mensagem("textoEncriptar.txt",inKey);



        // imprime a mensagem
        mensagem.imprimeTexto();
        System.out.println();

        // executa o metodo de encriptaçao
        encriptar(inKey,mensagem);
        System.out.println();

        // imprime o texto cifrado
        textoCifrado();
        System.out.println();

    }

    public static void encriptar(Chave chave, Mensagem mensagem) {
        // passar por mensagens convertidas divididas em partes do tamanho da chave
        // multiplique estes pedaços pela chave mod cada resultado por 26
        // resulta em uma matriz de caracteres gradeFinal

        // temp armazenará os trechos da mensagem que serão multiplicados pela chave
        int[][] temp = new int[chave.size][1];
        // product conterá o produto da multiplicação da matriz entre os fragmentos de chave e de mensagem
        int[][] product = new int[chave.size][1];
        // instanciar o gradeFinal, isso manterá nosso texto cifrado como um array do tipo char
        gradeFinal = new char[mensagem.converteTexto.length];
        int findex = 0;

        // loop através da matriz de caracteres da mensagem
        // aqui estamos adicionando key.size a i, a cada vez
        // então obtemos o tamanho correto para multiplicar
        for (int i = 0; i < mensagem.converteTexto.length ; i = i + chave.size ) {

            // preenche a matriz temp com os blocos
            for (int j = 0 ; j < chave.size ; j++ ) {
                temp[j][0] = mensagem.converteTexto[i+j][0];
            }

            product = multiplicar(chave.grid,temp);

            // pegar o product e adiciona ele ao array gradeFinal
            // certificando-se de modificar cada resultado por 26 e converter de volta para uma letra
            for ( int k = 0 ; k < chave.size ; k++ ) {
                gradeFinal[findex] = Utility.findLetter(product[k][0] % 26);
                // we do not want the index of gradeFinal to restart every iteration of the larger loop
                // so I added its own index counter
                findex++;
            }
        }
    }

    // retorna C = A * B
    public static int[][] multiplicar(int[][] A, int[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = B[0].length;
        if (nA != mB) throw new RuntimeException("matriz invalida");
        int[][] C = new int[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += (A[i][k] * B[k][j]);
        return C;
    }

    public static void textoCifrado() {
        System.out.println("Texto cifrado:");
        //a saída deve ser de 80 caracteres por linha
        for (int i = 1; i < gradeFinal.length+1 ; i++ ) {

            if ( (i % 80) == 0 && i != 0 ) {
                System.out.println(""+ gradeFinal[i-1]);
            }
            else {
                System.out.print(""+ gradeFinal[i-1]);
            }
        }
    }
}
