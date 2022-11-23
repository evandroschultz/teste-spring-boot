package br.com.springbot.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resultado {
    
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

}
