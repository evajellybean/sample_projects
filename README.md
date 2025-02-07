# Code Samples - a bit about me!
## Introduction
I will be graduating in May 2025 from Rutgers University in New Brunswick with degrees in Computer Science, Statistics/Mathematics, and Data Science. My primary focus through my program has been on the various aspects of data science (data collection, aggregation, cleaning, modeling, analysis, and visualization) as well as on machine learning and AI systems. For these subjects, I have primarily worked in Python (with knowledge of Numpy, Scipy, Pandas, Matplotlib, PyTorch, and other essential libraries), though I have become familiar with SQL through my coursework in database systems and management and with R for my statistical programming projects. As part of my coursework I created several projects that demonstrate my skills and interests as well as my ability to develop clear and explanatory write-ups of the more technical aspects of the projects. I will include a brief description of each project below:
## Election Turnout Analysis (for my Bayesian Data Analysis course)
### Goal: 
In this project I sought to investigate what factors predict election turnout by county in the 2020 election using data from the Census Bureau and the MIT Election Data and Science Lab.
### Skills developed/demonstrated:
- Data collection, curation, cleaning, and aggregation, notably using geospatial data
- Developing Bayesian models of the data using Multi-Chain Monte Carlo (MCMC) methods
- Model checks and comparisons
- Interpretation of the models and write-up of the findings of the project
### Notes:
I wrote this project in R, and the code in an R Markdown (.Rmd) file is provided here, as well as a compiled version of the code as an HTML (.html) file. The HTML file is too large to be displayed within GitHub but I **highly recommend** downloading the HTML file as it is a much more readable way to view this project!
## Fire Extinguisher + Space Rats (for my Artificial Intelligence course)
### Goal:
Through the course of these two projects, we were asked to develop a sample framework for a game, create an AI agent to play the game as efficiently as possible, and analyze the performance of the bot we developed. I will include a more detailed description of the task below.
### Skills developed/demonstrated:
- Developing concrete frameworks/representations in code to represent abstract ideas (in this case, the rules of the game)
- Building AI algorithms and tools from scratch (including concepts like graph search, logical inference, and probabilistic knowledge bases)
- Deriving (and proving the optimality of) the mathematical formulas underlying our algorithm designs
- Simulating games and evaluating performance of our bot
### Notes:
The premise of these projects involves a spaceship (represented as a 40x40 grid) and a bot tasked with solving some problem. 
In the first project, we were asked to simulate a fire spreading across the ship (each turn, the fire would spread into neighboring cells with some probability $\alpha$), and the bot was tasked with finding the optimal path towards the one fire extinguisher aboard the ship while also avoiding the fire. Running into the fire, or the fire spreading into the fire extinguisher, would mean that the bot failed. 
In the second project, there was no fire; instead, the premise here was that the bot's memory was wiped so that it had no knowledge of its whereabouts on the ship (though it still had access to a map of the grid of the ship), and it was tasked with finding a space rat hidden somewhere aboard the ship. Each turn, the bot had the opportunity to attempt to move to a neighboring square, sense how many of the neighboring cells were blocked, or to use a 'space rat detector' where it received a ping with a probability that increased the closer the space rat was to the bot. Using the information it discovered, the bot would have to determine its location on the board and figure out the optimal path to the space rat. We were tasked with creating a simulation of this environment and developing AI algorithms that most efficiently combined the bot's potential actions to find the space rat the most efficiently.
## Student Database (for my Databases course)
### Goal: 
We were asked to design a SQL database that holds student names, classes, grades, and other relevant information, and populate the database with generated data. Then, write a program in Java that executes various SQL queries on the database depending on user input. 
### Skills developed/demonstrated
- Developing a SQL database from scratch and populating it with data
- Creating a program in Java that takes as input requests for data, reformats these requests in SQL queries, executes these queries, and returns the results in a readable manner
- Overall competency surrounding relational databases, from establishing them to running queries to writing code that automatically calls and uses results from queries
### Notes:
My work for this project is spread across several files. README.txt contains a description of my methods for generating sample data for the database, HW3.sql contains the SQL code establishing the database, names.csv contains a file of names and ID numbers (all fictional), and HW3.java contains the code that generates data for the database, deals with user input, and executes the SQL queries.
