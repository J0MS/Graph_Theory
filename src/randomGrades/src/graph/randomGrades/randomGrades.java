/**
*@author: Martinez Sánchez José Manuel.
*@version: 23/02/2018/_1.0
*/

/*<randomGrades.java> */
package graph.randomGrades;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.generator.*;
import org.graphstream.graph.*;

public class randomGrades {
	public static void main(String args[]) {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Graph graph = new SingleGraph("randomGrades");
		graph.addAttribute("ui.stylesheet", "url('Colors.css')");

		Generator gen = new RandomGenerator();
		gen.addSink(graph);
		gen.begin();
		while (graph.getNodeCount() < 100 && gen.nextEvents());
		gen.end();
		gen.removeSink(graph);
		for(Node n:graph) {
				if (n.getDegree() == 0) {
					n.addAttribute("ui.class", "sinVecinos");
					n.addAttribute("ui.label", n.getId());
				}
				if (n.getDegree() == 1) {
					n.addAttribute("ui.class", "unVecino");
					n.addAttribute("ui.label", n.getId());
				}
				if (n.getDegree() == 2) {
					n.addAttribute("ui.class", "dosVecinos");
					n.addAttribute("ui.label", n.getId());
				}
				if (n.getDegree() == 3) {
					n.addAttribute("ui.class", "tresVecinos");
					n.addAttribute("ui.label", n.getId());
				}
				if (n.getDegree() == 4) {
					n.addAttribute("ui.class", "cuatroVecinos");
					n.addAttribute("ui.label", n.getId());
				}
				if (n.getDegree() > 4) {
					n.addAttribute("ui.class", "cincoOmasVecinos");
					n.addAttribute("ui.label", n.getId());
				}
    }
		graph.display();
	}
}


/*</randomGrades.java> */
