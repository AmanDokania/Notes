> **Command** is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request. This transformation lets you pass requests as a method arguments, delay or queue a request’s execution, and support undoable operations.

- We cannot hardcode the code in the _Invoker Class_ (For eg. **Button**)
- Because we want to do different thing through the _Invoker Object_
- So where should we keep the code ?
- It is very similar to [[Strategy Design Pattern]]

#### ADVANTAGE:
- _Undoing_ becomes very easy
- If we want use option to rollback a series of commands

IMP: IN JS we have functions which are _first class citizen_ which we can pass around like commands but to achieve this type of functionality we use **Command Pattern**.

DESIGN 
![[Command Pattern Diagram]]

#### Command Interface
```java
interface ICommand(){
	void execute();
	void unExecute();
}
```

#### Command Concrete Class
```java
class LightOnCommand implements ICommand{
	Receiver light;
	
	public Command(Receiver Light){
		this.light = Light
	}

	public void execute(){
		this.light.on()
	}
}
```

#### HAS A relation with invoker
```java
class Invoker{
	ICommand on;
	ICommand off;
	ICommand up;
	ICommand down;

	public Invoker(ICommand onCommand, ICommand offCommand, ...){
		this.on = onCommand;
		this.off = offCommand;
		this.up = upCommand;
		this.down = downCommand;
	}
	
	void turnOnTheLight(){
		this.on.execute()
	}

	void turnOffTheLight(){
		this.off.execute()
	}
}

Invoker remote =  new Invoker(new LightOnCommand)

```