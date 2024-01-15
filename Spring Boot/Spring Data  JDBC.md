1. if we are using simple(plain) JDBC then each and every time we need to get the connection and create the statement and then we can execute our query

![[Pasted image 20230830101523.png]]

![[Pasted image 20230830101610.png]]

To overcome this problem spring provide JDBC template we can create JDBC template by providing **url,username and password** then we can inject this jdbc template whenever we required to write jdbc query

```Java
@Repository  
public class EmployeeRepositoryImpl implements EmployeeRepository {  
  
    @Autowired  
    private JdbcTemplate jdbcTemplate;  
  
  
    @Override  
    public int save(Employee employee) {  
        return jdbcTemplate.update("INSERT INTO EMPLOYEES_DATA (name,dept,email,doj) values(?,?,?,?)"  
                , employee.getName(), employee.getDept(), employee.getEmail(), employee.getDoj());  
    }  
  
    @Override  
    public List<Employee> findAll() {  
        return jdbcTemplate.query("SELECT * FROM EMPLOYEES_DATA",  
                (rs, rowNum) ->  
                        Employee.builder()  
                                .id(rs.getInt("id"))  
                                .name(rs.getString("name"))  
                                .dept(rs.getString("dept"))  
                                .email(rs.getString("email"))  
                                .doj(rs.getDate("doj"))  
                                .build());  
    }  
  
    @Override  
    public List<Employee> getALlEmployees() {  
        return jdbcTemplate.query("SELECT * FROM EMPLOYEES_DATA",  
                new BeanPropertyRowMapper<>(Employee.class));  
    }
}
```

