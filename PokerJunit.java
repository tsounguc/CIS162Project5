import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
/*****************************************************
 * The test class LotteryTest.
 *
 * @author  Scott Grissom
 * @version March 4, 2016
 ****************************************************/
public class PokerJunit{
    private PokerDice g;
    public final int THREE_OF_KIND = 25;
    public final static int SMALL_STRAIGHT = 30;
    public final static int LARGE_STRAIGHT = 45;
    public final static int FULL_HOUSE = 35;
    public final static int FOUR_OF_KIND = 40;
    public final static int FIVE_OF_KIND = 50;

    /************************************************
     * Sets up the test fixture.
     *
     * Called before every test case method.
     ***********************************************/
    @Before
    public void setUp()
    {
        g = new PokerDice(); 
    }
    
/*********************************************************
 * Ticket Constructor
 ********************************************************/
    @Test
    public void testConstructor()
    {      
        Assert.assertEquals("PokerDice() score should start at 0", 
                0, g.getScore());                

    } 
  
/*********************************************************
 * Game Over
 ********************************************************/
    @Test
    public void testGameOver()
    {      
            
        Assert.assertTrue("gameOver() game should not be over at start", 
                !g.gameOver());   
        g.checkFiveOfAKind(); 
        g.checkFourOfAKind(); 
        g.checkThreeOfAKind(); 
        Assert.assertTrue("gameOver() game should not be over after three rounds", 
                !g.gameOver());   
        g.checkFullHouse(); 
        g.checkLargeStraight(); 
        g.checkSmallStraight(); 
        Assert.assertTrue("gameOver() game should not be over after six rounds", 
                !g.gameOver());   
        g.checkSingles(1); 
        Assert.assertTrue("gameOver() game should not be over after 7 rounds", 
                !g.gameOver());  
        g.checkSingles(2); 
        Assert.assertTrue("gameOver() game should not be over after 8 rounds", 
                !g.gameOver());  
        g.checkSingles(3); 
        Assert.assertTrue("gameOver() game should not be over after 9 rounds", 
                !g.gameOver());  
        g.checkSingles(4); 
        Assert.assertTrue("gameOver() game should not be over after 10 rounds", 
                !g.gameOver()); 
        g.checkSingles(5); 
        Assert.assertTrue("gameOver() game should not be over after 11 rounds", 
                !g.gameOver()); 
        g.checkSingles(6); 
        Assert.assertTrue("gameOver() game should not be over after 12 rounds", 
                !g.gameOver());                 
        g.checkChance(); 
        Assert.assertTrue("gameOver() game should be over after 13 rounds", 
                g.gameOver());   


            
            } 
    
/*********************************************************
 * Ticket Constructor
 ********************************************************/
    @Test
    public void testOKtoRoll()
    {      
            
        Assert.assertTrue("okToRoll() should be OK to roll at start", 
                g.okToRoll());   
        g.rollDice(); 
        Assert.assertTrue("okToRoll() should be OK to roll after one roll", 
                g.okToRoll());   
        g.rollDice();  
        Assert.assertTrue("okToRoll() should be OK to roll after second roll", 
                g.okToRoll());   
        g.rollDice();         
        Assert.assertTrue("okToRoll() should NOT be OK to roll after third roll", 
                !g.okToRoll()); 
    } 
    
/*********************************************************
 * Roll Dice
 ********************************************************/
    @Test
    public void testRollDice()
    {
        int total;
        ArrayList<GVdie> original = g.getDice();
        ArrayList<GVdie> copy = new ArrayList<GVdie>();
        
        // copy dice values and hold each
        int values [] = new int[6];
        int x=0;
        for(GVdie d:original){
            d.setHeld(true);
            values[x] = d.getValue();
            x++;
        }
        
        // roll original dice
        g.rollDice();
        
        // copy should match original
        boolean worked = true;
        for(int i=0; i<original.size(); i++){
            int val1 = original.get(i).getValue();
            int val2 = values[i];
            if(val1 != val2)
                worked = false;
        }        
        Assert.assertTrue("checkRollDice() should not roll any dice if all selected", 
                worked);    
                
        x=0;
        for(GVdie d:original){
            d.setHeld(false);
            values[x] = d.getValue();
            x++;
        }
        
        // roll original dice
        g.rollDice();
        
        // copy should match original
        worked = false;
        for(int i=0; i<original.size(); i++){
            int val1 = original.get(i).getValue();
            int val2 = values[i];
            if(val1 != val2)
                worked = true;
        }        
        Assert.assertTrue("checkRollDice() should roll any dice not selected", 
                worked);                  
    }  
    
 /*********************************************************
 * Chance
 ********************************************************/
    @Test
    public void testChance()
    {
        int before = g.getScore();
        g.setDice(new int[] {3,3,3,3,3});
        g.checkChance();
        Assert.assertEquals("checkChance() should increase score by sum of dice", 
                before + 15, g.getScore());                
    }  

 /*********************************************************
 * Singles
 ********************************************************/
    @Test
    public void testSingles()
    {
        int before = g.getScore();
        g.setDice(new int[] {3,3,3,3,3});
        g.checkSingles(3);
        Assert.assertEquals("checkSngles(3) should have increased score by 15", 
                before + 15, g.getScore());                
    } 
    
