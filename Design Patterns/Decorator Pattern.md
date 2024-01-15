> It is a structural pattern where we use a decorator which is just extended just from the same base  class which `has a` and `is a` relation with the base class. 


We wrap an object with another object called _decorator_ and talk to **origin object** by our wrapper therefore a _Decorator_ **is a** and **has a** relation with the **original object** at the same time.

This pattern can use to handle **class explosion**. Foreg. A pizza can have multiple toppings, so for all combinations of toppings we need to create a new class, which leads to many bases classes.

For Eg: **Pizza Shop** 
	**Base Pizza**
		Toppings
			1. Extra Cheese
			2. Mushroom
			3. Jalepano
			4. Extra Vege

#Querstions
1. Design a pizza shop have pizzas with different toppings so we need to calculate the price

## DESIGN

- BasePizza (Abstract Class)
	- Margrita_Base_Pizza
		`is a` BasePizza
	- Veg_Delight_Base_Pizza
		`is a` BasePizza
	- ToppingDecorator (Abstract Class)
		`is a` BasePizza 
		`has a` BasePizza
		- ExtraCheese (*created by constructor injection*)
			`has a` BasePizza
			cost(){
				this.base.pizza()+10
			}
		- Mushroom
			`has a` BasePizza
			cost(){
				this.base.pizza()+5
			}
#### To create Margerita+Extracheese+Mushroom Pizza
```java
BasePizza myPizza = new MargeritaPizza
BasePizza myPizzaCheese = new ExtraCheese(myPizza)
BasePizza myPizzaCheeseMushroom = new Mushroom(myPizzaCheese)

myPizza.cost() // 100 = 100
myPizzaCheese.cost() // 100+10 = 110
myPizzaCheeseMushroom.cost() // 100+10+5 = 115 
```