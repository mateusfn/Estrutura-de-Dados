package principal;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import static principal.Cliente.listaDeClientes;
import static principal.Filme.listaDeFilmes;

public class Locar implements Serializable{
    private Cliente locador;
    private Filme locado;
    
    public static List<Locar> listaDeLocacao = new ArrayList();
    private final Path path = Paths.get("locacoes.arq");
    private final Charset utf8 = StandardCharsets.UTF_8; 
    
    public void locar(Cliente cliente, Filme filme){
        Locar locar = new Locar();
        locar.setLocador(cliente);
        locar.setLocado(filme);
        listaDeLocacao.add(locar);
    }
    
    public Cliente getLocador() {
        return locador;
    }

    public void setLocador(Cliente locador) {
        this.locador = locador;
    }

    public void setLocadorPorCadastro(int cadastro) {
        for(int i=0; i < listaDeClientes.size();i++){
            Cliente cliente = listaDeClientes.get(i);
            if(cliente.getCadastro() == cadastro){
                this.locador = cliente;
                return;
            }
        }
        System.out.println("Erro inesperado");
    }
    
    public void setLocadoPorCadastro(int cadastro){
        for(int i=0; i < listaDeFilmes.size();i++){
            Filme filme = listaDeFilmes.get(i);
            if(filme.getCadastro() == cadastro){
                this.locado = filme;
                return;
            }
        }
        System.out.println("Erro inesperado");
    }
    
    public Filme getLocado() {
        return locado;
    }

    public void setLocado(Filme locado) {
        this.locado = locado;
    }
    
    public void armazenar(List<Locar> locacoes){
        try(BufferedWriter writer = Files.newBufferedWriter(path, utf8)){
            for(Locar locar : locacoes){
                writer.write(locar.getLocador().getCadastro()+";"+
                             locar.getLocado().getCadastro()+"\r\n");
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
                Locar locar = new Locar();
                locar.setLocadorPorCadastro(Integer.parseInt(t[0]));
                locar.setLocadoPorCadastro(Integer.parseInt(t[1]));
                listaDeLocacao.add(locar); 
            } 
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
        return listaDeClientes;
    }
    
    
}
