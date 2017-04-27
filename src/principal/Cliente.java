package principal;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import static principal.Locar.listaDeLocacao;//Importaçoes

public class Cliente implements Serializable{
    private String nome;
    
    public int ultimoCadastro(){
        int ultimoCadastro = 1;
        for(int i=0; i < listaDeClientes.size();i++){
            Cliente ultimoCliente = listaDeClientes.get(i);
            ultimoCadastro = ultimoCliente.getCadastro() + 1;
            }
        return ultimoCadastro;
    }
    
    private int cadastro = ultimoCadastro();
    private String endereco;
    private int telefone;
    private String cpf;
    private String email;//^Variáveis do Cliente
    
    public static List<Cliente> listaDeClientes = new ArrayList();// Cria lista
    Scanner scan = new Scanner(System.in, "UTF-8");//Variável scanner   
    private final Path path = Paths.get("clientes.arq");
    private final Charset utf8 = StandardCharsets.UTF_8; 
    
    public void cadastrar(){ //Método para cadastrar cliente
        Cliente cliente = new Cliente();
        
        System.out.println("===== Cadastrar Clientes =====");
        System.out.print("Nome: ");
        cliente.setNome(scan.nextLine());
        System.out.print("Endereço: ");
        cliente.setEndereco(scan.nextLine());
        System.out.print("Telefone (Apenas numeros): ");
        cliente.setTelefone(Integer.parseInt(scan.nextLine()));
        System.out.print("CPF: ");
        cliente.setCpf(scan.nextLine());
        System.out.print("Email: ");
        cliente.setEmail(scan.nextLine());

        cliente.setCadastro(cadastro);
        cadastro++;

        System.out.println("Cliente de cadastro nº "+ cliente.getCadastro() +" cadastrado com sucesso!");
        listaDeClientes.add(cliente);
        
        System.out.print("\nDeseja cadastrar outro cliente? (S/N): ");
        String decisao = scan.nextLine();
        if(decisao.equals("S") || decisao.equals("s")){
            cadastrar();
        }
    }// Cadastrar cliente

    public void pesquisar() {
        Cliente cliente;
        boolean achou = false;
        
        imprimeMenu("pesquisar");
        
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                System.out.print("Digite o nome do cliente: ");
                String nomeTemp = scan.nextLine();
                for(int i=0; i < listaDeClientes.size();i++){
                    cliente = listaDeClientes.get(i);
                    if(cliente.getNome().contains(nomeTemp)){
                        cliente.imprimeInfo();
                        achou = true;
                    }
                }
                break;
            case 2:
                System.out.print("Digite o CPF do cliente: ");
                String cpfTemp = scan.nextLine();
                for(int i=0; i < listaDeClientes.size();i++){
                    cliente = listaDeClientes.get(i);
                    if(cliente.getCpf().equals(cpfTemp)){
                        cliente.imprimeInfo();
                        achou = true;
                    }
                }
                break;
            case 3:
                System.out.print("Digite o cadastro do cliente: ");
                int cadastroTemp = Integer.parseInt(scan.nextLine());
                for(int i=0; i < listaDeClientes.size();i++){
                    cliente = listaDeClientes.get(i);
                    if(cliente.getCadastro() == cadastroTemp){
                        cliente.imprimeInfo();
                        achou = true;
                    }
                }
                break;
            case 0:
                return;
            default :
                System.out.println("Opção inválida");
                break;
        }
        
        if (achou == false){ //Se não achar nenhum cliente
            System.out.println("Cliente não encontrado");
        } else { 
        System.out.println("======================"); //Caso ache terminar o layout
        }
        
