```rb

class Task < ApplicationRecord
	enum :priority, [:low, :medium, :height]
end
```

Now we can get task by access tasks like this

```rb

Task.low # all the low priority tasks
Taks.medium #all the medium priority tasks


Task.not_low #all the tasks other than low priority tasks

```