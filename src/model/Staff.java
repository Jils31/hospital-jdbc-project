package model;

public class Staff {
    private int staffId;
    private String firstName;
    private String lastName;
    private String role;
    private String specialization;

    public Staff(int staffId, String firstName, String lastName, String role, String specialization) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.specialization = specialization;
    }

    public int getStaffId() { return staffId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getRole() { return role; }
    public String getSpecialization() { return specialization; }
}
