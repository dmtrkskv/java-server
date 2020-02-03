package myapp.main;

public class User {

    private final String firstName;
    private final String secondName;
    private int age;

	public User(String firstName, String secondName, int age) {
		this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
	}

	public long getAge() {
		return age;
	}

	public String getFirstName() {
		return firstName;
    }
    
    public String getSecondName() {
        return secondName;
    }
}