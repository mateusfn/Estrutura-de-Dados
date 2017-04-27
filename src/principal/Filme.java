package principal;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import static principal.Locar.listaDeLocacao;//Importaçoes

public class Filme implements Serializable{
    private String titulo;
    
    public int ultimoCadastro(){
        int ultimoCadastro = 1;
        for(int i=0; i < listaDeFilmes.size();i++){
            Filme ultimoFilme = listaDeFilmes.get(i);
            ultimoCadastro = ultimoFilme.getCadastro() + 1;
            }
        return ultimoCadastro;
    }
    
    private int cadastro = ultimoCadastro();
    private int ano;
    private String genero;
    private float valorLocacao;
    private int quantidade;
    private int quantidadeDisponivel;//^Variáveis do Filme
    
    public static List<Filme> listaDeFilmes = new ArrayList();// Cria lista
    Scanner scan = new Scanner(System.in, "UTF-8");//Variável scanner
    private final Path path = Paths.get("filmes.arq");
    private final Charset utf8 = StandardCharsets.UTF_8; 
    
    public void cadastrar(){
        Filme filme = new Filme();
        
        System.out.println("===== Cadastrar Filmes =====");
        System.out.print("Título: ");
        filme.setTitulo(scan.nextLine());
        System.out.print("Ano: ");
        int anoTemp = Integer.parseInt(scan.nextLine());
        filme.setAno(anoTemp);
        System.out.print("Gênero: ");
        filme.setGenero(scan.nextLine());
        System.out.print("Valor da locação: ");
        filme.setValorLocacao(Float.parseFloat(scan.nextLine()));
        System.out.print("Quantidade: ");
        filme.setQuantidade(Integer.parseInt(scan.nextLine()));
        filme.setQuantidadeDisponivel(filme.getQuantidade());
        
        filme.setCadastro(cadastro);
        cadastro++;

        System.out.println("Filme de cadastro nº "+ filme.getCadastro() +" cadastrado com sucesso!");
        listaDeFilmes.add(filme);
        
        System.out.print("\nDeseja cadastrar outro filme (S/N): ");
        String decisao = scan.nextLine();
        if(decisao.equals("S") || decisao.equals("s")){
            cadastrar();
        }
    }// Cadastrar filme

