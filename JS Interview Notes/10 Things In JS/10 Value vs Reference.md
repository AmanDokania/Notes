#### JS have two types of values
1. **Primitive**
	1. String
	2. Number
	3. Boolean
	4. Null
	5. Undefined
2. **Object**
	1. Object
	2. Array
	3. Function
	4. Regex

Both types of values saves differently in JS.
- **Primitive** are assigned by **value**.
- **Object** are assigned by **reference**.

### Comparison
```js
let a = 1
let b = 1
let c = 2

a === b // true
a === c // false

let obj1 = { }
let obj2 = { }

obj1 === obj2 // true

```

### Passing in a function
- **Primitives** are passed as values
- **Objects** are passed as reference
Example:
```js
function incrementAge(age){
	age = age + 1
}
const myAge = 10
incrementAge(myAge)
myAge // 10

// Here value if mtAge didnot change because when we passed into the increment function we actually passed the  

function incrementAge(user){
	user.age = user.age + 1
}

const me = {age: 10}
incrementAge(me)

me.age // 11
// here me object is passed as refrence so we can change to it 

```

> When a function change a value of a variable which is declared outside of that function is known as **sideeffect**

