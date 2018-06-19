package ethos;

public enum ServerState {

	PUBLIC_PRIMARY(43594), PUBLIC_SECONDARY(5555), PRIVATE(43594);

	private int port;

	ServerState(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

}
