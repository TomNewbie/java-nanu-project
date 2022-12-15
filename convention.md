# Game Nanu Project for Java WS2022

## Convention:

naming will be **camelCase**

_variable_ start with low character. Ex: `player1`, `playerController`

_function_ start with Capital letter. Ex: `DrawCard()`, `StartGame()`

_class_ always start with Capital letter. Ex: `Controller`, `Card`

## Note:

Currently maven is not neccessary yet, if you want to find out you can check it

If you can not run the file by button you can run it manually

## Clean maven:

`mvn clean`
To clean the target file

## Build maven:

`mvn build`

Build file in src folder and test in test folder

The _build file_ will be in the _target folder_

## Command to run file:

`java -cp target/classes ws2022.client.controller.Controller`

Controller file live in **_target/class_ folder**

Controller has package **_ws2022.client.controller_** (the package that you write on the first line of filecode)

**java** is command to run java

**-cp** specify the #classpath

## Cover color code

- Dice.color = 0 `#FF0000` (red)
- Dice.color = 1 `#00FF00` (green)
- Dice.color = 2 `#0000FF` (blue)
- Dice.color = 3 `#FFFF00` (yellow)
- Dice.color = 4 `#FFA500` (orange)
- Dice.color = 5 => joker

## Resolution

Scene Resolution will be **1200\*600**

## Load JavaFX file

getLClass().getResource("/ws2022/Client/ViewFx/yourname.fxml"));
