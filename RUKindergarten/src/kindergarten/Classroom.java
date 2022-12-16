package kindergarten;

/**
 * This class represents a Classroom, with:
 * - an SNode instance variable for students in line,
 * - an SNode instance variable for musical chairs, pointing to the last student in the list,
 * - a boolean array for seating availability (eg. can a student sit in a given seat), and
 * - a Student array parallel to seatingAvailability to show students filed into seats 
 * --- (more formally, seatingAvailability[i][j] also refers to the same seat in studentsSitting[i][j])
 * 
 * @author Ethan Chou
 * @author Kal Pandit
 * @author Maksims Kurjanovics Kravcenko
 */
public class Classroom {
    private SNode studentsInLine;             // when students are in line: references the FIRST student in the LL
    private SNode musicalChairs;              // when students are in musical chairs: references the LAST student in the CLL
    private boolean[][] seatingAvailability;  // represents the classroom seats that are available to students
    private Student[][] studentsSitting;      // when students are sitting in the classroom: contains the students

    /**
     * Constructor for classrooms. Do not edit.
     * @param l passes in students in line
     * @param m passes in musical chairs
     * @param a passes in availability
     * @param s passes in students sitting
     */
    public Classroom ( SNode l, SNode m, boolean[][] a, Student[][] s ) {
		studentsInLine      = l;
        musicalChairs       = m;
		seatingAvailability = a;
        studentsSitting     = s;
	}
    /**
     * Default constructor starts an empty classroom. Do not edit.
     */
    public Classroom() {
        this(null, null, null, null);
    }

    /**
     * This method simulates students coming into the classroom and standing in line.
     * 
     * Reads students from input file and inserts these students in alphabetical 
     * order to studentsInLine singly linked list.
     * 
     * Input file has:
     * 1) one line containing an integer representing the number of students in the file, say x
     * 2) x lines containing one student per line. Each line has the following student 
     * information separated by spaces: FirstName LastName Height
     * 
     * @param filename the student information input file
     */
    public void makeClassroom ( String filename ) {

        // WRITE YOUR CODE HERE

        StdIn.setFile(filename); // reads the file.
        int x = StdIn.readInt(); // x amount of lines in the code.
        for (int i=0; i<x; i++){ // loops through amount of lines in code.
            String firstName = StdIn.readString(); // gets the first name
            String lastName = StdIn.readString(); // gets the last name
            int height = StdIn.readInt(); // gets the height in inches.
            Student information = new Student(firstName, lastName, height); //populates the student with the fistname, lastName, and height.
            SNode newNode = studentsInLine; // makes the node with the student information (points to null or nothing).
            if (newNode == null){ // if head is pointing to nothing
                studentsInLine = new SNode(information, null); // then studentsInLine is equal the node that was made.
                newNode = studentsInLine;
            }
            else{
                if(newNode.getStudent().compareNameTo(information)>0){ //if the first node is greater than alphabetically
                    // then node we got from the input
                    // file, then we do this:
                    studentsInLine = new SNode(information, newNode);
                    newNode = studentsInLine;
                }
                else{
                    SNode otherNode = newNode;
                    while (otherNode.getNext() != null && otherNode.getNext().getStudent().compareNameTo(information)<0){
                        otherNode = otherNode.getNext();
                    }
                    SNode l = new SNode(information, otherNode.getNext());
                    otherNode.setNext(l);
                }
            }
            //first case:

            //middle case:

            //last case:

        }

        // // creates a for loop to loop through the amount of lines in the code because thats
        // // how many first names, last names, and ages are in the code.

        // SNode front = studentsInLine;
        // if (front ==null){
        //     return;
        // }
        // SNode next = studentsInLine.getNext();

        // for (SNode ptr = front; ptr!=null; ptr=ptr.getNext()){
            
        // }


        // Student [] arrayStudent = new Student[x]; // populates array from student class.
        // for (int i=0; i<x; i++){ // loops through amount of lines in code.
        //     String firstName = StdIn.readString(); // gets the first name
        //     String lastName = StdIn.readString(); // gets the last name
        //     int height = StdIn.readInt(); // gets the height in inches.
        //     arrayStudent[i]= new Student(firstName, lastName, height); // populates array with one line of first name last name and height.

        //     // compares the name after it to put in alphabetical order:

        //     if (arrayStudent[i].compareNameTo(arrayStudent[i+1])>0){
        //         // returns 0 if the String is equal, so if greater than 0, it's not equal.
        //         // must switch strings because we can't have the string before it.
        //         // being greater alphabetically than the String after it.
        //         Student tempStudent = arrayStudent[i];
        //         arrayStudent[i] = arrayStudent[i+1];
        //         arrayStudent[i+1] = tempStudent;
        //     }
            
        // }
        // studentsInLine = new SNode(arrayStudent[0], null);
        // for(int i=1; i<x; i++){
        //     SNode line = new SNode(arrayStudent[i], studentsInLine.getNext());
        //     studentsInLine.setNext(line);
        // }
        // for(int i =0; i<arrayStudent.length; i++){ // compares the name after it to put in alphabetical order.
        //     if (arrayStudent[i].compareNameTo(arrayStudent[i+1])>0){
        //         // returns 0 if the String is equal, so if greater than 0, it's not equal.
        //         // must switch strings because we can't have the string before it 
        //         // being greater alphabetically than the String after it.
        //         Student tempStudent = arrayStudent[i];
        //         arrayStudent[i] = arrayStudent[i+1];
        //         arrayStudent[i+1] = tempStudent;
        //     }
        // }
        

    }

