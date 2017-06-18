/**
*
*/

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
* @author ireneginani
*
*/
public class Arquivos {


	public boolean Numero(String s) {
    // cria um array de char
    char[] c = s.toCharArray();
    boolean d = true;
    for ( int i = 0; i < c.length; i++ ){
        // verifica se o char não é um dígito
        if ( !Character.isDigit(c[i])) {
            d = false;
            break;
        }
    }
    return d;
}


	private String diretorio = "/proc";
	private static ArrayList<String> arqs = new ArrayList<String>();

	/**
	 * 
	 */
	public Arquivos() throws IOException {

		File file = new File(diretorio);
		File afile[] = file.listFiles();
		int i = 0;
		for (int j = afile.length; i < j; i++) {
			File arquivos = afile[i];
			if (Numero(arquivos.getName())){
				arqs.add(arquivos.getName());
			}
		}

	}
	public void listarDados(){
		try {


			  for(int i = 0; i < arqs.size(); i++){

			      FileReader arq = new FileReader("/proc/"+arqs.get(i)+"/stat");
			      BufferedReader lerArq = new BufferedReader(arq);
			 
			      String linha = lerArq.readLine();

			      while (linha != null) {

			        String texto_split [] = linha.split(" ");
			        System.out.println("PID: "+texto_split[0]);
			        System.out.println("Nome: "+texto_split[1]);
			        System.out.println("Tamanho: "+texto_split[23]+"kb");
			 
			        linha = lerArq.readLine(); // lê da segunda até a última linha
			      }
			 
			      arq.close();
			      System.out.println();
		 	 }

	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			Arquivos v = new Arquivos();
			v.listarDados();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}