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

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        mensajeEnviado = 0;
        mensajeRecibido = 0;
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        MailItem devolverMensajeQueVaASerRecibido = server.getNextMailItem(user);
        if(devolverMensajeQueVaASerRecibido != null) {
            mensajeRecibido += 1;
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
            mensajeRecibido += 1;
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
    }
}
