# Spring Boot Revise

## 3 - Spring Boot 3 Database Access with Hibernate,JPA CRUD

## Table of Contents
1. [Dependencies](#dependencies)
2. [Annotations](#annotations)
3. [Entity](#entity)
4. [CRUD](#crud)
    * [Save](#save)
    * [Find](#find)
        * [Find By Id](#find-by-id)
        * [Find All](#find-all)
        * [Find By Last Name](#find-by-last-name)
    * [Update](#update)
    * [Delete](#delete)
        * [Delete Single](#delete-single)
        * [Delete All](#delete-all)

5. [CommandLineRunner](#commandlinerunner)
6. [Primary Keys](#primary-keys)
7. [ddl-auto](#ddl-auto)

# Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

# Annotations
```java
@Entity
@Table(name="student")
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
@Repository
@Autowired
@Override
@Transactional
@SpringBootApplication
@Bean
```

# Entity

```java
@Entity //It is typically used to mark a Java class as an entity, representing a table in a relational database.
@Table(name="student")
public class Student {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    // define constructors
    public Student() {

    }

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // define getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    // define toString() method
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
```

# CRUD

## Save

```java
entityManager.persist(theStudent);
```

## Find

### Find By Id
```java
entityManager.find(Student.class, id); //If not found, returns null
```

### Find All
```java
// Name of JPA Entity … the class name

        // this is NOT the name of the database table
        // All JPQL syntax is based on
        // entity name and entity fields
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        // return query results
        return theQuery.getResultList();
```

### Find By Last Name
```java
@Override
    public List<Student> findByLastName(String theLastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                                        "FROM Student WHERE lastName=:theData", Student.class);

        // set query parameters
        theQuery.setParameter("theData", theLastName);

        // return query results
        return theQuery.getResultList();
    }
```

## Update

```java
entityManager.merge(theStudent);
```

## Delete

### Delete Single
```java
// retrieve the student
Student theStudent = entityManager.find(Student.class, id);

// delete the student
entityManager.remove(theStudent);
```

### Delete All

```java
int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
```

# CommandLineRunner

```java
@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) { //Inject the StudentDAO

		return runner -> {
			createStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
        ...
	}
}
```

# Primary Keys
```sql
ALTER TABLE student_tracker.student auto_increment=3000
```

# ddl-auto

```
spring.jpa.hibernate.ddl-auto=none
```

| Option       | Description                                                                                     |
|--------------|-------------------------------------------------------------------------------------------------|
| none         | No action will be performed                                                                     |
| create-only  | Database tables are only created                                                                |
| drop         | Database tables are dropped                                                                     |
| create       | Database tables are dropped followed by database tables creation                                |
| create-drop  | Database tables are dropped followed by database tables creation. On application shutdown, drop the database tables |
| validate     | Validate the database tables schema, does not change table                                      |
| update       | Update the database tables schema                                                               |

