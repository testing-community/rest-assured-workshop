# Rest Assured Workshop

!Bienvenido! El objetivo de este taller es aprender sobre llamados rest con [RestAssured](https://rest-assured.io/). Mediante el desarrollo de varios ejercicios prácticos, se abarcará diferentes temas.

Se asume que la persona tiene conocimientos previos en:

* Git (Puede seguir este [enlace](https://services.github.com/on-demand/downloads/es_ES/github-git-cheat-sheet/) con los comandos más utilizados en git)
* GitHub
* Basic Java knowledge

**Recursos**:

* [Wiki](https://github.com/testing-community/rest-assured-workshop/wiki)

## Steps

### Tabla de Contenido

1. [Configuración Inicial del Proyecto](#1-configuración-inicial-del-proyecto)
<!-- 1. [EXAMPLE this is how you should do it](#2-EXAMPLE-this-is-how-you-should-do-it)
delete this when your item is done -->

### 1. Configuración Inicial del Proyecto

**Descripción**: Se configurará inicialmente el proyecto con [Java](https://www.oracle.com/co/java/technologies/javase/javase8u211-later-archive-downloads.html) en el IDE [eclipse](https://www.eclipse.org/downloads/). Adicionalmente se creará la configuración necesaria básica para un repositorio de [Github](https://help.github.com/)

**Nota:** Si no tiene conocimiento sobre Github se le recomienda realizar las [Guias de Github](https://guides.github.com/activities/hello-world/) o el lab de [Introduction to Github](https://lab.github.com/githubtraining/introduction-to-github)

1. [Instalar JDK](https://www.oracle.com/co/java/technologies/javase/javase8u211-later-archive-downloads.html) en su equipo si no lo tiene instalado
1. [Instalar Eclipse](https://www.eclipse.org/downloads/) en su equipo si no lo tiene instalado (Eclipse IDE for Java Developers).
1. Configurar la variable de ambiente [JAVA_HOME](https://www.codejava.net/java-core/how-to-set-java-home-environment-variable-on-windows-10)
1. Iniciar eclipse y crear un nuevo proyecto maven:
    * Archivo (File)
    * Nuevo (New)
    * Otro.. (Other..)
    * En el cuadro de busqueda poner Maven y seleccionar **Maven Project**
    * Usar ubicación por defecto o seleccionar una. Siguiente
    * Buscar **maven-archetype-quickstart** del group id apache. Seleccionar y siguiente
    * En Group Id poner: com.restassured
    * En Artifact Id: workshop
    * Click en Finish
    * En la parte izquierda se genero el proyecto con nombre workshop. Se puede cambiar
1. Cambiar la librería para que ejecute con Java 8
    * Abrír el archivo **pom.xml** y cambiar donde esta maven.compiler.source y maven.compiler.target de 1.7 a 1.8. Guardar y cerrar
    * Click derecho al proyecto > Maven > Update Project
    * Si al lado derecho de JRE System Library esta JavaSE-1.8 todo esta bien

1. Crear una cuenta en Github si no la tiene.
1. Crear un repositorio en limpio dentro de la página de GitHub con el nombre de “**rest-assured-workshop**”
1. En el equipo, abrir la carpeta donde se creo el proyecto de Maven en eclipse y abrir una consola en esa ruta
1. Crear archivo .gitignore ```echo "" >> .gitignore```
1. Copiar el siguiente contenido y guardar con los cambios

    ```
    # Compiled class file
    *.class

    # Log file
    *.log

    # Package Files #
    *.jar
    *.war
    *.zip
    *.tar.gz
    *.rar

    # Auto-generated

    test-output/
    .classpath
    .project
    .settings
    target/

    pom.xml.tag
    pom.xml.releaseBackup
    pom.xml.versionsBackup
    pom.xml.next
    ```

1. Seguir las instrucciones para realizar el primer commit

    ``` shell
    echo "# rest-assured-workshop" >> README.md
    git init
    git add .
    git commit -m "first commit"
    git branch -M main
    git remote add origin git@github.com:<su-usuario>/rest-assured-workshop.git
    git push -u origin main
    ```

1. Add rest-assured and TestNG dependencies

    * [RestAssured](https://mvnrepository.com/artifact/io.rest-assured/rest-assured) dependency
    * [TestNG](https://mvnrepository.com/artifact/org.testng/testng) dependency

<!-- The next one should be improve - delete this after done that -->

First example ( https://www.youtube.com/watch?v=vgMyJhrMV0o&list=PLhW3qG5bs-L8xPrBwDv66cTMlFNeUPdJx&index=4)
1. Create Packages 
	test: com.restassured.test
	
1. Create a Java Class - "TestsExample" on the Packages test: com.restassured.test
1. Create a function and anotation with @Test (TestNG)
1. Run GET request
1. Store response and print response data
1. Add assertion
1. Rund and veryfy

Get and Post petititios 
1. Create a Java Class - "GetAndPostExample.java" on the Packages test: com.restassured.test

PUT, PATCH and DELETE petitions
1. Create a Java Class - "PutPatchDeleteExmple.java" on the Packages test: com.restassured.test

How to Handle Authentication in RestAssured
1. Create a Java Class - "RestAssureAuth.java" on the Packages test: com.restassured.test and create request for authentication

IN THIS EXERSICE USE:

Authentication Methods
GET
Basic Auth
https://postman-echo.com/basic-auth
This endpoint simulates a basic-auth protected endpoint.
The endpoint accepts a default username and password and returns a status code of 200 ok only if the same is provided.
Otherwise it will return a status code 401 unauthorized.

Username: postman
Password: password

To use this endpoint, send a request with the header Authorization: Basic cG9zdG1hbjpwYXNzd29yZA==.
The cryptic latter half of the header value is a base64 encoded concatenation of the default username and password.
Using Postman, to send this request, you can simply fill in the username and password in the "Authorization" tab and Postman will do the rest for you.

To know more about basic authentication, refer to the Basic Access Authentication wikipedia article.
The article on authentication helpers elaborates how to use the same within the Postman app.

More information: https://www.postman.com/postman/workspace/published-postman-templates/documentation/631643-f695cab7-6878-eb55-7943-ad88e1ccfd65?ctx=documentation#42c867ca-e72b-3307-169b-26a478b00641

1. Complete the petition with the class BaseClassAuth.java with the parameters
