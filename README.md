# Complete Spring Boot and Security Demo


## Description
Ce projet est une application complète utilisant Spring Boot 3 et Spring Security 6 pour démontrer le flux d'enregistrement et de connexion des utilisateurs, ainsi que la vérification par e-mail et la réexpédition des tokens de vérification expirés et la réinitialisation du mot de passe oublié


## Fonctionnalités

- **Enregistrement des utilisateurs: Permet aux nouveaux utilisateurs de s'inscrire.** 
- **Connexion des utilisateurs: Permet aux utilisateurs existants de se connecter.**
- **Vérification par e-mail: Envoie un e-mail de vérification après l'enregistrement.**
- **Réexpédition des tokens expirés: Permet aux utilisateurs de demander un nouveau token de vérification si le précédent a expiré.**
- **la réinitialisation du mot de passe oublié**

## Prérequis

- Java 17
- Maven 3.6.0 ou plus récent
- MySQL ou tout autre base de données relationnelle supportée par Spring Data JPA

## Installation

1. Clonez le repository :


2. Configurez la base de données dans `src/main/resources/application.properties` :

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/votre_db
    spring.datasource.username=your_db_user
    spring.datasource.password=your_db_password

    spring.jpa.hibernate.ddl-auto=update
    ```

3. Compilez et démarrez l'application :

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

## Utilisation

### Test de registration avec POSTMAN
<img src="https://github.com/MohamedAmineHammi/Complete-spring-boot-and-security-demo/blob/master/Registration%20Test%20with%20POSTMAN.png" width="350" height="200"> 

### Inscription et Vérification par Email / Cas d'Expiration du Token

L'utilisateur s'inscrit avec son email, et un email de vérification est envoyé. Une fois l'email vérifié, l'utilisateur peut se connectersi non le token sera expiré !

<img src="https://github.com/MohamedAmineHammi/Complete-spring-boot-and-security-demo/blob/master/Token%20Expired.png" width="350" height="200"> 

### Réception d'un nouveau Token


<img src="https://github.com/MohamedAmineHammi/Complete-spring-boot-and-security-demo/blob/master/New%20Token%20Sended.png" width="350" height="200"> 


### Email vérifié avec nouveau Token

  
  <img src="https://github.com/MohamedAmineHammi/Complete-spring-boot-and-security-demo/blob/master/Email%20Verified.png" width="350" height="200">

  ### la réinitialisation du mot de passe oublié
  **Test de demande de réinitialisation de demande de mot de passe avec Postman**
  
  <img src="https://github.com/MohamedAmineHammi/Complete-spring-boot-and-security-demo/blob/master/ResetPassword%20Test%20with%20Postman.png" width="350" height="200">

  **E-mail de vérification de demande de réinitialisation de mot de passe**
  
  <img src="https://github.com/MohamedAmineHammi/Complete-spring-boot-and-security-demo/blob/master/Password%20Reset%20Request%20Verification%20Email.png" width="350" height="200">

  **Test de réinitialisation du mot de passe avec Postman**
  
  <img src="https://github.com/MohamedAmineHammi/Complete-spring-boot-and-security-demo/blob/master/ResetPassword%20Test%20with%20Postman.png" width="350" height="200">

  **Jeton de réinitialisation de mot de passe généré dans la base de données**
  
  <img src="https://github.com/MohamedAmineHammi/Complete-spring-boot-and-security-demo/blob/master/Password%20ResetToken%20generated%20in%20DB.png" width="350" height="200">

 
## Technologies

- Java 17
- Spring Boot
- Spring Security
- MySQL

## Contribuer

Les contributions sont les bienvenues ! Merci de suivre ces étapes :

1. Fork le projet
2. Créez votre branche de fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Commitez vos modifications (`git commit -m 'Add some AmazingFeature'`)
4. Poussez votre branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## Licence

Distribué sous la licence MIT. Voir `LICENSE` pour plus d'informations.

## Contact

Mohamed Amine Hammi - eminehammi@gmail.com
