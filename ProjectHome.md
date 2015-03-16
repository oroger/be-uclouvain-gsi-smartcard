# Project Origin #

This project is a small playground for smardcard applications.

It is the continuation of Jean-François Houzard and Olivier Roger master thesis "Development of an ePassport API and deployment in real-life applications".

The ePassport API is called [pyppasport](http://code.google.com/p/pypassport/) and one of the real-life applications is called [ePassport Viewer](http://code.google.com/p/epassportviewer/) are available freely on google code.

## Smartcard ? ##

Working on ePassport was an interesting introduction to the smardcard world. It basicly gathers two types of devices. On one side the contact chip used in banking card, access card or more recently electronic identification card (eID). On the other side are contactless tags powered by Radio Frequency (RFID) used in animal taging, supply chains and ePassport.

The chip/tag memory and processing capabilities strongly vary depending on the targeted applications.

# Project Objective #

The objective of this project is to use the smartcard technology as a guideline to learn new technologies/programming languagues/tools and share the results with the open-source community.


---


# Applications #

## Simple Belgian eID Reader ##

The application allows to read the content of a Belgian eID card using only the Java 6 smartcard API. It is an very early release, more features will come soon. Feel free to provide feedback.


### Prerequisite ###
  1. Plug-in a smartcard reader in your computer (all PC/SC reader should be supported).
  1. Insert your eID.

### Launch the application ###
  1. Click on the this button: [![](http://java.sun.com/docs/books/tutorial/images/jws-launch-button.png)](http://be-uclouvain-gsi-smartcard.googlecode.com/files/java-eid-swing.jnlp)
  1. If you trust me answer yes to the certificate verification. Otherwise you can read the source and make your own jar once your are convinced it is safe.

### Usage ###
  1. Select a reader in the terminal list.
  1. Press read.
  1. Observe results.

### Dependencies ###
  1. [iText](http://www.lowagie.com/iText/) to produce PDF document. (already bundled in jar file)