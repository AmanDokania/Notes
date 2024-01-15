### Anonymous Function
- A function without a name is known as anonymous function.
- Ecma script donot allow us to declare an anonymous function.
- I can be used where function is treated like a values.
For Eg. 

- It can be assignment to a variable 
```rb
var b = function(){
	console.log("anonymous function assignment to a variable")
}
```

- It can be passed as a parameters
```js
setTimeout(function (){
console.log("This is an anonymous function")
}, 5000);
```