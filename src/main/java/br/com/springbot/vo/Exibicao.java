package br.com.springbot.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exibicao {

    private List<Resultado> min = new ArrayList<>();
    private List<Resultado> max = new ArrayList<>();
    //private Resultado min = new Resultado();
    //private Resultado max = new Resultado();


    
}
