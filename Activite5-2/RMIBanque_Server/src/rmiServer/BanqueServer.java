package rmiServer;

import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import rmiService.BanqueImpl;

public class BanqueServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            BanqueImpl banque = new BanqueImpl();
            Context ctx = new InitialContext();
            ctx.rebind("Banque", banque);
            System.out.println("Serveur de banque démarré, service publié via JNDI : Banque");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
