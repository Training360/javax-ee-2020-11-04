# Java EE tranzakciókezelés

## Előkészítés

* DBeaver letöltés és telepítés
* Postgres próba (`postgres`/`password`)
* WildFly 21.0.0
* JDBC driver letöltés
* DataSource telepítése
* Projekt klónozás
* Build/deploy
* Alkalmazás architektúrájának felderítése
* Postman telepítése
* Alkalmazás működésének felderítése

## Feladatok

* Tranzakciókezelés naplójának bekapcsolása futás közben

```shell
/subsystem=logging/logger=com.arjuna:write-attribute(name = "level", value="TRACE") 
/subsystem=logging/logger=com.arjuna:read-resource
```

A `standalone/configuration/standalone.xml` fájlt is írja.

Log a `wildfly-21.0.0.Final/standalone/log/server.log` fájlban.

* Kezdeti implementáció

A `TransferBean` `createTransfer(CreateTransferCommand command)` metódusából a
`// TODO` részen, a `Transfer` entitás mentése után hívd meg az előbb létrehozott
`credit()` metódust a cél számlával
pozitív összeggel, majd a forrás számlával és negatív összeggel!
A kivételt dobd tovább!

Mi történik, ha túl nagy összeget utalnál át, és kivétel történik?

Az Exception módosításával hogyan lehet ezen módosítani? (Leszármazás, annotáció?)

* Metóduson exception meghatározása

Hogy tudod a metóduson lévő annotációval módosítani?

* Exception kezelése

Tedd try-catch-be! Mi történik?

* Programozott megjelölés

Hogy tudod állítani programozottan, hogy mégis rollback legyen?

* Implementálj naplózást!

Először a `TransferBean`-en belül. 
Tedd át másik metódusba, és arra tegyél annotációt! (Lokális metódushívás.)
Majd egy külön beanben.