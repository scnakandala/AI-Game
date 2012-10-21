/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecdienttest;

import gameclient.GameClient;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author naka
 */
public class GameClientTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testEstablishConnection() {
        boolean status = GameClient.establishConectiin();
        Assert.assertEquals(true, status);
    }
    
    @Test
    public void testJoinTheGame(){
        boolean status = GameClient.joinTheGame();
        Assert.assertEquals(true, status);
    }
}
