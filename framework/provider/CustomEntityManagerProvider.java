/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package  com.occulue.provider;

import org.axonframework.common.jpa.EntityManagerProvider;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import javax.persistence.EntityManager;

public class CustomEntityManagerProvider implements EntityManagerProvider {

    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext(unitName = "eventStore")
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}