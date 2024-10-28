## Overview
This rule engine application evaluates user eligibility based on attributes such as age, department, income, and experience using an Abstract Syntax Tree (AST) representation of conditional rules. The application includes a simple user interface, a RESTful API for interaction, and a backend for rule processing and storage.

## Features
- Create and evaluate eligibility rules using user attributes.
- Combine multiple rules for complex evaluations.
- Simple and intuitive user interface.
- RESTful API for easy integration and automation.

## Architecture
The application follows a 3-tier architecture:
1. **Presentation Layer:** UI to accept user attributes and display eligibility results.
2. **API Layer:** RESTful API endpoints for rule management.
3. **Backend Layer:** Handles AST creation and evaluation, interacting with the database.

## Getting Started

### Prerequisites
- [Node.js](https://nodejs.org/) (for the backend)
- [MySQL](https://www.mysql.com/) (for the database)

### Installation
1. Clone the repository:
  
   git clone https://github.com/PratimaR6/Rule-engine.git
   cd Rule-engine

2. Install dependencies:

npm install

3. Set up the MySQL database:

Create a new database (e.g., rule_engine):


CREATE DATABASE rule_engine;
Use the following SQL statements to create the necessary tables:

Table creation 
USE rule_engine;

CREATE TABLE rules (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rule_string TEXT NOT NULL
);

CREATE TABLE metadata (
    id INT AUTO_INCREMENT PRIMARY KEY,
    attribute VARCHAR(255) NOT NULL,
    value VARCHAR(255) NOT NULL
);

API Endpoints
Create Rule

POST /api/rules
Request Body: { "rule_string": "your_rule_string" }
Response: Returns the created AST node.
Combine Rules

POST /api/combine
Request Body: { "rules": ["rule1_string", "rule2_string"] }
Response: Returns the combined AST node.
Evaluate Rule

POST /api/evaluate


Implementation Details
AST Creation and Evaluation
createRule: Parses the rule string and constructs the AST.
combineRules: Merges multiple rule ASTs to optimize evaluations.
evaluateRule: Recursively evaluates the AST against provided data.
Error Handling
Includes error handling for parsing and evaluation failures.

Testing
Unit tests are implemented for each function to ensure correctness.
