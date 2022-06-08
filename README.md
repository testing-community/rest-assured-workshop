# Rest Assured Workshop

!Bienvenido! El objetivo de este taller es aprender sobre llamados rest con [RestAssured](https://rest-assured.io/). Mediante el desarrollo de varios ejercicios prácticos, se abarcará diferentes temas.

Se asume que la persona tiene conocimientos previos en:

* Git (Puede seguir este [enlace](https://services.github.com/on-demand/downloads/es_ES/github-git-cheat-sheet/) con los comandos más utilizados en git)
* GitHub
* Conocimiento básico en JAVA

**Recursos**:

* [Wiki](https://github.com/testing-community/rest-assured-workshop/wiki)

## Steps

### Tabla de Contenido

1. [Configuración Inicial del Proyecto](#1-configuración-inicial-del-proyecto)
1. [Llamados a métodos HTTP](#2-llamados-a-métodos-http)

### 1. Configuración Inicial del Proyecto

**Descripción**: Se configurará inicialmente el proyecto con [Java](https://www.oracle.com/co/java/technologies/javase/javase8u211-later-archive-downloads.html) en el IDE [eclipse](https://www.eclipse.org/downloads/). Adicionalmente se creará la configuración necesaria básica para un repositorio de [Github](https://help.github.com/)

**Nota:** Si no tiene conocimiento sobre Github se le recomienda realizar las [Guias de Github](https://guides.github.com/activities/hello-world/) o el lab de [Introduction to Github](https://lab.github.com/githubtraining/introduction-to-github)

1. [Instalar JDK](https://www.oracle.com/co/java/technologies/javase/javase8u211-later-archive-downloads.html) en su equipo si no lo tiene instalado
1. [Instalar Eclipse](https://www.eclipse.org/downloads/) en su equipo si no lo tiene instalado (Eclipse IDE for Java Developers).
1. Configurar la variable de ambiente [JAVA_HOME](https://www.codejava.net/java-core/how-to-set-java-home-environment-variable-on-windows-10)
1. Instalar plugin de TestNG
    * Click en la pestaña de Help
    * Eclipse Marketplace
    * En la barra de busqueda poner TestNG
    * Instalar la primera, TestNG for Eclipse. De click en Confirm y Finish
    * Marque todas las casillas y click en Trust Selected
    * Le pedira que reincie eclipse, acepte
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
    * Si al lado derecho de JRE System Library aparece JavaSE-1.8 todo esta funcionando correctamente

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
    target/
    .idea/
    .vscode/

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

1. Agregar dependencias de RestAssured y TestNG en el pom.xml

    * [RestAssured](https://mvnrepository.com/artifact/io.rest-assured/rest-assured) 
    * [TestNG](https://mvnrepository.com/artifact/org.testng/testng)
    * [Json-simple](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple)

    Las dependencias deberian quedar similiar a:
    ```go
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.0.1</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.5</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.googlecode.json-simple</groupId>
            <artifactId>json-simple</artifactId>
            <version>1.1.1</version>
        </dependency>
	</dependencies>
    ```

1. Crear carpeta de pruebas (de ahora en adelante `test`)
    * En la ruta `src/test/java/com/restassured` crear carpeta con de nombre `test`

**Los siguientes 2 pasos son opcionales en caso de querer revisión de este workshop para cada punto**

1. Proteger la rama `main` para que los pull request requieran revisión de otros desarrolladores y se compruebe el estado de nuestros test ("ok" :heavy_check_mark: o "fallaron" :x:) antes de hacer un merge a la rama.

    Ir a Settings > Branches adicionamos una regla dando click en **add rule**. Escribimos `main` en el campo de **branch name pattern**. Una vez hecho eso, damos click en las siguientes opciones:
    ![branch rules](https://raw.githubusercontent.com/testing-community/cypress-training-doc/main/media/branch_protection_configuration.png)

1. Añadir como colaboradores (ir a settings del repositorio y en Collaborators) a:
   * [dianakrog](https://github.com/dianakrog)
   * [Scot3004](https://github.com/Scot3004)
   * [kliver98](https://github.com/kliver98)

### 2. Llamados a métodos HTTP

#### Creando peticiones GET y POST 
1. Crear el archivo `GetAndPostExample.java` en la carpeta de `test`
    En caso que el archivo este vacío, copie y pegue:
    ```java
    package com.restassured.test;

    public class GetAndPostExample {
    }
    ```
1. Ahora vamos a crear nuestra prueba enviando una petición GET

    Copie y pegue:
    ```java
    @Test
	public void testGet() {
		
		baseURI =  "https://reqres.in/api";
		
		when().
			get("/users?page=2").
		then().
			statusCode(200).
			body("data.size()", is(6)).
			body("data.first_name", hasItems("George", "Rachel"));
				
	}
    ```
    Primero definimos la baseURI que especifica la url base donde esta el servicio que consumiremos. Seguidamente prepariamos el request, pero en este caso no tenemos ninguna precondición (given) entonces podemos ir a la acción (when) que define un método al API de tipo GET (get) para el endpoint que retorna los usuarios.
    Finalmente (then) validamos el status code de respuesta y datos del body de respuesta, como que contenga 6 elementos y especificamente contenga George y Rachel.

1. Vamos a crear la petición para el POST

    Copie y pegue:
    ```java
    @Test
	public void testPost() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Raghav");
		request.put("job", "Teacher");
		
		baseURI =  "https://reqres.in/api";
		
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).
			log().all();
				
	}
    ```
    Primero preparamos la request que enviaremos como un Json, para esto usamos la clase JSONObject y después imprimimos como se veria ese json que creamos.
    Luego especificamos la url base (baseURI) a la cual le enviaremos el request.
    Finalmente en formato gherkin preparamos, enviamos y validamos el request. Aquí ponemos los header necesarios y el body a enviar, luego la acción que se sería la url base y adicionamos el resto del endpoint para el método post, con el then verificamos el status code de la petición e imprimimos lo que nos retorno el endpoint.
    La parte importante aquí es la acción (when) para especificar el método HTTP.
    __Nota:__ Si quisieramos imprimir (por debuguear rápidamente por ejemplo) como esta el request formado, podemos usar `System.out.println(request.toJSONString());`

#### Creando peticiones PUT, PATCH and DELETE
1. Crear el archivo `PutPatchDeleteExample.java` en la carpeta de `test`
    En caso que el archivo este vacío, copie y pegue:
    ```java
    package com.restassured.test;

    public class PutPatchDeleteExample {
    }
    ```
1. Ahora vamos a crear nuestras pruebas enviando una petición PUT y otra de PATCH

    Copie y pegue las siguientes porciones de código:
    ```java
    @Test
	public void testPut() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Raghav");
		request.put("job", "Teacher");
		
		baseURI =  "https://reqres.in/api";
		
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/users/2").
		then().
			statusCode(200).
			log().all();
				
	}
    ```
    ```java
    @Test
	public void testPatch() {
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Raghav");
		request.put("job", "Teacher");
		
		baseURI =  "https://reqres.in";
		
		given().
			header("Content-Type", "application/json").
			contentType(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("/api/users/2").
		then().
			statusCode(200).
			log().all();
				
	}
    ```
    Primero preparamos la request que enviaremos como un Json, para esto usamos la clase JSONObject y después imprimimos como se veria ese json que creamos.
    Luego especificamos la url base (baseURI) a la cual le enviaremos el request.
    Finalmente en formato gherkin preparamos, enviamos y validamos el request. Aquí ponemos los header necesarios y el body a enviar, luego la acción que se sería la url base y adicionamos el resto del endpoint para el método (put o patch), con el then verificamos el status code de la petición e imprimimos lo que nos retorno el endpoint.
    La parte importante aquí es la acción (when) para especificar el método HTTP.
1. Vamos a crear la petición para el DELETE

    Copie y pegue:
    ```java
    @Test
	public void testDelete() {
		
		baseURI =  "https://reqres.in";
		
		when().
			delete("/api/users/2").
		then().
			statusCode(204).
			log().all();
				
	}
    ```
    En este caso no es necesario definir precondiciones o preparar lo que enviaremos (Given), debido a que el método DELETE de este endpoint solo se le especifica en la url (eliminar el usuario con id 2). Finalmente validamos el status code e imprimimos la respuesta de la petición.


<!--How to Handle Authentication in RestAssured
1. Create a Java Class - "RestAssuredAuth.java" on the Packages test: com.restassured.test and create request for authentication

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
