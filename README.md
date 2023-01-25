# A1 - Piraten Karpen

  * Author: < Shike Chen >
  * Email: < doubizhandouji@gmail.com>

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar`
  * To run 42 games in trace mode:
    * `mvn exec:java -Dexec.args="'42' 'true'"` (copy and paste to terminal, it does not work here idk why)
  * To run with player1 with smart stretagy, player2 plays randomly
    * `mvn -q exec:java -Dexec.args="svd"`

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * feature is functional to its description

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| ✓   | F01 | Roll a dice |  D | 23/01/14 | 23/01/14 |
| ✓   | F02 | Roll eight dices  |  D | 23/01/14 | 23/01/14 |
| ✓   | F03 | Select how many games as command-line arg.  |  D  | 23/01/14 | 23/01/14 |
| ✓   | F04 | end of game with three cranes | D | 23/01/14 | 23/01/14 |
| ✓   | F05 | Player keeping random dice at their turn | D | 23/01/14 | 23/01/14 |
| ✓   | F06 | Score points: 3-of-a-kind | D | 23/01/14 | 23/01/14 |
| ✓   | F07 | Score points: gold and diamond  | D  | 23/01/14 | 23/01/14 |
| ✓   | F08 | Implement stretagy "Randomly roll dice until the end" for player  |D| 23/01/15 | 23/01/15 |
| ✓   | F09 | Implement game with 2 players using identical stretagy  | D| 23/01/15 | 23/01/15 |
| ✓   | F10 | Print results of the game | D | 23/01/15 | 23/01/15 |
| x   | F11 | Always play 42 games without command-line arg | D | 23/01/15 | 23/01/15 |
| ✓   | F12 | Implement player with higher point wins| D | 23/01/15 | 23/01/15 |
| ✓   | F13 | Implement score system from 3 of a kind to 7 of a kind | D | 23/01/15 | 23/01/15 |
| ✓   | F14 | Create Player as a seperate Object | D | 23/01/17 | 23/01/17 |
| x   | F15 | Implement full score system| D | 23/01/15 | 23/01/15 |
| ✓   | F17 | Implement comulated 6000 point player wins otherwise continue | D | 23/01/20 | 23/01/20 |
| x   | F18 | Implement Stretagy for player | D | 23/01/22 | 23/01/22 |
| ✓   | F19 | don't gain point with 3 Skull | D | 23/01/22 | 23/01/22 |
| ✓   | F20 | Player gets to choose which dice to keep, which dice to reroll  | D | 23/01/22 | 23/01/22 |
| ✓   | F20 | Implemented Random player vs Combo player in command lines| D | 23/01/22 | 23/01/22 |
| ✓   | F21 | implement Card that is iterable, comparable| S |23/01/25 | |
| ✓   | F22 | implement Deck that can be shuffled| P(F21) | | |
| ✓   | F22 | implement Full Chest| P | | |
| ... | ... | ... |

