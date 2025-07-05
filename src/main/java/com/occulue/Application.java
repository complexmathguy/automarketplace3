/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;	
@SpringBootApplication
@ComponentScan(basePackages = "com.occulue.*")
@EnableJpaRepositories(basePackages = "com.occulue.repository")
public class Application {

    public static void main(String[] args) {
    	ApplicationContext context = SpringApplication.run(Application.class, args);
    	
		System.out.println( "=================================" );
		System.out.println( "Checking in ApplicationContext for discovered handler components:\n" );
        System.out.println( "- Contains chassis-handler = " + context.containsBeanDefinition("chassis-handler"));
        System.out.println( "- Contains body-handler = " + context.containsBeanDefinition("body-handler"));
        System.out.println( "- Contains engine-handler = " + context.containsBeanDefinition("engine-handler"));
        System.out.println( "- Contains transmission-handler = " + context.containsBeanDefinition("transmission-handler"));
        System.out.println( "- Contains brakes-handler = " + context.containsBeanDefinition("brakes-handler"));
        System.out.println( "- Contains interior-handler = " + context.containsBeanDefinition("interior-handler"));
        System.out.println( "=================================" );
    }
    
}