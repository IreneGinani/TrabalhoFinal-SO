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
	public void dadosTotais(){

		try{
			FileReader arq = new FileReader("/proc/meminfo");
			BufferedReader lerArq = new BufferedReader(arq);
			String linha0 = lerArq.readLine();
			String linha1 = lerArq.readLine();
			String texto_split0[] = linha0.split(":        ");
			//System.out.println(texto_split0[1]);
			String linha2 = texto_split0[1];
			String texto_split1[] = linha1.split(":         ");
			//System.out.println(texto_split1[1]);
			String linha3 = texto_split1[1];

			String texto_split2 [] = linha2.split(" kB");
			String texto_split3 [] = linha3.split(" kB");

			int mem_total = Integer.parseInt(texto_split2[0]);
			int mem_livre = Integer.parseInt(texto_split3[0]);

			int calma = mem_total - mem_livre;

			float porcentagem = 100 - ((mem_livre * 100)/mem_total);

			System.out.println("Memória usada: ");
			System.out.println(porcentagem); // vc ta usando tanto da memória do seu celular, feche algumas aplicações para melhorar o funcionamento.
		
		}catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	}
	public void processoMaior(){

		try{

			FileReader arq = new FileReader("dados.txt");
			BufferedReader lerArq = new BufferedReader(arq);
			String linha = lerArq.readLine();
			int counter = 0;
			String texto_split [] = null;
			String processo = null;

			while(linha != null){

				if (counter == 1){

					texto_split  = linha.split(" ");
				}

				linha = lerArq.readLine();
				counter++;

			}		
				  for(int i = 0; i < arqs.size(); i++){

			      FileReader arq1 = new FileReader("/proc/"+arqs.get(i)+"/stat");
			      BufferedReader lerArq1 = new BufferedReader(arq1);
			 
			      String linha1 = lerArq1.readLine();

			      while (linha1 != null) {

			        String texto_split1 [] = linha1.split(" ");
			        
			        if (texto_split1[0].equals(texto_split[0])){

			        	 processo = texto_split1[1];
			        }
			 
			        linha1 = lerArq1.readLine(); //  ps -o etime 1( tempo do processo aberto) lê da segunda até a última linha, talvez 14 + 13, tenho que pegar a memoria total, ver as procentagens, e daí mandar notificações caso esteja usando muito do que o além. exibir tbm os que não são de sistema.
			      }
			 
			      arq1.close();
		 	 }

		 	float porcm = Float.parseFloat(texto_split[1]);
		 	float porcc = Float.parseFloat(texto_split[3]);
		 	if(porcm > 8.0){ 
				System.out.println("para melhorar, feche o programa, memoria: ");
				System.out.println(processo);
			}
			if(porcc > 2.0){ 
				System.out.println("para melhorar, feche o programa, cpu: ");
				System.out.println(processo);
			}

		}catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }


	}
	public void comCPU(){
		try {

			 

			  for(int i = 0; i < arqs.size(); i++){

			      FileReader arq = new FileReader("/proc/"+arqs.get(i)+"/stat");
			      BufferedReader lerArq = new BufferedReader(arq);

			 
			      String linha = lerArq.readLine();


			      FileReader arq1 = new FileReader("dados1.txt");
			 	  BufferedReader lerArq1 = new BufferedReader(arq1);

			 	  String linha1 = lerArq1.readLine();
				  
				  String texto_split1 [] = linha1.split(" ");

			 	  linha1 = lerArq1.readLine();


			      while (linha != null){

			        String texto_split [] = linha.split(" ");
			        int numero = Integer.parseInt(texto_split[23]);
			        if(!(texto_split[1].equals("(compiz)"))){
			        	if(!(texto_split[1].equals("(systemd)"))){
			        		if(!(texto_split[1].equals("(Xorg)"))){
			        			if(!(texto_split[1].equals("(irq/47-iwlwifi)"))){

			        				

			        				while (linha1 != null){

									    texto_split1 = linha1.split("  ");

				        				if ((numero > 16000) && (texto_split[0].equals(texto_split1[0]))){ //tentar tirar os espaços em branco, do texto_split1.
									        System.out.println("PID: "+texto_split[0]);
									        System.out.println("Nome: "+texto_split[1]);
									        System.out.println("Tamanho: "+texto_split[23]+"kb"); // listar os processos que estão em execução, consumindo mais que 0.16 de memória. 
									    	System.out.println("CPU:: "+texto_split1[1]); 
									     }

									     linha1 = lerArq1.readLine();
									}
							    }
							}
						}
					}

			 
			        linha = lerArq.readLine(); //  ps -o etime 1( tempo do processo aberto) lê da segunda até a última linha, talvez 14 + 13, tenho que pegar a memoria total, ver as procentagens, e daí mandar notificações caso esteja usando muito do que o além. exibir tbm os que não são de sistema.
			      }
			 
			      arq.close();
		 	 }

	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	}

	public void listarDados(){
		try {

			 

			  for(int i = 0; i < arqs.size(); i++){

			      FileReader arq = new FileReader("/proc/"+arqs.get(i)+"/stat");
			      BufferedReader lerArq = new BufferedReader(arq);

			 
			      String linha = lerArq.readLine();



			      while (linha != null){

			        String texto_split [] = linha.split(" ");
			        int numero = Integer.parseInt(texto_split[23]);
			        if(!(texto_split[1].equals("(compiz)"))){
			        	if(!(texto_split[1].equals("(systemd)"))){
			        		if(!(texto_split[1].equals("(Xorg)"))){
			        			if(!(texto_split[1].equals("(irq/47-iwlwifi)"))){

				        				if (numero > 16000) {
									        System.out.println("PID: "+texto_split[0]);
									        System.out.println("Nome: "+texto_split[1]);
									        System.out.println("Tamanho: "+texto_split[23]+"kb"); // listar os processos que estão em execução, consumindo mais que 0.16 de memória. 
									     }

							    }
							}
						}
					}

			 
			        linha = lerArq.readLine(); //  ps -o etime 1( tempo do processo aberto) lê da segunda até a última linha, talvez 14 + 13, tenho que pegar a memoria total, ver as procentagens, e daí mandar notificações caso esteja usando muito do que o além. exibir tbm os que não são de sistema.
			      }
			 
			      arq.close();
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

			final LocalShell shell = new LocalShell();
        	shell.executeCommand("ps -e -o pid,%mem,%cpu --sort=-pmem,-pcpu | head -2 >> dados.txt");
        	shell.executeCommand("ps -e -o pid,%cpu --sort=-pcpu | head -100 >> dados1.txt");

			Arquivos v = new Arquivos();
			//v.dadosTotais();
			//v.listarDados();
			//v.processoMaior();
			v.comCPU();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}