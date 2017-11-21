
public class Test
{   
    /**
     * Constructor para objetos de MailClient.
     */
    public Test() {

    }

    /**
     * Comprobaci�n de mensajes enviados y recibidos y 
     * del correo m�s largo y su n�mero de caracteres.
     */
    public void testFuncionalidad05()
    {
        int numeroDeTests = 0;
        int numeroDeTestsOk = 0;
        int numeroDeTestsFail = 0;
        
        MailServer servidor1 = new MailServer();

        String usuario1 = "javier@outlook.com";        
        MailClient cliente1 = new MailClient(servidor1, usuario1);

        String usuario2 = "fernando@outlook.com";
        MailClient cliente2 = new MailClient(servidor1, usuario2);

        String usuario3 = "maria@outlook.com";
        MailClient cliente3 = new MailClient(servidor1, usuario3);
        
        String asunto = "";

        String mensaje1 = "";
        String mensaje2 = "Hola";
        String mensaje3 = "regalo";

        
        System.out.println("Creaci�n de un servidor: Outlook");
        System.out.println("Creaci�n del primer cliente: " + usuario1);
        System.out.println("Creaci�n del segundo cliente: " + usuario2);
        System.out.println("Creaci�n del tercer cliente: " + usuario3);
        
        
        numeroDeTests += 1;
        System.out.println(usuario1 + " env�a un mensaje vac�o a " + usuario2);
        System.out.println(usuario2 + " deber�a recibir 1 mensaje y el usuario con el mensaje m�s largo deber�a ser " + usuario1);
        cliente1.sendMailItem(usuario2, asunto, mensaje1);
        if(cliente2.getNextMailItem() != null) {
            cliente2.numeroMensajeYCorreoMasLargo();
            System.out.println("�Funciona perfectamente!");
            numeroDeTestsOk += 1;
        }
        else {
            System.out.println("�ERROR!");
            numeroDeTestsFail += 1;
        }

        
        numeroDeTests += 1;
        System.out.println(usuario3 + " env�a un mensaje a " + usuario2 + " (contiene: " + mensaje2 + ")");
        cliente3.sendMailItem(usuario2, asunto, mensaje2);
        System.out.println(usuario2 + " deber�a haber recibido 2 mensajes, el usuario con el nombre m�s largo tendr�a que ser " + usuario3);
        if(cliente2.getNextMailItem() != null) {
            cliente2.numeroMensajeYCorreoMasLargo();
            System.out.println("�Funciona perfectamente!");
            numeroDeTestsOk += 1;
        }
        else {
            System.out.println("�ERROR!");
            numeroDeTestsFail += 1;
        }

        
        numeroDeTests += 1;
        System.out.println(usuario2 + " env�a un mensaje a " + usuario1 + " (contiene: " + mensaje3 + ")");
        cliente2.sendMailItem(usuario1, asunto, mensaje3);
        System.out.println(usuario1 + " no deber�a haber recibido ning�n mensaje porque es SPAM");
        if(cliente1.getNextMailItem() == null) {
            cliente1.numeroMensajeYCorreoMasLargo();
            System.out.println("�Funciona perfectamente!");
            numeroDeTestsOk += 1;
        }
        else {
            System.out.println("�ERROR!");
            numeroDeTestsFail += 1;
        }
        
        
        System.out.println("N�mero de tests: " + numeroDeTests);
        System.out.println("N�mero de tests acertados: " + numeroDeTestsOk);
        System.out.println("N�mero de tests fallados: " + numeroDeTestsFail);
    }
}