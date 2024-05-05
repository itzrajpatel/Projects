import random

def determine_winner(player_choice, computer_choice):
    if player_choice == computer_choice:
        return "It's a tie!"
    elif (player_choice == 'rock' and computer_choice == 'scissors') or \
         (player_choice == 'paper' and computer_choice == 'rock') or \
         (player_choice == 'scissors' and computer_choice == 'paper'):
        return "You win!"
    else:
        return "Computer wins!"

def main():
    choices = ['rock', 'paper', 'scissors']
    player_score = 0
    computer_score = 0
    
    player_name = input("Enter your name: ")
    print(f"Welcome {player_name} to Rock, Paper, Scissors!")
    
    while True:
        print(f"{player_name}'s: {player_score} | Computer Score: {computer_score}")
        player_choice = input("Enter your choice (rock/paper/scissors): ").lower()
        
        if player_choice not in choices:
            print("Invalid choice. Please enter rock, paper, or scissors.")
            continue
        
        computer_choice = random.choice(choices)
        print(f"Computer chose: {computer_choice}")
        
        result = determine_winner(player_choice, computer_choice)
        print(result)
        
        if "win" in result:
            if result == "You win!":
                player_score += 1
            else:
                computer_score += 1
        elif "tie" not in result:
            computer_score += 1
        
        play_again = input("Do you want to play again? (yes/no): ").lower()
        if play_again != 'yes':
            print("Thanks for playing ",player_name,"!")
            print(f"Final Score - {player_name}: {player_score} | Computer: {computer_score}")
            break

if __name__ == "__main__":
    main()
