### **self** inside a method of a class
self is just like **this keyword** it gives access to instance of the class (i.e *Object calling it*)
```rb
class Cat
  def meow
    puts self
  end
end
Cat.new.meow
# <Cat:0x7a14c5>
```

### **.self** to define a method
To create class level methods i.e. *we can call directly by class*
```rb
class Salad
  def self.buy_olive_oil
    # ...
  end
end
Salad.buy_olive_oil
```
