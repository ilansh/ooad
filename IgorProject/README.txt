OOAD Project README

yaelgreen 200631927
igorl01 304357262

To lunch a game example run tester.java


Regarding the 4 requirements: 

We have made real examples of the design patterns which simulates the following 
principles of out design (not only described in words).


1.To display the board through various graphical representations, we 
had used the MVC principle. The FourInARowView class is an observer of the 
FourInARowModel class (the game) which is the observerer. 
It is easy for example to supply a class which implements the interface 'Graphics' 
(example BoardGraphics) and present the board in some way on screen.
The independency between the game and the it's representation via the MVC allows
also to have simultaneously different representations. The game doesn't care 
how it is being displayed and by whom since it knows nothing about it.


2.It's possible to add more computer heuristics thanks to the Strategy pattern we'd 
implemented (Human/Computer strategies implements the Strategy interface).
Moreover, It is possible to choose a particular player strategy 
each game which is configured by the controller. 


3.The user can configure parameters of the visual appearance of the board which is
given by the Composite pattern we made. The compositeGraphics enable to choose any
nested subset of the board appearance (CellGraphics & BoardGraphics as an example)
and draw the game graphics by giving the command (within the controller) only
to the top graphical entity. For actually doing so, it is possible to extend a view 
(from FourInARowView) and present the user to pick the wanted graphic structure. 


4.The game optimizes memory usage by the following ways:
b)Using a pooling mechanizm together with a static factory method to get instances of immutable graphics classes(as example BoardGraphicsPool & CellGraphicsPool) to save 
memory space by reusing existing objects (in case of many instance games etc).
a)Minimizing number of objects - in the game model (FourInARowModel) we have used array based board instead of object (which is slower from primitive types). 
c)Reducing objects functions (methods) where possible & instead use static functions.
This helps to reduce the overhead of the frequently creating instances of classes. 


General comment:
Ofcourse nothing is perfect & we had many alternative ideas as to how make the design.
Also it looked a bit as an overkill for such a simple game (especially with out text based example).




