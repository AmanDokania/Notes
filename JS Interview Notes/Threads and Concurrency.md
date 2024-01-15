#### Ques
If JS is an single threaded languages then where the asynchronous tasks executed.
#### Ans. 
>In JS in our Browser we have something called **Web Workers** which is a **Javascript Program** running on a different thread parallel to our main thread.

## Create a one
Generally we do not use 
```js
let worker = new Worker('worker.js')
worker.postMessage('Hello')
```

- **Web Workers** do not have access to Window Objects, Document Object.
- But they have
	- setTimeout()
	- location
	- navigator
