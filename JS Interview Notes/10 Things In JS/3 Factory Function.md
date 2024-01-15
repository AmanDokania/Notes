Factory Function are the functions that create object and return them.
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
me.talk() // Hello I am Kushal

const ben = personFactory("ben")
ben.talk() // Hello I am ben

```

#### Why ?
- Simple 
- Reduce Code Duplication
- More Secure
[x]  [x]  
> One good example can be to create HTMLelement through factory functions. By passing the data like `type`, `color`, `text` etc