    public void pesquisar() {
        Filme filme;
        boolean achou = false;
        
        imprimeMenu("pesquisar");
        
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                System.out.print("Digite o título do filme: ");
                String nomeTemp = scan.nextLine();
                for(int i=0; i < listaDeFilmes.size();i++){
                    filme = listaDeFilmes.get(i);
                    if(filme.getTitulo().equals(nomeTemp)){
                        filme.imprimeInfo(filme);
                        achou = true;
                    }
                }
                break;
            case 2:
                System.out.print("Digite o cadastro do filme: ");
                int cadastroTemp = Integer.parseInt(scan.nextLine());
                for(int i=0; i < listaDeFilmes.size();i++){
                    filme = listaDeFilmes.get(i);
                    if(filme.getCadastro() == cadastroTemp){
                        filme.imprimeInfo(filme);
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
        
        if (achou == false){ //Se não achar nenhum filme
            System.out.println("Filme não encontrado");
        } else { 
        System.out.println("======================"); //Caso ache terminar o layout
        }
        
        System.out.print("\nDeseja pesquisar outro filme? (S/N): ");
        String decisao = scan.nextLine();
        if(decisao.equals("S") || decisao.equals("s")){
            pesquisar();
        }
    }// Pesquisar filme

    public void editar(){
        Filme filme;
        boolean achou = false;
        
        imprimeMenu("editar");
        
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                System.out.print("Digite o título do filme: ");
                String nomeTemp = scan.nextLine();
                for(int i=0; i < listaDeFilmes.size();i++){
                    filme = listaDeFilmes.get(i);
                    if(filme.getTitulo().equals(nomeTemp)){
                        imprimeMenuEditar(filme);
                        achou = true;
                    }
                }
                break;
            case 2:
                System.out.print("Digite o cadastro do filme: ");
                int cadastroTemp = Integer.parseInt(scan.nextLine());
                for(int i=0; i < listaDeFilmes.size();i++){
                    filme = listaDeFilmes.get(i);
                    if(filme.getCadastro() == cadastroTemp){
                        imprimeMenuEditar(filme);
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
            System.out.println("Filme não encontrado");
            System.out.print("\nDeseja editar outro filme? (S/N): ");
            String decisao = scan.nextLine();
            if(decisao.equals("S") || decisao.equals("s")){
                editar();
            }
        }
    }// Editar filme
    
    public void excluir() {
        Filme filme;
        boolean achou = false;
        boolean apagado = false;
        
        imprimeMenu("excluir");
        
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                System.out.print("Digite o título do filme: ");
                String tituloTemp = scan.nextLine();
                for(int i=0; i < listaDeFilmes.size();i++){
                    filme = listaDeFilmes.get(i);
                    if(filme.getTitulo().equals(tituloTemp)){
                        System.out.print("Deseja realmente apagar o filme " + filme.getTitulo() +" (S/N): ");
                        scan.nextLine();
                        String decisao = scan.nextLine();
                        if(decisao.equals("S") || decisao.equals("s")){
                            listaDeFilmes.remove(filme);
                            System.out.println("Filme retirado do estoque");
                            apagado = true;
                        }
                        achou = true;
                    }
                }
                break;
            case 2:
                System.out.print("Digite o cadastro do filme: ");
                String cadastroTemp = scan.nextLine();
                for(int i=0; i < listaDeFilmes.size();i++){
                    filme = listaDeFilmes.get(i);
                    if(filme.getTitulo().equals(cadastroTemp)){
                        System.out.print("Deseja realmente apagar o filme " + filme.getTitulo() +" (S/N): ");
                        scan.nextLine();
                        String decisao = scan.nextLine();
                        if(decisao.equals("S") || decisao.equals("s")){
                            listaDeFilmes.remove(filme);
                            System.out.println("Filme retirado do estoque");
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
            System.out.println("Filme não encontrado");
            System.out.print("\nDeseja excluir outro filme? (S/N): ");
            String decisao = scan.nextLine();
            if(decisao.equals("S") || decisao.equals("s")){
                excluir();
            }
        }
        if (apagado == false){
            System.out.println("Filme não apagado");
            System.out.print("\nDeseja excluir outro filme? (S/N): ");
            String decisao = scan.nextLine();
            if(decisao.equals("S") || decisao.equals("s")){
                excluir();
            }
        }
        
    }// Excluir filme
    
    public void locar(){
        Filme filme;
        Cliente cliente = new Cliente();
        boolean achou = false;
        
        System.out.println("===============");
        System.out.println("= Locar Filme =");
        System.out.println("===============");
        System.out.println("[1] Título");
        System.out.println("[2] Cadastro");
        System.out.print  ("-- Digite a sua opção: ");
        
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                System.out.print("Digite o título do filme: ");
                String tituloTemp = scan.nextLine();
                for(int i=0; i < listaDeFilmes.size();i++){
                    filme = listaDeFilmes.get(i);
                    if (filme.getQuantidadeDisponivel() > 0){
                        if(filme.getTitulo().equals(tituloTemp)){
                            if (cliente.locar(filme) == true){
                                int x = filme.getQuantidadeDisponivel() - 1;
                                filme.setQuantidadeDisponivel(x);
                            } else {
                                System.out.println("Filme não locado");
                            }
                            achou = true;
                        }
                    } else {
                        System.out.println("Todo o estoque já foi locado");
                        achou = true;
                    }
                }
                break;
            case 2:
                System.out.print("Digite o cadastro do filme: ");
                int cadastroTemp = Integer.parseInt(scan.nextLine());
                for(int i=0; i < listaDeFilmes.size();i++){
                    filme = listaDeFilmes.get(i);
                    if (filme.getQuantidadeDisponivel() > 0){
                        if(filme.getCadastro() == cadastroTemp){
                            if (cliente.locar(filme) == true){
                                int x = filme.getQuantidadeDisponivel() - 1;
                                filme.setQuantidadeDisponivel(x);
                            } else {
                                System.out.println("Filme não locado");
                            }
                            achou = true;
                        }
                    } else {
                        System.out.println("Todo o estoque já foi locado");
                        achou = true;
                    }
                }
                break;
            default :
                System.out.println("Opção inválida");
                break;
        }
        
        if (achou == false){ //Se não achar nenhum filme
            System.out.println("Filme não encontrado");
        }
        
        System.out.print("\nDeseja locar outro filme? (S/N): ");
        String decisao = scan.nextLine();
        if(decisao.equals("S") || decisao.equals("s")){
            locar();
        }
    }// Locar filme
    
    public void desalocar(){
        Locar locTemp;
        Filme filme;
        Cliente cliente = new Cliente();
        boolean estaLocado = false;
        boolean achou = false;
        
        System.out.println("===================");
        System.out.println("= Desalocar Filme =");
        System.out.println("===================");
        System.out.println("[1] Nome");
        System.out.println("[2] Cadastro");
        System.out.print  ("-- Digite a sua opção: ");
        
        int opcao = Integer.parseInt(scan.nextLine());
        switch(opcao){
            case 1:
                System.out.print("Digite o título do filme: ");
                String nomeTemp = scan.nextLine();
                for(int i=0; i < listaDeLocacao.size();i++){
                    locTemp = listaDeLocacao.get(i);
                    if(locTemp.getLocado().getTitulo().equals(nomeTemp)){
                        if (cliente.desalocar(locTemp.getLocado()) == true){
                            filme = locTemp.getLocado();
                            int x = filme.getQuantidadeDisponivel() + 1;
                            filme.setQuantidadeDisponivel(x);
                        } else {
                            System.out.println("Filme não desalocado");
                        }
                        achou = true;
                    } else {
                        achou = true;
                        estaLocado = true;
                    }
                }
                break;
            case 2:
                System.out.print("Digite o cadastro do filme: ");
                int cadastroTemp = Integer.parseInt(scan.nextLine());
                for(int i=0; i < listaDeFilmes.size();i++){
                    locTemp = listaDeLocacao.get(i);
                    if(locTemp.getLocado().getCadastro() == cadastroTemp){
                        if (cliente.desalocar(locTemp.getLocado()) == true){
                            filme = locTemp.getLocado();
                            int x = filme.getQuantidadeDisponivel() + 1;
                            filme.setQuantidadeDisponivel(x);
                        } else {
                            System.out.println("Filme não desalocado");
                        }
                        achou = true;
                    } else {
                        achou = true;
                        estaLocado = true;
                    }
                }
                break;
            default :
                System.out.println("Opção inválida");
                break;
        }
        
        if (achou == false){ //Se não achar nenhum filme
            System.out.println("Filme não encontrado");
        }
        if (estaLocado == false){
            System.out.println("Este filme não está locado");
        }
        
        System.out.print("\nDeseja pesquisar outro filme? (S/N): ");
        String decisao = scan.nextLine();
        if(decisao.equals("S") || decisao.equals("s")){
            desalocar();
        }
    }// Desalocar filme
    
    public void imprimeMenuEditar(Filme filme){
        int opcao;
        do{ System.out.println("[1] Título: "+filme.getTitulo());
            System.out.println("[2] Ano: "+filme.getAno());
            System.out.println("[3] Gênero: "+filme.getGenero());
            System.out.println("[4] Valor de locação: "+filme.getValorLocacao());
            System.out.println("~~~ Quantidade: "+filme.getQuantidade());
            System.out.println("~~~ Quantidade disponível: "+filme.getQuantidadeDisponivel());
            System.out.println("[5] Adicionar ao estoque");
            System.out.println("[6] Remover do estoque");
            System.out.println("[0] Voltar ao menu principal");
            System.out.print("-- Qual informação deseja editar: ");

            opcao = Integer.parseInt(scan.nextLine());
            switch(opcao){
                case 1:
                    System.out.print("Título: ");
                    filme.setTitulo(scan.nextLine());
                    break;
                case 2:
                    System.out.print("Ano: ");
                    filme.setAno(Integer.parseInt(scan.nextLine()));
                    break;
                case 3:
                    System.out.print("Gênero: ");
                    filme.setGenero(scan.nextLine());
                    break;
                case 4:
                    System.out.print("Valor de locação: ");
                    filme.setValorLocacao(scan.nextFloat());
                    break;
                case 5:   
                    System.out.print("Adicionar ao estoque: ");
                    filme.adicionarQuantidade(Integer.parseInt(scan.nextLine()));
                    break;
                case 6:
                    System.out.print("Remover  do estoque: ");
                    int x = Integer.parseInt(scan.nextLine());
                    if (filme.getQuantidadeDisponivel()-x < 0){
                        System.out.println("ERRO: Você não pode remover esta quantidade, pois tem alguns filmes já alugados");
                    }
                    else {
                        filme.removerQuantidade(x);
                    }
                    break;
                default :
                    System.out.println("Opção inválida");
                break;
                }
        } while (opcao!=0);
    } //Imprime menu de edição
        
    public void imprimeInfo(Filme filme){
        Locar locTemp;
        System.out.println("======================" +
                           "\nTítulo: " +this.getTitulo()+
                           "\nCadastro: "+this.getCadastro()+
                           "\nAno:" +this.getAno()+
                           "\nGênero:" +this.getGenero()+
                           "\nValor de locação: " +this.getValorLocacao()+
                           "\nQuantidade disponível: " +this.getQuantidadeDisponivel()+
                           "\nLocado por: "+
                           "\nNOME; CPF;"
                            );
                for(int i=0; i < listaDeLocacao.size();i++){
                    locTemp = listaDeLocacao.get(i);
                    if(locTemp.getLocado() == filme){
                        System.out.print(locTemp.getLocador().getNome()+"; "+locTemp.getLocador().getCpf()+";\n");
                    }
                }
    }// Imprime informações do filme

    public void imprimeMenu(String opcao){
        switch (opcao) {
            case "pesquisar":
                System.out.println("===================");
                System.out.println("= Pesquisar Filme =");
                System.out.println("===================");
                System.out.println("[1] Título");
                System.out.println("[2] Cadastro");
                System.out.println("[0] Voltar ao menu principal");
                System.out.print  ("-- Digite a sua opção: ");
                break;
            case "editar":
                System.out.println("================");
                System.out.println("= Editar Filme =");
                System.out.println("================");
                System.out.println("[1] Título");
                System.out.println("[2] Cadastro");
                System.out.println("[0] Voltar ao menu principal");
                System.out.print  ("-- Digite a sua opção: ");
                break;
            case "excluir":
                System.out.println("=================");
                System.out.println("= Excluir Filme =");
                System.out.println("=================");
                System.out.println("[1] Título");
                System.out.println("[2] Cadastro");
                System.out.println("[0] Voltar ao menu principal");
                System.out.print  ("-- Digite a sua opção: ");
                break;
            default:
                System.out.println("Erro inesperado, entre em contato com o criador do código"); //Caso haja algum erro inesperado
                break;
        }
    }

    public void armazenar(List<Filme> filmes){
        try(BufferedWriter writer = Files.newBufferedWriter(path, utf8)){
            for(Filme filme : filmes){
                writer.write(filme.getTitulo()+";"+
                             filme.getCadastro()+";"+
                             filme.getAno()+";"+
                             filme.getGenero()+";"+
                             filme.getValorLocacao()+";"+
                             filme.getQuantidade()+";"+
                             filme.getQuantidadeDisponivel()+"\r\n");
                writer.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
    } 
    
    public List<Filme> recuperar(){
        try(BufferedReader reader = Files.newBufferedReader(path, utf8)){
            String line;
            while((line = reader.readLine()) != null ){
                String[] t = line.split(";");
                Filme filme = new Filme();
                filme.setTitulo(t[0]);
                filme.setCadastro(Integer.parseInt(t[1]));
                filme.setAno(Integer.parseInt(t[2]));
                filme.setGenero(t[3]);
                filme.setValorLocacao(Float.parseFloat(t[4]));                
                filme.setQuantidade(Integer.parseInt(t[5]));
                filme.setQuantidadeDisponivel(Integer.parseInt(t[6]));  
                listaDeFilmes.add(filme);
            }
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
        return listaDeFilmes;
    }
    
    
    //<editor-fold desc="Getters e Setters">
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCadastro() {
        return cadastro;
    }

    public void setCadastro(int cadastro) {
        this.cadastro = cadastro;
    }
    
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(float valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void adicionarQuantidade(int unidades){
        this.quantidade += unidades;
        this.quantidadeDisponivel += unidades;
    }
    
    public void removerQuantidade(int unidades){
        this.quantidade -= unidades;
        this.quantidadeDisponivel -= unidades;
    }
    
    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public void setScan(Scanner scan) {
        this.scan = scan;
    }
    //</editor-fold>
}