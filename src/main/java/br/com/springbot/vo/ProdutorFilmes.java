package br.com.springbot.vo;

import java.util.ArrayList;
import java.util.List;

import br.com.springbot.models.Filme;
import br.com.springbot.models.Produtor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutorFilmes {

    Produtor produtor;
    private List<Filme> listaFilmes = new ArrayList<Filme>();
    private List<Filme> listaFilmesVencedores = new ArrayList<Filme>();
    private List<Intervalos> listaIntervalos = new ArrayList<Intervalos>();

    public void getIntervalos() {

        if (this.listaFilmesVencedores.size() > 1) {
            for (int i = 0; i < listaFilmesVencedores.size(); i++) {

                Filme filme = (Filme) listaFilmesVencedores.get(i);
                Intervalos intervalos = new Intervalos();

                intervalos.setProximo(filme.getAno());

                for (int j = 1; j < listaFilmesVencedores.size(); j++) {
                    intervalos.setAnterior(listaFilmesVencedores.get(j).getAno());
                }

                intervalos.calcular();
                this.listaIntervalos.add(intervalos);

            }
        }

    }

}
