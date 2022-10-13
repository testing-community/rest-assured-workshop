# Rest Assured Workshop

!Bienvenido! El objetivo de este taller es aprender sobre llamados rest con [RestAssured](https://rest-assured.io/). Durante el taller exploraremos la configuración de un proyecto desde cero (creará un repositorio con su cuenta), prepararlo para un proceso de integración continua por medio de Github actions y se abarcarán varios ejercicios prácticos, incluyendo temas como llamados HTTP, códigos de respuesta, reportes y mucho más.

Para el desarrollo del taller usaremos [GitHub](https://github.com/) y [GitHub Flow](https://docs.github.com/en/get-started/quickstart/github-flow) para realizar la entrega de cada ejercicio práctico.

Se asume que la persona tiene conocimientos previos en:

* Git (Puede seguir este [enlace](https://services.github.com/on-demand/downloads/es_ES/github-git-cheat-sheet/) con los comandos más utilizados en git)
* GitHub
* Conocimiento básico en JAVA (Clases, Objetos, POM)

#### Tips de GitHub Flow:

1. Para cada ejercicio crear una rama (Investiga: _gitflow naming conventions_ )
1. Crea un Pull Request (PR) por cada punto (Recuerda las interacciones como comentarios en inglés)
1. Después de que se recibe aprobación de cada punto por parte de los colaboradores (punto 1.17) se debe hacer merge de la rama, utilizando squash and merge.
1. Antes de empezar un nuevo punto se debe hacer pull de main para asegurarnos que tenemos los últimos cambios del anterior punto.

## Pasos

### Tabla de Contenido

1. [Configuración Inicial del Proyecto](#1-configuración-inicial-del-proyecto)
1. [Llamados a métodos HTTP](#2-llamados-a-métodos-http)
1. [Authentication en RestAssured](#3-Authentication-en-RestAssured)
1. [Assertions con Hamcrest](#4-Assertions-con-Hamcrest)
1. [Configuremos nuestro reporte con Allure](#5-Configuremos-nuestro-reporte-con-Allure)

### 1. Configuración Inicial del Proyecto

**Descripción**: Se configurará inicialmente el proyecto con [Java](https://www.oracle.com/co/java/technologies/javase/javase8u211-later-archive-downloads.html) en el IDE [eclipse](https://www.eclipse.org/downloads/). Adicionalmente se creará la configuración necesaria básica para un repositorio de [Github](https://help.github.com/)

**Nota:** Si no tiene conocimiento sobre Github se le recomienda realizar las [Guias de Github](https://guides.github.com/activities/hello-world/) o el lab de [Introduction to Github](https://lab.github.com/githubtraining/introduction-to-github)

1. [Instalar JDK 8](https://www.oracle.com/co/java/technologies/javase/javase8u211-later-archive-downloads.html) en su equipo si no lo tiene instalado
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
1. Crear un repositorio en limpio dentro de la página de GitHub con el nombre de **rest-assured-workshop**
1. En el equipo, abrir la carpeta donde se creo el proyecto de Maven en eclipse y abrir una consola en esa ruta
1. Crear archivo .gitignore ```echo "" >> .gitignore```
1. Copiar, pegar y guardar el siguiente contenido. Esto excluira que se suban archivos al repositorio con estas extensiones:

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

1. Configura tu Git para revision del workshop para cada punto

1. Proteger la rama `main` para que los pull request requieran revisión de otros desarrolladores y se compruebe el estado de nuestros test ("ok" :heavy_check_mark: o "fallaron" :x:) antes de hacer un merge a la rama.

    Ir a Settings > Branches adicionamos una regla dando click en **add rule**. Escribimos `main` en el campo de **branch name pattern**. Una vez hecho eso, damos click en las siguientes opciones:
    ![branch rules](https://raw.githubusercontent.com/testing-community/cypress-training-doc/main/media/branch_protection_configuration.png)

1. Añadir como colaboradores (ir a settings del repositorio y en Collaborators) a:
   * [dianakrog](https://github.com/dianakrog)
   * [Scot3004](https://github.com/Scot3004)
   * [diegomtylop](https://github.com/diegomtylop)
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


### 3. Authentication en RestAssured

Muchos servicios requieren de autenticación para consumir sus métodos, en este ejercicio vamos a construir un ejemplo de autenticación básica (Basic Auth).


Para esto utilizaremos el recurso [basic-auth de postman]( https://postman-echo.com/basic-auth). El endpoint acepta un nombre de usuario y una contraseña predeterminados y devuelve un código de estado de 200 ok, solo si se proporciona el mismo correctamente. De lo contrario, devolverá un código de estado 401 no autorizado.

La información de autenticacion del servicio es:

Username: postman
Password: password

Empecemos

1. Crea una clase Java llamada `BaseClassAuth.java` en el Packages `test`: com.restassured.test y cree una peticion para autenticacion con el siguiente código, la cual contiene los parametros de la petición y autenticación:

	Copie y pegue:
    ```java
	package com.restassured.test;
	
	import org.testng.annotations.BeforeClass;
	
	import io.restassured.RestAssured;
	
	public class BaseClassAuth {
		
		@BeforeClass
		public void setup() {
			
			RestAssured.authentication = RestAssured.preemptive().basic("postman", "password");
			
			RestAssured.baseURI = "https://postman-echo.com/basic-auth";
			
		}
	
	}
    ```

1. Ahora crea la clase Java llamada `RestAssuredAuth.java` en el Packages `test`: com.restassured.test que se extiende de la clase `BaseClassAuth.java` y que hace la petición para la autenticación.

	Copie y pegue:
    ```java
    package com.restassured.test;

	import org.testng.annotations.Test;
	
	import io.restassured.RestAssured;
	
	public class RestAssureAuth extends BaseClassAuth{
		
		@Test
		public void test1() {
			
			int code = RestAssured.given().
					get().
					getStatusCode();
			
			System.out.println("Response code form server is " + code);
			
		}
	
	}
    ```
    
 1. Ahora verifiquemos que el método de autenticación quedo correcto. Desde la clase `RestAssuredAuth.java` ejecute la prueba y verifique que el código de respuesta que se imprime en consola es 200.
 
 
 ### 4. Assertions con Hamcrest
 
 Vamos a realizar la verificación de una de nuestras pruebas. Para esto usaremos Hamcrest. Lo primero que debemos hacer es agregar la librería que nos permite hacer las aserciones.
 
 1. En el archivo pom.xml agrega la dependencia de Hamcrest All que se encuentra en el repositorio de mavem.
 
	 Copie y pegue:
	 ```java
	 <dependency>
	   <groupId>org.hamcrest</groupId>
	   <artifactId>hamcrest-all</artifactId>
	   <version>1.3</version>
	   <scope>test</scope>
	 </dependency>
	 ```
	    
 1. Para que los cambios sean tomados actualice las librerías. Desde Eclipse puede hacer clic derecho desde el proyecto, seleccione la opción Maven y luego Update Project. Verifique que este seleccionado el proyecto sobre el cual esta trabajando y luego ejecute OK.
 
 1. Ahora, importe los metodos de Hamcrest agregando la siguiente linea: 
 
 	Copie y pegue:
	```java
	import static org.hamcrest.Matchers.*;
	```
	    
 1. Ahora actialice el metodo "test1" de la clase RestAssuredAuth.java para que quede de la siguiente forma.


	Copie y pegue:
	```java
	public void test1() {
		
	RestAssured.given()
		.get()
		.then()
		.statusCode(200)
		.body("authenticated", equalTo(true));
		
	}
	```
	  
	Note que se agregó. then() indicando que siguen las aserciones y posteriormente los matchers statusCode para validar que se entregue un Código de respuesta valida y el marcher body para verificar que sea el esperado.

 1. Ahora ejecutemos la prueba. Desde la clase RestAssuredAuth.java ejecuta la prueba, verifica que el test quedo OK.
 
 1. Has fallar tu asercion, en el statusCode(200), cambialo por 300 y ejecuta nuevamente. Veras que ahora la prueba quedo fallida.
 
 Puedes ver más Matchers [Aquí]( https://www.javadoc.io/doc/org.hamcrest/hamcrest/2.1/org/hamcrest/Matchers.html).
 
 
  ### 5. Configuremos nuestro reporte con Allure
  
 1. En el archivo pom.xml agrega la dependencia de Allure que se encuentra en el repositorio de mavem.
 
	 Copie y pegue:
	 ```java
	 <dependency>
		<groupId>io.qameta.allure</groupId>
		<artifactId>allure-testng</artifactId>
		<version>2.18.1</version>
		<scope>test</scope>
	</dependency>
	```
	
 1. En el archivo pom.xml agregue en la seccion properties la siguiente linea
 
	 Copie y pegue:
	 ```java
	 <aspectj.version>1.8.10</aspectj.version>
	 ```
	    
 1. En el archivo pom.xml agrega los siguientes plugins que le permitiran generar el reporte. Recuerde que en 
 
	 Copie y pegue:
	 ```java
	 <plugin>
	    <groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-compiler-plugin</artifactId>
		<configuration>
			<source>1.8</source>
			<target>1.8</target>
		</configuration>
	 </plugin>
	 <plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-surefire-plugin</artifactId>
		<version>2.20</version>
		<configuration>
			<argLine>
				-javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
			</argLine>
		</configuration>
		<dependencies>
			<dependency>
				<groupId>org.aspectj</groupId>
				<artifactId>aspectjweaver</artifactId>
				<version>${aspectj.version}</version>
			</dependency>
		</dependencies>
		</plugin>
		<plugin>
			<groupId>io.qameta.allure</groupId>
			<artifactId>allure-maven</artifactId>
			<version>2.8</version>
			<configuration>
				<reportVersion>2.7.0</reportVersion>
				<allureDownloadUrl>https://github.com/allure-framework/allure2/releases/download/2.7.0/allure-2.7.0.zip</allureDownloadUrl>
				<resultsDirectory> ${basedir}\allure-results</resultsDirectory>
			</configuration>
		</plugin>
	 ```
	 
 1. Para que los cambios sean tomados actualice las librerías. Desde Eclipse puede hacer clic derecho desde el proyecto, seleccione la opción Maven y luego Update Project. Verifique que este seleccionado el proyecto sobre el cual esta trabajando y luego ejecute OK.
 
 1. Ahora actialice el método `test1` de la clase `RestAssuredAuth.java` para que quede de la siguiente forma.
 
 	Copie y pegue:
	```java
	@Test(priority = 0, description="Valid Autentication Scenario with valid username and password.")
	@Severity(SeverityLevel.BLOCKER)
	@Description("Test Description: Login test with valid username and password.")
	@Story("Get autentication token")
	@Step("Petition get to autentication")
	public void test1() {
			
		RestAssured.given()
			.get()
			.then()
			.statusCode(200)
			.body("authenticated", equalTo(true));
			
	}
	```
 	
 	__Nota:__ Estas anotaciones son necesarias, ya que serán características de nuestro reporte en Allure y nos permitirá tener detalles de la prueba en el reporte. Existen otros tipos de anotaciones que le ayudaran a mejorar su reporte según lo necesite.
 
 1. Finalmente, abra una consola de comandos desde dentro de su carpeta del proyecto "rest-assured-workshop" y ejecute los siguientes comandos:  
 		
 		```
 		mvn clean test
 		
 		mvn allure:serve
 		
 		```
 	
 	Esto le abrira el reporte en el navegador, navegue el reporte y encuentre donde se encuentran las anotaciones puestas en la clase de prueba. vera algo como lo siguiente:
 	![branch rules](https://docs.qameta.io/allure/images/testcase.png)
 	
 	
 	__Nota:__ Lea más acerca de Allure [Aquí](https://docs.qameta.io/allure/#_testng).
 
