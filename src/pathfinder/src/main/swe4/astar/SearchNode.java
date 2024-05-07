package swe4.astar;

import java.util.ArrayList;
import java.util.List;

// SearchNode ist eine Hilfsklasse, die zur Implementierung des A*-Algorithmus 
// benötigt wird. Damit kann man den Weg von einem SearchNode zum Startknoten
// zurückverfolgen, da dieser mit seinem Vorgängerknoten verkettet ist. Ein
// SearchNode kennt die Kosten vom Startknoten bis zu ihm selbst. SearchNode 
// kann auch eine Schätzung für den Weg zum Zielknoten berechnen.
public class SearchNode implements Comparable<SearchNode> {

  private State state;
  private State targetState;
  private Transition transition;
  private SearchNode predecessor;
  private double costsFromStart;

  // Suchknoten mit dem Zustand state und dem Zielzustand target initialisieren.
  // Es existiert kein Vorgängerknoten.
  public SearchNode(State state, State targetState) {
    this.state = state;
    this.targetState = targetState;
    this.predecessor = null;
    this.transition = null;
    this.costsFromStart = 0;
  }

  // Suchknoten mit dem Zustand state und dem Zielzustand target initialisieren.
  // predecessor ist der Vorgängerknoten, von dessen Zustand man mit transition
  // zum Zustand dieses Knotens kommt.
  public SearchNode(State state, State targetState, SearchNode predecessor, Transition transition) {
    this(state, targetState);
    this.transition = transition;
    this.predecessor = predecessor;
    this.costsFromStart = 0;
    SearchNode n = this.predecessor;
    while (n != null) {
      this.costsFromStart += n.costsFromStart;
      n = n.predecessor;
    }

    if (this.transition != null) {
      this.costsFromStart += transition.costs();
    }
  }

  // Gibt den Zustand dieses Knotens zurück.
  public State getState() {
    return state;
  }

  // Gibt den Vorgängerknoten zurück.
  public SearchNode getPredecessor() {
    return predecessor;
  }

  // Setzt den Vorgängerknoten.
  public SearchNode setPredecessor(SearchNode predecessor) {
    this.predecessor = predecessor;
    return predecessor;
  }

  // Gibt Transition zurück, die Vorgängerknoten in diesen Knoten überführt.
  public Transition getTransition() {
    return transition;
  }

  // Setzt Transition, die Vorgängerknoten in diesen Knoten überführt.
  public void setTransition(Transition transition) {
    this.transition = transition;
  }

  // Gibt Kosten vom Startknoten bis zu diesem Knoten zurück.
  public double costsFromStart() {
    return costsFromStart;
  }

  // Setzt die Kosten vom Startknoten bis zu diesem Knoten.
  public void setCostsFromStart(double costsFromStart) {
    this.costsFromStart = costsFromStart;
  }

  // Gibt geschätzte Kosten von diesem Knoten bis Zum Zielknoten zurück.
  public double estimatedCostsToTarget() {
    return this.state.estimatedCostsToTarget(this.targetState);
    }

  // Gibt Schätzung der Kosten vom Startknoten über diesen Knoten bis zum
  // Zielknoten zurück (= Kosten bis zu diesem Knoten + geschätzte Kosten
  // bis zum Zielknoten).
  public double estimatedTotalCosts() {
    return this.costsFromStart + this.estimatedCostsToTarget();
  }

  // Gibt zurück, ob dieser Knoten und der Knoten other denselben Zustand
  // repräsentieren.
  // Vorsicht: Enthaltenen Zustand per Wert, vergleichen, nicht die Referenzen.
  // Muss konsistent mit compareTo implementiert werden:
  // a.equals(b) <==> a.comparesTo(b) == 0.
  @Override
  public boolean equals(Object obj) {
    SearchNode other = (SearchNode) obj;
    return (this.state.equals(other.getState()) && this.targetState.equals(other.targetState));
    }

  // Notwendig für Verwendung in Hash-basierten Behältern. Muss konsistent
  // mit equals implementiert werden: a.equals(b) => a.hashCode() == b.hashCode.
  @Override
  public int hashCode() {
    return state.hashCode() + targetState.hashCode();
  }

  // Vergleicht zwei Knoten auf Basis der gesch�tzten Gesamtkosten.
  // <0: Kosten dieses Knotens sind kleiner oder gleich als die Kosten von other.
  //  0: Dieser Knoten und other sind gleich.
  // >0: Kosten dieses Knotens sind höher als Kosten von other.
  @Override
  public int compareTo(SearchNode other) {
    return (this.costsFromStart > other.costsFromStart) ? 1 :
            ((this.costsFromStart < other.costsFromStart) ? -1 : 0);
  }

  // Konvertiert die Knotenliste, die bei diesem Knoten ihren Ausgang hat,
  // in eine Liste von Transitionen. Da der Weg in umgekehrter Reihenfolge
  // gespeichert ist, muss die Transitionsliste invertiert werden.
  public List<? extends Transition> getTransitionsFromStart() {
    List<Transition> transitions = new ArrayList<Transition>();
    SearchNode other = this;
    while (other.predecessor != null) {
      transitions.add(other.getTransition());
      other = other.predecessor;
    }
    return transitions.reversed();
  }

  @Override
  public String toString() { throw new UnsupportedOperationException(); }
}
