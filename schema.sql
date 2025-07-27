DROP DATABASE IF EXISTS hospital_management;
CREATE DATABASE hospital_management;
USE hospital_management;


CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    dob DATE,
    gender ENUM('male', 'female', 'other', 'prefer_not_to_say'),
    contact_number VARCHAR(20),
    email VARCHAR(100),
    address TEXT,
    blood_group ENUM('A+', 'A-', 'B+', 'B-', 'AB+', 'AB-', 'O+', 'O-')
);

CREATE TABLE staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    dob DATE,
    gender ENUM('male', 'female', 'other', 'prefer_not_to_say'),
    contact_number VARCHAR(20),
    email VARCHAR(100),
    address TEXT,
    role ENUM('doctor', 'nurse', 'technician', 'receptionist', 'pharmacist', 'lab_assistant', 'admin'),
    specialization VARCHAR(100)
);

CREATE TABLE appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    staff_id INT,
    appointment_datetime DATETIME,
    status ENUM('scheduled', 'completed', 'cancelled', 'no-show'),
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (staff_id) REFERENCES staff(staff_id)
);

CREATE TABLE prescriptions (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT,
    prescribed_date DATE,
    notes TEXT,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);

CREATE TABLE medications (
    medication_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    type ENUM('antibiotics', 'antifungals', 'antidepressants', 'analgesics', 'antipyretics', 'antivirals', 'vaccines', 'antacids'),
    dosage VARCHAR(50),
    manufacturer VARCHAR(100)
);

CREATE TABLE prescription_items (
    prescription_item_id INT AUTO_INCREMENT PRIMARY KEY,
    prescription_id INT,
    medication_id INT,
    dosage VARCHAR(50),
    duration INT,
    instructions TEXT,
    FOREIGN KEY (prescription_id) REFERENCES prescriptions(prescription_id),
    FOREIGN KEY (medication_id) REFERENCES medications(medication_id)
);

CREATE TABLE diagnoses (
    diagnosis_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT,
    diagnosis_text TEXT,
    diagnosis_date DATE,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);

CREATE TABLE procedures_catalog (
    procedure_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    description TEXT,
    procedure_type ENUM('imaging', 'laboratory_tests', 'endoscopy', 'surgery', 'biopsy', 'therapy', 'emergency'),
    cost DECIMAL(10,2)
);

CREATE TABLE appointment_procedures (
    appointment_procedure_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT,
    procedure_id INT,
    performed_by INT,
    result TEXT,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id),
    FOREIGN KEY (procedure_id) REFERENCES procedures_catalog(procedure_id),
    FOREIGN KEY (performed_by) REFERENCES staff(staff_id)
);

CREATE TABLE billing (
    bill_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    appointment_id INT,
    total_amount DECIMAL(10,2),
    payment_status ENUM('pending', 'partial', 'completed', 'failed', 'refunded'),
    billing_date DATE,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id)
);

CREATE TABLE bill_items (
    bill_item_id INT AUTO_INCREMENT PRIMARY KEY,
    bill_id INT,
    item_type ENUM('appointment', 'procedure', 'prescription'),
    description TEXT,
    amount DECIMAL(10,2),
    FOREIGN KEY (bill_id) REFERENCES billing(bill_id)
);

CREATE TABLE insurance (
    insurance_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    provider_name VARCHAR(100),
    policy_number VARCHAR(100),
    coverage_details TEXT,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

CREATE TABLE insurance_claims (
    claim_id INT AUTO_INCREMENT PRIMARY KEY,
    insurance_id INT,
    bill_id INT,
    claim_status ENUM('pending', 'approved', 'denied'),
    claim_amount DECIMAL(10,2),
    approved_amount DECIMAL(10,2),
    claim_date DATE,
    FOREIGN KEY (insurance_id) REFERENCES insurance(insurance_id),
    FOREIGN KEY (bill_id) REFERENCES billing(bill_id)
);

CREATE TABLE rooms (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_number VARCHAR(10),
    room_type ENUM('office', 'ward', 'icu', 'consultation_room', 'operation_theatre', 'emergency_room', 'pharmacy', 'lab'),
    capacity INT
);

CREATE TABLE room_allocations (
    allocation_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT,
    room_id INT,
    allocated_date DATETIME,
    discharged_date DATETIME,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);

CREATE TABLE payments (
    payment_id INT AUTO_INCREMENT PRIMARY KEY,
    bill_id INT,
    payment_date DATE,
    amount DECIMAL(10,2),
    payment_method ENUM('cash', 'card', 'upi', 'netbanking', 'insurance'),
    status ENUM('pending', 'success', 'failed'),
    FOREIGN KEY (bill_id) REFERENCES billing(bill_id)
);



INSERT INTO patients (first_name, last_name, dob, gender, contact_number, email, address, blood_group)
VALUES
('Alice', 'Smith', '1990-04-15', 'female', '9876543210', 'alice@example.com', '123 Main St', 'A+'),
('Bob', 'Johnson', '1985-09-30', 'male', '9876543211', 'bob@example.com', '456 Oak Rd', 'B+');


INSERT INTO staff (first_name, last_name, dob, gender, contact_number, email, address, role, specialization)
VALUES
('Dr. John', 'Doe', '1975-06-10', 'male', '9998887777', 'john.doe@example.com', '78 Clinic Ave', 'doctor', 'Cardiology'),
('Nina', 'Sharma', '1988-03-22', 'female', '8887776666', 'nina.sharma@example.com', '22 Nurse Lane', 'nurse', NULL);


INSERT INTO appointments (patient_id, staff_id, appointment_datetime, status, notes)
VALUES
(1, 1, '2025-07-27 10:30:00', 'scheduled', 'General consultation');


INSERT INTO medications (name, type, dosage, manufacturer)
VALUES
('Paracetamol', 'antipyretics', '500mg', 'Cipla'),
('Amoxicillin', 'antibiotics', '250mg', 'Sun Pharma');


INSERT INTO billing (patient_id, appointment_id, total_amount, payment_status, billing_date)
VALUES (1, 1, 500.00, 'pending', '2025-07-27');

INSERT INTO bill_items (bill_id, item_type, description, amount)
VALUES (1, 'appointment', 'Doctor consultation', 500.00);


INSERT INTO payments (bill_id, payment_date, amount, payment_method, status)
VALUES (1, '2025-07-27', 500.00, 'upi', 'success');