    /**
     * 
     * This method creates and initializes the seatingAvailability (2D array) of 
     * available seats inside the classroom. Imagine that unavailable seats are broken and cannot be used.
     * 
     * Reads seating chart input file with the format:
     * An integer representing the number of rows in the classroom, say r
     * An integer representing the number of columns in the classroom, say c
     * Number of r lines, each containing c true or false values (true denotes an available seat)
     *  
     * This method also creates the studentsSitting array with the same number of
     * rows and columns as the seatingAvailability array
     * 
     * This method does not seat students on the seats.
     * 
     * @param seatingChart the seating chart input file
     */
    public void setupSeats(String seatingChart) {

	// WRITE YOUR CODE HERE
    StdIn.setFile(seatingChart); //reads the seating chart (seating1.in-seating4.in) input file.
    int rows = StdIn.readInt(); // first line that represents the number of rows in the classroom (say r).
    int columns = StdIn.readInt(); // second line represents number of columns in the classroom (say c).

    // uses seatingAvailabilty instance variable. (creates and populates it).
        // ^ represents the classroom seats that are available to students
    
    // also creates studentSitting array w/ the same number of rows and columns as the seatingAvailabilty array.
        // ^ when students are sitting in the classroom: contains the students
    seatingAvailability = new boolean[rows][columns];
    studentsSitting = new Student[rows][columns];

    // for loop to check how many true or false values are in seatingAvailabilty:
    for(int i =0; i<rows; i++){
        for(int j =0; j<columns; j++){
            seatingAvailability[i][j] = StdIn.readBoolean();
        }
    }

    }

    /**
     * 
     * This method simulates students taking their seats in the classroom.
     * 
     * 1. seats any remaining students from the musicalChairs starting from the front of the list
     * 2. starting from the front of the studentsInLine singly linked list
     * 3. removes one student at a time from the list and inserts them into studentsSitting according to
     *    seatingAvailability
     * 
     * studentsInLine will then be empty
     */
    public void seatStudents () {

	// WRITE YOUR CODE HERE
   // studentsInLine = new SNode(null, musicalChairs);  // first, seats any remaining students from muscialChairs.


        
    for(int i=0; i<seatingAvailability.length; i++){
        for(int j=0; j<seatingAvailability[i].length; j++){
            if(studentsInLine != null){ // while there is are still studentsInLine
                if (seatingAvailability[i][j]==true){
                studentsSitting[i][j] = studentsInLine.getStudent(); // seats first student in line, then second, third, and so on.
                studentsInLine = studentsInLine.getNext();
            }
        }
    }
}

    // use musicalChairs instance variable. // when students are in musical chairs: references the LAST student in the CLL

	
    }

    /**
     * Traverses studentsSitting row-wise (starting at row 0) removing a seated
     * student and adding that student to the end of the musicalChairs list.
     * 
     * row-wise: starts at index [0][0] traverses the entire first row and then moves
     * into second row.
     */
    public void insertMusicalChairs () {
        
        // WRITE YOUR CODE HERE

        for (int i =0; i<studentsSitting.length; i++){ // traverses through rows and columns of students that are sitting b/c they are
            // avalaible.
            for (int j =0; j<studentsSitting[i].length; j++){
                if (studentsSitting[i][j]!=null){ // if not null then add them to circular linked list.
                    if (musicalChairs==null){ // if there is some nodes in musical chairs.
                        musicalChairs = new SNode(studentsSitting[i][j], null); // makes a node specific position that studentsSitting
                        // array is in
                        musicalChairs.setNext(musicalChairs); // inserts that node into circular linked list.



                    }
                    else{ 
                        // adds a last node to reference :
                        SNode oldLastNode = musicalChairs;
                        musicalChairs = new SNode(studentsSitting[i][j], oldLastNode.getNext());
                        oldLastNode.setNext(musicalChairs);

                        
                    }

                }
            }
        }
        //makes seats available again:
        //seatingAvailability represents the classroom seats that are available to students.
        for (int i =0; i<seatingAvailability.length; i++){
            for (int j =0; j<seatingAvailability[i].length; j++){
                if (seatingAvailability[i][j]==true){ // if there's avaliable seats, make them me
                    studentsSitting[i][j]=null;
                }
            }
        }

    }

