/**
*@author: Martinez Sánchez José Manuel.
*@version: 15/03/2018/_1.0
*/

/*<messageGraph.java> */
package graph.messageGraph;
import java.util.*;
import org.graphstream.algorithm.generator.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class messageGraph {

	/**
	*Objetos para almacenar el numero e identidd de mensajes enviados.
	*/
static LinkedList<Object> messagesList(){
	LinkedList<Object> messagesList = new LinkedList<Object>();
	return messagesList;
}

static LinkedList<Object> sentMessages = new LinkedList<Object>();

static int number_of_Messages=0;

/**
*Metodo simple para generar un ID para cada mensaje.
*/
static int u_ID_Generator(){
	return number_of_Messages + 1;
}

	public static void main(String args[]) {
		/**
		*Generacion de los elementos de la grafica.
		*/
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new SingleGraph("P1");
		graph.addAttribute("ui.stylesheet", "url('Colors.css')");
		Generator gen = new RandomGenerator(5);
		gen.addSink(graph);
		gen.begin();
		for(int i=0; i<100; i++)
			 gen.nextEvents();
		gen.end();
		int number_of_Nodes = 0;



		for(Node n:graph) {
			number_of_Nodes++;
    }

		Random ran = new Random();
		int x = ran.nextInt(number_of_Nodes);
		int y = ran.nextInt(number_of_Nodes);

		for(Node n:graph) {
			n.addAttribute("messagesList", messagesList() );
			if (x == graph.getNode(n.getId()).getIndex() || y == graph.getNode(n.getId()).getIndex() && x !=y) {
				n.addAttribute("ui.class", "sender");
				n.addAttribute("ui.label", "Remitente");
			}

			if (y == graph.getNode(n.getId()).getIndex()) {
				n.addAttribute("ui.class", "reciver");
				n.addAttribute("ui.label", "Destinatario");
			}

			 if( n.hasAttribute("messagesList") ){
				 LinkedList<Object> array = n.getAttribute("messagesList");
			 }

    }

		/**
		*Clase interna para representar los mensajes.
		*/

		class Message {
			public String sender;
			public String reciver;
			public String message;
			public int u_ID;
			LinkedList<Node> route = new LinkedList<Node>();
			LinkedList<String> r = new LinkedList<String>();

			/**
			*Constructor de la clase.
			*/
			public Message(String sender, String reciver,String message ,int u_ID) {
		    this.sender = sender;
				this.reciver = reciver;
				this.message = message;
				this.u_ID = u_ID;
				number_of_Messages++;
				this.route.add(graph.getNode(sender));
				this.route.add(graph.getNode(reciver));
			}
			/**
			*Metodo para generar mensajes.
			*@return Un objeto de tipo mensaje.
			*/
			public Message genMessage(String sender, String reciver,String message ,int u_ID){
				Message m = new Message(sender,reciver,message,u_ID);
				return m;
			}
			/**
			*Devuelve la cadena del mensaje.
			*@return String del mensaje.
			*/
			public  String printMessage(){
				return this.message;
			}
			/**
			*Envia un  mensaje desde un nodo remitente a un nodo destinatario.
			*@return true si el mensaje fue enviado con exito, false en caso contrario.
			*/
			public boolean sendMessage(Node x, Node y){
				boolean achieved = false;
				if (x.hasAttribute("Message")) {
					Message message = x.getAttribute("Message");
					y.setAttribute("Message", message);
					x.removeAttribute("Message");
					message.route.add(x);
					message.route.add(y);
					achieved = true;
				}else{
					achieved = false;
				}
				return achieved;
			}

		}
		Node sender = graph.getNode(x);
		Node pointer = graph.getNode(x);
		Node reciver = graph.getNode(y);
		Message message = new Message(Integer.toString(x),Integer.toString(y),"Hola Mundo",u_ID_Generator());

		int test = 0;
		System.out.println("Nodo Remitente ->"+graph.getNode(x));
		while(!reciver.hasAttribute("Message")) {
			LinkedList<String> routeTemp = new LinkedList<String>();
			message = message.genMessage(sender.getId(),pointer.getId(),"Hola Mundo",u_ID_Generator());
			sender.setAttribute("Message", message);
			Iterator<Node> neighbors = sender.getNeighborNodeIterator();
			String neighborNodes[] = new String[sender.getDegree()];
			int temp = 0;
			while(neighbors.hasNext()) {
				Node u = neighbors.next();
				neighborNodes[temp] = u.getId();
				temp++;
			}
			int direction = ran.nextInt(sender.getDegree());
			pointer = graph.getNode(neighborNodes[direction]);
			message.sendMessage(sender, pointer);
			System.out.println("Posicion actual: Nodo ->" + pointer.getId());
			sender = pointer;
			if (reciver.hasAttribute("Message")) {
				for (Node n : message.route) {
					test++;
				}
			}
			//test++;
		}


		System.out.println("Nodo Destinatario - >"+reciver.getId());
		System.out.println("Numero de mensajes enviados: " + number_of_Messages);
		System.out.println("Computaciones equivalentes: " + test);


		graph.display();
	}
}


/*</messageGraph.java> */
