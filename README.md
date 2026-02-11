# Travel Destination Planner API

A Spring Boot backend application for managing travel destinations, featuring Admin and User dashboards. This project serves as the backend for a travel planning application.

##  Features

### Core Features
- **Authentication**: Secure user registration and login using **JWT (JSON Web Tokens)**.
- **Role-Based Access Control**:
    - **Admin**: Can manage destinations (add, remove, bulk add) and fetch suggestions from external APIs.
    - **User**: Can browse destinations, search, view details, and manage a personal wishlist.
- **Data Persistence**: Uses **PostgreSQL** for reliable data storage.

### Admin Dashboard API
- **REST Countries :** check https://github.com/apilayer/restcountries
- **Fetch Suggestions**: Integrates with `https://restcountries.com/v3.1/all?fields=name,capital,region,population,currencies,flags` to suggest destinations.
- **CRUD Operations**: Add single or multiple destinations to the system.
- **Bulk Import**: Add a list of destinations in one request (Transactional).

### User Dashboard API
- **Browse & Search**: View approved destinations with **Pagination** support.
- **Search**: Filter destinations by Country or Capital name.
- **Wishlist**: Users can mark destinations as "Want to Visit" (Many-to-Many relationship).

##  Technologies
- **Java 17**
- **Spring Boot 3.2.2**
    - Spring Web
    - Spring Data JPA
    - Spring Security
- **PostgreSQL** (Database)
- **Lombok** (Boilerplate reduction)
- **JWT** (Stateless Authentication)

##  How to Run

### Prerequisites
1.  **Java 17** SDK installed.
2.  **PostgreSQL** installed and running on port `5432`.

### Setup Steps
1.  **Create Database**:
    Open pgAdmin or your terminal and create a database named `travel_db`.
    ```sql
    CREATE DATABASE travel_db;
    ```
2.  **Configure Credentials**:
    Check `src/main/resources/application.properties` and update username/password if needed.
    ```properties
    spring.datasource.username=postgres
    spring.datasource.password=password
    ```
3.  **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```
    Or run `TravelPlannerApplication.java` from your IDE.

4.  **Default Admin User**:
    On the first run, the app will automatically create an Admin user:
    - **Username**: `kareem`
    - **Password**: `8561`

## 📚 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description | Public |
| :--- | :--- | :--- | :---: |
| `POST` | `/api/auth/register` | Register a new user (`ADMIN` or `USER`) | ✅ |
| `POST` | `/api/auth/login` | Login and receive JWT Token | ✅ |

### 🛠️ Admin Operations (Requires `ROLE_ADMIN`)
| Method | Endpoint | Description | Payload |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/admin/suggestions` | Fetch destination suggestions from External API | - |
| `POST` | `/api/admin/destinations` | Add a single destination | `{ "country": "...", ... }` |
| `POST` | `/api/admin/destinations/bulk` | Bulk add destinations | `[ { ... }, { ... } ]` |
| `DELETE` | `/api/admin/destinations/{id}` | Delete a destination | - |

### 👤 User Operations (Requires `ROLE_USER` or `ROLE_ADMIN`)
| Method | Endpoint | Description | Parameters |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/user/destinations` | Get all destinations (Paginated) | `?page=0&size=10` |
| `GET` | `/api/user/destinations/{id}` | Get details of a specific destination | - |
| `GET` | `/api/user/destinations/search` | Search by Country or Capital | `?query=Egypt` |
| `GET` | `/api/user/wishlist` | Get current user's wishlist | - |
| `POST` | `/api/user/wishlist/{id}` | Add destination to wishlist | - |
| `DELETE`| `/api/user/wishlist/{id}` | Remove destination from wishlist | - |

##  Data Structures

### Destination Request JSON (Example)
```json
{
      "country": "Andorra",
      "capital": "Andorra la Vella",
      "region": "Europe",
      "population": 88406,
      "currency": "Euro (€)",
      "flagImageUrl": "https://flagcdn.com/w320/ad.png"
}
```

### Standard API Response
All endpoints return a unified response structure:
```json
{
  "success": true,
  "message": "Operation successful",
  "data": { ... } // or null
}
```

##  CORS Configuration
The backend is configured to allow requests from `http://localhost:4200` (Angular Default Port).
