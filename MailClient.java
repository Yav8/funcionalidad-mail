/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    // Recoge el número de mensajes enviados.
    private int mensajeEnviado;
    // Recoge el número de mensajes recibidos.
    private int mensajeRecibido;
    // Recoge el mensaje.
    private String mensaje;
    // Recoge el número de caracteres del mensaje más largo.
    private int numeroDeCaracteresDelMensajeMasLargo;
    // Recoge el nombre del usuario con el mensaje más largo.
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
            // Contabilizamos el número de mensajes recibidos.
            mensajeRecibido += 1;
            // Introducimos el mensaje que recibe un usuario.
            mensaje = devolverMensajeQueVaASerRecibido.getMessage();
            // Obtención del nombre de usuario con el mensaje más largo y
            // el mensaje que tiene el mayor número de caracteres a través de este condicional.
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
            // Contabilizamos el número de mensajes recibidos.
            mensajeRecibido += 1;
            // Introducimos el mensaje que recibe un usuario.
            mensaje = item.getMessage();
            // Obtención del nombre de usuario con el mensaje más largo y
            // el mensaje que tiene el mayor número de caracteres a través de este condicional.
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
        // Contabilizamos el número de mensajes enviados.
        mensajeEnviado += 1;
    }

    /**
     * Muestra por pantalla el número total de mensajes recibidos,
     * el número total de mensajes enviados y la dirección de correo
     * de la persona que envió el email más largo con el número de
     * caracteres.
     */
    public void numeroMensajeYCorreoMasLargo() {
        System.out.println("El número de mensajes enviados es " + mensajeEnviado);
        System.out.println("El número de mensajes recibidos es " + mensajeRecibido);
        if(mensajeRecibido > 0) {
            System.out.println("El correo recibido con el mensaje más largo es " + usuarioConMensajeMasLargo + " y tiene " + numeroDeCaracteresDelMensajeMasLargo + " caracteres");
        }
    }
}
