/*
 * 2/20/12 - Allowed continuous play.
 * 2/19/12 - Added betAmount
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BlackJack {
	private static ArrayList<HumanPlayer> humans = new ArrayList<HumanPlayer>();
	private static Dealer dealer = new Dealer();
	private static Card drawnCard;
	private static Deck deck;
	private static Action action;
	
	public static void main(String[] args) {
		BlackJack blackjack = new BlackJack();		
		blackjack.startBlackJackGame();
	}
	
	private void startBlackJackGame() {
		// Get Player Count
		getPlayerCount();
		do {
			// Bet amount
			setBetAmount();
			if(!humans.isEmpty()) {
				// Get Dealer cards
				initializeDealer();
				// Action for Human Players 
				playHuman();
				// Action for Dealer
				playDealer();
				// Clear Player Hands
				System.out.println("Re-shuffling Cards\n");
			}
			for(HumanPlayer human:humans) {
				human.clearCards();
			}
		} while(!humans.isEmpty());
		System.out.println("Game Over.");
		System.exit(0);
	}

	private void getPlayerCount() {
		Scanner scan = new Scanner(System.in);
		int playerCount = 0;
		String[] playerNames = {"Paul","David","Jenny","Walter","Barbara"};
		do {
			GetPlayer:
			try {
				System.out.println("How many players?");
				String readInput = scan.nextLine();
				playerCount = Integer.parseInt(readInput);
				if(playerCount < 1 || playerCount > 5) {
					System.out.println("Player Count must be between 1 to 5");
				}
			} catch(NumberFormatException e) {
				System.out.println("Invalid Number Format");
				break GetPlayer;
			}
		} while(!(playerCount >= 1 && playerCount <= 5));
		// Add human players
		for(int i=0;i<playerCount;i++) {
			String playerName = playerNames[i];
			HumanPlayer humanPlayer = new HumanPlayer();
			// Set Name
			humanPlayer.setFirstName(playerName);
			humans.add(humanPlayer);
		}
		System.out.println("Players: "+playerCount);
	}
	
	private void setBetAmount() {
		Iterator i = humans.iterator();
		while(i.hasNext()) {
			HumanPlayer human = (HumanPlayer)i.next();
			if(human.getChips().getChipCount() < 1) {
				System.out.println(human.getFirstName() + " has no more chips.");
				i.remove();
			} else {
				human.setBetAmount();
			}
		}
	}
	
	private void playHuman() {

		for(HumanPlayer human: humans) {
			// Deal 2 cards to each human player			
			drawnCard = deck.drawCard(); 		
			human.addCard(drawnCard);
			drawnCard = deck.drawCard(); 
			human.addCard(drawnCard);
			
			boolean allowDoubleDown = true;
			playLoop:
			do {
				System.out.print(human.getFirstName()+ " has " + human.showCards() + "\n");
				action = human.performAction(allowDoubleDown);
				switch(action) {
					case HIT:
						drawnCard = deck.drawCard();
						human.addCard(drawnCard);
						allowDoubleDown = false;
						break;
					case DOUBLE_DOWN:		
						drawnCard = deck.drawCard();
						human.addCard(drawnCard);
						break;
					//case SPLIT:				
					case STAND:
					default:
						break;
				}
				if(human.sumOfCards() > 21) {
					System.out.print(human.getFirstName()+ " has " + human.showCards() + "\n");
					System.out.println(human.getFirstName()+ " has busted with " + human.sumOfCards());
					break playLoop;
				} else if (action == Action.DOUBLE_DOWN || action == Action.STAND){
					System.out.println(human.getFirstName()+ " has " + human.showCards() + "\n");
				}
				
			} while(action == Action.HIT && human.getCards().size() < 5);
		}
	}
	
	private void initializeDealer() {
		// Create new deck
		deck = new Deck();
		dealer.clearCards();
		// Deal 2 cards to Dealer
		drawnCard = deck.drawCard(); 		
		dealer.addCard(drawnCard);
		
		drawnCard = deck.drawCard(); 		
		dealer.addCard(drawnCard);
		
		System.out.print("Dealer has " + dealer.hideCards() + "\n");
	}
	
	private void playDealer() {
		int sum = dealer.sumOfCards();
		System.out.print("Dealer has " + dealer.showCards() + "\n");
		
		while(sum < 17 && dealer.getCards().size() < 6) {
			drawnCard = deck.drawCard();
			dealer.addCard(drawnCard);
			sum = dealer.sumOfCards();
			System.out.print("Dealer has " + dealer.showCards() + "\n");
		} 
		
		System.out.print("Dealer has " + sum + " points.\n");

		if(sum > 21) {
			System.out.print("Dealer busts!\n");
			for(HumanPlayer human:humans) {
				if(human.sumOfCards() > 21) {
					System.out.print(human.getFirstName()+ " busts!\n");
					lose(human);
				} else {
					System.out.print(human.getFirstName()+ " wins!\n");
					win(human);
				}
			}
		} else {
			for(HumanPlayer human:humans) {
				if(human.sumOfCards() > 21) {
					System.out.print(human.getFirstName()+ " busts!\n");
					lose(human);
				} else if(human.sumOfCards() == sum) {
					System.out.print(human.getFirstName()+ " pushes!\n");
				} else if(human.sumOfCards() > sum) {
					System.out.print(human.getFirstName()+ " wins!\n");
					win(human);
				} else {
					System.out.print(human.getFirstName()+ " loses!\n");
					lose(human);
				}
			}
		}
	}
	
	private void win(HumanPlayer human) {
		int chips = human.getChips().getChipCount();
		int bet = human.getBetAmount();
		chips += bet;
		human.getChips().setChipCount(chips);
		System.out.println(human.getFirstName()+ " won "+ bet + " and has " + chips + " chips now.");
	}
	
	private void lose(HumanPlayer human) {
		int chips = human.getChips().getChipCount();
		int bet = human.getBetAmount();
		chips -= bet;
		human.getChips().setChipCount(chips);
		System.out.println(human.getFirstName()+ " lost "+ bet + " and has " + chips + " chips left.");
	}
}
