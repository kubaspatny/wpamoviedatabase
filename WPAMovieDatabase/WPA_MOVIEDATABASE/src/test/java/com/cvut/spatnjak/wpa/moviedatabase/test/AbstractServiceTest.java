/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kuba
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/WEB-INF/context/applicationContext.xml"})
@TransactionConfiguration(defaultRollback=true, transactionManager="txManager")
@Transactional
public abstract class AbstractServiceTest {
    public AbstractServiceTest() {
    }
}
