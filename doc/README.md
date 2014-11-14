# <center>Design document of Rukshar Wagid Hosain 10694676</center>

## Classes:

> A list of classes and public methods (and their return types and/or arguments) that I’ve decided to implement.

### Class main_activity:
Welcome screen, possibly a welcome sound. The screen shows a list of images (as buttons)  and a settings button to change the level and possibly to turn sounds on or off. Some functions in this class are:
```java
public void StartGame(){
//Opens startgame activity
}
public void Settings(){
//Opens settings activity
}
```

### Class settings:
Shows three buttons with three modes, namely “easy”, “medium” and “hard”. The default settings should be medium. The chosen setting could be displayed with a checkmark next to the button. The sound setting should also be a button . If another difficulty is chosen this difficulty should be remembered . The text should change into on or off depending on the user’s behavior. There should also be a back button to go back to the main_activity.

### Class start_game:
The selected image should be shown, this is how the solution should look. After 3 seconds this image should disappear and a shuffled image (reversed) should appear. The user can know swipe the tiles. Some functions in this class are:
```java

public void displaySolution(){
    // shows the image for 3 second 
}
public void startGame(){
    // shuffles the image's tiles. The position of the tiles shall be reversed
}
public void moveTile(){
    // switches the empty tile with the clicked tile only 
    // if the empty tile is next to the clicked tile 
}
public void CountSets(){
    // returns the number of clicks performed by the user 
}

```

### Class play_settings:
In this screen a pop-up should appear in which the options are to shuffle the puzzle again, to change the difficulty, to turn the sound on and off or to quit. If quit is selected the main_activity should be started again. If the difficulty button is selected it should restart the start_game activity however with the chosen difficulty. If another difficulty is chosen this difficulty should be remembered. Some functions in this class are:
```java
public void resumeGame(){
    // if resume is clicked get the gamestate
}
public void quitGame(){
    // if clicked then go to startscreen
}

```

### Class Solved:
This activity starts when a puzzle is solved. It congratulates the user, displays the original image and displays the number of moves they have used to solve the puzzle. There is a button to return to the main_activity class. Some functions in this class are:
```java
public void showImage(){
    // displays solved image
}
public void Menu(){
    // if clicked then go to start activity
}
public void getSets(){
    // prints the number of sets
}

```

### Class square:
This activity should create the squares and safe the squares. All the properties of the squares are defined in this class. Some functions in this class are:
```java
public void sliceImage(){
    // slices the image to tiles according to the difficulty 
}
public void sizeTile(){
    // returns the x and y coordinates of a tile  
}

```

### Class level:
This activity should distinguish between the levels
  
## Sketches of UIs

> More advanced sketches of UIs that clearly explain which features are connected to which underlying classes.

This is the start screen. If the button settings is clicked, it will go to the settings screen. If an image is clicked, it will go to the startgame screen.

<center>!(https://github.com/Rukshar/Native-App/blob/master/doc/startscreen.png)</center>

This is the settings screen. Here the difficulty of the puzzle can be selected, and the sound can be turned on and off. If "back" is clicked, it will go back to the start screen.

<center>!(https://github.com/Rukshar/Native-App/blob/master/doc/settings.png)</center>

This is the startgame screen. For 3 seconds the chosen image is displayed as solved. After that the view will be changed to the shuffled image as can be seen in the startgame2 screen. 

<center>!(https://github.com/Rukshar/Native-App/blob/master/doc/startgame.png)</center>

This is the startgame2 screen. Here the tiles can be swiped. Also the menubutton of the phone can be used to select the play settings. If the puzzle is solved it will go to the solved screen.

<center>!(https://github.com/Rukshar/Native-App/blob/master/doc/startgame2.png)</center>

This is the playsettings screen and is called when the menubutton of the phone is cicked. If resume is clicked, it will go back to the puzzle (as in startgame2), if quit is clicked it will go back to the start screen, if the level is changed it will go back to the startgame screen with the same image however sliced to the difficulties settings. The sound can be turned on and off. 

<center>[playsettings]!(https://github.com/Rukshar/Native-App/blob/master/doc/playsettings.png)</center>

This is the solved screen. It congratulates the user and displays the number of sets used to solve the puzzle. The user can click the menu button, which goes back to the start screen.

<center>!(https://github.com/Rukshar/Native-App/blob/master/doc/solved.png)</center>

## APIs

>A list of APIs that I will be using to provide functionality in my app.
