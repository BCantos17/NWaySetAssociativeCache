# N Way Set Associative Cache

By [Bryan Cantos](bcantos17@gmail.com)

## Instructions

1. Unzip File
2. Using Intellij, go to file -> new -> new project from existing sources, go to where you unzipped the file, 
open with sbt and open. Open sbt toolbar and run task package to build and compile.
 Note I used Java 11 so use that if anything below does not work.
3. Go to com.simon.codingexercise.Main and test out the Data Structure NsetCache within the same package
4. I deleted all my target and project folders when zipping it up as I do not know what is allowed through the filter, this can cause some issues running the Main class I have ready.
If this class does not run then you'll need to create a new class with the main method there and copy and paste the code I have in Main.scala


## Discussion

I used the following technologies: scala 2.13.
I built this application using Java 11 but will work on Java 8 and above.

## Requirements

####  Create data structure based on N Way Set Associative Cache
####  Must be client friendly
#### implement LRU, MRU or allow client to implement their own algorithm

Will store values that was specified by the client.