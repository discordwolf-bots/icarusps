package test;

public class Door {

	private int id;
	private int x;
	private int y;
	private int z;
	private int face;

	public Door(int id, int x, int y, int z, int face) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
		this.face = face;
	}

	public String toJSON() {
		return "{\"id\": " + id + ", \"x\": " + x + ", \"y\": " + y + ", \"h\": " + z + ", \"face\": " + face + "},";
	}

}
