package projects.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	private static Scanner in = new Scanner(System.in);
	private static List<Card> playerStack = new ArrayList<Card>();
	private static List<Card> dealerStack = new ArrayList<Card>();
	private static int blackJack = 21;
	private static Player player;
	private static Dealer dealer;
	private static int bet = 0;
	private static boolean isFlipped;

	public static void main(String[] args) {

		System.out.println("=== Welcome to Blackjack! ===");
		Options();

		player = new Player(GameOptions.getMoney());
		dealer = new Dealer();
		dealer.shuffle();

		while (true) {

			clearTable();

			System.out.println("Your money: $" + player.getMoney());
			if (player.getMoney() == 0) {
				System.out.println("Not enough money to play.");
				break;
			}

			int placeBetMsg = placeBet();
			if (placeBetMsg < 0) {
				continue;
			} else if (placeBetMsg > 0) {
				break;
			}

			printInfo();

			firstDeal();

			while (!endGame()) {
				System.out.print("Hit (h) or stand (s): ");

				String input = in.nextLine();

				if (input.equals("h")) {
					playerStack.add(dealer.deal());
					printInfo();
				} else if (input.equals("s")) {
					System.out.println("Stand");
					isFlipped = true;
					printInfo();
					while (!endGame()) {

						if (getSum(dealerStack) == 17 && hasAce(dealerStack)) {
							if (GameOptions.getSoft17() == GameOptions.dealerHits) {
								dealerStack.add(dealer.deal());
								shortPause();
								printInfo();
								continue;
							} else if (GameOptions.getSoft17() == GameOptions.dealerStands) {
								break;
							}
						}

						if (getSum(dealerStack) >= 17) {
							break;
						}

						dealerStack.add(dealer.deal());
						shortPause();
						printInfo();

					}

					break;

				} else {
					System.out.println("Invalid input.");
				}

			}

			findWinner();

		}

		in.close();

		System.out.println("Thanks for playing Blackjack!");

	}

	public static void Options() {

		Options: while (true) {

			GameOptions.printOptions();
			System.out
					.print("Choose an option you would like to change or '6' to play: ");

			int optionToChange;

			try {
				optionToChange = Integer.parseInt(in.nextLine());

				switch (optionToChange) {
				case 1:
					System.out.print("Enter number of decks (1 to 8): ");

					try {
						int numberOfDecks = Integer.parseInt(in.nextLine());

						if (1 <= numberOfDecks && numberOfDecks <= 8) {
							GameOptions.setNumberOfDecks(numberOfDecks);
						} else {
							System.out.println("Invalid input.");
						}

					} catch (NumberFormatException e) {
						System.out.println("Invalid input.");
					}
					break;
				case 2:
					GameOptions.toggleDoubleAfterSplit();
					break;
				case 3:
					GameOptions.toggleSurrender();
					break;
				case 4:
					GameOptions.toggleSoft17();
					break;
				case 5:
					System.out
							.print("Enter money amount (multiples of 5, 5 minimum): ");

					try {
						int money = Integer.parseInt(in.nextLine());
						;

						if (money >= 5) {
							GameOptions.setMoney(money);
						} else {
							System.out.println("Invalid input.");
						}

					} catch (NumberFormatException e) {
						System.out.println("Invalid input.");
					}
					break;
				case 6:
					break Options;
				default:
					System.out.println("Invalid input.");
					break;
				}

			} catch (NumberFormatException e) {
				System.out.println("Invalid input.");
			}

		}

	}

	public static int getSum(List<Card> cards) {
		int sum = 0;

		for (Card card : cards) {
			sum += getCardValue(card);
		}

		if (sum > blackJack && hasAce(cards)) {
			return sum - 10;
		}

		return sum;
	}

	public static int getCardValue(Card card) {

		if (card.getValue() == 1) {
			return 11;
		} else if (1 < card.getValue() && card.getValue() < 10) {
			return card.getValue();
		} else if (card.getValue() >= 10) {
			return 10;
		}

		return 0;
	}

	public static boolean hasAce(List<Card> cards) {

		for (Card card : cards) {

			if (card.getValue() == 1) {
				return true;
			}

		}

		return false;
	}

	public static void printInfo() {

		System.out.println();
		System.out.println("Bet: $" + bet);

		System.out.println("= Player =\t\t");

		if (!playerStack.isEmpty()) {

			for (Card card : playerStack) {
				System.out.println(card);
			}
		}

		System.out.println("Total: " + getSum(playerStack));

		System.out.println();
		System.out.println("= Dealer =\t\t");

		if (!dealerStack.isEmpty()) {

			if (dealerStack.size() == 2 && isFlipped == false) {

				if (hasNatural(dealerStack)) {
					for (Card card : dealerStack) {
						System.out.println(card);
					}
					System.out.println("Total: " + getSum(dealerStack));
				} else {
					System.out.println(dealerStack.get(0));
					System.out.println("?");
					System.out.println("Total: "
							+ getCardValue(dealerStack.get(0)));
				}
			} else {
				for (Card card : dealerStack) {
					System.out.println(card);
				}
				System.out.println("Total: " + getSum(dealerStack));
			}
		}
	}

	public static void shortPause() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void firstDeal() {
		playerStack.add(dealer.deal());

		shortPause();

		printInfo();

		dealerStack.add(dealer.deal());

		shortPause();

		printInfo();

		playerStack.add(dealer.deal());

		shortPause();

		printInfo();

		dealerStack.add(dealer.deal());

		shortPause();

		printInfo();
	}

	public static boolean hasNatural(List<Card> cards) {
		return getSum(cards) == 21;
	}

	public static boolean hasBusted(List<Card> cards) {

		if (getSum(cards) > blackJack) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean endGame() {

		if (hasNatural(dealerStack) && hasNatural(playerStack)) {
			return true;
		} else if (hasNatural(dealerStack) && !hasNatural(playerStack)) {
			return true;
		} else if (!hasNatural(dealerStack) && hasNatural(playerStack)) {
			return true;
		}

		if (hasBusted(playerStack)) {
			return true;
		}

		if (hasBusted(dealerStack)) {
			return true;
		}

		return false;

	}

	public static int placeBet() {
		System.out
				.print("Place bet (multiples of 5, from 5 to 100) or 'q' to quit: ");

		String input = in.nextLine();

		if (input.equals("q")) {
			return 1;
		}

		try {
			bet = Integer.parseInt(input);

			if (bet % 5 != 0 || (bet < 5 || bet > 100)) {
				System.out.println("Invalid input.");
				return -1;
			} else if (bet > player.getMoney()) {
				System.out.println("Not enough money.");
				return -1;
			}

		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
			return -1;
		}

		player.remove(bet);

		return 0;
	}

	public static void clearTable() {
		if (!playerStack.isEmpty()) {
			dealer.getCads(playerStack);
			playerStack.clear();
		}
		if (!dealerStack.isEmpty()) {
			dealer.getCads(dealerStack);
			dealerStack.clear();
		}
		isFlipped = false;
	}

	public static void findWinner() {
		int player = 1;
		int draw = 0;
		int dealer = -1;

		if (hasNatural(dealerStack) && hasNatural(playerStack)) {
			System.out.println("*** Draw ***");
			calculateBetResults(draw);
			return;
		} else if (hasNatural(dealerStack) && !hasNatural(playerStack)) {
			System.out.println("*** You lost ***");
			calculateBetResults(dealer);
			return;
		} else if (!hasNatural(dealerStack) && hasNatural(playerStack)) {
			System.out.println("*** You won ***");
			calculateBetResults(player);
			return;
		}

		if (hasBusted(playerStack)) {
			System.out.println("*** You lost ***");
			calculateBetResults(dealer);
			return;
		} else if (hasBusted(dealerStack)) {
			System.out.println("*** You won ***");
			calculateBetResults(player);
			return;
		}

		if (getSum(playerStack) > getSum(dealerStack)) {
			System.out.println("*** You won ***");
			calculateBetResults(player);
			return;
		} else if (getSum(playerStack) == getSum(dealerStack)) {
			System.out.println("*** Draw ***");
			calculateBetResults(draw);
			return;
		} else if (getSum(playerStack) < getSum(dealerStack)) {
			System.out.println("*** You lost ***");
			calculateBetResults(dealer);
			return;
		}

	}

	public static void calculateBetResults(int winner) {

		if (winner == 1) {
			if (getSum(playerStack) == 21) {
				player.add(bet + (int) (bet * 1.5));
			} else if (getSum(playerStack) < 21) {
				player.add(bet + bet);
			}
		} else if (winner == 0) {
			player.add(bet);
		}
	}

}
