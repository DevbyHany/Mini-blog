# Mini-Blog (Spring Boot + Thymeleaf + H2)

Jednoduchá blogová aplikace postavená na Spring Boot 3, Thymeleaf a H2 databázi.  
Projekt umožňuje spravovat články (CRUD operace: vytvoření, úprava, smazání, zobrazení).   

---

# Funkce:
- Výpis všech článků se stránkováním  
- Vytvoření nového článku s validací vstupů  
- Úprava existujícího článku  
- Smazání článku (s potvrzením v modalu)  
- Zobrazení chybových hlášek a flash zpráv (success/error)  
- H2 databáze inicializovaná testovacími daty (`data.sql`)  

---

# Spuštění projektu

# Předpoklady:
- Java 17+  
- Maven  

# Kroky:
1. Naklonuj repozitář (CMD/PowerShell)
   - cd /d %USERPROFILE%\Desktop
   - git clone https://github.com/DevbyHany/Mini-blog.git
   - cd Mini-blog

3. Spusť aplikaci (Spuštění bez instalace Mavenu (Maven Wrapper))
   - .\mvnw.cmd spring-boot:run
   
4. Otevři v prohlížeči  
   - Aplikace: http://localhost:8080/articles
     
---

# Použité technologie:
- Java 17  
- Spring Boot 3.3 (Web, Data JPA, Validation, Security, Thymeleaf)  
- H2 Database  
- Maven  
- Bootstrap 5 (UI)  

---

# Ukázky obrazovek:

# Výpis článků
![Výpis článků](docs/images/list.png)

# Formulář pro nový článek
![Nový článek](docs/images/new.png)

# Detail článku
![Detail článku](docs/images/detail.png)

# Edit článku
![Edit článku](docs/images/edit.png)

# Flash zprávy
![Odstranění](docs/images/flash-delete.png)
![Úprava](docs/images/flash-edit.png)
![Nový](docs/images/flash-new.png)

# Ostatní
![NotBlank](docs/images/notblank-message.png)
![Delete](docs/images/delete-message.png)

---

## Další rozvoj
- Přidat autentizaci uživatelů (Spring Security, login/registrace)  
- Připojit produkční databázi (PostgreSQL/MySQL)  
- Rozšířit validace formulářů  
- Integrační testy s Testcontainers  
