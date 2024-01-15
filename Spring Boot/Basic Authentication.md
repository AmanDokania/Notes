look the slides as well (Java Guides and Java Techie)

![[Pasted image 20230415174903.png]]
1. for customize username and password we can provide username ans password in application.properties file
2. Spring security enable both form based login and basic authentication automatically.
3. we can implement basic authentication by crating bean for (from spring security 5.2 onwards basic authentication is enabled by default )  springSecurityFilterChain
```java
@Bean  
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  
  
http.csrf().disable().and().authorizeHttpRequests((authorize)->{   
.anyRequest().authenticated();  
})
}
```

Implementation of basic authentication
![[Pasted image 20230415175442.png]]


Implementation of database  authentication
![[Pasted image 20230415190224.png]]


1. UserNamePasswordAuthentication token is implementation class  of Authentication interface
2. Authentication internally use basicauthenticationFIlter class to create Authentication object which return object of UserNamePasswordAuthentication
3. BasicAuthenticationFilter (basic authentication)and AthenticationFilter(Dao authentication) class is  working same
4. we need to override loaduserbyname() method of UserDetailsService interface to get the user from database . and provide this user to authenticate method of Authentication Manager

In spring boot 3.0
UsernamePasswordAuthenticationFilter class is used instead of basicauthenticationFIlter and this create authentication object and then it called authentication method of AuthenticationManager Interface and rest of process is same


Authentication >> Validate the user (who are you) 401
Authorization >> Role >> What all things we can access >> 403(forbidden)

For Authorization
```Java
@PreAuthorize("hasRole('ADMIN')")
or
@PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
```

We can use this annotation as well with Spring Security Configuration
```Java
@EnableGlobalMethodSecurity(prePostEnabled = true)
```
