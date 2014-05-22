package world;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import place.Room;
import thing.Thing;
import world.World;
import actor.Person;

/**
 * Prototype controller for Worlds.  Observes updates in World and
 * supports browsing of World. Supports some simple actions.
 * If a person and place are selected then move allows relocation.
 * A person can take or drop a thing from their current location.
 * 
 *  Needs facilities to allow creation and editing of worlds.
 *  
 * @author Neville
 *
 */
public class WorldControl implements Observer {
	private World w;
	
	private Person selectedPerson;
	private Room selectedRoom;
	private Thing selectedThing;
	
	private JFrame jf = new JFrame("World Control");
	
	private JList roomList = new JList();
	private JList actorList = new JList();
	private JList stuff = new JList();
	private JList contents = new JList();
	
	JScrollPane jsp;
	JPanel jp;
	
	private void buildGui() {
		jp = new JPanel();
		
		jp.setLayout(new GridLayout(3,2));
		jsp = new JScrollPane(jp);
		
		buildRooms();
		buildPeople();
		buildContents();
		buildStuff();
		buildControls();
		
		jf.add(jsp);
		jf.pack();
		jf.setVisible(true);
	}
	
	private void buildContents() {
		JPanel jpc = new JPanel();
		TitledBorder b = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Room Contents");
		jpc.setBorder(b);
		if(selectedRoom != null) {
			b.setTitle(b.getTitle() + "[" + selectedRoom.label());
		}
		jpc.add(contents);
		
		jp.add(jpc);
	}
	
	private void buildStuff() {
		JPanel jps = new JPanel();
		jps.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Actor Inventory"));
		jps.add(stuff);
		jp.add(jps);
		
	}
	
	private void buildPeople(){
		actorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actorList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent lse) {
				selectedPerson = (Person) actorList.getSelectedValue();
				if(selectedPerson != null) {
					stuff.setListData(selectedPerson.inventory().toArray(new Thing[0]));
					roomList.setSelectedValue(selectedPerson.location(), true);
				}
			}
		});
		
		JPanel jpp = new JPanel();
		jpp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Actors"));

		jpp.add(actorList);
		jp.add(jpp);
	}
	
	private void buildRooms() {
		roomList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse) {
				Room r = (Room)roomList.getSelectedValue();
				selectedRoom = r;
				if(r != null) {
					contents.setListData(r.contents().toArray(new Thing[0]));
				}
			}
		});
	
		
		JPanel jpr = new JPanel();
		jpr.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Places"));

		jpr.add(roomList);
		jp.add(jpr);
		
	}
	
	private void buildWorld() {
		w = new World();
		w.demoWorld();
		roomList.setListData( w.places().toArray(new Room[0]));
		actorList.setListData(w.actors().toArray(new Person[0]));
		w.addObserver(this);
		// A separate view
		WorldView wv = new WorldView(w);
	}
	
	private void buildControls() {
		/*
		 * Moves selected person to selected room.
		 */
		JButton go = new JButton("Go To");
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				Object o = roomList.getSelectedValue();
				if (o instanceof Room) {
					if(selectedPerson == null){
						System.err.println("No actor selected");
					} else {
						selectedRoom = (Room)o;
						System.out.println("moving " + selectedPerson.name() + " from " + 
								selectedPerson.location() + " to " + ((Room)roomList.getSelectedValue()));
						selectedPerson.moveTo((Room) roomList.getSelectedValue());
					}
				} else {
					System.err.println("Not a Room---you can't go there");
				}
			}
		});
		
		/*
		 * Selected person can take selected thing from selected room
		 *
		 */
		JButton take = new JButton("Take");
		take.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(selectedRoom == null) {
					System.err.println("Can't take: no Room selected");
					return;
				} else if (selectedPerson == null)  {
					System.err.println("Can't take: no Actor selected");
					return;
				} else if(selectedPerson.location() != selectedRoom) {
					System.err.println("Must be in " + selectedRoom + " to take this");
					return;
				} else {	
					selectedThing = (Thing)contents.getSelectedValue();
					if(selectedThing != null) {
						selectedPerson.take(selectedThing);
					}
				}
			}
		});
		
		/*
		 * Selected person can drop selected thing for inventory
		 */
		JButton drop = new JButton("Drop");
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(selectedRoom == null) {
					System.err.println("Can't drop: no Room selected");
					return;
				} else if (selectedPerson == null)  {
					System.err.println("Can't drop: no Actor selected");
					return;
				} 
				if(selectedPerson.location() != selectedRoom) {
					// will drop it where we are, irrespective of list state
					roomList.setSelectedValue(selectedRoom, true);
				}	
				selectedThing = (Thing)stuff.getSelectedValue();
				if(selectedThing != null) {
					selectedPerson.drop(selectedThing);
				}
			}
		});
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actorList.clearSelection();
				roomList.clearSelection();
				contents.clearSelection();
				stuff.clearSelection();
			}
		});
		
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				jf.setVisible(false);
				jf.dispose();
				System.exit(0);
			}
		});
		
		JButton addPerson = new JButton("Add Person");
		addPerson.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				String name = JOptionPane.showInputDialog("Name:");
				Person p = new Person(name);
				w.addPerson(p);
				//p.moveTo(w);
			}
		});
		
		JButton addRoom = new JButton("Add Room");
		addRoom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				String label = JOptionPane.showInputDialog("Label:");
				Room r = new Room(label);
				w.addRoom(r);
			}
		});
		
		/*
		 * !!!!!INCOMPLETE!!!!
		 */
		JButton addThing = new JButton("Add Item");
		addRoom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				String name = JOptionPane.showInputDialog("Name:");
				Thing t = new Thing(name);
				w.addThing(t);
			}
		});
		
		JPanel buttons = new JPanel();
		JPanel otherButtons = new JPanel();
		JPanel modButtons = new JPanel();
		
		buttons.add(go);
		buttons.add(take);
		buttons.add(drop);
		
		otherButtons.add(clear);
		otherButtons.add(quit);
		
		modButtons.add(addPerson);
		modButtons.add(addRoom);
		modButtons.add(addThing);
		
		jp.add(buttons);
		jp.add(otherButtons);
		jp.add(modButtons);
		
	}
	
	
	
	/**
	 * Update actors list after possible room change. Do we really 
	 * need this if everything else is working properly?
	 */
	private void refreshActors() {
		actorList.setListData(w.actors().toArray(new Person[0]));
		actorList.clearSelection();
		stuff.clearSelection();
		stuff.setListData(new Thing[0]);
	}
	/**
	 * Update rooms list after possible room change.
	 * Do we really need this if everything else is
	 * working properly?
	 */
	private void refreshRooms() {
		roomList.setListData(w.places().toArray(new Room[0]));
		roomList.clearSelection();
		contents.setListData(new Room[1]);
	}

	/**
	 * Assemble a demo world (since we don't have world editing capability yet).
	 * GUI includes separate WorldView viewer.
	 */
	public static void main(String[] args) {
		WorldControl me = new WorldControl();
		me.buildGui();
		me.buildWorld();
		
	}

	/**
	 * Something in the world has changed.  Update GUI content accordingly.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		// Something in the world changed
		System.out.println("Arg0: " + arg0);
		System.out.println("Arg1: " + arg1.toString());
		
		refreshActors();
		refreshRooms();
		
	}

}
