package model;

public class Patient {
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String contactNumber;
    private String email;
    private String address;
    private String bloodGroup;

    public Patient(String firstName, String lastName, String dob, String gender,
                   String contactNumber, String email, String address, String bloodGroup) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.contactNumber = contactNumber;
        this.email = email;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDob() { return dob; }
    public String getGender() { return gender; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getBloodGroup() { return bloodGroup; }
}
