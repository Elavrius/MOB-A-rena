public class Witch extends Champion {
	
	boolean printLog = true;

	Witch() {
		super("Witch");
	}
	
	@Override
	public void receiveDamage(int n) {
		int half = getEffectiveHP() / 2;
		if (getCurrentHP() > half && (getCurrentHP() - n) < half) {
			super.receiveDamage(n);
			setEffectiveMinMagicDamage(getEffectiveMinMagicDamage() + 50);
			setEffectiveMaxMagicDamage(getEffectiveMaxMagicDamage() + 80);
			if (printLog) {
				String message = "Transforming into Mad Witch!" + "\n";
				Utility.showMessage(message);
			}
		}

		else if (getCurrentHP() < half && (getCurrentHP() - n) > half) {
			super.receiveDamage(n);
			applyEquipment();
			if (printLog) {
				String message = "Transforming back!" + "\n";
				Utility.showMessage(message);
			}
		} else {
			super.receiveDamage(n);
		}

	}
}
