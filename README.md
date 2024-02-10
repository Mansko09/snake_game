# Presentation
This is the good old snake game. Credits : dev_ression on ytb


# How to import this project in IntelliJ IDE :

1. In the IDE, go to `File > New > Project from Version Control`, under URL paste this repository's url (found under `<>Code > HTTPS` on github)
2. Make sure you have javafx downloaded in a easily aceessible folder
3. Go to `File > Project Structure > Libraries > Add`, and copy the path of the /lib folder of javafx
4. In the top right, click on `Main > Edit configuration > Main`, under VM options, copy this :
```
--module-path <./lib file path> --add-modules javafx.controls
```
Make sure you :
- replace `<./lib file path>` with your /lib folder's path

You can now run the code.
If the IDE tells you that there is a problem with the JDK, click on solve automatically and wait for the download to end, then run the code again

# How to play the game :
1. The game starts as soon as you click on run
2. To move, press the left,right,up and down keys on your keyboard.
3. If the snake touches himself our goes out of bound then it is GAME OVER.
4. Each time the snake eats food, the score goes up. 
