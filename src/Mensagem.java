import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mensagem {
    String fullMessage;
    String strMensagemFinal;
    char[] mensagemFinal;
    int[][] converteTexto;


    public Mensagem(String inFile, Chave chave) throws FileNotFoundException {
        // configura duas variáveis locais para ser usado nesse método
        String scanMessage = "";
        String result = "";

        // abre o arquivo e acrescenta a mensagem
        Scanner scanner = new Scanner(new File(inFile));
        while (scanner.hasNextLine()) {
            scanMessage += scanner.nextLine();
        }

        // replica a mensagem e  e coloca todas em minúsculas
        result = scanMessage.replaceAll("[^\\p{Alpha}]+","").replaceAll("\\s+", "+").toLowerCase();
        this.strMensagemFinal = result;

        int pad = result.length() % chave.size;
        // avalia se a mensagem precisa de preenchimento ou não
        // se necessario, e necessario instanciar uma versão maior do que apenas o resultado.
        if ( pad != 0 ) {
            this.mensagemFinal = new char[result.length()+pad];
            // aqui continua adicionando H até que não seja mais necessário
            while (result.length() % chave.size != 0) {
                result += 'H';
            }
        }
        else {
            this.mensagemFinal = new char[result.length()];
        }
        // variável de classe strFinal com a nova string finalizada
        this.strMensagemFinal = result;

        // converte a  string final em um array
        this.mensagemFinal = result.toCharArray();
        // cria uma  matriz de conversão de representação do índice das letras
        converteTexto();

        scanner.close();
    }


    public void imprimeTexto() {

        System.out.println("Texto a ser cifrado:");
        // o output deve ser de 80 caracteres por linha
        for (int i = 1; i < this.mensagemFinal.length ; i++ ) {

            if ( (i % 80) == 0 && i != 0 ) {
                System.out.println(""+this.mensagemFinal[i-1]);
            }
            else {
                System.out.print(""+this.mensagemFinal[i-1]);
            }

        }
    }

    public void converteTexto() {

        this.converteTexto = new int[this.mensagemFinal.length][1];
        // lê o texto e troca o char pelo seu índice correspondente
        for (int i = 0; i < mensagemFinal.length ; i++ ) {
            this.converteTexto[i][0] = Utility.findAlphaIndex(this.mensagemFinal[i]);
        }
    }

}
