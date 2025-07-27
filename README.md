# 🏥 Hospital Management System (JDBC + MySQL)

A console-based hospital management system built using **Java**, **JDBC**, and **MySQL**. This project is built as part of a DBMS improvement task and enables basic hospital operations like managing patients, staff, appointments, prescriptions, billing, and more.

---

##  Features

-  Patient Registration and Management
-  Staff Management (Doctors, Nurses, etc.)
-  Appointment Scheduling
-  Medication & Prescriptions
-  Diagnosis Entry
-  Billing System with Bill Items
-  Payment Tracking
-  Room Allocation (Schema + optional implementation)
-  Insurance and Claim Management (Schema-ready)

---

## 🛠 Tech Stack

- Java 17+
- JDBC (MySQL Connector J 9.4.0)
- MySQL 8+
- Console-based Java Application
- SQL (`schema.sql`) for DB setup

---

## How to Run This Project

### 1 Clone the Repository

```bash
git clone https://github.com/Jils31/hospital-jdbc-project.git
cd hospital-jdbc-project
```

### 2 Import the Database Schema
In MySQL Workbench or MySQL CLI, run:

```bash
SOURCE schema.sql;
```

### 3 Compile the Java Code
Ensure you're in the project root, then run:

```bash
javac -cp ".;lib/mysql-connector-j-9.4.0.jar" -d bin src/model/*.java src/dao/*.java src/Main.java
```
For Linux/macOS, use : instead of ;
Make sure the .jar is inside the lib/ folder.

### 4 Create the Executable .jar File
Ensure your manifest.txt contains:

```bash
Main-Class: Main
Class-Path: lib/mysql-connector-j-9.4.0.jar
```

Then run:
```bash
jar cfm hospital_project.jar manifest.txt -C bin .
```
This generates the final hospital_project.jar file.

### 5 Run the Application
Run the application from terminal or command prompt:

```bash
java -jar hospital_project.jar
```
You should see a console menu to interact with the system (add/view patients, appointments, staff, etc.).


## Project Structure

```text
hospital-jdbc-project/
├── src/
│   ├── model/              # Java POJOs like Patient.java, Staff.java
│   ├── dao/                # DAO classes like PatientDAO.java, StaffDAO.java
│   └── Main.java           # Entry point with console-based menu
├── lib/
│   └── mysql-connector-j-9.4.0.jar
├── bin/                    # Compiled class files (excluded from GitHub)
├── schema.sql              # SQL file with schema + dummy data
├── hospital_project.jar    # Final runnable JAR file
├── manifest.txt            # Used to define JAR metadata
├── .gitignore              # To ignore build files & system files
└── README.md               # This file
```

