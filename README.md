# Test Plan for Tennis Exercise

Test Plan for Tennis Exercise

## Match
* Make sure that it’s not possible to get more than 3 match points and match doesn’t continue (add extra point adding methods)

* Make sure that happy scenario of Player 1 winning all 3 match points equals to Player 1 winning overall match

* Make sure that happy scenario of Player 2 winning all 3 match points equals to Player 2 winning overall match

## Set

* Make sure that it’s not possible to get than 6 set points and no match point is given (had a bug when a lot of sets were played infinitely) (add extra point adding methods)

* Make sure that happy scenario of Player 1 winning all 6 set points in a row equals to Player 1 winning match point

* Make sure that happy scenario of Player 2 winning all 6 set points in a row equals to Player 2 winning match point

* Make sure that when Player 1 wins 7 points and Player 2 wins 5 points, Player 1 wins match point

* Make sure that when Player 2 wins 7 points and Player 1 wins 5 points, Player 2 wins match point

* Make sure that when Player 1 wins 6 points and Player 2 wins 6 points, Tie Break activates and when Player 1 wins 7 set points in the Tie Break, make sure Player 1 wins the set

* Make sure that when Player 2 wins 6 points and Player 1 wins 6 points, Tie Break activates and when Player 2 wins 7 set points in the Tie Break, make sure Player 2 wins the set


## Game

* Make sure that it’s not possible to get more than 3 game points and no set point is given (had same bug as with sets) (add extra point adding methods)

* Make sure that happy scenario of Player 1 winning all 3 game points in a row ("LOVE" (as 0), "15", "30", "40") equals to Player 1 winning game

* Make sure that happy scenario of Player 2 winning all 3 game points in a row ("LOVE" (as 0), "15", "30", "40") equals to Player 2 winning game

* Make sure that when Player 1 wins 3 points and Player 2 wins 2 points, Player 1 wins the game

* Make sure that when Player 1 wins 2 points and Player 2 wins 3 points, Player 2 wins the game

* (Deuce) Make sure that when Player 1 wins 3 points and Player 2 wins 3 points, and Player 1 gets the advantage point and scores, Player 1 wins the game

* (Deuce) Make sure that when Player 1 wins 4 points and Player 1 wins 4 points, and Player 2 gets the advantage point and scores, Player 2 wins the game

* (Deuce extended) Simulate Player 1 vs Player 2 on Deuce rule with few iterations of resetting the points back to 40-40, so that the last winning point is given to Player 1, make sure Player 1 wins the game

* (Deuce extended) Simulate Player 1 vs Player 2 on Deuce rule with few iterations of resetting the points back to 40-40, so that the last winning point is given to Player 2, make sure Player 2 wins the game

