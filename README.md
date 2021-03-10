# Citizens can schedule appointments at the agencies.
# Employees controll the appointments of their agency.
# The admin of the system regulates the agencies and the employees.

![image](https://user-images.githubusercontent.com/79916050/110692637-3b7ac300-81ef-11eb-9003-2a22e3eec227.png)

## Registration: 
Ο χρήστης γράφει όνομα, κωδικό, ηλικία, περιοχή κατοικίας εάν είναι πολίτης(citizen). 
Σε περίπτωση που είναι υπάλληλος(employee) πρέπει να συμπληρώσει ακόμα και τον φορέα για τον οποίο εργάζεται.
Ο λογαριασμός του διαχειριστής φορέων(admin) δημιουργείται αυτόματα όταν το πρόγραμμα τρέξει για πρώτη φορά.

## Sign Up:
Ο χρήστης γράφει όνομα και κωδικό για να αιτηθεί είσοδο στο σύστημα. 
Για την είσοδο του διαχειριστή το όνομα και ο κωδικός είναι ADMIN.


![image](https://user-images.githubusercontent.com/79916050/110692677-47ff1b80-81ef-11eb-8ffa-2a5981e57526.png)

## Make Appointment:
Ο πολίτης δημιουργεί ένα ραντεβού συμπληρώνοντας τα πεδία Date, Time, Place, Agency.
Το αίτημα ραντεβού θα δημιουργηθεί μόνο εάν υπάρχει το αντίστοιχο Agency.

## Cancel Appointment:
Ο πολίτης έχει τη δυνατότητα να διαγράψει ένα ραντεβού που είχε αιτηθεί.

## See my Appointments:
O Server εμφανίζει τα ραντεβού του συγκεκριμένου χρήστη.


![image](https://user-images.githubusercontent.com/79916050/110692761-6402bd00-81ef-11eb-902a-792533a92a3f.png)

## Request Confirmation:
Ο υπάλληλος κάνει αίτηση δημιουργίας φορέα. Τα δεδομένα σχετικά με το φορέα συμπληρώνονται αυτόματα μέσω των δεδομένων του συγκεκριμένου υπαλλήλου που κάνει την αίτηση. 
Η αίτηση δεν θα σταλεί εάν υπάρχει ήδη ο συγκεκριμένος φορέας ή κάποια άλλη αίτηση για τον φορέα αυτόν.

## Approve/Decline Appointments:
Ο υπάλληλος έχει τη δυνατότητα να εγκρίνει ή και όχι τα αιτήματα ραντεβού από τους πολίτες.
Μπορεί να το κάνει μόνο στα ραντεβού που αφορούν τον φορέα του.

## See my Agencys’ Appointments:
Ο Server εμφανίζει τα ραντεβού που έχουν γίνει από πολίτες για τον συγκεκριμένο φορέα.


![image](https://user-images.githubusercontent.com/79916050/110693425-15a1ee00-81f0-11eb-8102-19543a25f5b5.png)

## Approve/Decline Request:
Ο διαχειριστής έχει τη δυνατότητα να εγκρίνει ή όχι τις αιτήσεις δημιουργίας φορέα από τους υπαλλήλους. 

## View Agencies:
O Server εμφανίζει όλους τους φορείς που υπάρχουν στο σύστημα.

## View Employees:
O Server εμφανίζει όλους τους υπαλλήλους που υπάρχουν στο σύστημα.

## View Requests:
O Server εμφανίζει όλες τις αιτήσεις φορέων που υπάρχουν στο σύστημα.


Για να τρέξει το project πρέπει να χρησιμοποιηθεί μια βάση δεδομένων mysql στην οποία να τρέξουμε τις παρακάτω εντολές:
create database projectdata;

use projectdata;


