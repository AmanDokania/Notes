There are different types of attcks hijackers can do on web applications

#### 1. Session Hijaking (*Force SSL*)
Stealing a user's session ID lets an attacker use the web application in the victim's name.
*Conunter Measure* : `config.force_ssl = true` add this in config file to force *SSL*.

#### 2. Session Storage (_Cookie Encryption_)
- Rails `CookieStore` saves the session hash in a cookie on the client-side. 
- The `CookieStore` uses the [encrypted](https://api.rubyonrails.org/v7.0.4/classes/ActionDispatch/Cookies/ChainedCookieJars.html#method-i-encrypted) cookie jar to provide a secure, encrypted location to store session data.
- To generate a new secret use `rails secret`
*If your application's secrets may have been exposed, strongly consider changing them. Changing `secret_key_base` will expire currently active sessions.*

#### 3. Rotating Encrypted and Signed Cookies Configurations
Rotation is ideal for changing cookie configurations and ensuring old cookies aren't immediately invalid.

#### 4. Replay Attacks for CookieStore Sessions
It works like this:

-   A user receives credits, the *amount is stored in a session* (which is a bad idea anyway, but we'll do this for demonstration purposes).
-   The user buys something.
-   The new adjusted credit value is stored in the session.
-   The user takes the cookie from the first step (which they previously copied) and *replaces the current cookie in the browser*.
-   The user has their original credit back.

#### 5. Session Fixation
This attack focuses on fixing a user's session ID known to the attacker, and forcing the user's browser into using this ID. It is therefore not necessary for the attacker to steal the session ID afterwards. 
*Countermeasure :* `reset_session`

#### 6. Session Expiry
Sessions that never expire extend the time-frame for attacks such as cross-site request forgery (CSRF), session hijacking, and session fixation.

To set expirytime to the session:
```rb
class Session < ApplicationRecord
  def self.sweep(time = 1.hour)
    where("updated_at < ?", time.ago.to_fs(:db)).delete_all
  end
end
```

#### 7. Cross-Site Request Forgery (CSRF)
This attack method works by including malicious code or a link in a page that accesses a web application that the user is believed to have authenticated. If the session for that web application has not timed out, an attacker may execute unauthorized commands.

*Countermesaures*:
- Use *get* and *post* appropriately only when needed. 
- Using a secret cookie in *post*. 
```rb
rescue_from ActionController::InvalidAuthenticityToken do |exception|
  sign_out_user # Example method that will destroy the user cookies
end
```

#### 8. Redirection
Whenever the user is allowed to pass (parts of) the URL for redirection, it is possibly vulnerable. The most obvious attack would be to redirect users to a fake web application which looks and feels exactly as the original one. This so-called **phishing attack** works by sending an unsuspicious link in an email to the users

The URL looks legitimate but it redirects to hacker's website
**For eg.** : `http://www.example.com/site/redirect?to=www.attacker.com`.
