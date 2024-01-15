
Closures remember the outer function scope even after creation time.


```js
function human(n){
	name = n
	function sayHi(){
		console.log(`Hi ${name}`)
	}

	function sayHowYouFeel(){
		console.log(`${name} is feeling good`)
	} 
	return {
		sayHi,
		sayHowAreYou
	}
}

const me = human('Kushal')

me.sayHi() // Hi Kushal
me.sayHowAreYou() // Kushal is feeling good
```

Practical Example:
```html
<button onclick="onClick12" >12px</button>
<button onclick="onClick14" >14px</button>
<button onclick="onClick16" >16px</button>
```
```js
const onClick12 = makeClickHandeler(12);
const onClick14 = makeClickHandeler(14);
const onClick16 = makeClickHandeler(16);

function makeClickHandeler(size){
	return function() {
		document.body.style.fontSize = `${size}px`
	}
}

// Here the inner function made closure with the size variable

```