In angular we almost never required to create our own variables. But if we do when should **unsubscribe** them when it is not needed to avoid **memory leak**.

1. First **store** our *observable subscription* in an **variable**.
 2. Now **onDestroy** of the angular component, **unsubscribe** our subscription. 

```ts
import {Subscription , interval} from 'rxjs'

firstObsSubscription: Subscription = interval(1000).subscribe({next(count)=>{
	console.log(count)
}});

ngOnDestroy():void {
	this.firstObsSubscription.unsubscribe();
}


```

#### Observables provided by Angular are not required to unsubscribe manually.
- route.params
- http

## [[Operators]]
We often donot required **raw data** from observables. We required to **transform or modify** the data in between. Operators are a great way to achive that.   

## [[Subject]]
- Use it instead of **EventEmitter** for communicating across component.
- But it is not recommended when we pass data to parent using **@Output**.
