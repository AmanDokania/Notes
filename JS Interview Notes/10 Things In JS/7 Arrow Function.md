**Note** : In regular functions even if we didn't declared the parameters we can pass and get them by using **arguments** in regular function. But not in *arrow functions*.
``` js
const myFunc = function(){
	console.log(arguments)// List of arguments
}


myFunc('h1','h2') // arguments = [h1, h2]

```

> **Arrow functions** cannot be a named function. Therefore we cannot convert an *constructor function* into *arrow function*

#### Benefits of Arrow function
1. **Aesthetics** : They are short, cute
2. Binding with **this**

Inside an object function
```js
const me = {
	name: 'Kushal',
	talk: function(){
		return this
	},
	arrowTalk: ()=>{
		return this
	}
}

me.talk() // return me object

me.arrowTalk() // return window object

```

> How it can be beneficial ? inside an callback function


```js
const me = {
	name: 'Kushal',
	talk(){
		setTimeout(()=>{
			return this.name // Kushal
		}, 100)
	}
}
```

```js
function Person(name){
	this.name = name
}

Person.prototype.talk = function(){
	return this
}

Person.prototype.arrowTalk = function(){
	return this
}

const me = new Person('Kushal')

me.talk() // me object

me.arrowTalk() // window object

```

#### Inside an eventlistener
```js

document.body.addEventListener('click', function(){
	console.log(this) // It will print body element
})

document.body.addEventListener('click', ()=>{
	console.log(this) // It will print window object
})

```

The interesting thing is that normal function prints the element on which we add event listerner.
Because the eventlister bind the function to the dom element.


When not to use **arrow functions** 
1. Inside an `object function` because it will not bind to the that object instead it will bind to *surrounding object*.
2. To create constructor function
3. In Event Handelers because it do not bind to dom element unlike regular function.

NOTE: Whenever we want to know how **this** is binding than just treat like there is not arrow function and how **this** will behave there. 