    /**
     * 
     * This method repeatedly removes students from the musicalChairs until there is only one
     * student (the winner).
     * 
     * Choose a student to be elimnated from the musicalChairs using StdRandom.uniform(int b),
     * where b is the number of students in the musicalChairs. 0 is the first student in the 
     * list, b-1 is the last.
     * 
     * Removes eliminated student from the list and inserts students back in studentsInLine 
     * in ascending height order (shortest to tallest).
     * 
     * The last line of this method calls the seatStudents() method so that students can be seated.
     */
    public void playMusicalChairs() {

        // WRITE YOUR CODE HERE

        SNode deleteNode = null;
        SNode temporaryNode = musicalChairs;
        SNode lastNode = musicalChairs;
        SNode p = null;
        SNode previouNode = null;
        int counterVar = 0;
        if (temporaryNode!= null){
            do{
                temporaryNode = temporaryNode.getNext();
                counterVar++;
            } while(temporaryNode != musicalChairs);
        }
        int length = counterVar;
        for (int i=0; i<counterVar-1; i++){
            p = musicalChairs;
            previouNode =p;
            int posD = (int)(StdRandom.uniform(length));

            if (posD==0 || posD==length){
                deleteNode = lastNode.getNext();
                lastNode.setNext(deleteNode.getNext());
            }else if(posD==length-1){
                deleteNode = musicalChairs;
                while(p.getNext() !=musicalChairs){
                    p = p.getNext();
                }
                lastNode = p;
                p.setNext(musicalChairs.getNext());
                musicalChairs = p;
            }else {
                int del = 0;
                while (p.getNext() != musicalChairs){
                    previouNode = p;
                    p = p.getNext();
                    if(del==posD){
                        deleteNode = p;
                        previouNode.setNext(p.getNext());
                        break;
                    }
                    del++;
                }
            }
            length--;

            deleteNode.setNext(null);
            if (studentsInLine==null){
                studentsInLine = deleteNode;
            }
            else{
                SNode curr = studentsInLine;
                SNode prev2 = null;

                while(curr !=null){
                    if(deleteNode.getStudent().getHeight()<curr.getStudent().getHeight()){
                        if(prev2==null){
                            deleteNode.setNext(curr);
                            studentsInLine = deleteNode;
                            break;
                        }
                        deleteNode.setNext(curr);
                        prev2.setNext(deleteNode);
                        break;
                    }

                    if(deleteNode.getStudent().getHeight()==curr.getStudent().getHeight()){
                        deleteNode.setNext(curr);
                        prev2.setNext(deleteNode);
                        break;
                    }
                    prev2 = curr;
                    curr = curr.getNext();
                }
                if (curr==null){
                    prev2.setNext(deleteNode);
                    deleteNode.setNext(null);
                }
            }
        }
        for (int i = 0; i < studentsSitting.length; i++) {
            for (int j = 0; j < studentsSitting[0].length; j++) {
                if (seatingAvailability[i][j]==true && musicalChairs != null){
                    studentsSitting[i][j] = musicalChairs.getStudent();
                    musicalChairs = null;
                }
                else if(seatingAvailability[i][j]== true && studentsInLine !=null){
                    studentsSitting[i][j] = studentsInLine.getStudent();
                    studentsInLine=studentsInLine.getNext();
                }
                else{
                    studentsSitting[i][j]=null;
                }
            }
        }






        //assume the students are in musical chairs

        // method eliminates a student from the game until a final player is left

        
        // look at paper to find int b:
        // int count = 1;
        // for(SNode ptr = musicalChairs.getNext(); ptr!=musicalChairs; ptr=ptr.getNext()){
        //     count++;
        // }
        
        // while (musicalChairs.getNext()!=musicalChairs){
        //     int b = StdRandom.uniform(count);// gets a random number from 0 to x to eliminate student at that number from musicalChairs.
        //     SNode front = musicalChairs.getNext();
        //     for (int i=0; i<b; i++){
        //         front = front.getNext();
        //     }
        //     Student aStudent = front.getNext().getStudent();
        //     SNode nodeForStudent = new SNode(aStudent, studentsInLine);
        //     if (studentsInLine.getStudent()==null){
        //         studentsInLine.setStudent(aStudent);
        //     }
        //     else{
                
        //     }
        // }


    

        //students from studentsInLine will be seating in height order bc they are already put in hight order from first method (i think)
        // maybe copy and paste the first method.


        //elimates node at the random n position:

        // // search node at n postition:
        // SNode firstNode = studentsInLine;

        // SNode matcherNode = matcherNode(); // gets the random node at position n. This student is to be continuously called an eliminated.
        
        // //might need to use a private method to find the node and the random line n. 

        // // SNode randomNodeToDelete = new SNode(null, studentsInLine);

        // //for loop to eliminate matchernodes:

        // for (SNode ptr = firstNode; ptr != matcherNode(); ptr=ptr.getNext()){

        // }

        

        // if (musicalChairs.getNext()==musicalChairs)

        // //for loop to place studentsInLine in height order (shortest to tallest):

        // //If students have the same height, add them in order of insertion, after.

        



        // //seatStudents method at the end of the code:
        // seatStudents();
        // //the winner IS TO BE seated in the first available seat,
        // // then students from studentsInLine will be seated in height order, because the students in this linked list ARE ALREADY in ascending height order (see playMusicalChairs method). 

    }

