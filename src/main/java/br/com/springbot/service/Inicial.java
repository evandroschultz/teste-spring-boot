package br.com.springbot.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.springbot.models.Concurso;
import br.com.springbot.models.Estudio;
import br.com.springbot.models.Filme;
import br.com.springbot.models.Produtor;

@Component
public class Inicial {

    private List<String[]> dados;
    private Map<String, Estudio> lstEstudios = new HashMap<String, Estudio>();
    private Map<String, Produtor> lstProdutores = new HashMap<String, Produtor>();
    private Map<String, Filme> lstFilmes = new HashMap<String, Filme>();

    @Value("${filePath}")
    private String filePath;

    @Autowired
    ConcursoService concursoService;

    @Autowired
    EstudioService estudioService;

    @Autowired
    ProdutorService produtorService;

    @Autowired
    FilmeService filmeService;

    

    @EventListener(ContextRefreshedEvent.class)
    public void warmup() {       

        try {
            this.lerArquivo2();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.adicionarConcurso();
    }

    private void adicionarConcurso() {
        // leva em considaração concuro inicial para estruturar banco
        Concurso concurso = new Concurso();
        concurso.setNome("Golden Raspberry Awards");
        concursoService.salvar(concurso);
    }

    private void lerArquivo2() throws IOException {
        Scanner scanner = new Scanner(new FileReader(filePath)).useDelimiter("\\n");
        scanner.next();
        String linha;
        while (scanner.hasNext()) {
            linha = scanner.next();
            this.popularEstudios(linha);
            this.popularProdutores(linha);
            this.popularFilmes(linha);            
        }

        System.out.println("Numero de filmes = " + lstFilmes.size());
    }

    private void popularEstudios(String dado) {

        Estudio estudio;

        String[] filme = dado.split(";");
        if (!lstEstudios.containsKey(filme[2])) {
            estudio = new Estudio();
            estudio.setNome(filme[2]);
            estudioService.salvar(estudio);
            lstEstudios.put(filme[2], estudio);
        }
    }

    private void popularProdutores(String dado) {

        // encontrar uma maneira mais elegante de trocar o AND por ,
        String[] filme = dado.split(";");

        String nomeProdutores = filme[3];
        nomeProdutores.replaceAll(" and ", ",");
        String[] arrProdutores = filme[3].split(",");

        for (int i = 0; i < arrProdutores.length; i++) {
            this.cadProdutor(arrProdutores[i].trim());
        }

    }

    private void cadProdutor(String nome) {
        Produtor produtor;
        if (!lstProdutores.containsKey(nome)) {

            // convencionar que um nome com virgula, então
            // sera uma parceria entre varios diretores

            produtor = new Produtor();
            produtor.setNome(nome.trim());
            produtorService.salvar(produtor);
            lstProdutores.put(nome, produtor);
           
        }
    }
 
    private Produtor getProdutor(String nome) {
        return lstProdutores.get(nome);
    }

    private Estudio getEstudio(String nome) {
        return lstEstudios.get(nome);
    }

    private Filme getFilme(String nome) {
        return lstFilmes.get(nome);
    }

    private Boolean isWinner(String[] linha) {
        if (linha.length == 5) {
            return true;
        }
        return false;
    }

    

    private void popularFilmes(String dado) {

        String[] linha = dado.split(";");
        Estudio estudio = this.getEstudio(linha[2]);
        Filme filme = new Filme();
        filme.setAno(Integer.parseInt(linha[0]));
        filme.setNome(linha[1]);
        filme.setEstudio(estudio);
        filme.setWinner(this.isWinner(linha));
        this.getProdutorFilme(linha[3], filme);
        
        filmeService.salvar(filme);

    }

    private void getProdutorFilme(String dado, Filme filme) {

        dado.replaceAll(" and ", ",");
        String[] arrProdutores = dado.split(",");

        for (int i = 0; i < arrProdutores.length; i++) {
            Produtor produtor = lstProdutores.get(arrProdutores[i].trim());
            
            filme.getProdutores().add(produtor);
        }

    }
}
