- These methods are defined in the controller where we can write logic for the views.
- Like changing number to words.
- Converting a name in upper or lower case.

Some helper function are already available in the rails 
- `time_ago_in_word`
- `number_to_human`

#### To create our own helpers 
- Create a *module* in `app/helpers/user_helper.rb` 
- Write helpers fuctions in this file.
```rb
module CustomHelper

  def custom(word)
    return "Article: #{word}"
  end
end
```

*include* this module in our controller
```rb
class ApplicationController < ActionController::Base
  include CustomHelper

  helper_method :title
  def title(word)
    word.upcase
  end
end
```

TO access these helpers in our **views** we can directly use them without any annotations
like : `<%= title("kushal")>`

To access these helpers in our **controller** we use `helper.`
```rb
helper.title(name)
```