    //private method to get node:
    // private SNode matcherNode(){
    //     SNode targetNode = new SNode(null, null);
    //     int x = StdIn.readInt();
    //     int n = StdRandom.uniform(0, x);
    //     if (n==0){
    //         return studentsInLine;
    //     }
    //     else if (n==1){
    //         return studentsInLine.getNext();
    //     }
    //     else{
    //         SNode current = studentsInLine;
    //         for (int i=0; i<x-1; i++){
    //             current = current.getNext();
    //         }
    //         SNode targetNode1 = targetNode.getNext();
    //         SNode currentNext = current.getNext();
    //         targetNode1 = current.getNext();
    //         currentNext = targetNode1;
    //         return targetNode1;
    //     }
    // }
    // //private method to delete node and put it in studentsInLine linked list:
    // private SNode deleter(){
    //     return null;
    // }

    /**
     * Insert a student to wherever the students are at (ie. whatever activity is not empty)
     * Note: adds to the end of either linked list or the next available empty seat
     * @param firstName the first name
     * @param lastName the last name
     * @param height the height of the student
     */
    public void addLateStudent ( String firstName, String lastName, int height ) {
        
        // WRITE YOUR CODE HERE
        if (studentsInLine != null || musicalChairs != null){

            if (studentsInLine != null){
                Student lateStudent = new Student(firstName, lastName, height);
                SNode ptr = studentsInLine;
                while (ptr.getNext() != null){
                    ptr = ptr.getNext();

                }
                SNode removerNode = new SNode(lateStudent, null);


                // removes the late student


                ptr.setNext(removerNode);
            } else{
                Student aStudent = new Student(firstName, lastName, height);
                SNode rightBeforeLast = musicalChairs;


                // gets the student to position it before musical chairs node
                musicalChairs = new SNode(aStudent, rightBeforeLast.getNext());
                rightBeforeLast.setNext(musicalChairs);
            }
        }
        else{
            Student studentForNow = new Student(firstName, lastName, height);

            for (int i =0; i< seatingAvailability.length; i++){
                for(int j=0; j< seatingAvailability[i].length; j++){

                    if(seatingAvailability[i][j]==true && studentsSitting[i][j]==null){
                        studentsSitting[i][j] = studentForNow;
                        return;
                    }
                }
            }
        }
        
    }

