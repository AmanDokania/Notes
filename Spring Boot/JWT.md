![[Pasted image 20230415142709.png]]
![[Pasted image 20230415142858.png]]
![[Pasted image 20230415143014.png]]

1. Header includes type of algorithm is used and type of token
2. Payload contains the claims
	1. calims are the statement about the entity such as subject,user name and  role of user etc..
 3. Signature is generated using base64 url encode of header , payload and sceret key  it can also contain type of algo

Basic call flow of jwt
![[Pasted image 20230415143850.png]]


Disadvanntage of Basic Spring Security

![[Pasted image 20230415144122.png]]

In basic authentication we need to pass username ans password in header section
that's why we use JWT token

![[Pasted image 20230415144449.png]]

![[Pasted image 20230415144804.png]]

1. 3 depemdecies required 
	1. jjwt-impl
	2. jjwt-api
	3. jjwt-jackson
2. JwtAuthenticationENtryPoint implemets AuthenticationEntryPoint interface which contain commence method and this  method  is called when an exception is occured if unauthorized user want to acces secured resource 
```java
@Component  
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {  
@Override  
public void commence(HttpServletRequest request,  
HttpServletResponse response,  
AuthenticationException authException)  
throws IOException, ServletException {  
  
response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());  
}  
}
```

3. Added jwt property in application properties file
	```java
	app.jwt-secret=1430434d0fd3fd395785ac50d8f9d4dfa07fbe237947f5bd479532ba40baa277  
app-jwt-expiration-milliseconds=684800000
```
	sercet key is encrypted using sha-256

4. create jwtTokenProvider class 
	1. it contains some utility method such as 
		1. create token
		2. validate token
		3. getUsername form token

5. JwtAuthenticationProvider extends OncePerFilterRequest
		Filter base class that aims to guarantee a single execution per request dispatch, on any servlet container.
		It will execute before spring security filter
		steps perform in this class
			1. get the token from httpservlet reuest
			2. validate the jwt token using jwt parser with same secret key
			3. get the username of from token
			4. load the user associated with token
```java
UserDetails userDetails = userDetailsService.loadUserByUsername(username);
```

	5. create usernamePasswordAuthenticationToken by providing it userDetails
	6. after that pass usernamePasswordAuthenticationToken to SecurityContextHolder 

6. create jwtAuthResponseDto that will be send after successful login
7. now need to configure jwt in spring security
```java
@Bean  
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  
  
CorsConfiguration corsConfiguration = new CorsConfiguration();  
  
corsConfiguration.addAllowedOriginPattern("*"); // this allows all origin  
corsConfiguration.addAllowedHeader("*"); // this allows all headers  
corsConfiguration  
.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"));  
corsConfiguration.setAllowCredentials(true);  
corsConfiguration.setExposedHeaders(List.of("Authorization"));  
  
http.csrf().disable().cors().configurationSource(request -> corsConfiguration).and().authorizeHttpRequests((authorize)->{  
authorize.requestMatchers(HttpMethod.GET, "/api/posts").permitAll()  
.requestMatchers("/api/auth/**").permitAll()  
.requestMatchers("/image/**").permitAll()  
.anyRequest().authenticated();  
}).exceptionHandling(exception-> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))  
.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));  
  
http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  
  
return http.build();  
}
```
 1. line 18 we need add exceptionHandling to call authentication entry point class
 2. line 19 jwt is stateless so we need to create sessionManagement policy as stateless
 3. after that we need to exceute jwtAuthenticationFilter class before spring security filer 
 ```java
 http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
```

8. need to change login/signin rest api to retuen jwt token
```java
@Override  
public String login(LoginDto loginDto) {  
Authentication authentication=authenticationManager.authenticate(  
new UsernamePasswordAuthenticationToken(loginDto.getUserNameOrEmail(),loginDto.getPassword()));  
  
SecurityContextHolder.getContext().setAuthentication(authentication);  
  
String token=jwtTokenProvider.generateToken(authentication);  
return token;  
  
// return "user login successfully";  
}
```
