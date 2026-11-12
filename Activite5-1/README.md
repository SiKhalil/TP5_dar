# TP5 – Activité 5-1 : Service de conversion de devise (RMI)

**Étudiant :** Fattoum Mohamed Khalil – LSI3 1.2

## Structure

- `RMIConversion_Server` : interface `IConversion`, implémentation `ConversionImpl`, serveur `ConversionServer`
- `RMIConversion_Client` : client `ConversionClient`
- `conversionClient.jar` : contient uniquement `IConversion`, ajouté au classpath du client

## Exécution

1. Lancer `rmiServer.ConversionServer` : il crée le RMIRegistry (port 1099) et publie le service sous le nom `Conversion`.
2. Lancer `rmiClient.ConversionClient` : il recherche l'objet distant et appelle `convertirMontant(500)`.

## Résultat

```
500.0 EUR = 1700.0 TND
```

Capture d'écran du serveur : _à insérer_
Capture d'écran du client : _à insérer_

## Synthèse

Le service distant suit le cycle de vie RMI : définition de l'interface (`IConversion` étend `Remote`, chaque méthode déclare `RemoteException`), implémentation (`ConversionImpl` étend `UnicastRemoteObject`), enregistrement dans le RMIRegistry (`Naming.rebind`) puis invocation par le client (`Naming.lookup`). Le client ne connaît que l'interface, fournie par le JAR, et jamais l'implémentation. Le taux utilisé est de 1 EUR = 3,4 TND.
