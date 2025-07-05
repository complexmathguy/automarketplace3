/*******************************************************************************
  Turnstone Biologics Confidential
  
  2018 Turnstone Biologics
  All Rights Reserved.
  
  This file is subject to the terms and conditions defined in
  file 'license.txt', which is part of this source code package.
   
  Contributors :
        Turnstone Biologics - General Release
 ******************************************************************************/
package com.occulue.test;

import java.util.logging.*;

/**
 * Base class for application Test classes.
 *
 * @author    your_name_here
 */
public class BaseTest
{
	/**
	 * hidden
	 */
	protected BaseTest() {
	}
	
	public static void runTheTest( Handler logHandler ) 
    {
         try {
		    new ChassisTest().setHandler(logHandler).testCRUD();
		    new BodyTest().setHandler(logHandler).testCRUD();
		    new EngineTest().setHandler(logHandler).testCRUD();
		    new TransmissionTest().setHandler(logHandler).testCRUD();
		    new BrakesTest().setHandler(logHandler).testCRUD();
		    new InteriorTest().setHandler(logHandler).testCRUD();
        } catch( Throwable exc ) {
        	exc.printStackTrace();
        }
    }
	
}
