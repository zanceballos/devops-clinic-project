# 🏥 ClinicHub: DevOps Project

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/Apache%20Tomcat-F8DC75?style=for-the-badge&logo=apache-tomcat&logoColor=black)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?style=for-the-badge&logo=selenium&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![Prometheus](https://img.shields.io/badge/Prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white)
![Grafana](https://img.shields.io/badge/Grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white)

> ## 📖 Description

**ClinicHub** is a Clinic Appointment Web Application designed to solve a specific problem: enabling users to seamlessly browse, book, and manage clinic appointments. 

Beyond the core web application, this repository serves as a comprehensive **DevOps showcase**, featuring a fully automated Continuous Integration and Continuous Deployment (CI/CD) pipeline, automated testing frameworks, and a containerized monitoring infrastructure.

---

## ✨ Core Functionalities

### Web Application Features
* **Browse Clinics:** Users can view available clinics and their operating details.
* **Book Appointments:** Securely schedule appointments with preferred healthcare providers.
* **Manage Bookings:** Users can view, update, or cancel their upcoming clinic appointments.

### DevOps & Infrastructure Features
* **Automated CI/CD Pipeline:** A declarative `Jenkinsfile` controls the build and deployment process, utilizing SCM polling to automatically trigger builds upon code commits.
* **Automated Testing & Reporting:** Jenkins executes Selenium (UI) and JUnit 5 (Unit) test cases automatically during the pipeline, generating accessible test reports in the Jenkins dashboard.
* **Automated Deployment:** Successful builds are automatically deployed to a local XAMPP Apache Tomcat server.
* **System Monitoring & Analytics:** A Dockerized monitoring stack uses Prometheus to scrape metrics from the Jenkins pipeline, which are then queried and visualized through custom Grafana dashboards to track pipeline health and performance.

---

## 🛠️ Tech Stack & Tools

**Application Development:**
* **Language:** Java
* **IDE:** Eclipse
* **Web Server:** Apache Tomcat (via XAMPP)

**DevOps & Automation:**
* **CI/CD:** Jenkins (Declarative Pipelines)
* **Unit Testing:** JUnit 5
* **UI/End-to-End Testing:** Selenium
* **Containerization:** Docker
* **Monitoring & Metrics:** Prometheus
* **Data Visualization:** Grafana

---

## 🚀 Getting Started (Local Usage)

To run the base web application locally:

1. **Clone the repository** to your local machine.
2. In Eclipse, right-click inside the **Project Explorer** and select **Import projects from Git**.
3. Select the local repository directory you just cloned.
4. Select the `master` branch.
5. Choose the **Import as general project** option and finish the setup.
6. Open the **Servers** tab in Eclipse, right-click your **Tomcat Server**, and click **Start** to run the application.

---

## 👥 Authors & Acknowledgements

This project was developed by:

* **Izzan** ([@zanceballos](https://github.com/zanceballos)) - *izzan2234@outlook.com*
* **zal07**
* **carlodl**
* **dhevakash**

## 🤝 Contributing & Support

**Support:**
If you have any inquiries regarding the project setup or need technical support, please contact the project administrator at [izzan2234@outlook.com](mailto:izzan2234@outlook.com).

**Contributing:**
Currently, this is a closed academic project. If you are interested in exploring potential collaborations, please reach out via email.
