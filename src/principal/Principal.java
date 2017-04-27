package principal;

import java.util.Scanner;
import static principal.Cliente.listaDeClientes;
import static principal.Filme.listaDeFilmes;
import static principal.Locar.listaDeLocacao;

public class Principal {  //classe principal
    
    Scanner scan = new Scanner(System.in, "UTF-8");
    Cliente cliente = new Cliente();
    Filme filme = new Filme();
    Locar locar = new Locar();
    public static void main(String[] args) {   //metodo principal
        new Cliente().recuperar();
        new Filme().recuperar();
        new Locar().recuperar();
        new Principal().sistema();
    }

    @SuppressWarnings("InfiniteRecursion")
    public void sistema(){
     try{
        imprimirMenu();             
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                cliente.cadastrar();
                break;
            case 2:
                cliente.pesquisar();
                break;
            case 3:
                cliente.editar();
                break;
            case 4:
                cliente.excluir();
                break;
            case 5:
                filme.cadastrar();
                break;
            case 6:
                filme.pesquisar();
                break;
            case 7:
                filme.editar();
                break;
            case 8:
                filme.excluir();
                break;
            case 9:
                filme.locar();
                break;
            case 10:
                filme.desalocar();
                break;
            case 11:
                new Cliente().armazenar(listaDeClientes);
                new Filme().armazenar(listaDeFilmes);
                new Locar().armazenar(listaDeLocacao);
                break;
            case 0:
                System.out.print("Deseja realmente encerrar o sitema? (S/N): ");
                String decisao = scan.nextLine();
                switch (decisao){
                    case "S": case "s":
                        System.out.print("Deseja salvar as alteraçõoes feitas? (S/N): ");
                        decisao = scan.nextLine();
                        switch (decisao){
                            case "S": case "s":
                                new Cliente().armazenar(listaDeClientes);
                                new Filme().armazenar(listaDeFilmes);
                                new Locar().armazenar(listaDeLocacao);
                                break;
                        }
                        System.exit(0);
                    break;
                }
                break;            
            default:
                System.out.println("Digite uma opção válida");
                break;
        }
        
     } catch (IndexOutOfBoundsException e){
        
     } catch (NumberFormatException e){
        System.out.println("Erro: Digite apenas números");
     } catch (Exception e){
        System.out.println("Erro: "+ e);
     } finally {
         sistema();
     }
    }
    
    public void imprimirMenu(){
        System.out.println("=============================");
        System.out.println("======= Vídeo Locadora ======");
        System.out.println("=============================");
        System.out.println("[1] Cadastrar cliente");
        System.out.println("[2] Pesquisar cliente");
        System.out.println("[3] Editar cliente");
        System.out.println("[4] Excluir cliente");
        System.out.println("[5] Cadastrar filme");
        System.out.println("[6] Pesquisar filme");
        System.out.println("[7] Editar filme");
        System.out.println("[8] Excluir filme");
        System.out.println("[9] Locar filme");
        System.out.println("[10]Desalocar filme");
        System.out.println("[11]Salvar arquivos");
        System.out.println("[0] Sair");
        System.out.print  ("-- Digite a sua opção: ");
    }

}
