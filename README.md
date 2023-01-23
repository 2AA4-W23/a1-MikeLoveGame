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
  * To run the program in trace mode:
    * `mvn exec:java -Dexec.args="'42' 'true'"` (copy and paste to terminal, it does not work here idk why)
  * To run with player1 with smart stretagy, player2 plays randomly
    * mvn exec:java -Dexec.args=”svd”

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * feature is functional to its description

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice |  D | 01/01/13| 01/01/13 |
| x   | F02 | Roll eight dices  |  D | 01/01/13| 01/01/13 |
| x   | F03 | Select how many games as command-line arg.  |  D  | 01/01/13| 01/01/13 |
| x   | F04 | end of game with three cranes | D | 01/01/14| 01/01/14 |
| x   | F05 | Player keeping random dice at their turn | D | 01/01/14| 01/01/14 |
| x   | F06 | Score points: 3-of-a-kind | D | 01/01/14| 01/01/14 |
| x   | F07 | Score points: gold and diamond  | D  | 01/01/15| 01/01/15 |
| x   | F08 | Implement stretagy "Randomly roll dice until the end" for player  |D| 01/01/15| 01/01/15 |
| x   | F09 | Implement game with 2 players using identical stretagy  | D| 01/01/15| 01/01/15 |
| x   | F10 | Print results of the game | D | 01/01/15| 01/01/15 |
| x   | F11 | Always play 42 games without command-line arg | D | 01/01/15| 01/01/15 |
| x   | F12 | Implement player with higer point wins| D | 01/01/15| 01/01/15 |
| x   | F13 | Implement score system from 3 of a kind to 7 of a kind | D | 01/01/15| 01/01/15 |
| x   | F14 | Create Player as a seperate Object | D | 01/01/17| 01/01/17 |
| x   | F15 | Implement full score system| D | 01/01/15| 01/01/15 |
| x   | F17 | Implement comulated 6000 point player wins otherwise continue | D | 01/01/20| 01/01/20 |
| x   | F18 | Implement Stretagy for player | D | 01/01/21 | 01/01/22 |
| x   | F19 | don't gain point with 3 Skull | D | 01/01/22 | 01/01/22 |
| x   | F20 | Player gets to choose which dice to keep, which dice to reroll  | D | 01/01/22 | 01/01/22 |
| x   | F20 | Implemented Random player vs Combo player in command lines| D | 01/01/22 | 01/01/22 |
| ... | ... | ... |

