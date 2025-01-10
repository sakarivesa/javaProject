# javaProject

Contacts App
Author Sakari Vesa

Video overview (Finnish)
https://www.youtube.com/watch?v=u9Q-Gt08nlg

This app is for storing and handling Finnish contact information which
includes a person's personal ID, name, phone number and optionally the address and email address.
You can store and browse contacts, add new contacts, update and remove them.
This program is used via a command line interface where the options for the inputs are given.


Intalling and running the program

The user should have java 21 or newer installed. Run the program with a command interface such as
Command Prompt, Powershell or Bash using the commands:
cd src
java ContactsApp


How to Use

The program will first print out contacts stored in the Contacts.csv file
You can then choose an action by writing in the input "P", "A", "U", "R" or "X" which stand for
Print, Add, Update, Remove and Exit. The commands also work with lowercase letters.
Removing and editing contacts will ask you for the number of line, which can be read on the left
side of the printed contacts. Therefore using the Print command is recommended before editing or
removing contacts.
When adding information, you can skip an optional field by pressing RETURN.


Known bugs

After removing all the stored contacts with the remove command, the next time the program is run
it will print out an exception when trying to print the stored contacts. The program will still
work normally.
