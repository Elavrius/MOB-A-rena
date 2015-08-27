public class Team {

	private String name;
	private int money;
	private Champion[] champion = new Champion[4];

	Team(String name) {
		this.name = name;
		money = 10000;
	}

	public void setChampion(Champion ch, int index) {
		if (champion[index] != null) {
			String message = "This champion slot is occupied with "
					+ ch.getName();
			Utility.showMessage(message);
		} else {
			champion[index] = ch;
		}
	}

	public Champion getChampion(int nr) {
		return champion[nr];

	}
	
	public void removeChampion(int nr){
		if(champion[nr]!=null){
		for(int i=0; i<4;i++){
			sell(champion[nr], i);
		}}
		champion[nr]=null;
	}

	public void buy(Equipment item, Champion champ, int socket) {
		if(champ.getEquipment(socket)!=null){
			sell(champ,socket);
		}
		if(money - item.getPrice()>0){
		money = money - item.getPrice();
		champ.setEquipment(socket, item);}
		else{
			String message="Not enough money";
			Utility.showMessage(message);			
		}
		String message="Money left "+money;
		Utility.showMessage(message);
	}

	public void sell(Champion champ, int socket) {
		if(champ.getEquipment(socket)!=null){
		money = money + champ.getEquipment(socket).getPrice();
		champ.removeEquipment(socket);
		System.out.println("Sell "+money);}
		String message=(money+"");
		Utility.showMessage(message);
	}

	public boolean checkRepetition(Champion ch) {
		for (Champion champ : champion) {
			if (champ != null) {
				if (ch.getName().equals(champ.getName()))
					return true;
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		String out = "";
		for (Champion champ : champion) {
			if (champ != null)
				out += champ.getName() + ", ";
			else
				out += "empty, ";
		}
		return out;
	}
}
