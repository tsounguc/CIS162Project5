import java.util.ArrayList;
/**
 * Description: This class takes care of all the functions and 
 * calculations for the PokerDice Game
 *
 * @author Christian Tsoungui Nkoulou
 * @version 4/17/18
 */
public class PokerDice
{
    // instance variables - replace the example below with your own
    private ArrayList <GVdie> myDice; 
    private int score;
    private int numberOfRolls;
    private int numberOfRounds;
    private int bonusScore;
    private int[] tally;

    public final static int THREE_OF_A_KIND = 25;
    public final static int FULL_HOUSE = 35;
    public final static int FOUR_OF_A_KIND = 40;
    public final static int SMALL_STRAIGHT = 30;
    public final static int LARGE_STRAIGHT = 45;
    public final static int FIVE_OF_A_KIND = 50;

    /**
     * Constructor instantiate the ArrayList and fill it with five GVdie.  
     */
    public PokerDice()
    {
        myDice = new ArrayList <GVdie> ();

        for(int i = 0; i < 5; i++)
        {
            myDice.add(new GVdie());
        }
        tally = new int[7];
        resetGame();
    }

    /**
     * Returns the current score 
     */
    public int getScore()
    {
        score += getBonusScore();
        if(bonusScore >= 63)
        {
            bonusScore = bonusScore % 62;
        }
        return score;
    }

    /**
     * Return true if it is legal to roll the dice 
     * Otherwise, return false
     * There are only three rolls per round 
     */
    public boolean okToRoll()
    {
        if(numberOfRolls < 3 && numberOfRounds <13)
        {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Return true if the game is over
     * Otherwise, return false 
     */
    public boolean gameOver()
    {
        if(numberOfRounds == 13)
        {
            return true;
        }else{
            return false;
        }
    }

    /**
     * Return the ArrayList of GVdie
     */
    public ArrayList <GVdie> getDice()
    {
        return myDice;
    }

    // Helper Methods
    
    /**
     * Update the array of integers to tally the number of 
     * 1s, 2s, 3s, 4s, 5s and 6s for the current dice
     */
    private void tallyDice()
    {
        for(int i =1; i < tally.length; i++)
        {
            tally [i] = 0;
        }

        for (GVdie d : myDice)
        {
            tally[d.getValue()]++;
        }
    }

    /**
     * Determine if the set of dice contains a sequence of length numbers
     * @parameter length
     */
    private boolean hasStraight(int length)
    {
        tallyDice();
        int sequence = 0;
        for(int i = 1; i< tally.length; i++)
        {
            if(tally[i] >= 1 )
            {
                sequence++;
                if(sequence == length)
                {
                    return true;
                }
            }
            else{
                sequence = 0;
            }
        }
        return false;
    }

    /**
     * Return true if there are count, or more, identical values
     * Otherwise, return false.
     * @parameter count
     */
    private boolean hasMultiples(int count)
    {
        tallyDice();
        for(int i = 1; i<tally.length; i++)
        {
            if(tally[i] >= count)
            {
                return true;
            }
        }    
        return false;
    }

    /**
     * Determine if the set of dice contains a pair of any number
     * (but only a pair)
     */
    private boolean hasStrictPair()
    {
        tallyDice();
        for(int i = 1; i<tally.length; i++)
        {
            if(tally[i] == 2)
            {
                return true;
            }
        }    
        return false;
    }

    /**
     * this private helper method prepares for the next round by
     * incrementing the round counter, setting rolls count to zero,
     * setting all dice to blank and unselected.
     */
    private void nextRound()
    {
        numberOfRounds += 1;
        numberOfRolls = 0;
        for(GVdie d: myDice)
        {
            d.setBlank();
            d.setHeld(false);
        }
    }

    // mutator methods
    
    /**
     * set instance variables to zero and all dice to blank 
     */
    public void resetGame()
    {
        score = 0;
        numberOfRolls = 0;
        numberOfRounds = 0;
        bonusScore = 0;

        for(GVdie d: myDice)
        {
            d.setBlank();
            d.setHeld(false);
        }
    }

    /**
     * increment the number of rolls
     * and roll each of the dice that are not currently held.  
     */
    public void rollDice()
    {
        numberOfRolls += 1;
        for(GVdie d: myDice)
        {
            if(!d.isHeld())
            {
                d.setBlank();
                d.roll();
            }
        }
    }

    /**
     * update the score if the dice currently include 
     * a three of a kind.
     */
    public void checkThreeOfAKind()
    {
        if(hasMultiples(3))
        {
            score += THREE_OF_A_KIND;
        }
        nextRound();
    }

    /**
     * update the score if the dice currently include a full house
     */
    public void checkFullHouse()
    {
        if(hasMultiples(3) && hasStrictPair() || hasMultiples(5))
        {
            score += FULL_HOUSE;
        }else{
            nextRound();
        }
    }

    /**
     * update the score if the dice currently include a small straight
     */
    public void checkSmallStraight()
    {
        if(hasStraight(4))
        {
            score += SMALL_STRAIGHT;
        }
        nextRound();
    }

    /**
     * update the score if the dice currently include a large straight
     */
    public void checkLargeStraight()
    {
        if(hasStraight(5))
        {
            score += LARGE_STRAIGHT;
        }
        nextRound();
    }

    /**
     * update the score if the dice currently include a four-of-a-kind
     */
    public void checkFourOfAKind()
    {
        if(hasMultiples(4))
        {
            score += FOUR_OF_A_KIND;
        }
        nextRound();
    }

    /**
     * update the score if the dice currently include a five of a kind
     */
    public void checkFiveOfAKind()
    {
        if(hasMultiples(5))
        {
            score += FIVE_OF_A_KIND;
        }
        nextRound();
    }

    /**
     * update the score by adding all dice with the value of val
     * @parameter val
     */
    public void checkSingles(int val)
    {
        tallyDice();
        score += val*tally[val];
        bonusScore += val*tally[val];
        nextRound();
    }

    /**
     * update the score with the sum of all dice
     */
    public void checkChance()
    {
        tallyDice();
        for(int i = 1; i < tally.length; i++)
        {
            score += i*tally[i];
        }
        nextRound();
    }

    // Bonus Score

    /**
     * Return 35 or 0 if bonus reaches 63
     */
    public int getBonusScore()
    {
        if(bonusScore > 62)
        {
            return 35;
        }
        return 0;
    }

    /**
     * allows a player to 'cheat'by selecting values 
     * for each of the five dice. 
     */
    public void setDice(int[] values)
    {
        for(int i = 0; i < values.length; i++)
        {
            if(values[i] < 1 || values[i] > 6)
            {
                values[i] = 1;
            }
            GVdie die = myDice.get(i);
            die.roll();
            while(die.getValue() != values[i])
            {
                die.roll();
            }
        }
    }
}