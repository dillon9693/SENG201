package world;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import place.Room;
import thing.Thing;
import actor.Actor;
import actor.Person;
import actor.Creature;


/**
 * View of the world.  Observes and reports
 * updates in a World.  Linked with World Control (controller)
 * through buttons. Observes the World model and changes when
 * the World changes. Provides graphical user interface (GUI) to
 * user and allows the user to interact with the model (World)
 * 
 * 
 * @author Dillon Kerr
 *
 */
public class WorldView implements Observer{

	private World w;
	private WorldControl wc;
	
	private Actor selectedPerson;
	private Room selectedRoom;
	private Thing selectedThing;
	
	private JFrame jf = new JFrame("World View");
	private JScrollPane jsp;
	private JPanel jp;
	/*
	private DefaultListModel roomListModel = new DefaultListModel();
	private DefaultListModel actorListModel = new DefaultListModel();
	private DefaultListModel stuffModel = new DefaultListModel();
	private DefaultListModel contentsModel = new DefaultListModel();
	
	private JList roomList = new JList(roomListModel);
	private JList actorList = new JList(actorListModel);
	private JList stuff = new JList(stuffModel);
	private JList contents = new JList(contentsModel);
	*/
	
	private JList roomList = new JList();
	private JList actorList = new JList();
	private JList stuff = new JList();
	private JList contents = new JList();
	
	/**
	 * Builds GUI of the World, allowing user to
	 * interact with individual parts of the world
	 */
	private void buildGUI() {
		jp = new JPanel();
		
		jp.setLayout(new GridLayout(4,2));
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
	
	/**
	 * Builds panel of GUI that displays the contents of the selected room.
	 */
	private void buildContents() {
		JPanel jpc = new JPanel();
		TitledBorder b = BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Room Contents");
		jpc.setBorder(b);
		if(selectedRoom != null) {
			b.setTitle(b.getTitle() + "[" + selectedRoom.label());
		}
		
		jpc.add(new JScrollPane(contents));
		jp.add(jpc);
	}
	
	/**
	 * Builds panel of GUI that displays the contents of an actor's inventory.
	 */
	private void buildStuff() {
		JPanel jps = new JPanel();
		jps.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Actor Inventory"));
		
		jps.add(new JScrollPane(stuff));
		jp.add(jps);
		
	}
	
	/**
	 * Builds panel of GUI that displays the actors in the world.
	 */
	private void buildPeople(){
		actorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		actorList.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent lse) {
				if(actorList.getSelectedValue() instanceof Person) {
					selectedPerson = (Person)actorList.getSelectedValue();
					if(selectedPerson != null) {
						stuff.setListData(((Person) selectedPerson).inventory().toArray(new Thing[0]));
						roomList.setSelectedValue(selectedPerson.location(), true);
					}
				}
				else
					selectedPerson = (Creature) actorList.getSelectedValue();
					if(selectedPerson != null) {
						//stuff.setListData(selectedPerson.inventory().toArray(new Thing[0]));
						roomList.setSelectedValue(selectedPerson.location(), true);
				}
			}
		});
		
		JPanel jpp = new JPanel();
		jpp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Actors"));
		

		jpp.add(new JScrollPane(actorList));
		jp.add(jpp);
	}
	
	/**
	 * Builds panel of GUI that displays the rooms in the world.
	 */
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

		jpr.add(new JScrollPane(roomList));
		jp.add(jpr);
		
	}
	
	/**
	 * Builds buttons that allow the user to interact with the world. Buttons
	 * are connected to the controller, which manipulates the state of the world.
	 */
	private void buildControls() {
		/*
		 * Moves selected person to selected room.
		 * Error if room or person is not selected, or person already in selected room.
		 */
		JButton go = new JButton("Go To");
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				Object o = roomList.getSelectedValue();
				WorldControl.goToRoom(o, selectedPerson);
			}
		});
		
		/*
		 * Selected person can take selected thing from selected room
		 * Error if no room, person, or thing selected.
		 */
		JButton take = new JButton("Take");
		take.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Object o = contents.getSelectedValue();
				WorldControl.takeItem(selectedRoom, selectedPerson, o);
			}
		});
		
		/*
		 * Selected person can drop selected thing for inventory
		 * Error if no person or thing selected.
		 */
		JButton drop = new JButton("Drop");
		drop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Object o = stuff.getSelectedValue();
				WorldControl.dropItem(selectedPerson, o);
			}
		});
		
		/*
		 * Clears all selections in GUI Lists.
		 */
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				actorList.clearSelection();
				roomList.clearSelection();
				contents.clearSelection();
				stuff.clearSelection();
			}
		});
		
		/*
		 * Quits the program.
		 */
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				jf.setVisible(false);
				jf.dispose();
				System.exit(0);
			}
		});
		
		/*
		 * Button to add new Person to the world.
		 * Error if no name entered in pop-up form.
		 */
		JButton addPerson = new JButton("Add Person");
		addPerson.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				WorldControl.addPerson(w);
			}
		});
		
		JButton addCreature = new JButton("Add Creature");
		addCreature.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				WorldControl.addCreature(w);
			}
		});
		
		/*
		 * Button to add new Room to the World.
		 * Error if no label entered into pop-up form.
		 */
		JButton addRoom = new JButton("Add Room");
		addRoom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				WorldControl.addRoom(w);
			}
		});
		

		/*
		 * Button to add a new Item to the World
		 * Error if no name entered into pop-up form.
		 */
		JButton addThing = new JButton("Add Item");
		addThing.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				WorldControl.addItem(w);
			}
		});
		
		/*
		 * Button to get the history of the selected person.
		 * Error if selected actor is not a person.
		 */
		JButton getHistory = new JButton("Get History");
		getHistory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
					WorldControl.getPersonHistory(selectedPerson);
			}
		});
		
		JPanel buttons = new JPanel();
		JPanel otherButtons = new JPanel();
		JPanel modButtons = new JPanel();
		
		buttons.add(go);
		buttons.add(take);
		buttons.add(drop);
		
		buttons.add(clear);
		buttons.add(quit);
		
		modButtons.add(addPerson);
		modButtons.add(addCreature);
		modButtons.add(addRoom);
		modButtons.add(addThing);
		modButtons.add(getHistory);
	
		jp.add(modButtons);
		jp.add(buttons);
		jp.add(otherButtons);
		
	}
	
	
	
	
	
	/**
	 * Sets up a demo world and adds an observer to the world.
	 */
	private void createWorld() {
		w = new World();
		w.demoWorld();
		roomList.setListData( w.places().toArray(new Room[0]));
		actorList.setListData(w.actors().toArray(new Actor[0]));
		w.addObserver(this);
	}
	/**
	 * Enables stand-alone use.  
	 */
	public static void main(String[] args) {
		WorldView me = new WorldView();
		me.createWorld();
	}

