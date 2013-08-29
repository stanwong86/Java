import java.util.Scanner;


public class HumanPlayer extends Player {
	
	private int betAmount = 0;
	private Chip chips;
	
	public HumanPlayer() {
		chips = new Chip();
	}
	
	public int getBetAmount() {
		return betAmount;
	}

	public void setBetAmount() {
		Scanner key = new Scanner(System.in);
		boolean validBetAmount = false;

		System.out.println(this.getFirstName() + ", You have " + chips.getChipCount() + " chips.");
		do {
			System.out.println("How much do you want to bet?");
			String input = key.nextLine();
			try {
				betAmount = Integer.parseInt(input);
				if(betAmount < 1) {
					System.out.println("Your bet has to be a positive number.");
				} else if(betAmount > chips.getChipCount()) {
					System.out.println("Your bet cannot be greater than your chip stack.");
				} else {
					validBetAmount = true;
					return;
				}
			} catch(NumberFormatException e) {
				System.out.println("Could not recognize input. Please try again.");
			}
		} while(!validBetAmount);
	}

	public Chip getChips() {
		return chips;
	}
	
	public Action performAction(boolean allowDoubleDown) {
		Scanner key = new Scanner(System.in); 
		
		do {
			System.out.println(this.getFirstName() + ", What would you like to do (h)it,(d)ouble down,(s)tand?");
			String input = key.nextLine();
			
			if(input.equalsIgnoreCase("h")) {
				return Action.HIT;
			} else if(input.equalsIgnoreCase("d")) {
				if (!allowDoubleDown) {
					System.out.println("You cannot double down after hitting.");
				} else if ((betAmount * 2) > getChips().getChipCount()) {
					System.out.println("Insufficient chips to double down.");
				} else {
					betAmount *= 2;
					return Action.DOUBLE_DOWN;	
				}
			} else if(input.equalsIgnoreCase("p")) {
				return Action.SPLIT;
			} else if(input.equalsIgnoreCase("s")) {
				return Action.STAND;
			} else {
				System.out.println("Sorry, did not recognize action. Please try again.");
			}
		} while(true);
	}
}
