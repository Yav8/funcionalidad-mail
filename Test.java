
public class Test
{   
    /**
     * Constructor para objetos de MailClient.
     */
    public Test() {

    }

    /**
     * Comprobación de mensajes enviados y recibidos y 
     * del correo más largo y su número de caracteres.
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

        
        System.out.println("Creación de un servidor: Outlook");
        System.out.println("Creación del primer cliente: " + usuario1);
        System.out.println("Creación del segundo cliente: " + usuario2);
        System.out.println("Creación del tercer cliente: " + usuario3);
        
        
        numeroDeTests += 1;
        System.out.println(usuario1 + " envía un mensaje vacío a " + usuario2);
        System.out.println(usuario2 + " debería recibir 1 mensaje y el usuario con el mensaje más largo debería ser " + usuario1);
        cliente1.sendMailItem(usuario2, asunto, mensaje1);
        if(cliente2.getNextMailItem() != null) {
            cliente2.numeroMensajeYCorreoMasLargo();
            System.out.println("¡Funciona perfectamente!");
            numeroDeTestsOk += 1;
        }
        else {
            System.out.println("¡ERROR!");
            numeroDeTestsFail += 1;
        }

        
        numeroDeTests += 1;
        System.out.println(usuario3 + " envía un mensaje a " + usuario2 + " (contiene: " + mensaje2 + ")");
        cliente3.sendMailItem(usuario2, asunto, mensaje2);
        System.out.println(usuario2 + " debería haber recibido 2 mensajes, el usuario con el nombre más largo tendría que ser " + usuario3);
        if(cliente2.getNextMailItem() != null) {
            cliente2.numeroMensajeYCorreoMasLargo();
            System.out.println("¡Funciona perfectamente!");
            numeroDeTestsOk += 1;
        }
        else {
            System.out.println("¡ERROR!");
            numeroDeTestsFail += 1;
        }

        
        numeroDeTests += 1;
        System.out.println(usuario2 + " envía un mensaje a " + usuario1 + " (contiene: " + mensaje3 + ")");
        cliente2.sendMailItem(usuario1, asunto, mensaje3);
        System.out.println(usuario1 + " no debería haber recibido ningún mensaje porque es SPAM");
        if(cliente1.getNextMailItem() == null) {
            cliente1.numeroMensajeYCorreoMasLargo();
            System.out.println("¡Funciona perfectamente!");
            numeroDeTestsOk += 1;
        }
        else {
            System.out.println("¡ERROR!");
            numeroDeTestsFail += 1;
        }
        
        
        System.out.println("Número de tests: " + numeroDeTests);
        System.out.println("Número de tests acertados: " + numeroDeTestsOk);
        System.out.println("Número de tests fallados: " + numeroDeTestsFail);
    }
}