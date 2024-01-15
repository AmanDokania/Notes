
We attach a **callback** function with an event and whenever that event occurs that function is called.

```js
document.getElementById('myButton').addEventListner('click', ()=>{
	console.log("This function will be called whenever the click event will occur")
})
```


### Why do we need to remove event listeners ? 
**Ans**: 
- Event Listners are heavy means that they take high memory so it is a good practice to remove the eventlistner whener they are not required so that garbage collector can collect it.
- They do not release memory beacuse they are just waiting for the event to occur. So they cannot release memory by their own.

### What is an Event Deligation ?
*Ans* : When we create an web page and we want to add event listners to many elements we add a **Single Event Listener**. 
We donot use many event listners beacuse it consumes **high memory**.
We can add event listener to the **parent element** and from `event.target` we can get which child is clicked or other event happend.
```js
document.querySelector('#products').addEventListner('click', (event)=>{
	console.log(event.target.id); // Gives Id of the clicked element
	console.log(event.target.tagName); // Gives tag name of the clicked element
})
```