    /**
     * A student decides to leave early
     * This method deletes an early-leaving student from wherever the students 
     * are at (ie. whatever activity is not empty)
     * 
     * Assume the student's name is unique
     * 
     * @param firstName the student's first name
     * @param lastName the student's last name
     */
    public void deleteLeavingStudent ( String firstName, String lastName ) {

        // WRITE YOUR CODE HERE
        if (studentsInLine != null || musicalChairs !=null){
            if (studentsInLine !=null){
                SNode pNode = studentsInLine;
                SNode beforePNode = null;
                while ( !pNode.getStudent().getFirstName().equals(firstName) && pNode != null && !pNode.getStudent().getLastName().equals(lastName)){
                    beforePNode = pNode;
                    pNode = pNode.getNext();
                }
    
                if (pNode == null){
                    return;
                }
                else if (beforePNode == null){
                    return;
                }
                else{
                    beforePNode.setNext(pNode.getNext());
                }
                }
            else{
                SNode ptrNOde = musicalChairs.getNext();
                SNode previousNode = musicalChairs;
    
                do{
                    if (ptrNOde.getStudent().getLastName().equals(lastName) && ptrNOde.getStudent().getFirstName().equals(firstName)){
                        break;
                    }
    
                    previousNode = ptrNOde;
                    ptrNOde = ptrNOde.getNext();
                } while (ptrNOde != musicalChairs.getNext());
    
                if (musicalChairs.getNext() == musicalChairs){
                    musicalChairs = null;
                }
                else if (previousNode.getNext() == musicalChairs){
                    previousNode.setNext(musicalChairs.getNext());
                    musicalChairs = previousNode;
                }
                else if (ptrNOde == musicalChairs.getNext()){
                    musicalChairs.setNext(musicalChairs.getNext().getNext());
                }
                else{
                    previousNode.setNext(ptrNOde.getNext());
                }
            }
        } else {
            for (int i=0; i<seatingAvailability.length; i++){
                for(int j =0; j< seatingAvailability[i].length; j++){
    
                    if (studentsSitting[i][j] !=null &&  seatingAvailability[i][j]==true){



                        if (studentsSitting[i][j].getLastName().equals(lastName) && studentsSitting[i][j].getFirstName().equals(firstName)){
                            studentsSitting[i][j] = null;
                        }
                    }
                }
            }
        }
    }
        

    /**
     * Used by driver to display students in line
     * DO NOT edit.
     */
    public void printStudentsInLine () {

        //Print studentsInLine
        StdOut.println ( "Students in Line:" );
        if ( studentsInLine == null ) { StdOut.println("EMPTY"); }

        for ( SNode ptr = studentsInLine; ptr != null; ptr = ptr.getNext() ) {
            StdOut.print ( ptr.getStudent().print() );
            if ( ptr.getNext() != null ) { StdOut.print ( " -> " ); }
        }
        StdOut.println();
        StdOut.println();
    }

    /**
     * Prints the seated students; can use this method to debug.
     * DO NOT edit.
     */
    public void printSeatedStudents () {

        StdOut.println("Sitting Students:");

        if ( studentsSitting != null ) {
        
            for ( int i = 0; i < studentsSitting.length; i++ ) {
                for ( int j = 0; j < studentsSitting[i].length; j++ ) {

                    String stringToPrint = "";
                    if ( studentsSitting[i][j] == null ) {

                        if (seatingAvailability[i][j] == false) {stringToPrint = "X";}
                        else { stringToPrint = "EMPTY"; }

                    } else { stringToPrint = studentsSitting[i][j].print();}

                    StdOut.print ( stringToPrint );
                    
                    for ( int o = 0; o < (10 - stringToPrint.length()); o++ ) {
                        StdOut.print (" ");
                    }
                }
                StdOut.println();
            }
        } else {
            StdOut.println("EMPTY");
        }
        StdOut.println();
    }

    /**
     * Prints the musical chairs; can use this method to debug.
     * DO NOT edit.
     */
    public void printMusicalChairs () {
        StdOut.println ( "Students in Musical Chairs:" );

        if ( musicalChairs == null ) {
            StdOut.println("EMPTY");
            StdOut.println();
            return;
        }
        SNode ptr;
        for ( ptr = musicalChairs.getNext(); ptr != musicalChairs; ptr = ptr.getNext() ) {
            StdOut.print(ptr.getStudent().print() + " -> ");
        }
        if ( ptr == musicalChairs) {
            StdOut.print(musicalChairs.getStudent().print() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    /**
     * Prints the state of the classroom; can use this method to debug.
     * DO NOT edit.
     */
    public void printClassroom() {
        printStudentsInLine();
        printSeatedStudents();
        printMusicalChairs();
    }

    /**
     * Used to get and set objects.
     * DO NOT edit.
     */

    public SNode getStudentsInLine() { return studentsInLine; }
    public void setStudentsInLine(SNode l) { studentsInLine = l; }

    public SNode getMusicalChairs() { return musicalChairs; }
    public void setMusicalChairs(SNode m) { musicalChairs = m; }

    public boolean[][] getSeatingAvailability() { return seatingAvailability; }
    public void setSeatingAvailability(boolean[][] a) { seatingAvailability = a; }

    public Student[][] getStudentsSitting() { return studentsSitting; }
    public void setStudentsSitting(Student[][] s) { studentsSitting = s; }

}
