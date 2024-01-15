
#### Factory Function
```js
function personFactory(name){
	return {
		name,
		talk(){
			return `Hello I am ${name}`
		}	
	}
}

const me = personFactory("Kushal")
const ben = personFactory("Ben")
```
1. the *talk()* function is *copied* in both object so this function *not same* in memory.
2. We are occupying more space in memory
3. If we want to *change the logic* of the function we have to change in all the *objects seperately*.
4. So we are not using inheritance hierarcy. 

To add inheritance in factory we can do this:
```js
const myCoolProto = {
	talk(){
		return `Hello, I am ${this.name}`
	}
}

function createPerson(name){
	return Object.create(myCoolProto, {
		name: {
			value: name
		}
	})
}
```
Now the talk function will not be copied in each object instead it will be inherited through `__proto__`


#### Constructor
```js
function Person(name){
	this.name = name
}

Person.prototype.talk = function(){
	return `Hello: I am ${name}`
}  
```

Factory Functions are more flexible and also very secure as we cannot change `name`  here.
