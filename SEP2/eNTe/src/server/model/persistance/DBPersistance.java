package server.model.persistance;

import java.util.LinkedList;

import model.Post;
import model.User;

public class DBPersistance implements DBAdapter{

	@Override
	public LinkedList<Post> getPosts() {
		Post post = new Post("Title",
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
						+ "Proin mattis at dolor sed aliquam. Nulla facilisi. "
						+ "Maecenas sodales urna quis risus sollicitudin, "
						+ "eget posuere neque aliquet. Nulla lacinia maximus "
						+ "risus non elementum. Donec egestas sit amet lacus vitae"
						+ " efficitur. Nulla ac mauris in turpis condimentum tincidunt "
						+ "sed id metus. Cras vel lectus rutrum, interdum tellus non,"
						+ " venenatis eros. Etiam posuere tempus est non maximus."
						+ " Pellentesque diam tortor, fringilla eget cursus pretium,"
						+ " dictum posuere dolor. Donec non eros commodo," + " ultrices risus sed, fermentum dolor."
						+ " Cras facilisis neque at scelerisque placerat.");
		
		LinkedList<Post> list = new LinkedList<>();
		list.add(post);
		return list;
	}

	@Override
	public LinkedList<User> getUsers() {
		User user = new User("login", "a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8");
		
		LinkedList<User> list = new LinkedList<>();
		list.add(user);
		return list;
	}

	
	
}
