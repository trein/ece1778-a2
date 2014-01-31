ECE1778 A2
==========

Android app following the requirements of ECE1778 assignment 2.

Requirements
------------
Write an app that allows the user to create a list of people along with their age and  favorite foods, and allows the storage and loading of those lists in multiple files on the device. This app should contain a total of one `FragmentActivity` from the native support v4 library; all transitions between screens in this assignment should be accomplished by swapping in and out fragments dynamically, not launching new activities. 
 
The app should work as follows:

- The starting screen should have five buttons: *"EnterNames"*, *"View"*, *"Store"*, *"Load"* and *"Exit"*. 

- Pressing *"EnterNames"* should take the user to a new screen where they can enter a series of records describing people: their name, age, and favourite food. The selection of the favourite food should be from a drop down list of different kinds of food, not free-form text. The user should be able to enter as many names as desired from this screen, and return to the starting screen through a *"Done"* button. 

- The *"Store"* button should cause the list to be written out to a file on the device using a file name provided by the user. 


- The *"Load"* button should allow the user to read in a previously stored file by showing a list of all the previously stored files. (This takes some extra work).

- The *"View"* button displays, in a scrollable list, the set of people entered, along with their age and favorite food. 

- The *"Exit"* button terminates the application, but makes sure any that any list that has been created or modified, will be stored first.