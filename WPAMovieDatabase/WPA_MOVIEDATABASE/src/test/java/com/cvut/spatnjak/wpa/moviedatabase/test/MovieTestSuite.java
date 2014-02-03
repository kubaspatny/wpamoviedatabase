/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Kuba
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.cvut.spatnjak.wpa.moviedatabase.test.MovieServiceTest.class, com.cvut.spatnjak.wpa.moviedatabase.test.ActorServiceTest.class, com.cvut.spatnjak.wpa.moviedatabase.test.DirectorServiceTest.class, com.cvut.spatnjak.wpa.moviedatabase.test.ProductionCompanyServiceTest.class, com.cvut.spatnjak.wpa.moviedatabase.test.WriterServiceTest.class})
public class MovieTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}