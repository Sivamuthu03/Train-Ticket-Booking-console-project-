# 🚆 Train Ticket Booking System (Java Console Application)

A **console-based Train Ticket Booking System** built using **Java**, simulating a real-world railway booking flow similar to IRCTC.

This project allows users to search trains by **From → To**, select **journey date (Today / Tomorrow / Custom)**, choose class, book **multiple seats**, enter **multiple passenger details**, simulate payment, and receive a **ticket confirmation with PNR**.

---

## ✨ Features

✔ Search trains by **From Station → To Station**  
✔ Select journey date:
  - Today
  - Tomorrow
  - Custom date (YYYY-MM-DD)  
✔ Display available trains list  
✔ Choose train by train number  
✔ Choose class (**Sleeper / AC / General**)  
✔ Display real-time available seats  
✔ Book **multiple seats in one ticket**  
✔ Enter passenger details for each seat  
✔ Payment confirmation simulation  
✔ Ticket confirmation with **PNR number**  
✔ Correct seat deduction across multiple bookings (same run)

---

## 🧠 Key Logic Highlight

> Seats are maintained using a **HashMap keyed by Train Number + Date**, ensuring that seat availability updates correctly when the same train is booked multiple times on the same date.

---

## 🛠️ Technologies Used

- **Language:** Java  
- **Concepts:** OOP, Collections, Control Flow  
- **Input:** Scanner  
- **Execution:** Command Line / Terminal  

---

## 📂 Project Structure

Train-Ticket-Booking-System/
│
├── Main.java
├── README.md

yaml
Copy code

---

## ▶️ How to Run the Project

### Step 1: Clone the Repository
```bash
git clone https://github.com/your-username/train-ticket-booking-system.git
Step 2: Navigate to the Project Folder
bash
Copy code
cd train-ticket-booking-system
Step 3: Compile the Program
bash
Copy code
javac Main.java
Step 4: Run the Program
bash
Copy code
java Main
🧪 Sample Execution
yaml
Copy code
Enter From Station: chennai
Enter To Station: bangalore

Select Journey Date:
1. Today
2. Tomorrow
3. Choose Date
Enter choice: 1

Available Trains:
101 - Chennai Express
102 - Intercity SF

Enter Train Number: 102
Choose Class: AC
Available Seats: 8
Enter number of seats: 2

Enter Passenger 1 Name: Siva
Enter Passenger 2 Name: Arun

Total Fare: ₹1800
Confirm Payment: yes

===== TICKET CONFIRMED =====
PNR: PNR63226
🚀 Future Enhancements
Ticket cancellation using PNR

Seat number allocation (S1-01, A1-02)

File or database storage

User authentication

GUI version (JavaFX / Swing)

REST API using Spring Boot

👨‍💻 Author
Your Name
🎓 Computer Science Student
💻 Java | OOP | Console Applications
