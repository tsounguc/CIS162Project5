/**
 * Write a description of class PokerTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PokerTest
{
    public static void main(String [] args){

        int before;
        PokerDice game = new PokerDice();
        System.out.println("\nTesting begins...");
        
        // check for valid small straight
        before = game.getScore();
        game.setDice(new int[] {2,4,5,3,3});
        game.checkSmallStraight();
        System.out.println(before);
        System.out.println(game.getScore());
        if(before + PokerDice.SMALL_STRAIGHT != game.getScore()){
            System.out.println("FAIL: small straight not scored");
        }

        // check for bad small straight
        before = game.getScore();
        game.setDice(new int[] {2,4,4,3,3});
        game.checkSmallStraight();
        if(before != game.getScore()){
            System.out.println("FAIL: small straight scored but not valid");
        }
        
        // check for valid large straight
        before = game.getScore();
        game.setDice(new int[] {2,3,4,5,6});
        game.checkLargeStraight();
        if(before + PokerDice.LARGE_STRAIGHT != game.getScore()){
            System.out.println("FAIL: large straight not scored");
        }

        // check for bad large straight
        before = game.getScore();
        game.setDice(new int[] {2,4,4,3,3});
        game.checkLargeStraight();
        if(before != game.getScore()){
            System.out.println("FAIL: large straight scored but not valid");
        }
        
        // check for valid three of a kind
        before = game.getScore();
        game.setDice(new int[] {2,2,2,3,3});
        game.checkThreeOfAKind();
        if(before + PokerDice.THREE_OF_A_KIND != game.getScore()){
            System.out.println("FAIL: three of a kind not scored");
        }

        // check for bad three of a kind
        before = game.getScore();
        game.setDice(new int[] {2,4,4,3,3});
        game.checkThreeOfAKind();
        if(before != game.getScore()){
            System.out.println("FAIL: three of a kind scored but not valid");
        }
        
        // check for valid four of a kind
        before = game.getScore();
        game.setDice(new int[] {2,3,3,3,3});
        game.checkFourOfAKind();
        if(before + PokerDice.FOUR_OF_A_KIND != game.getScore()){
            System.out.println("FAIL: four of a kind not scored");
        }

        // check for bad four of a kind
        before = game.getScore();
        game.setDice(new int[] {2,4,4,3,3});
        game.checkFourOfAKind();
        if(before != game.getScore()){
            System.out.println("FAIL: four of a kind scored but not valid");
        }
        
        // check for valid five of a kind
        before = game.getScore();
        game.setDice(new int[] {5,5,5,5,5});
        game.checkFiveOfAKind();
        if(before + PokerDice.FIVE_OF_A_KIND != game.getScore()){
            System.out.println("FAIL: five of a kind not scored");
        }

        // check for bad five of a kind
        before = game.getScore();
        game.setDice(new int[] {2,4,4,3,3});
        game.checkFiveOfAKind();
        if(before != game.getScore()){
            System.out.println("FAIL: five of a kind scored but not valid");
        }
        
        // check for valid singles
        before = game.getScore();
        // System.out.println(before);
        game.setDice(new int[] {3,3,3,3,4});
        game.checkSingles(3);
        // System.out.println(game.getScore());
        if(before + 12 != game.getScore()){
            System.out.println(game.getScore());
            System.out.println("FAIL: single not scored");
        }

        // check for bad singles
        before = game.getScore();
        game.setDice(new int[] {2,4,4,3,3});
        game.checkSingles(6);
        if(before != game.getScore()){
            System.out.println("FAIL: single scored but not valid");
        }
        
        // check for valid chance
        before = game.getScore();
        game.setDice(new int[] {1,2,3,4,5});
        game.checkChance();
        if(before + 15 != game.getScore()){
            System.out.println("FAIL: chance not scored");
        }
        
        System.out.println("Testing completed.");
    }
}
