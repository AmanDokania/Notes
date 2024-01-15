> It pulls tasks from micro task queue and callback queue and push them into callstack when it is empty, where these tasks get excuted.

[Event loop](https://www.youtube.com/watch?v=8aGhZQkoFbQ) 
[Asynchrony](https://www.youtube.com/watch?v=SrNQS8J67zc)

- JavaScript is a syncronous single threaded language.
- It have one callstack and it can perform one task at a time.
- This callstack in javascript **wait for none**.
- JS Engine itself donot have any timer so it just run the code as soon as call stack get any task.
- The event loop is the secret by which JavaScript gives us an *illusion of being multithreaded* even though it is single-threaded.


## Web APIs
We can increase the functionality of javascript by using these web api.
These all are the part of `Window` object. eg. `window.setTimeout(()=> { // task })`
> Because `window` is a gloabal object we do not required to write it explicitly.

1. setTimeout()
2. setInterval()
3. fetch()
4. DOM APIs
5. localstorage
6. console
7. location

## Working Of Event Loop
1. The [[Global Execution Contecxt]] is created of JS File. Then code is started to run line by line.
2. Whenever an asyncronous task arrives it goes to WEB API and executes there.
3. While the code moves forward to next line.
4. In the background when asyncronous task is finished it pushed to **Callback Queue** or **Micro Task Queue** if is a promise.
5. When the execution of whole code is finished.
6. Then the work of **Event Loop** starts.
7. It pushes the task present in *Callback Queue* and *Micro Task Queue*.
8. Here **Micro Task Queues** Given The Priority over **Callback queue**

```js
console.log("Program Starts Here") // => Runs 1. 

setTimeout(()=>{
console.log('Set Timeout callback executed') // => Runs 4. Last when event loop push it into callstack.
}, 0)

Promise.resolve('Resolved Promise Data')
.then(function(response){
	console.log(response) // => Runs 3. because promises goes in high priority micro task queue 
})

console.log("Program Ends Here") // => Runs 2. because JS waits for none
```
In the above code :
- First Line Executes and Print : `Program Starts Here`
- Then `setTimeout()` Web Api runs asyncronously and because time is 0. It completes immediatly.
- But still it not print because **Callstack** is not empty, instead it passed to **Callback Queue**
- Now `Promise` is also asyncronous and it runs in other thread and resolve immediatly and passed to **Micro Task Queue**.
- Now we reach end line and print : `Program Ends Here`
- Now Between Callback and Micro Task. Micro Task is given the priority and **Event Loop** push this task to **Call Stack** first and Prints: `Resolved Promise Data`
- And At last timeout call back is pushed into **Call Stack** by the event loop where is executes and print `Set Timeout Callback Executed.`

#### Q. What is callback queue starvation ?
Ans. When a micro task calls another micro task and it goes on, then it creates **Starvation** for Callback Queue Because they never gets the chance to pushed into **Call stack**.

#### Q. What is micro task queue.
Ans. Here the callback of **Promises** waits to be pushed into **Call Stack** ones its empty by the **Event Loop**. They are given more priority then **Callback Queue**.


