import java.io.*;

class Teams {
	private int n, k;

	public void showTeam(int total, int team) {
		n = total;
		k = team;
		showTeams(total, team, "");
	}

	private void showTeams(int total, int team, String members) {
		if (members.length() == k) {
			System.out.println(members);
			return;
		}
		if (team == 0)
			return;
		char a = (char) ((int) 'A' + n - total);
		if (total > team)
			showTeams(total - 1, team, members);
		members += a;
		showTeams(total - 1, team - 1, members);
	}

}

class TeamsApp {
	public static void main(String[] args) throws IOException {
		Teams teams = new Teams();
		System.out.print("Enter number of total members: ");
	    int total = getInt();
	    System.out.print("Enter number of team members : ");
	    int team = getInt();
		teams.showTeam(total, team);
	}
	
	public static String getString() throws IOException {
	    InputStreamReader isr = new InputStreamReader(System.in);
	    BufferedReader br = new BufferedReader(isr);
	    String s = br.readLine();
	    return s;
    }
	
	public static int getInt() throws IOException {
	    String s = getString();
	    return Integer.parseInt(s);
    }
}
