package br.com.springbot.service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.springbot.models.Filme;
import br.com.springbot.models.Produtor;
import br.com.springbot.repository.FilmeRepository;
import br.com.springbot.repository.ProdutorRepository;
import br.com.springbot.vo.Exibicao;
import br.com.springbot.vo.Intervalos;
import br.com.springbot.vo.ProdutorFilmes;
import br.com.springbot.vo.Resultado;


@Service
public class ProdutorService {
    
    @Autowired
    ProdutorRepository produtorRepository;

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    FilmeService service;

    

    public List getProdutores() {

        List produtores = new ArrayList<>();
        produtorRepository.findAll().forEach(produtor -> produtores.add(produtor));
         return produtores;

    }

    public Produtor getProdutorPorId(Long id) {
        Produtor produtor = produtorRepository.findById(id).get();
        return produtor;
     }

     public void salvar(Produtor produtor) {
        produtorRepository.save(produtor);
    }

     public void delete(Produtor produtor) {
        produtorRepository.delete(produtor);
     }

     public List<Produtor> getFilmesPorProdutor(){

         List<Produtor> list = this.getProdutores();


        return list;

     }

     public List getListaProdutoresFilmes() {

        List<Produtor> list = this.getFilmesPorProdutor();
        ArrayList<ProdutorFilmes> listaProdutorFilmes = new ArrayList<ProdutorFilmes>();

        Iterator itr = list.iterator();

        while(itr.hasNext()){

            Produtor produtor = (Produtor) itr.next();
            ProdutorFilmes produtorFilmes = new ProdutorFilmes();

            produtorFilmes.setProdutor(produtor);
            
            List listaFilmes = produtor.getFilmes();

            Iterator itrFilmes = listaFilmes.iterator();

            while(itrFilmes.hasNext()){
                Filme filme = (Filme) itrFilmes.next();
                produtorFilmes.getListaFilmes().add(filme);

                if(filme.getWinner()){
                    produtorFilmes.getListaFilmesVencedores().add(filme);
                }
            }
            produtorFilmes.getListaFilmes().sort(Comparator.comparing(Filme::getAno).reversed());
            produtorFilmes.getListaFilmesVencedores().sort(Comparator.comparing(Filme::getAno).reversed());
            produtorFilmes.getIntervalos();
            listaProdutorFilmes.add(produtorFilmes);
            
        }       

        return listaProdutorFilmes;

    }

    public List getVencedores() {

        List list = this.getListaProdutoresFilmes();

        ArrayList<Resultado> lstResultado = new ArrayList<Resultado>();

        Iterator itr = list.iterator();

        while (itr.hasNext()) {

            ProdutorFilmes produtorFilmes = (ProdutorFilmes) itr.next();

            List<Intervalos> listaIntervalos = produtorFilmes.getListaIntervalos();
            Iterator lstIntervalos = listaIntervalos.iterator();

            while (lstIntervalos.hasNext()) {
                Resultado resultado = new Resultado();
                resultado.setProducer(produtorFilmes.getProdutor().getNome());
                Intervalos intervalo = (Intervalos) lstIntervalos.next();
                resultado.setPreviousWin(intervalo.getAnterior());
                resultado.setFollowingWin(intervalo.getProximo());
                resultado.setInterval(intervalo.getIntervalo());
                lstResultado.add(resultado);
            }

        }

        return lstResultado;
    }
     

    public Exibicao getExibirResultado(){

        List min = this.vencedores();
        List max = this.vencedores();

        min.sort(Comparator.comparing(Resultado::getInterval)) ;
        max.sort(Comparator.comparing(Resultado::getInterval).reversed()) ;
        
        Exibicao exibicao = new Exibicao();
        exibicao.setMin(min);
        exibicao.setMax(max);
        return exibicao;
    }

    public List vencedores() {

        List listResultado = this.getVencedores();
        return listResultado;
    }
}
