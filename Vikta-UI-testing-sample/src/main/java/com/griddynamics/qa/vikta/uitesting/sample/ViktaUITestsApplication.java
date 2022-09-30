package com.griddynamics.qa.vikta.uitesting.sample;

import com.griddynamics.qa.vikta.uitesting.sample.config.TestDataConfiguration;
import com.griddynamics.qa.vikta.uitesting.sample.config.TestSetupConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(
  value = { TestSetupConfiguration.class, TestDataConfiguration.class }
)
@SpringBootApplication
public class ViktaUITestsApplication {

  public static void main(String[] args) {
    SpringApplication.run(ViktaUITestsApplication.class);
  }
}
