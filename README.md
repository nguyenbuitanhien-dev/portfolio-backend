# 🗂️ Portfolio Backend

REST API backend cho hệ thống **Personal Portfolio** được xây dựng với **Spring Boot 3**, **MongoDB** và **Redis**. Cung cấp đầy đủ các API quản lý hồ sơ cá nhân, dự án, kỹ năng, giải thưởng, và xử lý tin nhắn liên hệ theo thời gian thực qua WebSocket.

---

## 🚀 Tech Stack

| Thành phần | Công nghệ |
|---|---|
| Framework | Spring Boot 3.5 (Java 17) |
| Database | MongoDB |
| Cache | Redis |
| Bảo mật | Spring Security + JWT (JJWT 0.11.5) |
| Email | Spring Mail (SMTP async) |
| Real-time | WebSocket + STOMP |
| Build Tool | Maven |
| Container | Docker + Docker Compose |

---

## 📁 Cấu trúc thư mục

```
portfolio-backend/
├── src/
│   └── main/
│       ├── java/com/portfoliobackend/
│       │   ├── PortfolioBackendApplication.java   # Entry point (@EnableAsync, @EnableMongoRepositories)
│       │   ├── config/
│       │   │   ├── WebSocketConfig.java            # Cấu hình STOMP WebSocket broker
│       │   │   └── DataInitializer.java            # Seed dữ liệu admin mặc định
│       │   ├── controller/
│       │   │   ├── AuthController.java             # POST /api/auth/login
│       │   │   ├── ProfileController.java          # GET/PUT /api/profile
│       │   │   ├── EducationController.java        # CRUD /api/education
│       │   │   ├── SkillController.java            # CRUD /api/skills
│       │   │   ├── ProjectController.java          # CRUD /api/projects
│       │   │   ├── AwardController.java            # CRUD /api/awards
│       │   │   ├── SocialLinkController.java       # CRUD /api/social-links
│       │   │   └── ContactMessageController.java   # POST (public) + CRUD (admin)
│       │   ├── entity/
│       │   │   ├── Profile.java
│       │   │   ├── Education.java
│       │   │   ├── Skill.java / SkillCategory.java
│       │   │   ├── Project.java
│       │   │   ├── Award.java
│       │   │   ├── SocialLink.java
│       │   │   ├── ContactMessage.java
│       │   │   └── User.java
│       │   ├── repository/                         # Spring Data MongoDB Repositories
│       │   ├── service/                            # Interface + impl/ tương ứng
│       │   │   └── impl/
│       │   ├── security/
│       │   │   ├── SecurityConfig.java             # JWT filter chain + CORS config
│       │   │   ├── JwtTokenProvider.java
│       │   │   └── JwtAuthenticationFilter.java
│       │   ├── payload/                            # Request/Response DTOs
│       │   └── exception/                         # Global exception handler
│       └── resources/
│           └── application.properties             # Cấu hình từ biến môi trường
├── .env                                           # Biến môi trường thực (bị gitignore)
├── .env.example                                   # Mẫu biến môi trường
├── Dockerfile                                     # Multi-stage Docker build
├── docker-compose.yml                             # MongoDB + Redis + App
└── pom.xml
```

---

## ⚙️ Cấu hình môi trường

Sao chép file `.env.example` thành `.env` và điền thông tin thực tế:

```bash
cp .env.example .env
```

| Biến | Mô tả | Mặc định |
|---|---|---|
| `PORT` | Cổng chạy server | `8080` |
| `MONGO_URI` | Chuỗi kết nối MongoDB | `mongodb://localhost:27017/portfolio_db` |
| `REDIS_HOST` | Host Redis | `localhost` |
| `REDIS_PORT` | Cổng Redis | `6379` |
| `ADMIN_USERNAME` | Tên đăng nhập admin | `admin` |
| `ADMIN_PASSWORD` | Mật khẩu admin | `admin123` |
| `JWT_SECRET` | Secret key JWT (≥ 256 bit) | *(bắt buộc thay đổi)* |
| `JWT_EXPIRATION` | Thời hạn token (ms) | `86400000` *(1 ngày)* |

> ⚠️ **Lưu ý bảo mật**: Luôn thay `JWT_SECRET` bằng chuỗi ngẫu nhiên mạnh trước khi deploy production.

---

## 🏃 Cách chạy

### 1. Chạy local (yêu cầu MongoDB & Redis đã cài sẵn)

```bash
# Bước 1: Clone dự án
git clone <repo-url>
cd portfolio-backend

# Bước 2: Tạo file .env
cp .env.example .env
# Chỉnh sửa .env với thông tin của bạn

# Bước 3: Chạy ứng dụng
./mvnw spring-boot:run
```

Ứng dụng khởi động tại: `http://localhost:8080`

---

### 2. Chạy bằng Docker Compose *(Khuyến nghị)*

```bash
# Bước 1: Tạo file .env
cp .env.example .env

# Bước 2: Build và chạy toàn bộ stack (MongoDB + Redis + App)
docker-compose up --build -d

# Xem log
docker-compose logs -f app

# Dừng
docker-compose down
```

