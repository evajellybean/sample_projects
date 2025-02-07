To generate the data I followed these steps: 
    - For the student names:
        - I generated a random list of names using a free website which can be found at the hyperlink https://fossbytes.com/tools/random-name-generator,
          and then I uploaded this list to a Google Sheets and formatted the names correctly.
        - To add in the IDs, I generated 100 random numbers in the range 100,000,000 and 999,999,999 (for a nine digit ID) from https://numbergenerator.org/, 
          and I added the list of IDs. I downloaded this spreadsheet as a csv and uploaded it to VS Code and then inputted to my database.
    - For the classes:
        - I came up with names for 27 classes and assigned credits (3 or 4) to them arbitrarily.
    - For the majors and minors:
        - For each student, I generate the number of majors they have (1, 2, or 3) according to the distribution:
          70% chance of 1, 25% chance of 2, and 5% chance of 3.
        - Then I randomly generated the corresponding number of major(s) from the list of departments for that student and filled in the database accordingly.
        - I randomly generate how many minors the student has according to the same distribution as the majors.
        - I pick from the remaining departments to choose the student's minors.
    - For the classes students have taken:
        - For each student, I uniformly randomly generate how many classes they have taken (from 0 to 34). 
        - Then, I randomly sample that number of classes from the classes list.
        - I ran some test runs with 1000 students to make sure this process resulted in approximately 25% in each grade, and it did.
        - For the grade each student gets in a class, I uniformly randomly choose from A, B, C, D, F and assign that.
    - For the classes students are taking:
        - For every student, I sample 5 classes from the classes list and assign them to that student. 
