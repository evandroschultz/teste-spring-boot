package br.com.springbot.vo;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IntervalosPremicao {
    
    ArrayList<Resultado> min = new ArrayList<Resultado>();
    ArrayList<Resultado> max = new ArrayList<Resultado>();
    

}
