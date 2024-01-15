Constructor function are somewhat similar to factory function but it do not return object directly instead there is *this* and *new* keywords are involved.

> **NOTE** : Classes in JS are basically constructor function under the hood

Constructor function by convention start with *capital letter* 
```js
function Person(name){
	this.name = name
}

const me = new Person("Kushal")

me.name // Kushal
```

When we add *new* keyword JS do these things automatically for us:
1. Add and object with name *this*.
2. And return it at last.
```js
function Person(name){
	this = {} // First thing done by JS

	// Our code
	this.name = name
	this.talk = ()=>{
		return `Hello, I am ${this.name}`
	}
	
	return this //Second thing done by js
}
```


#### One Good Example 
```js

function SuperElement(type, content){
	this.el = document.createElement(type)
	this.innerText = content
	document.body.append(this.el)
	this.el.addEventListener('click', ()=>{
		console.log(this.el)
	})
}

const h1 = new SuperElement('h1', 'Helloooo!')
```