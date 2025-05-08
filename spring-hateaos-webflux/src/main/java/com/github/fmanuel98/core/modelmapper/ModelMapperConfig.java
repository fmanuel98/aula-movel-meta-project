package com.github.fmanuel98.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {

  @Bean
  public ModelMapper mapper() {
    var model = new ModelMapper();
    return model;
  }
}