        System.out.print("\nDeseja pesquisar outro cliente? (S/N): ");
        String decisao = scan.nextLine();
        if(decisao.equals("S") || decisao.equals("s")){
            pesquisar();
        }
    }// Pesquisar cliente

    public void editar(){  //Método para editar cliente
        Cliente cliente;
        boolean achou = false;
        
        imprimeMenu("editar");
        
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                System.out.print("Digite o nome do cliente: ");
                String nomeTemp = scan.nextLine();
                for(int i=0; i < listaDeClientes.size();i++){
                    cliente = listaDeClientes.get(i);
                    if(cliente.getNome().equals(nomeTemp)){
                        imprimeMenuEditar(cliente);
                        achou = true;
                    }
                }
                break;
            case 2:
                System.out.print("Digite o CPF do cliente: ");
                String cpfTemp = scan.nextLine();
                for(int i=0; i < listaDeClientes.size();i++){
                    cliente = listaDeClientes.get(i);
                    if(cliente.getCpf().equals(cpfTemp)){
                        imprimeMenuEditar(cliente);
                        achou = true;
                    }
                }
                break;
            case 3:
                System.out.print("Digite o cadastro do cliente: ");
                int cadastroTemp = Integer.parseInt(scan.nextLine());
                for(int i=0; i < listaDeClientes.size();i++){
                    cliente = listaDeClientes.get(i);
                    if(cliente.getCadastro() == cadastroTemp){
                        imprimeMenuEditar(cliente);
                        achou = true;
                    }
                }
                break;
            case 0:
                return;
            default :
                System.out.println("Opção inválida");
                break;
        }
        
        if (achou == false){
            System.out.println("Cliente não encontrado");
            System.out.print("\nDeseja editar outro cliente? (S/N): ");
            String decisao = scan.nextLine();
            if(decisao.equals("S") || decisao.equals("s")){
                editar();
            }
        }
    }// Editar cliente
    
    public void excluir() {
        Cliente cliente;
        boolean achou = false;
        boolean apagado = false;
        
        imprimeMenu("excluir");
        
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                System.out.print("Digite o CPF do cliente: ");
                String cpfTemp = scan.nextLine();
                for(int i=0; i < listaDeClientes.size();i++){
                    cliente = listaDeClientes.get(i);
                    if(cliente.getCpf().equals(cpfTemp)){
                        System.out.print("Deseja realmente apagar o cliente " + cliente.getNome() +" (S/N): ");
                        String decisao = scan.nextLine();
                        if(decisao.equals("S") || decisao.equals("s")){
                            listaDeClientes.remove(cliente);
                            listaDeClientes.remove(cliente.getCadastro());
                            System.out.println("Cliente excluido");
                            apagado = true;
                        }
                        achou = true;
                    }
                }
                break;
            case 2:
                System.out.print("Digite o cadastro do cliente: ");
                int cadastroTemp = Integer.parseInt(scan.nextLine());
                for(int i=0; i < listaDeClientes.size();i++){
                    cliente = listaDeClientes.get(i);
                    if(cliente.getCadastro() == cadastroTemp){
                        System.out.print("Deseja realmente apagar o cliente " + cliente.getNome() +" (S/N): ");
                        String decisao = scan.nextLine();
                        if(decisao.equals("S") || decisao.equals("s")){
                            listaDeClientes.remove(cliente);
                            listaDeClientes.remove(cliente.getCadastro());
                            System.out.println("Cliente excluido");
                            apagado = true;
                        }
                        achou = true;
                    }
                }
                break;
            case 0:
                return;
            default :
                System.out.println("Opção inválida");
                break;
        }
        
        if (achou == false){
            System.out.println("Cliente não encontrado");
            System.out.print("\nDeseja excluir outro cliente? (S/N): ");
            String decisao = scan.nextLine();
            if(decisao.equals("S") || decisao.equals("s")){
                excluir();
            }
        }
        
        if (apagado == false){
            System.out.println("Cliente não apagado");
            System.out.print("\nDeseja excluir outro cliente? (S/N): ");
            String decisao = scan.nextLine();
            if(decisao.equals("S") || decisao.equals("s")){
                excluir();
            }
        }
        
    }// Excluir cliente
    
    public boolean locar(Filme filme){
        Cliente cliente;
        Locar locTemp = new Locar();
        System.out.print("Digite o CPF do cliente: ");
        String cpfTemp = scan.nextLine();
        for(int i=0; i < listaDeClientes.size();i++){
            cliente = listaDeClientes.get(i);
            if(cliente.getCpf().equals(cpfTemp)){
                System.out.print("Deseja locar o filme " +filme.getTitulo()+" para o cliente "+cliente.getNome()+"? (S/N): ");
                String decisao = scan.nextLine();
                if(decisao.equals("S") || decisao.equals("s") && (filme.getQuantidadeDisponivel() - 1) > 0){
                    System.out.println("Filme locado a "+cliente.getNome());
                    locTemp.locar(cliente, filme);
                    return true;
                } else {
                    return false;
                }
            }
        }
        
        System.out.println("Cliente não encontrado");//Caso não ache um cliente vem até aqui
        return false;
    }
    
    public boolean desalocar(Filme filme){
        Locar locTemp;
        
        System.out.println("Clientes que locaram o filme "+filme.getTitulo()+": ");
        System.out.println("NOME; CPF;");
        for(int i=0; i < listaDeLocacao.size();i++){
            locTemp = listaDeLocacao.get(i);
            if(locTemp.getLocado() == filme){
                System.out.println(locTemp.getLocador().getNome()+"; "+locTemp.getLocador().cpf+";");
            }
        }
        
        System.out.print("Digite o CPF do cliente que locou: ");
        String cpfTemp = scan.nextLine();
        for(int i=0; i < listaDeLocacao.size();i++){
            locTemp = listaDeLocacao.get(i);
            if(locTemp.getLocador().getCpf().equals(cpfTemp)){
                System.out.print("Deseja desalocar o filme " +filme.getTitulo()+" do cliente "+locTemp.getLocador().getNome()+"? (S/N): ");
                String decisao = scan.nextLine();
                if(decisao.equals("S") || decisao.equals("s") && (filme.getQuantidadeDisponivel() - 1) > 1){
                    System.out.println("Filme desalocado de "+locTemp.getLocador().getNome());
                    listaDeLocacao.remove(i);
                    return true;
                } else {
                    return false;
                }
            }
        }
        
        System.out.println("Cliente errado");//Caso o CPF não bata com nenhum dos clientes que alugou
        return false;
        }
    
    public void imprimeMenuEditar(Cliente cliente){
        int opcao;
        do{ System.out.println("[1] Nome: "+cliente.getNome());
            System.out.println("[2] Endereço: "+cliente.getEndereco());
            System.out.println("[3] Telefone: "+cliente.getTelefone());
            System.out.println("[4] CPF: "+cliente.getCpf());
            System.out.println("[5] Email: "+cliente.getEmail());
            System.out.println("[0] Voltar ao menu principal");
            System.out.print("-- Qual informação deseja editar: ");

            opcao = Integer.parseInt(scan.nextLine());
            switch(opcao){
                case 1:
                    System.out.print("Nome: ");
                    cliente.setNome(scan.nextLine());
                    break;
                case 2:
                    System.out.print("Endereço: ");
                    cliente.setEndereco(scan.nextLine());
                    break;
                case 3:
                    System.out.print("Telefone: ");
                    cliente.setTelefone(Integer.parseInt(scan.nextLine()));
                    break;
                case 4:
                    System.out.print("CPF: ");
                    cliente.setCpf(scan.nextLine());
                    break;
                case 5:   
                    System.out.print("Email: ");
                    cliente.setEmail(scan.nextLine());
                    break;
                case 0:
                    return;
                default :
                    System.out.println("Opção inválida");
                break;
                }
        } while (opcao!=0);
    } //Imprime menu de edição
    
    public void imprimeInfo(){
        System.out.println("======================" +
                           "\nNome: " +this.nome+
                           "\nCadastro: "+this.cadastro+
                           "\nEndereço:" +this.endereco+
                           "\nTelefone:" +this.telefone+
                           "\nCPF: " +this.cpf+
                           "\nEmail:" +this.email);   
    }// Imprime informações do cliente
    
    public void imprimeMenu(String opcao){
        switch (opcao) {
            case "pesquisar":
                System.out.println("==================");
                System.out.println("= Pesquisar por: =");
                System.out.println("==================");
                System.out.println("[1] Nome");
                System.out.println("[2] CPF");
                System.out.println("[3] Cadastro");
                System.out.println("[0] Voltar ao menu principal");
                System.out.print  ("-- Digite a sua opção: ");
                break;
            case "editar":
                System.out.println("==================");
                System.out.println("== Editar por:  ==");
                System.out.println("==================");
                System.out.println("[1] Nome");
                System.out.println("[2] CPF");
                System.out.println("[3] Cadastro");
                System.out.println("[0] Voltar ao menu principal");
                System.out.print  ("-- Digite a sua opção: ");
                break;
            case "excluir":
                System.out.println("==================");
                System.out.println("== Excluir por: ==");
                System.out.println("==================");
                System.out.println("[1] CPF");
                System.out.println("[2] Cadastro");
                System.out.println("[0] Voltar ao menu principal");
                System.out.print  ("-- Digite a sua opção: ");
                break;
            default:
                System.out.println("Erro inesperado, entre em contato com o criador do código"); //Caso haja algum erro inesperado
                break;
        }
    }
 
    public void armazenar(List<Cliente> clientes){
        try(BufferedWriter writer = Files.newBufferedWriter(path, utf8)){
            for(Cliente cliente : clientes){
                writer.write(cliente.getNome()+";"+
                             cliente.getCadastro()+";"+
                             cliente.getEndereco()+";"+
                             cliente.getTelefone()+";"+
                             cliente.getCpf()+";"+
                             cliente.getEmail()+"\r\n");
                writer.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
    } 
    
    public List<Cliente> recuperar(){
        try(BufferedReader reader = Files.newBufferedReader(path, utf8)){
            String line;
            while((line = reader.readLine()) != null ){
                String[] t = line.split(";");
                Cliente cliente = new Cliente();
                cliente.setNome(t[0]);
                cliente.setCadastro(Integer.parseInt(t[1]));
                cliente.setEndereco(t[2]);
                cliente.setTelefone(Integer.parseInt(t[3]));
                cliente.setCpf(t[4]);                
                cliente.setEmail(t[5]);
                listaDeClientes.add(cliente);
            } 
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
        return listaDeClientes;
    }
    
//<editor-fold desc="Getters e Setters">
    public void setNome(String nome){
        this.nome=nome;
    }
    public String getNome(){
        return this.nome;
    }
    public void setCadastro(int cadastro){
        this.cadastro=cadastro;
    }
    public int getCadastro(){
        return this.cadastro;
    }
    public void setEndereco(String endereco){
        this.endereco=endereco;
    }
    public String getEndereco(){
        return this.endereco;
    }
    public void setTelefone(int telefone){
        this.telefone=telefone;
    }
    public int getTelefone(){
        return this.telefone;
    }
    public void setCpf(String cpf){
        this.cpf=cpf;
    }
    public String getCpf(){
        return this.cpf;
    }
    public void setEmail(String Email){
        this.email=Email;
    }
    public String getEmail(){
        return this.email;
    }
    //</editor-fold>

}