# project-management-api

### REST API application that will help the manager to schedule the work, and the employees must fix the start and end of the scheduled work.
The program should be designed for two types of clients: managers who will distribute work for employees and for employees who will record the arrival and departure from work. The program must have Swagger UI. Authentication/authorization must be implemented using the Keycloak server.

### Authentication, authorization
Only authenticated and authorized users should be able to access the REST API (the manager account will be created in the Keycloak server, and the employee accounts will be created by the manager using the application API). Users with manager rights can use management-only endpoints, and employees can use management-only endpoints (they must have different roles). Authentication must also be available from the Swagger UI.

### Description of the API
Managers can add new employees and distribute them to projects. To do this, there must be a list of projects, from where the manager will select a specific project and schedule the days for the employee to work on this project.

### Manager API:
- get a list of projects
- adding a project
- deleting a project
- change project (name)
- adding an employee (you need to add a new employee to the Keycloak server, which will have a name and surname). The employee will be assigned the appropriate role).
- generating a new password for an employee and storing it in the Keycloak server
- getting a list of employees and searching for employees by name and surname
- assignment of an employee to projects. For example, Monday is Project1, Tuesday is Project2, Wednesday is Project1, etc.
- obtaining a distribution list for employees. The list should return a specific employee and his distribution by project during the week. For example, Employee1: Monday - Project1, Tuesday - Project2, Wednesday - Project1, etc.
change distribution for a particular employee. For example, on Monday, Project1 change to Project2

### Employee API:
- information about the authenticated employee (name, surname, project for the current day (if exists)
- begin of work (save date and time of work starting)
- end of work (save date and time of work starting)

### Reports
Daily reports:
- a list of employees who came to work on a specific selected day, where the date and time of the begin and end of work will be indicated


**_Work in progress ..._**
