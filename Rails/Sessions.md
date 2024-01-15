Your application has a session for each user in which you can store small amounts of data that will be persisted between requests.
It can be stored in
-   [`ActionDispatch::Session::CookieStore`](https://api.rubyonrails.org/v7.0.4/classes/ActionDispatch/Session/CookieStore.html) - Stores everything on the client.
-   [`ActionDispatch::Session::CacheStore`](https://api.rubyonrails.org/v7.0.4/classes/ActionDispatch/Session/CacheStore.html) - Stores the data in the Rails cache.
-   `ActionDispatch::Session::ActiveRecordStore` - Stores the data in a database using Active Record (requires the `activerecord-session_store` gem).
-   [`ActionDispatch::Session::MemCacheStore`](https://api.rubyonrails.org/v7.0.4/classes/ActionDispatch/Session/MemCacheStore.html) - Stores the data in a memcached cluster (this is a legacy implementation; consider using `CacheStore` instead).


#### To access the session
(*For eg.* To get id of current logge in user)
```rb
class ApplicationController < ActionController::Base
  private
  def current_user
    @_current_user ||= session[:current_user_id] &&
      User.find_by(id: session[:current_user_id])
  end
end
```

#### To Store data in Session
(*For eg.* To create a new session)
```rb
class LoginsController < ApplicationController
  # "Create" a login, aka "log the user in"
  def create
    if user = User.authenticate(params[:username], params[:password])
      # Save the user ID in the session so it can be used in
      # subsequent requests
      session[:current_user_id] = user.id
      redirect_to root_url
    end
  end
end
```

#### To delet data from cookie
( *For eg.* To logout an user)
```rb
class LoginsController < ApplicationController
  # "Delete" a login, aka "log the user out"
  def destroy
    # Remove the user id from the session
    session.delete(:current_user_id)
    # Clear the memoized current user
    @_current_user = nil
    redirect_to root_url
  end
end
```