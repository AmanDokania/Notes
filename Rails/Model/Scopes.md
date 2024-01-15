- We can specify commonly used queries in the **Model**. So that we can use it later.
- Call the scope *just like a class method*.

```rb
class Book < ApplicationRecord
	scope :out_of_print, -> { where(out_of_print: true) }
end

```

- We can also **chain the scopes**
```rb
class Book < ApplicationRecord
	scope :out_of_print, -> { where(out_of_print: true) }
	scope :out_of_print_and_expensive, -> { out_of_print.where("price > 500") }
end
```

- **Passing the argumets**
```rb
class Book < ApplicationRecord
  scope :costs_more_than, ->(amount) { where("price > ?", amount) }
end
```

- Appling a **default scope**
```rb
class Book < ApplicationRecord
  default_scope { where(out_of_print: false) }
end
```

TO do more complex in the default scope we can  also define as a **class method**
```rb
class Book < ApplicationRecord
  def self.default_scope
    # Should return an ActiveRecord::Relation.
  end
end
```

- **Merging the scopes**
	- Just like where clauses, scopes are merged using AND conditions.
	- We can chain them like *AND*
```rb
# 2 scopes chained together
Book.out_of_print.old
```

- **Unscope** : to remove all scope
```rb
Book.unscoped.all
Book.where(out_of_print: true).unscoped.all
```
