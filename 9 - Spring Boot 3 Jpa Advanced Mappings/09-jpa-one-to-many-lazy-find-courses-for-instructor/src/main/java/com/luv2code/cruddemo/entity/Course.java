package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="course")
public class Course {

    // define our fields

    // define constructors

    // define getter setters

    // define toString

    // annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                          CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    public Course() {

    }

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    // It is used this query fetch instructor or not: TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);

    // Test Results:
    // Courses: [Course{id=10, title='Air Guitar - The Ultimate Guide', instructor=Instructor{id=1, firstName='Susan', lastName='Public', email='susan.public@luv2code.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='http://www.youtube.com', hobby='Video Games'}}}, Course{id=11, title='The Pinball Masterclass', instructor=Instructor{id=1, firstName='Susan', lastName='Public', email='susan.public@luv2code.com', instructorDetail=InstructorDetail{id=1, youtubeChannel='http://www.youtube.com', hobby='Video Games'}}}]

    // Answer: Yes, it fetched instructor.
    /**
    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructor=" + instructor +
                '}';
    }
     */
}
