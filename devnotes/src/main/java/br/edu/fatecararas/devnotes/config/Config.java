package br.edu.fatecararas.devnotes.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Config {
   
    @Bean
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
