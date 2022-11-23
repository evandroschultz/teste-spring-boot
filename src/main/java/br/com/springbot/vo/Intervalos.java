package br.com.springbot.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Intervalos {
    

    private int anterior;
    private int proximo;
    private Integer intervalo = 0;

    public void calcular(){
        this.intervalo =  this.anterior - this.proximo;
    }


}
