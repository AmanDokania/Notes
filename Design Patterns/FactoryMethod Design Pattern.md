It is a [[Creational]] Design Pattern
> We create objects without exposing the creation logic to the client and the client uses the same common interface to create a new type of object.

- The idea is to use a static member-function (*static factory method*) that creates & returns instances, hiding the details of class modules from the user.
- Instead of the library is now responsible to decide which object type to create based on an input.
- _The end goal is that if we add more types of classes in library we do not need to change code in client. It should be totally decoupled from the client code._

## Abstract Factory Method
> It is the factory for factory objects
> Basically we can group the factories