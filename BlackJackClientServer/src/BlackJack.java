/*
 * 3/01/2012 - Fixed bug that started thread on redeal button. GUI portion is done and working
 * 2/29/2012 - Added callback for gui, Need to fix Redeal button, does not redraw icons
 * 2/20/2012 - Allowed continuous play.
 * 2/19/2012 - Added betAmount
 */

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class BlackJack implements HumanInteraction{
	private HumanPlayer human;
	private Dealer dealer;
	private Deck deck;
	private Action action;
	private boolean allowDoubleDown;	// Prevent Double Down after Hit or Stand
	private boolean readyToRedeal;		// Deals new cards when Redeal Button is pressed
	private boolean gameInPlay;			// Prevents Redeal button when round is not over
	private boolean disableButtons;		// Prevents Hit, DoubleDown, Stand buttons when no human interaction is required.
	private BlackJackGUI gui;
	
	private void startBlackJackGame() {
		human = new HumanPlayer();
		dealer = new Dealer();
		
		gui = new BlackJackGUI(this);
		gui.setVisible(true);
		
		// Get Player Name
		String playerName = getPlayerName();	
		gui.getPlayerLabel().setText(playerName + "'s Hand");
		
		while(true) {
			gameInPlay = true;
			readyToRedeal = false;
			redealEvents();
			gameInPlay = false;
			while(!readyToRedeal);
		}
	}
	
	private void clearCards() {
		dealer.clearCards();
		for(int i=0;i < gui.getDealerCards().length;i++) {
			gui.getDealerCards()[i].setIcon(null);
		}
		human.clearCards();
		for(int i=0;i < gui.getPlayerCards().length;i++) {
			gui.getPlayerCards()[i].setIcon(null);
		}
	}
	
	private void redealEvents() {
		showChipAmount();	// Show Chip Amount
		setBetAmount();		// Set Bet Amount
		initializeDealer();	// Get Dealer cards
		playHuman();		// Play Human
		playDealer();		// Play Dealers
	}

	private void showChipAmount() {
		String chipsStr = Integer.toString(human.getChips().getChipCount());
		gui.getChips().setText(chipsStr);
		//JOptionPane.showMessageDialog(null, human.getFirstName() + ", You have " + human.getChips().getChipCount() + " chips.");
	}
	
	private String getPlayerName() {
		String inputValue;
		do {
			inputValue = JOptionPane.showInputDialog("Please input first name");
			if(inputValue != null && !inputValue.equals("")) {
				human.setFirstName(inputValue);
				return inputValue;
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Name");
			}
		} while(inputValue == null || inputValue.equals(""));
		return null;
	}
	
	
	private void setBetAmount() {
		if(human.getChips().getChipCount() < 1) {
			JOptionPane.showMessageDialog(null, human.getFirstName() + " has no more chips.");
		} else {
			human.setBetAmount();
			String betAmountStr = Integer.toString(human.getBetAmount());
			gui.getBetAmount().setText(betAmountStr);
		}
	}
	
	// Draw Cards from deck and add <cardCount> cards to Player
	private void drawCards(int cardCount, Player player) {
		for(int i=0;i < cardCount;i++) {
			Card drawnCard = deck.drawCard(); 		
			player.addCard(drawnCard);
		}
			
	}
	
	private void playHuman() {
		// Deal 2 cards to each human player			
		drawCards(2, human);

		showCards(human);
		allowDoubleDown = true;
		action = null;
		
		disableButtons = false;
		int sum;
		do {
			// wait until action is not null
			while(action == null);
			sum = human.sumOfCards();
			
			if(sum > 21) {
				JOptionPane.showMessageDialog(null, human.getFirstName()+ " has busted with " + human.sumOfCards());
				disableButtons=true;
				return;
			} else if (action == Action.DOUBLE_DOWN || action == Action.STAND){
				JOptionPane.showMessageDialog(null, human.getFirstName()+ " has " + human.sumOfCards());
				disableButtons=true;
				return;
			} 
			action = null;
		} while(human.getCards().size() < 5);
		disableButtons=true;
	}
	
	public void performHit() {
		if(!disableButtons) {
			drawCards(1, human);
			showCards(human);
			// Seems to be a timing issue if addCard is called too soon. ConcurrentModificationException gets thrown if next line is first in the method
			action = Action.HIT;
			allowDoubleDown = false;
		}
	}
	
	public void performDoubleDown() {
		if(!disableButtons) {
			if (!allowDoubleDown) {
				JOptionPane.showMessageDialog(null, "You cannot double down after hitting.");
			} else if ((human.getBetAmount() * 2) > human.getChips().getChipCount()) {
				JOptionPane.showMessageDialog(null, "Insufficient chips to double down.");
			} else {
				int betAmount = human.getBetAmount() * 2;
				human.setBetAmount(betAmount);
				String betAmountStr = Integer.toString(betAmount);
				gui.getBetAmount().setText(betAmountStr);
				
				drawCards(1, human);
				action = Action.DOUBLE_DOWN;
				showCards(human);
			}
			allowDoubleDown = false;
		}
	}
	
	public void performStand() {
		if(!disableButtons) {
			action = Action.STAND;
			allowDoubleDown = false;
		}
	}
	
	private void initializeDealer() {
		// Create new deck
		deck = new Deck();
		// Deal 2 cards to Dealer
		drawCards(2, dealer);
		
		showCards(dealer);
		// Hide first card
		ImageIcon icon = createImageIcon("images/back-blue.png");
		gui.getDealerCards()[0].setIcon(icon);
		
	}
	
	private void showCards(Dealer dealer) {
		for(int i=0;i<dealer.getCards().size();i++) {
			String cardStr = dealer.getCards().get(i).getRank().rank() + dealer.getCards().get(i).getSuit().suit();
			ImageIcon icon = createImageIcon("images/" + cardStr + ".png");
			gui.getDealerCards()[i].setIcon(icon);
		}
	}
	
	private void showCards(HumanPlayer human) {
		for(int i=0;i<human.getCards().size();i++) {
			String cardStr = human.getCards().get(i).getRank().rank() + human.getCards().get(i).getSuit().suit();
			ImageIcon icon = createImageIcon("images/" + cardStr + ".png");
			gui.getPlayerCards()[i].setIcon(icon);
		}
	}
	
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	private void playDealer() {
		int dealerSum = dealer.sumOfCards();
		showCards(dealer);
		
		while(dealerSum < 17 && dealer.getCards().size() < 6) {
			drawCards(1, dealer);
			dealerSum = dealer.sumOfCards();
			showCards(dealer);
		} 
	
		JOptionPane.showMessageDialog(null, "Dealer has " + dealerSum + " points.");

		if(human.sumOfCards() > 21) {
			lose(human, human.getFirstName()+ " busts!");
		} else if(dealerSum > 21) {
			win(human, "Dealer busts! " + human.getFirstName()+ " wins!");
		} else if(human.sumOfCards() == dealerSum) {
			JOptionPane.showMessageDialog(null, "Push!");
		} else if(human.sumOfCards() > dealerSum) {
			win(human,human.getFirstName()+ " wins!");
		} else {
			lose(human, human.getFirstName()+ " loses!");
		}

		if(human.getChips().getChipCount() == 0) {
			JOptionPane.showMessageDialog(null, "You have no more chips. Game Over!");
			System.exit(0);
		}
	}
	
	private void win(HumanPlayer human, String resultStr) {
		int chips = human.getChips().getChipCount();
		int bet = human.getBetAmount();
		chips += bet;
		human.getChips().setChipCount(chips);
		String chipsStr = Integer.toString(chips);
		gui.getChips().setText(chipsStr);
		JOptionPane.showMessageDialog(null, resultStr + "\n" +  
			human.getFirstName()+ " won "+ bet + " and has " + chips + " chips now.");
	}
	
	private void lose(HumanPlayer human, String resultStr) {
		int chips = human.getChips().getChipCount();
		int bet = human.getBetAmount();
		chips -= bet;
		human.getChips().setChipCount(chips);
		String chipsStr = Integer.toString(chips);
		gui.getChips().setText(chipsStr);
		JOptionPane.showMessageDialog(null, resultStr + "\n" + 
			human.getFirstName()+ " lost "+ bet + " and has " + chips + " chips left.");
	}
	
	@Override
	public void redeal() {
		if(gameInPlay) {
			JOptionPane.showMessageDialog(null, "Cannot Redeal. Game is still in session!");
		} else {
			clearCards();
			showCards(dealer);
			showCards(human);
			readyToRedeal = true;
		}
	}
	
	public static void main(String args[]) {
		BlackJack blackjack = new BlackJack();
		blackjack.startBlackJackGame();
	}


}
