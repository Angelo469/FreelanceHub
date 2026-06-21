# FreelanceHub 🚀

A comprehensive Freelancer and Project Management System built with Java, JavaFX, and MySQL.

## 📋 About The Project

FreelanceHub is a desktop application designed to manage freelancers, international projects, and work assignments. The system handles freelancer registration, project tracking, work hour allocation, and automatic currency conversion from USD to BRL.

### Built With

* Java 21
* JavaFX 21
* MySQL 8.3.0
* Maven
* SceneBuilder

## 🏗️ Architecture

The project follows **Clean Architecture** principles with **MVC** pattern:

```
src/
├── model/          → Entities (Freelancer, Projeto, Atribuicao)
├── repository/     → DAOs and Database Connection
├── controller/     → Business Logic Controllers
└── view/           → FXML views and CSS styling
```

## ✨ Current Features

### Freelancer Management
- ✅ Full CRUD operations (Create, Read, Update, Delete)
- ✅ Field validation with visual alerts
- ✅ Data displayed in interactive TableView
- ✅ Auto-selection for editing
- ✅ Clean form after operations

### Database
- ✅ MySQL connection with JDBC
- ✅ Three main tables: `freelancer`, `projeto`, `atribuicao`
- ✅ Foreign key relationships

### UI/UX
- ✅ Custom CSS styling with modern design
- ✅ Dark theme with blue accents
- ✅ Responsive buttons and fields
- ✅ Error handling with JavaFX Alerts

## 🚧 Roadmap - To Be Implemented

### Phase 1: Complete CRUD Operations
- [ ] Project (Projeto) management screen
- [ ] Assignment (Atribuicao) management screen
- [ ] Navigation between screens

### Phase 2: Enhanced Validations
- [ ] Confirmation dialog before deletion
- [ ] Success messages after operations
- [ ] Empty field validations for Update operation
- [ ] Duplicate entry prevention

### Phase 3: Advanced Features
- [ ] Currency conversion (USD → BRL) with live rates
- [ ] Project status tracking (In Progress, Completed, Delayed)
- [ ] Deadline alerts for overdue projects
- [ ] Search and filter functionality
- [ ] Export data to PDF/Excel

### Phase 4: Polish & Professional Features
- [ ] User authentication system
- [ ] Multiple screen navigation with menu
- [ ] Dashboard with statistics
- [ ] Backup and restore database
- [ ] Application settings panel

## 🗄️ Database Schema

### Freelancer Table
```sql
- id (INT, AUTO_INCREMENT, PRIMARY KEY)
- nome (VARCHAR)
- especialidade (VARCHAR)
- pais (VARCHAR)
- taxa_por_hora (DOUBLE)
```

### Projeto Table
```sql
- id (INT, AUTO_INCREMENT, PRIMARY KEY)
- nome (VARCHAR)
- cliente (VARCHAR)
- prazo (DATE)
- valor_dolar (DOUBLE)
- status (VARCHAR)
```

### Atribuicao Table
```sql
- id (INT, AUTO_INCREMENT, PRIMARY KEY)
- freelancer_id (INT, FOREIGN KEY)
- projeto_id (INT, FOREIGN KEY)
- horas_trabalhadas (INT)
```

## 🚀 Getting Started

### Prerequisites

* JDK 21 or higher
* MySQL 8.0 or higher
* Maven
* IntelliJ IDEA (recommended) or any Java IDE

### Installation

1. Clone the repository
```bash
git clone https://github.com/Angelo469/FreelanceHub.git
```

2. Create the database
```sql
CREATE DATABASE freelancehub;
USE freelancehub;

-- Run the SQL scripts from the project
```

3. Configure database connection
   - Open `src/repository/DatabaseConnection.java`
   - Update with your MySQL credentials

4. Build with Maven
```bash
mvn clean install
```

5. Run the application
   - Execute `Main.java`

## 🎨 Screenshots

*(Screenshots will be added as the project evolves)*

## 📝 Development Log

- **Day 1**: Project setup, database structure, Model entities, DAOs, Controllers, Freelancer CRUD UI with validations and CSS styling

## 👨‍💻 Author

**Angelo**
- GitHub: [@Angelo469](https://github.com/Angelo469)

## 📄 License

This project is open source and available for educational purposes.

## 🙏 Acknowledgments

* Built as a learning project to practice Java, JavaFX, and Clean Architecture
* Inspired by real-world freelancer management needs
* Previous project: [CarTracker](https://github.com/Angelo469/Cartracker)

---

**Status**: 🚧 Active Development - Phase 1 in Progress
