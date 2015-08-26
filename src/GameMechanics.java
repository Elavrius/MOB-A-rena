public class GameMechanics {

	static boolean printLog = true;

	public static void checkStats(Champion ch) {
		// crit chance can't be more than 100%
		if (ch.getEffectiveCritChance() > 100) {
			ch.setEffectiveCritChance(100);
		}
		// maximum speed is set to 300
		if (ch.getEffectiveSpeed() > 300) {
			ch.setEffectiveSpeed(300);

		}
		// always do after the speed check, so the minimum amount should be 10
		calcSpeedPoints(ch);

	}

	// always do after the speed check, so the minimum amount should be 10
	public static void calcSpeedPoints(Champion ch) {
		ch.setSpeedPoints(310 - ch.getEffectiveSpeed());
	}

	public static int physicalDamageCalculator(Champion ch) {
		int min = ch.getEffectiveMinPhysicalDamage();
		int max = ch.getEffectiveMaxPhysicalDamage();
		int out = (int) (Math.random() * (max - min + 1)) + min;
		if (printLog) {
			String message = ch.getName() + "\n" + "min Physical Damage= "
					+ min + "\n" + "max Physical Damage = " + max + "\n"
					+ "output Damage= " + out + "\n";
			Utility.showMessage(message);
		}
		return out;

	}

	public static double naturalArmorCalculator(Champion ch) {
		double divider = ch.getEffectiveNaturalArmor() / 100+1;//+1 even if armor is 0, the minimum divider is 1
		if (printLog) {
			String message = ch.getName() + "\n" + "Natural armor divider= "
					+ divider + "\n";
			Utility.showMessage(message);
		}
		return divider;
	}

	public static void attack(Champion ch1, Champion ch2) {
		int damage = physicalDamageCalculator(ch1);
		double armor = naturalArmorCalculator(ch2);
		int output = (int) (damage / armor);
		if (printLog) {
			String message = ch1.getName() + " does " + output
					+ " total damage to " + ch2.getName() + "\n";
			Utility.showMessage(message);
		}
		ch2.receiveDamage(output);
	}
}
