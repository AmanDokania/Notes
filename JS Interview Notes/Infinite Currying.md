```js

function add(a){
	return function(b){
		return function(){
			return a+b
		}
	}
}

// Now we can use two level Currying

console.log(add(5)(7)()) // 20
```



### For Infinite
```js

function add(a){
	return function(b){
		if (b): return add(a+b);
		return a;
	}
}


// Now we can do infinite currying 
console.log(add(4)(3)(3)(8)(7)())  // 25
```

