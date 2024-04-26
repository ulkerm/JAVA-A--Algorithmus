package swe4.astar;

import java.util.List;

// SearchNode ist eine Hilfsklasse, die zur Implementierung des A*-Algorithmus 
// benötigt wird. Damit kann man den Weg von einem SearchNode zum Startknoten
// zurückverfolgen, da dieser mit seinem Vorgängerknoten verkettet ist. Ein
// SearchNode kennt die Kosten vom Startknoten bis zu ihm selbst. SearchNode 
// kann auch eine Schätzung für den Weg zum Zielknoten berechnen.
public class SearchNode implements Comparable<SearchNode> {

  // Suchknoten mit dem Zustand state und dem Zielzustand target initialisieren.
  // Es existiert kein Vorgängerknoten.
  public SearchNode(State state, State targetState) { throw new UnsupportedOperationException(); }

  // Suchknoten mit dem Zustand state und dem Zielzustand target initialisieren.
  // predecessor ist der Vorgängerknoten, von dessen Zustand man mit transition
  // zum Zustand dieses Knotens kommt.
  public SearchNode(State state, State targetState, SearchNode predecessor, Transition transition) { throw new UnsupportedOperationException(); }

  // Gibt den Zustand dieses Knotens zurück.
  public State getState() { throw new UnsupportedOperationException(); }

  // Gibt den Vorgängerknoten zurück.
  public SearchNode getPredecessor() { throw new UnsupportedOperationException(); }

  // Setzt den Vorgängerknoten.
  public SearchNode setPredecessor(SearchNode predecessor) {
    throw new UnsupportedOperationException();
  }

  // Gibt Transition zurück, die Vorgängerknoten in diesen Knoten überführt.
  public Transition getTransition() {
    throw new UnsupportedOperationException();
  }

  // Setzt Transition, die Vorgängerknoten in diesen Knoten überführt.
  public void setTransition(Transition transition) {
    throw new UnsupportedOperationException();
  }

  // Gibt Kosten vom Startknoten bis zu diesem Knoten zurück.
  public double costsFromStart() {
    throw new UnsupportedOperationException();
  }

  // Setzt die Kosten vom Startknoten bis zu diesem Knoten.
  public void setCostsFromStart(double costsFromStart) {
    throw new UnsupportedOperationException();
  }

  // Gibt geschätzte Kosten von diesem Knoten bis Zum Zielknoten zurück.
  public double estimatedCostsToTarget() {
    throw new UnsupportedOperationException();
  }

  // Gibt Schätzung der Kosten vom Startknoten über diesen Knoten bis zum
  // Zielknoten zurück (= Kosten bis zu diesem Knoten + geschätzte Kosten
  // bis zum Zielknoten).
  public double estimatedTotalCosts() {
    throw new UnsupportedOperationException();
  }

  // Gibt zurück, ob dieser Knoten und der Knoten other denselben Zustand
  // repräsentieren.
  // Vorsicht: Enthaltenen Zustand per Wert, vergleichen, nicht die Referenzen.
  // Muss konsistent mit compareTo implementiert werden:
  // a.equals(b) <==> a.comparesTo(b) == 0.
  @Override
  public boolean equals(Object obj) {throw new UnsupportedOperationException();}

  // Notwendig für Verwendung in Hash-basierten Behältern. Muss konsistent
  // mit equals implementiert werden: a.equals(b) => a.hashCode() == b.hashCode.
  @Override
  public int hashCode() {
    throw new UnsupportedOperationException();
  }

  // Vergleicht zwei Knoten auf Basis der gesch�tzten Gesamtkosten.
  // <0: Kosten dieses Knotens sind kleiner oder gleich als die Kosten von other.
  //  0: Dieser Knoten und other sind gleich.
  // >0: Kosten dieses Knotens sind höher als Kosten von other.
  @Override
  public int compareTo(SearchNode other) {throw new UnsupportedOperationException();}

  // Konvertiert die Knotenliste, die bei diesem Knoten ihren Ausgang hat,
  // in eine Liste von Transitionen. Da der Weg in umgekehrter Reihenfolge
  // gespeichert ist, muss die Transitionsliste invertiert werden.
  public List<? extends Transition> getTransitionsFromStart() { throw new UnsupportedOperationException(); }

  @Override
  public String toString() { throw new UnsupportedOperationException(); }
}
