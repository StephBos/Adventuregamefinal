# Adventuregamefinal
This is an Adventure game with full working GUI. It is modular so it can be whatever you want all you have to do is provide the assets.
The GUI is done completely through javafx and can be run through the command line if you have all of the javafx libraries properly pathed.
The program reads a text file that contains the map, the items file, the pictures to use, the size of the map, and the size of the pictures.

Example map file

10 12
~~~~....MMMM
~~~....MMMM.
~~...MMMMMMM
~~~.MMMM*MMM
~~~..MMMMMMM
~~...fMMMMMM
~~.ffffMMMMM
~~..ffffff..
~~~~fffffff.
~~~~~~~fffff
60 60
map1items.txt
.;plains;MapPics/plain.png;
M;mountain;MapPics/mountain.png;
f;forest;MapPics/forest.png;
~;water;MapPics/water.png;
*;goal;MapPics/treasure.png;
-;out;MapPics/out.png;
1;person;MapPics/person.png;

Example items file

0;1;a knife;
0;1;a rock;
2;5;a dagger;
4;7;a magic sword;
2;8;a bag of coins;

To run the program from the command line you will call the program Adventure and then the name of the map file and location.
From the text box you can move with the arrow keys and enter commands.  There are 4 commands: inventory to check your inventory,
take to take an item at your location, drop to drop an item, and quit to exit.  To use inventory just type inventory.  To take an 
item type take and then the item name.  To drop type drop and then the item name. To quit type quit.
There are two buttons to save and open.  Save will create a text file of where you are where all the items are and what items you have.
Open will open one of those said text files and the map will be just as you left it.