/**
 * An external controller may attach us to a world
 * which has been set up elsewhere or we may have 
 * constructed our own.
 * @param w World ready to be viewed
 */
	public void setWorld(World w) {
		this.w = w;
	}
	
	/**
	 * Constructor for class.  Build our interface
	 * and attach world supplied.
	 * @param world to view
	 */
	public WorldView(World w) {
		buildGUI();
		setWorld(w);
		w.addObserver(this);
	}
	
	/**
	 * Constructor for class. Make a demo world
	 * to have something to show.
	 */
	public WorldView() {
		buildGUI();
		createWorld();
	}
	
	private void refreshActors() {
		
		actorList.setListData(w.actors().toArray(new Actor[0]));
		actorList.clearSelection();
		stuff.clearSelection();
		stuff.setListData(new Thing[0]);
		
	}
	
	private void refreshRooms() {
		
		roomList.setListData(w.places().toArray(new Room[0]));
		roomList.clearSelection();
		contents.setListData(new Room[1]);
		
	}
	
	/**
	 * Report update occurrence and object provided by observable.
	 * If we did some more work we could provide more detail about
	 * what actually happened...
	 */
	@Override	
	public void update(Observable arg0, Object arg1) {
		System.out.println("Arg0: " + arg0);
		System.out.println("Arg1: " + arg1.toString());
		
		refreshActors();
		refreshRooms();
		
	}

}
