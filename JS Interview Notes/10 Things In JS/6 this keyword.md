- When we are **outside** a *function* `this` will always be **window object**
- When we use *this* inside a function this represent the caller of that function.
- So we can say that *this* can have different value depending on who is calling the function. 
```js
function talk(){
	return this
}

const me = {
	name: "Kushal",
	talk: talk
}

me.talk() // It will return the me object 

talk() // It will return the window object

```

We can also bind it to an object.
```js
function talk(){
	return this
}

const me = {
	name: "Kushal",
}
const meTalk = talk.bind(me) // It will give a function and for talk function me will act as this.

```

Read also : [[Call Bind & Apply]] to learn about binding this through function to a object.

##### this inside a Constructor Function

```js
function Person(n){
	this.name = n
	this.talk = function(){
		console.log(this) // Here this is the object of person
	}
}

const me = Person("Kushal")

me.talk() // It will print me object 

```

- Inside a constructor function **this** represent the object of  that constructor function 
- But in a **callback** function inside constructor function runs totally in different environment.
```js
function Person(n){
	this.name = n
	this.talk = function(){
		console.log(this) // Here this is the object of person
	}
	setTimeout(function(){
		console.log(this) // print window object
	}, 100)
}

const me = Person("Kushal")
 
me.talk() // It will print me object 

```

##### One to to fix this is to use **bind**.
```js
setTimeout(function(){
		console.log(this) // Now it will print peson object
	}.bind(this), 100)
```

##### Another way is to use arrow func`()=>{}`
```js
setTimeout(()=>{
		console.log(this) // print window object
	}, 100)
```

NOTE: In an [[7 Arrow Function |Arrow Function]] _this_ keyword represent the **surrounding object** to the object that calling that arrow function. 