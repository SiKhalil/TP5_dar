# TP5 – Activité 5-2 : Service de banque (RMI et JNDI)

**Étudiant :** Fattoum Mohamed Khalil – LSI3 1.2

## Structure

- `RMIBanque_Server`
  - `metier.Compte` : classe sérialisable (code, solde, date de création)
  - `rmiService.IBanque`, `rmiService.BanqueImpl`
  - `rmiServer.BanqueServer`
  - `src/jndi.properties`
- `RMIBanque_Client`
  - `rmiClient.BanqueClient`
  - `src/jndi.properties`
- `banqueClient.jar` : contient uniquement `IBanque` et `Compte` (sans `BanqueImpl`)

## Configuration JNDI (`jndi.properties`)

```
java.naming.factory.initial=com.sun.jndi.rmi.registry.RegistryContextFactory
java.naming.provider.url=rmi://localhost:1099
```

## Exécution

1. Lancer `rmiServer.BanqueServer` : il démarre le RMIRegistry et enregistre `BanqueImpl` avec `InitialContext.rebind("Banque", ...)`.
2. Lancer `rmiClient.BanqueClient` : il recherche l'objet avec `InitialContext.lookup("Banque")`, crée un compte, tente de le recréer, puis consulte un compte existant et un compte inexistant.

## Résultat

Serveur :

```
Serveur de banque démarré, service publié via JNDI : Banque
```

Client :

```
Compte 1 créé avec succès
Erreur : le compte 1 existe déjà
Compte [code=1, solde=1000.0, dateCreation=Thu Nov 12 15:45:46 UTC 2026]
Erreur : le compte 2 n'existe pas
```

Captures d'écran de chaque étape : _à insérer_

## Synthèse

Le serveur central gère les comptes dans une `Map` et expose `creerCompte` et `getInfoCompte`. L'objet `Compte` est transmis par valeur, d'où son implémentation de `Serializable`. Avec JNDI, `Naming` et `LocateRegistry` ne sont plus utilisés pour le binding et le lookup : `InitialContext` s'appuie sur `jndi.properties` pour atteindre le RMIRegistry, ce qui rend le code indépendant du service d'annuaire utilisé. Le registre est toujours créé côté serveur avec `LocateRegistry.createRegistry(1099)`, car JNDI s'y connecte mais ne le démarre pas.
