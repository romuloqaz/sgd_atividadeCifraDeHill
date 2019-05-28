import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Chave {
    int size;
    int[][] grid;

    public Chave(String keyFile) throws FileNotFoundException {

        // lê o arquivo de texto
        Scanner scanner = new Scanner(new File(keyFile));

        // obtem tamanho da matriz
        this.size = scanner.nextInt();

        // aloca na matriz grid
        this.grid = new int[size][size];

        // completa pelas linhas
        for( int row = 0 ; row < this.size ; row++ ){
            // completa pelas colunas
            for( int column = 0; column < this.size ; column++ ){
                // ler cada valor
                this.grid[row][column] = scanner.nextInt();
            }
        }

        scanner.close();
    }

}
