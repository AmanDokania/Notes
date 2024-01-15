### Function Expression
```js
var myFunc = function(){
	console.log("It is function expression")
}
```

- The difference between `Function Statement` and `Function Expression` is **[[Hoisting]]**. The statement will have full function stored during the first run and the expression will have `undefined` value in Global Context.
- So due to hoisting if we try to call a function before declaring them `statement` will run just fine but `expression` will give us an error.
```js
a() // log : It is function statement
b() // Error: b is undefined

// Function Statement 
function a(){
	console.log("It is function statement")
}

// Function Expression 
var b = function(){
	console.log("It is function expression")
}
```