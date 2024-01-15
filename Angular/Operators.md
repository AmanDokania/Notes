We often donot required **raw data** from observables. We required to **transform or modify** the data in between. Operators are a great way to achive that.

Some Common Operators.
- **map** : to change the response
- **filter** : to filter the response
- **tap** : it get *event* instead of *data*, so that we have a more granual 
- catchError

```ts
HTTP EventType

mYObservable.pipe(
	tap((event)=>{
		if (event.type === HttpEventType.Response){
			
		}
	}),
	filter((data)=>{
		return data > 0;
	}),
	map((data)=>{
		// Return New Data
		return 'Round:'+data
	}),
	cathError((error)=>{
		throwError(() => error)
	})
)
```