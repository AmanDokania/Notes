[Link](https://www.youtube.com/watch?v=jnME98ckDbQ&list=PL1PqvM2UQiMoGNTaxFMSK2cih633lpFKP&index=1)
- Inheritance is used to reduce code duplication.
- In JS inheritance can be done with or without the classes.

```js
class Person{
	talk(){
		return 'talking'
	}
}

const me = new Person();
const you = new Person();

me.talk(); // talking
you.talk(); // talking
```

- talk() function is not present in `me` var it is present in Class `__proto__` 
- And a Class have `prototype` , and `prototype` of class and `__proto__` of the object of that class are same.
- `__proto__ === prototype` 
- So if we want to fix a function of a class so that every object will also change.
- We can use **prototype** of a class.
```js
Person.prototype.talk = function(){
	return 'new and improved talking'
}
```

# Prototypal Inheritance
[[2 Prototypes|Prototypes]]
When we create a JS Class it is creating a function underneath
```js
function Person(){}

Person.prototype.talk = function(){
	return 'talking' 
}

const me = new Person();
me.talk(); // talking

```

# Constructor Function
```js 
function Person(){
	this.talk = function(){
		return 'talking'	
	}
}

const me = new Person();
me.talk(); //talking
```

- Although it look same but it is little bit different. 
- When we add a function to *this* it is not regarded as method but as a property.
- Due to this these function are regarded as **properties** and when we create a object these function are **copied** to the object. So it not inheritance anymore.
- So if we want to change in that function we need to change in **all objects**.
```js
function Person{
	this.age = 40
}

const me = new Person();
me.age // 40

Person.age = 12;

// It do not changed
me.age //40
```

```js

class Person{
	talk(){
		return "talking"
	}
}

class Superhuman extends Person {
	fly(){
		return "flying"	
	}
}

const me = new Superhuman();

me.talk() // talking
me.fly() //flying

```

# 3 Ways to create an object
```js

const person = {
	talk(){
		return "talking"
	}
}

const i = new person()

const me= Object.create(person)


const you = {}

Object.setPrototypeOf(me, person)
```