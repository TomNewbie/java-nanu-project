# Nanu Game Project for Java WS2022

![pic697952](https://user-images.githubusercontent.com/101057915/215149706-e69b1eae-c2de-4fcc-a657-d843b3739106.jpg)

Nanu Game - board game application using Java.

This project is started from 24th November 2022 to 7th February 2023.

The project documentation can be found Here

The API of the project can be see on the website ... or by open the index.html inside the apidocs folder with local host ???

If you have any questions or some feedback on the application then send a mail to ???

Thank you for spending your time to know more about our team!

Nanu Game Team

## Table of contents

* Introduction
  - [Introduction of the game](#Introduction-of-the-game)
  - [Set up and Concepts to play](#Set-up-and-Concepts-to-play)
* [Using Nanu](#Using-Nanu)

* Repository materials
  - [Folder stucture](#Folder-stucture)
  - [Convention](#Convention)
  - [Build maven](#Build-maven)
  - [Clean maven](#Clean-maven)
  - [Command to run file](#Command-to-run-file)
  - [Cover color code](#Cover-color-code)
  - [Clean maven](#Clean-maven)
  - [Resolution](#Resolution)
  - [Load JavaFX file](#Load-JavaFX-file)
  - [LaTeX Documentation](#LaTeX-Documentation)
  - [Figma GUI Design](#Figma-GUI-Design)
  - [Flow Chart](#Flow-Chart)
* How to play the game

* [Authors](#Authors)

## Introduction

### Introduction of the game

Children learn through play. Games and toys help to develop important cognitive, visual perceptual, motor, sensory, social, play and leisure skills. Remember five different color/image combinations at the same time. Now quickly forget those as new combinations are introduced, over and over. That’s Nanu?. A board game by (Uncredited) Heinz Meister, publisher: Ravensburger Verlag GmbH.

The goal is to collect the most discs by remembering the images under the covers. The discs are 1 5/8” in diameter and the plastic covers are just a little bit bigger so they can fit over the top of them. Each disc has one ordinary item pictured on it such as tree, rooster, ball, cat, cherries and sail boat and each disc has a different image. The covers are about 7/16” deep and are hollow so that they fit over the top of the disc without anything showing. The cover colors are red, blue, green, yellow and orange. The die has a different color on five of the sides, to match the colors of the covers, and the sixth side has a picture of a joker. The game will over when a player has the most discs.

###  Set up and Concepts to play

Place the 24 discs, picture side up, in a grid shape on the table top. Place the five covers over five discs. As you place each cover say something like “The tree is under the orange disc”. All players pay attention to this part and watch and listen to remember which pictures are
where.

The first player throws the die. The color he gets will indicate the color of cover he will he lifting. If he gets the joker he can lift any cover. Let’s pretend the player got an orange on the die. Before lifting the cover, the player must announce the image that is under the cover, saying something like ”Under the orange cover is the tree”. If he is correct, he takes the disc and sets it by his place (to be counted at the end of the game). He then takes the cover and puts it on any disc left in the grid, announcing what he is covering and the color of the cover. His turn is over. If he is incorrect, he has to leave the disc there, re-cover it, and the next player throws the die for their turn. The game will end when there are only four discs left in the grid and there is nowhere to place the fifth cover. Player with the most discs at this point is the winner.

## Using Nanu

There is documentation for the Nanu Game [here](http://projects.csail.mit.edu/jeeves/doc/jeeveslib.html). We have a [Quick Introduction to Jeeves](https://github.com/jeanqasaur/jeeves/wiki/A-Quick-Introduction-to-Jeeves). (Happy to take suggestions on how to make it more useful!)

You may also find it helpful to read our tests in ```test``` and ```test/gallery```.

## Repository materials

### Folder stucture:

<img width="202" alt="Folder structure" src="https://user-images.githubusercontent.com/101057915/215150670-6f53a55f-c46d-42be-8cb3-308b5645a7b0.png">

* .vscode: 
Workspace settings as well as debugging and task configurations are stored at the root in a .vscode folder.
* src/main: main folder, this is the part where we put all our code.
* java/ws2022: This folder contains 3 more subfolders: client, middleware, and server and one file app.java.
* Client: this folder contains the code for control the logic (offline game) and also the code for the client side (online game).
* Model: contains class structure of the game, directly manages data, logic, and rules of the application.
* View: contains the GUI of the game.
* Controller: contains of java file controlling fxml file.
* Middleware: the API is the translation message between the client and server (online game).
* Server: it contains the logic for set up the server to connect between multiple machines (online game)
* resources/ws2022: This folder keeps all the resources, e.g. sound, image for our games. In this folder, there are 3 subfolders: assets (keeps the image), sound (keeps sound files for sound effect), fxml (keeps all the file with the extension .fxml for the UI)
* target: The target folder is the maven default output folder. When a project is build or packaged, all the content of the sources, resources and web files will be put inside of it, it will be used for construct the artifacts and for run tests.
* .classpath: The .classpath maintains the project's source and target references for Java compilation and compressed file or project dependencies.
* .gitignore: When working with github to share our code, we don’t want to push some unnecessary files to our repository, e.g. .class, .vscode so we add some patterns to this file so github will ignore these files.
* pom.xml: The pom.xml file contains information of project and configuration information for the maven to build the project such as dependencies, build directory, source directory, test source directory, plugin, goals etc.
* readme.md: The What, Why, and How of the project are addressed in the readme.md file. Visitors to the repository will see the README automatically from GitHub.

### Convention:

naming will be **camelCase**

_variable_ start with low character. Ex: `player1`, `playerController`

_function_ start with Capital letter. Ex: `DrawCard()`, `StartGame()`

_class_ always start with Capital letter. Ex: `Controller`, `Card`

### Maven:

#### Build maven:

`mvn build`

Build file in src folder and test in test folder

The _build file_ will be in the _target folder_

#### Clean maven:

`mvn clean`
To clean the target file

> **Note:**
> * Currently maven is not neccessary yet, if you want to find out you can check it
> 
> * If you can not run the file by button you can run it manually

### Command to run file:

`java -cp target/classes ws2022.client.controller.Controller`

Controller file live in **_target/class_ folder**

Controller has package **_ws2022.client.controller_** (the package that you write on the first line of filecode)

**java** is command to run java

**-cp** specify the #classpath

### Cover color code

- Dice.color = 0 `#FF0000` (red)
- Dice.color = 1 `#00FF00` (green)
- Dice.color = 2 `#0000FF` (blue)
- Dice.color = 3 `#FFFF00` (yellow)
- Dice.color = 4 `#FFA500` (orange)
- Dice.color = 5 => joker

### Resolution

Scene Resolution will be **1000\*600**

### Load JavaFX file

getLClass().getResource("/ws2022/Client/ViewFx/yourname.fxml"));

### LaTeX Documentation

https://www.overleaf.com/7329553797sryqvnqsmpzd

### Figma GUI Design

https://www.figma.com/file/iQLAIDtyQSxNdCKzSO8HGx/Nanu-game?node-id=0%3A1&t=2ZMhV3Kt0HN656eo-1

### Flow Chart

https://www.figma.com/file/GRC56e384dnW1vtGOBuxu2/nanu?t=3MCzRTGohLyGowFa-0

## How to play the game

## Authors
* [Tho Phan Chi](https://github.com/TomNewbie)
* [Thanh Le Hoang Kim](https://github.com/Kimthanh11)
* [Ferid Gökkaya](https://github.com/ferid99)
* [Kim Tran Hoang](https://github.com/HoangKim1504)
* [Ngoc Pham Nhu](https://github.com/PhamNhuNgoc)
