package rmiServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import rmiService.ConversionImpl;

public class ConversionServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            ConversionImpl conversion = new ConversionImpl();
            Naming.rebind("rmi://localhost:1099/Conversion", conversion);
            System.out.println("Serveur de conversion démarré, service publié : Conversion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
