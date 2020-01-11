import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *GUI front end to a dice game called Poker Dice
 *
 * @author Christian Tsoungui Nkoulou
 * @version April 13, 2013
 */
public class PokerGUI extends JFrame implements ActionListener{
    /** instance variable*/
    PokerDice theGame;
    int bonusTracker = 0;

    /** menu items*/
    JMenuBar menus;
    JMenu fileMenu;
    JMenuItem quitItem;
    JMenuItem newGameItem;


    /** buttons and labels*/
    JButton onesButton, twosButton, threesButton, foursButton, fivesButton, sixesButton;
    JButton smallStraightButton, largeStraightButton, fullHouseButton;
    JButton threeOfAKindButton, fourOfAKindButton, fiveOfAKindButton, chanceButton, rollButton;

    JLabel score, bonus;

    /****************************************************************
    Main method start the game
     ****************************************************************/    
    public static void main(String args[]){
        PokerGUI gui = new PokerGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Christian Tsoungui Nkoulou");
        gui.pack();
        gui.setVisible(true);
    }

    /****************************************************************
    Create all elements and display within GUI
     *****************************************************************/
    public PokerGUI()
    {
        // initialise instance variables
        theGame = new PokerDice();

        score = new JLabel();
        bonus = new JLabel();

        // create the lay out
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();
        setBackground(Color.lightGray);

        ArrayList <GVdie> theDice = theGame.getDice();
        int i = 0;
        for(GVdie d: theDice)
        {
            position.gridx = i;
            position.gridy = 0;
            add(d,position);
            i++;
        }

        onesButton = new JButton("1s");
        position.gridx = 0;
        position.gridy = 1;
        add(onesButton, position);
        onesButton.addActionListener(this);

        twosButton = new JButton("2s");
        position.gridy = 2;
        add(twosButton, position);
        twosButton.addActionListener(this);
        
        threesButton = new JButton("3s");
        position.gridy = 3;
        add(threesButton, position);
        threesButton.addActionListener(this);
        
        foursButton = new JButton("4s");
        position.gridy = 4;
        add(foursButton, position);
        foursButton.addActionListener(this);
        
        fivesButton = new JButton("5s");
        position.gridy = 5;
        add(fivesButton, position);
        fivesButton.addActionListener(this);
        
        sixesButton = new JButton("6s");
        position.gridy = 6;
        add(sixesButton, position);
        sixesButton.addActionListener(this);
        
        bonus.setText("Bonus: " + theGame.getBonusScore());
        position.gridy = 7;
        add(bonus, position);
        
        smallStraightButton = new JButton("Small Straight");
        position.gridx = 1;
        position.gridy = 1;
        add(smallStraightButton, position);
        smallStraightButton.addActionListener(this);
        
        largeStraightButton = new JButton("Large Straight");
        position.gridy = 2;
        add(largeStraightButton, position);
        largeStraightButton.addActionListener(this);
        
        fullHouseButton = new JButton("Full House");
        position.gridy = 3;
        add(fullHouseButton, position);
        fullHouseButton.addActionListener(this);
        
        threeOfAKindButton = new JButton("3 of a Kind");
        position.gridy = 4;
        add(threeOfAKindButton, position);
        threeOfAKindButton.addActionListener(this);
        
        fourOfAKindButton = new JButton("4 of a Kind");
        position.gridy = 5;
        add(fourOfAKindButton, position);
        fourOfAKindButton.addActionListener(this);
        
        fiveOfAKindButton = new JButton("5 of a Kind");
        position.gridy = 6;
        add(fiveOfAKindButton, position);
        fiveOfAKindButton.addActionListener(this);
        
        chanceButton = new JButton("Chance");
        position.gridy = 7;
        add(chanceButton, position);
        chanceButton.addActionListener(this);
        
        rollButton = new JButton("roll");
        position.gridx = 2;
        position.gridy = 8;
        add(rollButton, position);
        rollButton.addActionListener(this);
        
        score.setText("Score: " + theGame.getScore());
        position.gridx = 3;
        position.gridy = 8;
        add(score, position);
        
        
        
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        newGameItem = new JMenuItem("New Game");
        
        fileMenu.add(quitItem);
        fileMenu.add(newGameItem);
        menus = new JMenuBar();
        setJMenuBar(menus);
        menus.add(fileMenu);
        
        quitItem.addActionListener(this);
        newGameItem.addActionListener(this);
        reset();
    }

