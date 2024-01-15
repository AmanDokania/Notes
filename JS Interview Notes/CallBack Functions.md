- In **JS** we can pass function as arguments these are known as callback function
- Callback Function gives us access to do **Asyncronous Operations**.
```js
function x(){

}
x(function y(){

})
```
- In above example we passed `func y()` in `func x()` now its x respnsibility to call `y()` whenever required.
- For Eg. We can pass our function in `setTimeout()` and it will call that function after the specified time.
```js
setTimeout(function(){
	console.log("Hello Kushal")
}, 5000);
```
- If there was no First Class Functions or Callback Function we whould be never able to do these asycronous operations in JS.