> Docker Compose sẽ tự động khởi chạy **MongoDB** (port 27017), **Redis** (port 6379), và **Spring Boot App** (port 8080).

---

### 3. Build JAR thủ công

```bash
./mvnw clean package -DskipTests
java -jar target/portfolio-backend-0.0.1-SNAPSHOT.jar
```

---

## 📡 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Mô tả | Quyền |
|---|---|---|---|
| `POST` | `/api/auth/login` | Đăng nhập, nhận JWT | Public |

### 👤 Profile
| Method | Endpoint | Mô tả | Quyền |
|---|---|---|---|
| `GET` | `/api/profile` | Lấy thông tin hồ sơ | Public |
| `PUT` | `/api/profile` | Cập nhật hồ sơ | Admin |

### 🎓 Education
| Method | Endpoint | Mô tả | Quyền |
|---|---|---|---|
| `GET` | `/api/education` | Danh sách học vấn | Public |
| `POST` | `/api/education` | Thêm mới | Admin |
| `PUT` | `/api/education/{id}` | Cập nhật | Admin |
| `DELETE` | `/api/education/{id}` | Xóa | Admin |

### 🛠️ Skills
| Method | Endpoint | Mô tả | Quyền |
|---|---|---|---|
| `GET` | `/api/skills` | Danh sách kỹ năng | Public |
| `POST` | `/api/skills` | Thêm mới | Admin |
| `PUT` | `/api/skills/{id}` | Cập nhật | Admin |
| `DELETE` | `/api/skills/{id}` | Xóa | Admin |

### 💼 Projects
| Method | Endpoint | Mô tả | Quyền |
|---|---|---|---|
| `GET` | `/api/projects` | Danh sách dự án | Public |
| `GET` | `/api/projects/{id}` | Chi tiết (tăng view) | Public |
| `POST` | `/api/projects` | Thêm mới | Admin |
| `PUT` | `/api/projects/{id}` | Cập nhật | Admin |
| `DELETE` | `/api/projects/{id}` | Xóa | Admin |

### 🏆 Awards
| Method | Endpoint | Mô tả | Quyền |
|---|---|---|---|
| `GET` | `/api/awards` | Danh sách giải thưởng | Public |
| `POST` | `/api/awards` | Thêm mới | Admin |
| `PUT` | `/api/awards/{id}` | Cập nhật | Admin |
| `DELETE` | `/api/awards/{id}` | Xóa | Admin |

### 🔗 Social Links
| Method | Endpoint | Mô tả | Quyền |
|---|---|---|---|
| `GET` | `/api/social-links` | Danh sách mạng xã hội | Public |
| `POST` | `/api/social-links` | Thêm mới | Admin |
| `PUT` | `/api/social-links/{id}` | Cập nhật | Admin |
| `DELETE` | `/api/social-links/{id}` | Xóa | Admin |

### 📬 Contact Messages
| Method | Endpoint | Mô tả | Quyền |
|---|---|---|---|
| `POST` | `/api/contact` | Gửi tin nhắn liên hệ | Public |
| `GET` | `/api/contact` | Danh sách tin nhắn | Admin |
| `PUT` | `/api/contact/{id}/read` | Đánh dấu đã đọc | Admin |
| `DELETE` | `/api/contact/{id}` | Xóa tin nhắn | Admin |

---

## 🔌 WebSocket

Kết nối WebSocket STOMP tại endpoint: `ws://localhost:8080/ws`

| Topic | Mô tả |
|---|---|
| `/topic/contact-messages` | Nhận thông báo real-time khi có tin nhắn mới |

Ví dụ kết nối thủ công (JavaScript thuần):

```js
const ws = new WebSocket('ws://localhost:8080/ws');
ws.onopen = () => {
  ws.send('CONNECT\naccept-version:1.1,1.0\nheart-beat:10000,10000\n\n\u0000');
  ws.send('SUBSCRIBE\nid:sub-0\ndestination:/topic/contact-messages\n\n\u0000');
};
ws.onmessage = (event) => {
  if (event.data.includes('/topic/contact-messages')) {
    // Có tin nhắn mới!
  }
};
```

---

## 🔒 Bảo mật

- Tất cả API **Admin** yêu cầu header `Authorization: Bearer <JWT_TOKEN>`
- Token JWT được cấp sau khi đăng nhập thành công qua `/api/auth/login`
- CORS được cấu hình `allowedOriginPatterns: *` (phù hợp development) — giới hạn lại trong production
- Redis được dùng để cache (không lưu session)

---

## 🧪 Kiểm thử nhanh với cURL

```bash
# Đăng nhập
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'

# Lấy profile (public)
curl http://localhost:8080/api/profile

# Cập nhật profile (cần JWT)
curl -X PUT http://localhost:8080/api/profile \
  -H "Authorization: Bearer <TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Nguyen Van A","title":"Fullstack Developer",...}'
```

---

## 📦 Yêu cầu hệ thống

- **Java 17+**
- **Maven 3.8+** *(hoặc dùng `./mvnw` wrapper đính kèm)*
- **MongoDB 6+**
- **Redis 7+**
- **Docker & Docker Compose** *(nếu chạy container)*
