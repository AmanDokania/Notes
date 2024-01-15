### High Order Functions

The functions which 
1. Either takes *another function as a parameter*
2. Or *return another function*

#### Some Examples of Higher Order Functions
- `setTimeout(()=>{})`
- `.filter()`
- `.map`
- `.reduce`

EXAMPLE: Factory Function as Higher Order Functions
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

```
