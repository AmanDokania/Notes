- Subject is also a type of observable to which we can **subscribe()**.
- But on Subject we can also use **next()** from the outside.
- In Short It can behave like **[[Observable]]** and **Observer**.

The functionality we achieved by **EventEmitter** in Angular can be more easily don by the use of **Subject**.

> **Note**: Keep in mind to unsubscribe them when we are not required them. 

In Service
```ts
activateEmitter = new Subject<boolean>();
```

In UserComponent On Button Click
```ts
onClick(){
	this.userService.activateEmitter.next(true)	
}
```

In AppComponent
```ts
ngOnInit(){
	this.userService.activateEmitter.subscribe({
		next: (didActivate)=>{
			this.userActivated = didActivate;
		}
	})
}
```

## Error Handeling

**Subjects** can also be used to haqndle the error

API Calling Service
```ts
error = new Subject<String>()

this.http.get<{name: string}>(this.url).subscribe({
	next: ()=>{
		// Login when data recieved
	},
	error: ()=>{
		this.error.next(error.message);
	}
})
```

In Componentn where we require the error message
```ts
errorSub = this.apiCallingService.error.subscribe({
	next: ()=>{
		// logic to display error message
	}
})

ngOnDestroy(){
	errorSub.unsubscribe();
}

```