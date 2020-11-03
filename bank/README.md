# Alkalmazás indítása

Adatbázis: PostgreSQL, legkönnyebben Dockerrel indítható (persze feltelepített is használható)

```
docker run --name bank-postgres -e POSTGRES_PASSWORD=mysecretpassword -d -p 5432:5432 postgres
```

Alkalmazásszerver: WildFly 21.0.0, letölthető a https://wildfly.org/downloads/ címről.
Ki kell csomagolni, majd a `bin/standalone.bat` paranccsal indítható. Ennek telepítését
nem ajánlom Dockerben, mert lokálisan telepítve akár IDE-ből könnyebben vezérelhető.
Java 13 szükséges.

Ez után fel kell venni a DataSource-ot, amin keresztül a PostgreSQL-hez kapcsolódhat.
Ehhez előbb kell a JDBC Driver, mely letölthető a következő címről: https://jdbc.postgresql.org/download.html

A `bin/jboss-cli.bat` állományt elindítva a parancssorba beírandó:

```
connect
deploy "postgresql-42.2.12.jar"
data-source add --name=BankDS --jndi-name=java:/jdbc/BankDS \
  --driver-name=postgresql-42.2.12.jar \
  --connection-url=jdbc:postgresql:postgres \
  --user-name=postgres \
  --password=mysecretpassword
```

A jar fájl megadásánál megadható a teljes elérési útja.

Telepíteni a WildFly-ra az alkalmazást a következő paranccsal lehetséges:
 
`mvn clean package wildfly:deploy`

Postmannel próbáld meghívni a REST végpontokat!
A `http://localhost:8080/bank/api/accounts` címen lehet lekérdezni
a számlákat. A `http://localhost:8080/bank/api/transfers` címen
az átutalásokat. 

Átutalás indítása, `POST` metódussal a `http://localhost:8080/bank/api/transfers`
címre a következő tartalommal:

```json
{
 "src": 1,
 "dest": 2,
 "amount": 50
}
```