package model.schemas;
import org.hibernate.annotations.Type;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private int Id;
    @Column(name = "name")
    private String Name;
    @Column(name = "email")
    private String Email;
    @Column(name = "password", columnDefinition = "LONGTEXT")
    private String Password;
    @Column(name = "question")
    private String Question;
    @Column(name = "answer")
    private String Answer;

    public User(){}

    public User(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
    
    public String getQuestion(){
        return Question;
    }
    
    public void setQuestion(String question){
        Question = question;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    
    public String getAnswer(){
        return Answer;
    }
    
    public void setAnswer(String answer){
        Answer = answer;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Question='" + Question + '\'' +
                "}";
    }
}
