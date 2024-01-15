[Refrence](https://www.youtube.com/watch?v=abbdJ4Yfm54&t=1319s)

## Call
Using call we can borrow the function of one object and use it for another. It means we can call the function of the object and pass the `new object` which it will treat as *this*.
```js
let myname = {
  firstname: "Kushal",
  lastname: "Kanungo",
  hello: function () {
    console.log(`Hello ${this.firstname} ${this.lastname}`);
  },
};

let myname2 = {
  firstname: "Tom",
  lastname: "Cruise",
};

myname.hello(); // It will print normally 
myname.hello.call(myname2); // It will treat myname2 as `this`

```

So that we can reuse a function.
Generally when we want to create a reusable function we keep it seperate.
```js
let hello = function (city, state) {
    console.log(`Hello ${this.firstname} ${this.lastname} ${city}, ${state}`);
  },

let myname = {
  firstname: "Kushal",
  lastname: "Kanungo",
};

let myname2 = {
  firstname: "Tom",
  lastname: "Cruise",
};

hello.call(myname, "Jaipur", "Rajasthan")
hello.call(myname, "Mumbai", "Maharashtra")

```

## Apply
Apply methods is also similar to [[#call]]. The only difference between them is in the passing the arguments, here arguments are passed in an *array*.
```js
hello.apply(myname, ["Jaipur", "Rajasthan"])
hello.apply(myname, ["Mumbai", "Maharashtra"]

```

## Bind
Bind methods looks similar to [[#call]] method. But it donot call the function, instead it bind the function with that object so that we can call it to later.
It gives us the *copy of the function* which can be invoked later.
```js

let printMyName = hello.apply(myname, ["Jaipur", "Rajasthan"])
// Now we can call printMyName()

```