    private void reset()
    {
        theGame.resetGame();
        bonusTracker = 0;
        onesButton.setEnabled(true);
        twosButton.setEnabled(true);
        threesButton.setEnabled(true);
        foursButton.setEnabled(true);
        fivesButton.setEnabled(true);
        sixesButton.setEnabled(true);
        
        smallStraightButton.setEnabled(true);
        largeStraightButton.setEnabled(true);
        fullHouseButton.setEnabled(true);
        threeOfAKindButton.setEnabled(true);
        fourOfAKindButton.setEnabled(true);
        fiveOfAKindButton.setEnabled(true);
        chanceButton.setEnabled(true);
        
    }

    /****************************************************************
    This method called when any button or a menu item
    is selected

    @param event - the JComponent just selected
     ****************************************************************/
    public void actionPerformed(ActionEvent event)
    {
        //player select menu to quit the game
        if(event.getSource() == quitItem)
        {
            System.exit(1);
        }
        //FIX ME: player selects menu item to start a new game
        if(event.getSource() == newGameItem)
        {
            reset();
        }
        
        if(event.getSource() == rollButton)
        {
            theGame.rollDice();
        }
        
        if(event.getSource() == onesButton)
        {
            theGame.checkSingles(1);
            onesButton.setEnabled(false);
        }
        
        if(event.getSource() == twosButton)
        {
            theGame.checkSingles(2);
            twosButton.setEnabled(false);
        }
        
        if(event.getSource() == threesButton)
        {
            theGame.checkSingles(3);
            threesButton.setEnabled(false);
        }
        
        if(event.getSource() == foursButton)
        {
            theGame.checkSingles(4);
            foursButton.setEnabled(false);
        }
        
        if(event.getSource() == fivesButton)
        {
            theGame.checkSingles(5);
            fivesButton.setEnabled(false);
        }
        
        if(event.getSource() == sixesButton)
        {
            theGame.checkSingles(6);
            sixesButton.setEnabled(false);
        }
        
        if(event.getSource() == smallStraightButton)
        {
            theGame.checkSmallStraight();
            smallStraightButton.setEnabled(false);
        }
        
        if(event.getSource() == largeStraightButton)
        {
            theGame.checkLargeStraight();
            largeStraightButton.setEnabled(false);
        }
        
        if(event.getSource() == fullHouseButton)
        {
            theGame.checkFullHouse();
            fullHouseButton.setEnabled(false);
        }
        
        if(event.getSource() == threeOfAKindButton)
        {
            theGame.checkThreeOfAKind();
            threeOfAKindButton.setEnabled(false);
        }
        
        if(event.getSource() == fourOfAKindButton)
        {
            theGame.checkFourOfAKind();
            fourOfAKindButton.setEnabled(false);
        }
        
        if(event.getSource() == fiveOfAKindButton)
        {
            theGame.checkFiveOfAKind();
            fiveOfAKindButton.setEnabled(false);
        }
        
        if(event.getSource() == chanceButton)
        {
            theGame.checkChance();
            chanceButton.setEnabled(false);
        }
        
        if(theGame.okToRoll())
        {
            rollButton.setEnabled(true);
        }else
        {
            rollButton.setEnabled(false);
        }
        
        bonusTracker+= theGame.getBonusScore();
        bonus.setText("Bonus: " + bonusTracker);
        score.setText("Score: " + theGame.getScore());
        
        if(theGame.gameOver())
        {
            JOptionPane.showMessageDialog(null, "Game Over! You Won!");
            rollButton.setEnabled(false);
        }
    }
}
