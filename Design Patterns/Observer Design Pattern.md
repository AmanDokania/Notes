We have to objects
- **Obervable**
- **Observer**

As soon as state changes of **observable** it will notify to all the **observer** who are watching it.
![[Observer Pattern]]

- #### Observable Interface
	`List<ObserverInterface>`
	add(Observer obj) `Registeration / Subscription / Add Observer Object`
	remove(Observer obj) `Remove Observer Object`
	notify() `Invoke update function of all observers`
	setData() `when this function change anything it will call notify method`
- #### Observer Interface
	update() `Observable will invoke this function to notify`
- #### Observer Concrete Class
	`has a` *ObservableIterface* obj; `It will help to observe multiple observables`
	update(){
		obj.getData();
	}

### EXAMPLE `Weather Station`
- ##### Weather Observable
	TV-Display Observer
	PC-Display Observer
	Mobile-Display Observer

```java
WeatherObserver station = new IObserver()
Display display1 = new TVDisplay(station)
Display display2 = new WindowDisplay(station)
Display display3 = new MobileDisplay(station)

station.add(display1)
station.add(display1)
station.add(display1)

// This will iterate in station
station.notify()

```