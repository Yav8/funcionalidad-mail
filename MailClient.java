/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    // Recoge el n�mero de mensajes enviados.
    private int mensajeEnviado;
    // Recoge el n�mero de mensajes recibidos.
    private int mensajeRecibido;
    // Recoge el mensaje.
    private String mensaje;
    // Recoge el n�mero de caracteres del mensaje m�s largo.
    private int numeroDeCaracteresDelMensajeMasLargo;
    // Recoge el nombre del usuario con el mensaje m�s largo.
    private String usuarioConMensajeMasLargo;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        mensajeEnviado = 0;
        mensajeRecibido = 0;
        mensaje = "";
        numeroDeCaracteresDelMensajeMasLargo = 0;
        usuarioConMensajeMasLargo = "";
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        MailItem devolverMensajeQueVaASerRecibido = server.getNextMailItem(user);
        if(devolverMensajeQueVaASerRecibido != null) {
            // Contabilizamos el n�mero de mensajes recibidos.
            mensajeRecibido += 1;
            // Introducimos el mensaje que recibe un usuario.
            mensaje = devolverMensajeQueVaASerRecibido.getMessage();
            // Obtenci�n del nombre de usuario con el mensaje m�s largo y
            // el mensaje que tiene el mayor n�mero de caracteres a trav�s de este condicional.
            if(numeroDeCaracteresDelMensajeMasLargo < mensaje.length()) {
                numeroDeCaracteresDelMensajeMasLargo = mensaje.length();
                usuarioConMensajeMasLargo = devolverMensajeQueVaASerRecibido.getFrom();
            }
        }
        return devolverMensajeQueVaASerRecibido;
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print();
            // Contabilizamos el n�mero de mensajes recibidos.
            mensajeRecibido += 1;
            // Introducimos el mensaje que recibe un usuario.
            mensaje = item.getMessage();
            // Obtenci�n del nombre de usuario con el mensaje m�s largo y
            // el mensaje que tiene el mayor n�mero de caracteres a trav�s de este condicional.
            if(numeroDeCaracteresDelMensajeMasLargo < mensaje.length()) {
                numeroDeCaracteresDelMensajeMasLargo = mensaje.length();
                usuarioConMensajeMasLargo = item.getFrom();
            }
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String subject, String message)
    {
        MailItem item = new MailItem (user, to, subject, message);
        server.post(item);
        // Contabilizamos el n�mero de mensajes enviados.
        mensajeEnviado += 1;
    }

    /**
     * Muestra por pantalla el n�mero total de mensajes recibidos,
     * el n�mero total de mensajes enviados y la direcci�n de correo
     * de la persona que envi� el email m�s largo con el n�mero de
     * caracteres.
     */
    public void numeroMensajeYCorreoMasLargo() {
        System.out.println("El n�mero de mensajes enviados es " + mensajeEnviado);
        System.out.println("El n�mero de mensajes recibidos es " + mensajeRecibido);
        if(mensajeRecibido > 0) {
            System.out.println("El correo recibido con el mensaje m�s largo es " + usuarioConMensajeMasLargo + " y tiene " + numeroDeCaracteresDelMensajeMasLargo + " caracteres");
        }
    }
}
