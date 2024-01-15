Bean Validation is also known as request validation.
We can validate our request manually in controller but it's not a recommended way because there is a possibility that we can have 100 fields in our DTO then it will be very tedious task to validate each field.

![[Pasted image 20231227130453.png]]
So we should find a way to validate request using framework.

Dependency Required:- 
```Java
<dependency>  
  <groupId>org.springframework.boot</groupId>  
  <artifactId>spring-boot-starter-validation</artifactId>  
</dependency>
```

![[Pasted image 20230814202412.png]]

``` Java
public class CourseRequestDTO {  
  
    @NotBlank(message = "Course name shouldn't be NULL OR EMPTY")  
    private String name;  
    @NotEmpty(message = "Trainer name should be define")  
    private String trainerName;  
    @NotNull(message = "duration must need to specify")  
    private String duration; // days  
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")  
    @Past(message = "start date can't be before date from current")  
    private Date startDate;  
    @CourseTypeValidation  
    private String courseType; //Live OR Recodring  
    @Min(value = 1500, message = "course price can't be less than 1500")  
    @Max(value = 5000, message = "course price can't be more than 5000")  
    private double fees;  
  
    private boolean isCertificateAvailable;  
    @NotEmpty(message = "description must be present")  
    @Length(min = 5,max = 10)  
    private String description;  
    @Email(message = "invalid email id")  
    private String emailId;  
    @Pattern(regexp = "^[0-9]{10}$")  
    private String contact;  
}
```

Automatic validation can be applied by annotating the argument with ***@Valid. annotation.
``` Java
@PostMapping  
public ServiceResponse<CourseResponseDTO> addCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {

}
```

if we get any error then we can see the error message on console
![[Pasted image 20230814223759.png]]


***Customized Annotataion>>***

``` Java
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidator.class)  
public @interface CourseTypeValidation {  
  
    String message() default "Course Type should be either LIVE OR RECORDING";  
  
    Class<?>[] groups() default {};  
  
    Class<? extends Payload>[] payload() default {};  
}

/**
@Retention(RetentionPolicy.RUNTIME)  >> When we want to run(excecute) this annotation
@Target({ElementType.FIELD,ElementType.PARAMETER})  >> Indicates that at which level we want to apply this anootation
@Documented  >>  if an annotation interface is annotated with Documented, by default a tool like javadoc will display annotations of that interface in its output while annotations of annotation interfaces without Documented will not be displayed.
*/
```

``` Java
public class CourseTypeValidator implements ConstraintValidator<CourseTypeValidation,String> {  
    @Override  
    public boolean isValid(String courseType, ConstraintValidatorContext constraintValidatorContext) {  
        List list= Arrays.asList("LIVE","RECORDING");  
        return list.contains(courseType);  
    }  
}
```


**This is for error handling**
When we create a RESTfull services we need to think about consumer that how does the consumer know what is wrong.
To resolve this problem we need to create global exception handler class.

if we are getting any error during bean validation then spring will throw MethodArgumentNotValid exception

```java
@RestControllerAdvice  
public class ApplicationGlobalExceptionHandler {  
  
    //MethodArgumentNotValidException  
    @ExceptionHandler(MethodArgumentNotValidException.class)  
    @ResponseStatus(HttpStatus.BAD_REQUEST)  
    public ServiceResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {  
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();  
        List<ErrorDTO> errorDTOList = new ArrayList<>();  
        exception.getBindingResult().getFieldErrors()  
                .forEach(error -> {  
                    ErrorDTO errorDTO = new ErrorDTO(error.getField()+" : "+error.getDefaultMessage());  
                    errorDTOList.add(errorDTO);  
                });  
        serviceResponse.setStatus(HttpStatus.BAD_REQUEST);  
        serviceResponse.setError(errorDTOList);  
        return serviceResponse;  
    }
}

```

It's always recommended that if we are fetching data from DAO layer inside service layer then we should use try and catch to handle exception and throw our own exception that should be handled in Global Exception Handler.

```Java
public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {  
    CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);  
    CourseEntity entity=null;  
    try {  
         entity = courseDao.save(courseEntity);  
        CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);  
        courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);  
        return courseResponseDTO;  
    }catch (Exception exception){  
        throw new CourseServiceBusinessException("onboardNewCourse service method failed..");  
    }  
}
```
