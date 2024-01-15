
> **Adapter** is a structural design pattern that allows objects with incompatible interfaces to collaborate
 
![[#APPLICATIONS]]

[Video Refrence](https://www.youtube.com/watch?v=2PKQtcJjYvc&list=PLrhzvIcii6GNjpARdnO4ueTUAVR9eMBpc&index=8)
[Read more](https://refactoring.guru/design-patterns/adapter)

![[Adapter Pattern Diagram]]

- We have two classess that cannot work each other like in client class we have a function _request()_ but in the class it want to use has function _specificRequest_.
- So we will create an adapter class in between which will make it comptible.
- We donot want to change the underline behaviour
```java
ITarget target = new Adapter
					(new Adaptee()
					)

target.request()
```

Adaptee can be an **external library** or any other code we have not created to work for aur client code
```java
class Adaptee{

	public void specificRequest(){
		...
		...
		...
	} 
}
```

```java
interface ITarget{
	void request()
}

class Adapter implements ITarget{

	private adaptee;

	public Adapter(Adaptee a){
		this.adaptee = a
	}

	public void request(){
		this.adaptee.specificRequest()
	} 
}
```

#### APPLICATIONS
- Use the **Adapter class** when you want to use some *existing class*, but its interface isn’t compatible with the rest of your code.

- The Adapter pattern lets you create a middle-layer class that serves as a _translator_ between your code and a legacy class, a 3rd-party class or any other class with a weird interface.