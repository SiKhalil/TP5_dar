package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import metier.Compte;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {

    private final Map<Integer, Compte> comptes = new HashMap<Integer, Compte>();

    public BanqueImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized String creerCompte(Compte c) throws RemoteException {
        if (comptes.containsKey(c.getCode())) {
            return "Erreur : le compte " + c.getCode() + " existe déjà";
        }
        comptes.put(c.getCode(), c);
        return "Compte " + c.getCode() + " créé avec succès";
    }

    @Override
    public synchronized String getInfoCompte(int code) throws RemoteException {
        Compte c = comptes.get(code);
        if (c == null) {
            return "Erreur : le compte " + code + " n'existe pas";
        }
        return c.toString();
    }
}