 /*********************************************************
 * Bonus Score
 ********************************************************/
    @Test
    public void testBonus()
    {
        Assert.assertEquals("bonus score should start as 0", 
                0, g.getBonusScore());  
        g.setDice(new int[] {4,4,1,1,1});
        g.checkSingles(4);
        g.setDice(new int[] {5,5,5,5,5});
        g.checkSingles(5);
        g.setDice(new int[] {6,6,6,6,6});
        g.checkSingles(6);
        Assert.assertEquals("bonus score should be 35 at this point", 
                35, g.getBonusScore());                
    }    
    
/*********************************************************
 * Five of a Kind
 ********************************************************/
    @Test
    public void testFiveOfKind(){
        int before = g.getScore();
        g.setDice(new int[] {3,3,3,3,3});
        g.checkFiveOfAKind();
        Assert.assertEquals("checkFiveOfKind() should increase score for (3,3,3,3,3)", 
                before + FIVE_OF_KIND, g.getScore()); 
                
        before = g.getScore();
        g.setDice(new int[] {2,3,3,3,3});
        g.checkFiveOfAKind();
        Assert.assertEquals("checkFiveOfKind() should NOT increase score for (2,3,3,3,3)", 
                before, g.getScore());                  
    } 
    
/*********************************************************
 * Four of a Kind
 ********************************************************/
    @Test
    public void testFourOfKind(){
        int before = g.getScore();
        g.setDice(new int[] {3,3,3,3,3});
        g.checkFourOfAKind();
        Assert.assertEquals("checkFourOfKind() should increase score for (3,3,3,3,3)", 
                before + FOUR_OF_KIND, g.getScore()); 
   
        before = g.getScore();
        g.setDice(new int[] {2,3,3,3,3});
        g.checkFourOfAKind();
        Assert.assertEquals("checkFourOfKind() should increase score for (2,3,3,3,3)", 
                before + FOUR_OF_KIND, g.getScore()); 
                
                
        before = g.getScore();
        g.setDice(new int[] {2,2,3,3,3});
        g.checkFourOfAKind();
        Assert.assertEquals("checkFourOfKind() should NOT increase score for (2,2,3,3,3)", 
                before, g.getScore());                  
    } 
    
/*********************************************************
 * Three of a Kind
 ********************************************************/
    @Test
    public void testThreeOfKind(){
        int before = g.getScore();
        g.setDice(new int[] {2,3,3,3,3});
        g.checkThreeOfAKind();
        Assert.assertEquals("checkThreeOfKind() should increase score for (2,3,3,3,3)", 
                before + THREE_OF_KIND, g.getScore()); 
   
        before = g.getScore();
        g.setDice(new int[] {2,2,3,3,3});
        g.checkThreeOfAKind();
        Assert.assertEquals("checkThreeOfKind() should increase score for (2,2,3,3,3)", 
                before + THREE_OF_KIND, g.getScore()); 
                
                
        before = g.getScore();
        g.setDice(new int[] {2,2,4,3,3});
        g.checkThreeOfAKind();
        Assert.assertEquals("checkThreeOfKind() should NOT increase score for (2,2,4,3,3)", 
                before, g.getScore());                  
    }   
    
/*********************************************************
 * Full House
 ********************************************************/
    @Test
    public void testFullHouse(){
        int before = g.getScore();
        g.setDice(new int[] {3,3,3,3,3});
        g.checkFullHouse();
        Assert.assertEquals("checkFullHouse() should increase score for (3,3,3,3,3)", 
                before + FULL_HOUSE, g.getScore()); 
   
        before = g.getScore();
        g.setDice(new int[] {2,2,3,3,3});
        g.checkFullHouse();
        Assert.assertEquals("checkFullHouse() should increase score for (2,2,3,3,3)", 
                before + FULL_HOUSE, g.getScore()); 
                
                
        before = g.getScore();
        g.setDice(new int[] {2,2,4,3,3});
        g.checkFullHouse();
        Assert.assertEquals("checkFullHouse() should NOT increase score for (2,2,4,3,3)", 
                before, g.getScore());                  
    } 
    
/*********************************************************
 * Small Straight
 ********************************************************/
    @Test
    public void testSmallStraight(){
        int before = g.getScore();
        g.setDice(new int[] {2,3,4,5,3});
        g.checkSmallStraight();
        Assert.assertEquals("checkSmallStraight() should increase score for (2,3,4,5,3)", 
                before + SMALL_STRAIGHT, g.getScore()); 

        before = g.getScore();
        g.setDice(new int[] {3,5,6,4,3});
        g.checkSmallStraight();
        Assert.assertEquals("checkSmallStraight() should increase score for (3,5,6,4,3)", 
                before + SMALL_STRAIGHT, g.getScore()); 
                
        before = g.getScore();
        g.setDice(new int[] {1,2,3,4,3});
        g.checkSmallStraight();
        Assert.assertEquals("checkSmallStraight() should increase score for (1,2,3,4,3)", 
                before + SMALL_STRAIGHT, g.getScore()); 
                
                
        before = g.getScore();
        g.setDice(new int[] {1,2,4,5,6});
        g.checkSmallStraight();
        Assert.assertEquals("checkSmallStraight() should NOT increase score for (1,2,4,5,6)", 
                before, g.getScore());                  
    }  
    
/*********************************************************
 * Large Straight
 ********************************************************/
    @Test
    public void testLargeStraight(){
        int before = g.getScore();
        g.setDice(new int[] {2,3,4,5,1});
        g.checkLargeStraight();
        Assert.assertEquals("checkLargeStraight() should increase score for (2,3,4,5,1)", 
                before + LARGE_STRAIGHT, g.getScore()); 

        before = g.getScore();
        g.setDice(new int[] {2,3,5,4,6});
        g.checkLargeStraight();
        Assert.assertEquals("checkLargeStraight() should increase score for (2,3,5,4,6)", 
                before + LARGE_STRAIGHT, g.getScore()); 
                
        before = g.getScore();
        g.setDice(new int[] {1,2,4,5,6});
        g.checkLargeStraight();
        Assert.assertEquals("checkLargeStraight() should NOT increase score for (1,2,4,5,6)", 
                before, g.getScore());                  
    }     
} 

 


