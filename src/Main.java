import dao.PatientDAO;
import dao.AppointmentDAO;
import dao.StaffDAO;
import dao.DiagnosisDAO;
import dao.PrescriptionDAO;
import dao.BillingDAO;
import dao.PaymentDAO;

import model.Patient;
import model.Appointment;
import model.Diagnosis;
import model.Prescription;
import model.PrescriptionItem;
import model.Bill;
import model.BillItem;
import model.Payment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/hospital_management";
        String user = "root";
        String password = "Undergrad@3110";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to the database!");

            Scanner sc = new Scanner(System.in);
            PatientDAO patientDAO = new PatientDAO(conn);
            AppointmentDAO apptDAO = new AppointmentDAO(conn);
            StaffDAO staffDAO = new StaffDAO(conn);

            while (true) {
                System.out.println("\n--- Hospital Management System ---");
                System.out.println("1. Register New Patient");
                System.out.println("2. View All Patients");
                System.out.println("3. Book Appointment");
                System.out.println("4. View All Appointments");
                System.out.println("5. View All Staff");
                System.out.println("6. Record Diagnosis");
                System.out.println("7. View All Diagnoses");
                System.out.println("8. Create Prescription");
                System.out.println("9. View All Prescriptions");
                System.out.println("10. Create Bill");
                System.out.println("11. View All Bills");
                System.out.println("12. Record Payment");
                System.out.println("13. View All Payments");
                System.out.println("14. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("First Name: ");
                        String fn = sc.nextLine();
                        System.out.print("Last Name: ");
                        String ln = sc.nextLine();
                        System.out.print("DOB (YYYY-MM-DD): ");
                        String dob = sc.nextLine();
                        System.out.print("Gender (male/female/other/prefer_not_to_say): ");
                        String gender = sc.nextLine();
                        System.out.print("Contact: ");
                        String contact = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();
                        System.out.print("Address: ");
                        String address = sc.nextLine();
                        System.out.print("Blood Group (A+/A-/B+/...): ");
                        String bg = sc.nextLine();

                        Patient patient = new Patient(fn, ln, dob, gender, contact, email, address, bg);
                        patientDAO.registerPatient(patient);
                        break;

                    case 2:
                        patientDAO.viewAllPatients();
                        break;

                    case 3:
                        System.out.print("Enter Patient ID: ");
                        int patientId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Staff ID (doctor): ");
                        int staffId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Appointment Date & Time (YYYY-MM-DD HH:MM:SS): ");
                        String dateTime = sc.nextLine();

                        System.out.print("Any notes: ");
                        String notes = sc.nextLine();

                        Appointment appt = new Appointment(patientId, staffId, dateTime, "scheduled", notes);
                        apptDAO.bookAppointment(appt);
                        break;

                    case 4:
                        apptDAO.viewAllAppointments();
                        break;

                    case 5:
                        staffDAO.viewAllStaff();
                        break;

                    case 6:
                        System.out.print("Appointment ID: ");
                        int aid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Diagnosis: ");
                        String diagnosis = sc.nextLine();

                        System.out.print("Diagnosis Date (YYYY-MM-DD): ");
                        String diagDate = sc.nextLine();

                        Diagnosis d = new Diagnosis(aid, diagnosis, diagDate);
                        new DiagnosisDAO(conn).addDiagnosis(d);
                        break;

                    case 7:
                        new DiagnosisDAO(conn).viewAllDiagnoses();
                        break;

                    case 8:
                        System.out.print("Appointment ID: ");
                        int appId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Prescription Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();

                        System.out.print("Remarks: ");
                        String remarks = sc.nextLine();

                        PrescriptionDAO pdao = new PrescriptionDAO(conn);
                        Prescription presc = new Prescription(appId, date, remarks);
                        int pid = pdao.createPrescription(presc);

                        if (pid != -1) {
                            System.out.print("Medication ID: ");
                            int medId = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Dosage: ");
                            String dosage = sc.nextLine();

                            System.out.print("Duration (in days): ");
                            int duration = sc.nextInt();
                            sc.nextLine();

                            System.out.print("Instructions: ");
                            String inst = sc.nextLine();

                            PrescriptionItem item = new PrescriptionItem(pid, medId, dosage, duration, inst);
                            pdao.addPrescriptionItem(item);
                        }
                        break;

                    case 9:
                        new PrescriptionDAO(conn).viewAllPrescriptions();
                        break;

                    case 10:
                        System.out.print("Patient ID: ");
                        int p_id = sc.nextInt();
                        System.out.print("Appointment ID: ");
                        int apptid = sc.nextInt();
                        System.out.print("Total Amount: ");
                        double amount = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Payment Status (pending/completed): ");
                        String status = sc.nextLine();
                        System.out.print("Billing Date (YYYY-MM-DD): ");
                        String billDate = sc.nextLine();

                        BillingDAO bdao = new BillingDAO(conn);
                        int billId = bdao.createBill(new Bill(p_id, apptid, amount, status, billDate));

                        if (billId != -1) {
                            System.out.print("Item Type (appointment/procedure/prescription): ");
                            String itemType = sc.nextLine();
                            System.out.print("Description: ");
                            String desc = sc.nextLine();
                            System.out.print("Amount: ");
                            double itemAmt = sc.nextDouble();
                            sc.nextLine();

                            BillItem item = new BillItem(billId, itemType, desc, itemAmt);
                            bdao.addBillItem(item);
                        }
                        break;

                    case 11:
                        new BillingDAO(conn).viewAllBills();
                        break;

                    case 12:
                        System.out.print("Bill ID: ");
                        int payBillId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Payment Date (YYYY-MM-DD): ");
                        String payDate = sc.nextLine();

                        System.out.print("Amount: ");
                        double payAmt = sc.nextDouble();
                        sc.nextLine();

                        System.out.print("Payment Method (cash/card/upi/netbanking/insurance): ");
                        String method = sc.nextLine();

                        System.out.print("Status (pending/success/failed): ");
                        String pstatus = sc.nextLine();

                        Payment payment = new Payment(payBillId, payDate, payAmt, method, pstatus);
                        new PaymentDAO(conn).recordPayment(payment);
                        break;

                    case 13:
                        new PaymentDAO(conn).viewAllPayments();
                        break;

                    case 14:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
